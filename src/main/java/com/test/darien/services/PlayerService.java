package com.test.darien.services;

import com.test.darien.dto.PlayerDto;
import com.test.darien.dto.TeamDto;
import com.test.darien.entities.Player;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface PlayerService {
    void savePlayer(PlayerDto playerDto);
    void deletePlayer(Long id);
    PlayerDto getPlayer(Long id);
    Set<PlayerDto> getFilteredPlayers (String playerDto);
    PlayerDto addPlayerToTeam(PlayerDto playerDto, Long id);
    PlayerDto editPlayer(PlayerDto playerDto);
    PlayerDto parcialEditPlayer(Long idPlayer, Map<Object,Object> playerFields);
}
