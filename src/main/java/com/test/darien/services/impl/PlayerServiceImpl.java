package com.test.darien.services.impl;

import com.test.darien.dto.PlayerDto;
import com.test.darien.dto.TeamDto;
import com.test.darien.entities.Player;
import com.test.darien.entities.Team;
import com.test.darien.exceptions.ResourceNotFoundException;
import com.test.darien.exceptions.ValidationException;
import com.test.darien.repositories.PlayerRepository;
import com.test.darien.repositories.TeamRepository;
import com.test.darien.services.PlayerService;
import lombok.experimental.Helper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.lang.management.PlatformLoggingMXBean;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;
    @Override
    public void savePlayer(PlayerDto playerDto) {
        Player player = new Player();
        player.setTotalGoals(playerDto.getGoals());
        player.setName(playerDto.getName());
        player.setCreatedAt(LocalDateTime.now());
        player.setUpdatedAt(null);
        try {
            playerRepository.save(player);
        }catch (Exception e){
            throw new ValidationException("Jugardor no pudo ser guardado: "+ e.getMessage());
        }
    }

    @Override
    public void deletePlayer(Long id) {
       Player playerJpa = playerRepository.findById(id).orElseThrow(()->{
           throw new ResourceNotFoundException("Jugador no encontrado");
       });
       playerJpa.setDeletedAt(LocalDateTime.now());
       playerRepository.save(playerJpa);
    }

    @Override
    public PlayerDto getPlayer(Long id) {
        Optional<Player> player = playerRepository.findById(id);
        if(player.isPresent()){
            PlayerDto playerDto = new PlayerDto();
            playerDto.setId(player.get().getId());
            playerDto.setName(player.get().getName());
            playerDto.setGoals(player.get().getTotalGoals());
            playerDto.setCreatedAt(player.get().getCreatedAt());
            playerDto.setUpdatedAt(player.get().getUpdatedAt());
            playerDto.setTeamName(player.get().getTeam().getName());
        return playerDto;
        }
        return null;
    }

    @Override
    public Set<PlayerDto> getFilteredPlayers(String name) {
        List<Player> playerList = playerRepository.searchBy(name);
        Set<PlayerDto> playerDtos = new HashSet<>();
        for(Player player : playerList ){
            PlayerDto playerDto = new PlayerDto();
            playerDto.setGoals(player.getTotalGoals());
            playerDto.setName(player.getName());
            playerDto.setCreatedAt(player.getCreatedAt());
            playerDto.setUpdatedAt(player.getUpdatedAt());
            playerDtos.add(playerDto);
        }
        return playerDtos;
    }

    @Override
    public PlayerDto addPlayerToTeam(PlayerDto playerDto, Long idTeam) {
        Team team = teamRepository.findById(idTeam).orElseThrow(()->{
            throw new ResourceNotFoundException("Equipo no encontrado");
        });
        Player player = playerRepository.findById(playerDto.getId()).orElseThrow(()->{
            throw new ResourceNotFoundException("Jugador no encontrado");
        });
        player.setTeam(team);
        playerRepository.save(player);
        playerDto.setTeamName(team.getName());
        return playerDto;
    }

    @Override
    public PlayerDto editPlayer(PlayerDto playerDto) {
        Optional<Player> player = playerRepository.findById(playerDto.getId());
        if(player.isPresent()){
            player.get().setName(playerDto.getName());
            player.get().setCreatedAt(playerDto.getCreatedAt());
            player.get().setUpdatedAt(LocalDateTime.now());
            Player updatedPlayer = playerRepository.save(player.get());
            return new PlayerDto(updatedPlayer.getId(),updatedPlayer.getName(),updatedPlayer.getTotalGoals()
                    ,updatedPlayer.getCreatedAt(),updatedPlayer.getUpdatedAt(),updatedPlayer.getTeam().getName());
        }

        return null;
    }

    @Override
    public PlayerDto parcialEditPlayer(Long idPlayer, Map<Object, Object> playerFields) {
        Optional<Player> player = playerRepository.findById(idPlayer);
        if(player.isPresent()){
            playerFields.forEach((key,value) -> {
                Field field = ReflectionUtils.findField(Player.class, (String) key);
                field.setAccessible(true);
                ReflectionUtils.setField(field,player.get(), value);
            });
            player.get().setUpdatedAt(LocalDateTime.now());
            Player updatedPlayer = playerRepository.save(player.get());
            PlayerDto updatedPlayerDto = new PlayerDto(updatedPlayer.getId(),updatedPlayer.getName()
                    ,updatedPlayer.getTotalGoals(),updatedPlayer.getCreatedAt(),updatedPlayer.getUpdatedAt(),null);
            return updatedPlayerDto;
        }

        return null;
    }

}
