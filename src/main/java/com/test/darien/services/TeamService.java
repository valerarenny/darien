package com.test.darien.services;

import com.test.darien.dto.TeamDto;
import com.test.darien.entities.Team;
import com.test.darien.specifications.TeamSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface TeamService {
    void saveTeam(TeamDto teamDto);
    void deleteTeam(Long idTeam);
    TeamDto editTeam (TeamDto teamDto);
    TeamDto parcialEditTeam(Long idTeam, Map<Object, Object> teamFields);
    Page<TeamDto> getSpecificationTeams(String name, String ciudad, Pageable pageable, TeamSpecification spec);


}
