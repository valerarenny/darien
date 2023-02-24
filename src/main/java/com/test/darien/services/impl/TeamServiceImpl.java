package com.test.darien.services.impl;

import com.test.darien.configuration.GenericModelMapper;
import com.test.darien.dto.PlayerDto;
import com.test.darien.dto.TeamDto;
import com.test.darien.entities.Player;
import com.test.darien.entities.Team;
import com.test.darien.exceptions.ResourceNotFoundException;
import com.test.darien.repositories.TeamRepository;
import com.test.darien.services.TeamService;
import com.test.darien.specifications.TeamSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamRepository teamRepository;

    static GenericModelMapper modelMapper = GenericModelMapper.singleInstance();

    @Override
    public void saveTeam(TeamDto teamDto) {
        Team team = new Team();
        team.setCity(teamDto.getCity());
        team.setName(teamDto.getName());
        team.setCreatedAt(LocalDateTime.now());
        team.setUpdatedAt(null);
        teamRepository.save(team);
    }

    @Override
    public void deleteTeam(Long idTeam) {
        Team teamJpa = teamRepository.findById(idTeam).orElseThrow(()->{
            throw new ResourceNotFoundException("Jugador no encontrado");
        });
        teamJpa.setDeletedAt(LocalDateTime.now());
        teamRepository.save(teamJpa);
    }

    @Override
    public TeamDto editTeam(TeamDto teamDto) {
        Optional<Team> team = teamRepository.findById(teamDto.getId());
        if(team.isPresent()){
            team.get().setCity(teamDto.getCity());
            team.get().setName(teamDto.getName());
            team.get().setUpdatedAt(LocalDateTime.now());
            Team updatedTeam = teamRepository.save(team.get());
            TeamDto teamDtoUpdated = modelMapper.mapToTeamDto(updatedTeam);
            return teamDtoUpdated;
        }
        return null;
    }

    @Override
    public TeamDto parcialEditTeam(Long idTeam, Map<Object, Object> teamFields) {
        Optional<Team> team = teamRepository.findById(idTeam);
        if(team.isPresent()){
            teamFields.forEach((key,value) -> {
                Field field = ReflectionUtils.findField(Player.class, (String) key);
                field.setAccessible(true);
                ReflectionUtils.setField(field,team.get(), value);
            });
            team.get().setUpdatedAt(LocalDateTime.now());
            Team updatedTeam = teamRepository.save(team.get());
            List<PlayerDto> playerDtoList = new ArrayList<>();
            for (Player player : updatedTeam.getPlayerList()){
                PlayerDto playerDto = modelMapper.mapToPlayerDeto(player);
                playerDtoList.add(playerDto);
            }
            TeamDto updatedTeamDto = new TeamDto(updatedTeam.getId(), updatedTeam.getName(), updatedTeam.getCity(),
                    updatedTeam.getCreatedAt(),updatedTeam.getUpdatedAt(),null,null,playerDtoList);
            return updatedTeamDto;
        }

        return null;
    }

    @Override
    public Page<TeamDto> getSpecificationTeams(String name, String ciudad,  Pageable pageable, TeamSpecification spec) {
        Page<Team> derechoUnicoExtraccionsList = teamRepository.findAll(spec, pageable);
        return null;
    }
}
