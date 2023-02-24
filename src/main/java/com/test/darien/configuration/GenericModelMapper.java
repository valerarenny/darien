package com.test.darien.configuration;

import com.test.darien.dto.PlayerDto;
import com.test.darien.dto.TeamDto;
import com.test.darien.entities.Player;
import com.test.darien.entities.Team;
import org.modelmapper.ModelMapper;

public class GenericModelMapper {
    private final ModelMapper mapper = new ModelMapper();

    private static GenericModelMapper instance;

    private GenericModelMapper(){

    }

    public static GenericModelMapper singleInstance(){
        if(instance == null){
            new GenericModelMapper();
        }
        return instance;
    }

    public PlayerDto mapToPlayerDeto(Player player){
        return mapper.map(player,PlayerDto.class );
    }

    public TeamDto mapToTeamDto(Team team){
        return mapper.map(team,TeamDto.class );
    }

}
