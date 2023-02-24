package com.test.darien.controllers;

import com.test.darien.dto.PlayerDto;
import com.test.darien.dto.TeamDto;
import com.test.darien.entities.Player;
import com.test.darien.exceptions.ValidationException;
import com.test.darien.services.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

@RestController
@CrossOrigin("*")
@Tag(name = "Jugador")
@RequestMapping(
        value = "/player",
        produces = MimeTypeUtils.APPLICATION_JSON_VALUE
)
public class PlayerController
{
    @Autowired
    PlayerService playerService;

    @Operation(summary = "Guardar jugador")
    @PostMapping(value = "/")
    public ResponseEntity<String> savePlayer(@RequestBody PlayerDto playerDto){
        playerService.savePlayer(playerDto);
        return new ResponseEntity<String>("Jugador guardado exitosamente", HttpStatus.OK);
    }

    @Operation(summary = "Filtro de jugadores")
    @GetMapping(value = "/")
    public ResponseEntity<Set<PlayerDto>> filteredPlayers(@RequestParam("nombre") String nombre){

        return new ResponseEntity<Set<PlayerDto>>(playerService.getFilteredPlayers(nombre), HttpStatus.OK);
    }

    @Operation(summary = "Obtener jugador por id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<PlayerDto> filteredPlayers(@PathVariable Long id){

        return new ResponseEntity<PlayerDto>(playerService.getPlayer(id), HttpStatus.OK);
    }

    @Operation(summary = "Eliminación logica de jugadores")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable Long id){
        playerService.deletePlayer(id);
        return new ResponseEntity<String>("Jugador eliminado exitosamente", HttpStatus.OK);
    }

    @Operation(summary = "Agregar jugadores a equipos")
    @PutMapping(value = "/{idTeam}")
    public ResponseEntity<PlayerDto> editPlayersTeam(@PathVariable Long idTeam, @RequestBody PlayerDto playerDto){

        return new ResponseEntity<PlayerDto>(playerService.addPlayerToTeam(playerDto, idTeam), HttpStatus.OK);
    }

    @Operation(summary = "Editar jugadores")
    @PutMapping(value = "/")
    public ResponseEntity<PlayerDto> editPlayer( @RequestBody PlayerDto playerDto){

        return new ResponseEntity<PlayerDto>(playerService.editPlayer(playerDto), HttpStatus.OK);
    }

    @Operation(summary = "Editar jugadores parcialmente")
    @PatchMapping(value = "/{idTeam}")
    public ResponseEntity<PlayerDto> parcialEditPlayer(@PathVariable Long idTeam, @RequestBody Map<Object, Object> playerDtofields){

        return new ResponseEntity<PlayerDto>(playerService.parcialEditPlayer(idTeam,playerDtofields), HttpStatus.OK);
    }
}
