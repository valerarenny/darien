package com.test.darien.controllers;

import com.test.darien.dto.PlayerDto;
import com.test.darien.dto.TeamDto;
import com.test.darien.entities.Team;
import com.test.darien.exceptions.ValidationException;
import com.test.darien.services.PlayerService;
import com.test.darien.services.TeamService;
import com.test.darien.specifications.TeamSpecification;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

@RestController
@CrossOrigin("*")
@Tag(name = "Equipo")
@RequestMapping(
        value = "/team",
        produces = MimeTypeUtils.APPLICATION_JSON_VALUE
)
@ControllerAdvice()
public class TeamController {
    @Autowired
    TeamService teamService;

    @Operation(summary = "Guardar equipo")
    @PostMapping(value = "/")
    public ResponseEntity<String> guardarActas(@RequestBody TeamDto teamDto) throws ValidationException {
        teamService.saveTeam(teamDto);
        return new ResponseEntity<String>("Equipo guardado exitosamente", HttpStatus.OK);
    }

    @Operation(summary = "Filtro de Equipos")
    @GetMapping(value = "/")
    public ResponseEntity<?> filteredTeams(TeamSpecification spec,
    @RequestParam(value = "nombre", required = false) String nombre,
        @RequestParam(value = "ciudad", required = false) String ciudad,  @Parameter(hidden = true)
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){

        return new ResponseEntity<Page<TeamDto>>(teamService.getSpecificationTeams(nombre,ciudad,  pageable, spec), HttpStatus.OK);
    }

    @Operation(summary = "Eliminación logica de equipos")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable Long id){
        teamService.deleteTeam(id);
        return new ResponseEntity<String>("Equipo eliminado exitosamente", HttpStatus.OK);
    }


    @Operation(summary = "Editar equipos")
    @PutMapping(value = "/")
    public ResponseEntity<TeamDto> editPlayer( @RequestBody TeamDto teamDto){

        return new ResponseEntity<TeamDto>(teamService.editTeam(teamDto), HttpStatus.OK);
    }

    @Operation(summary = "Editar jugadores parcialmente")
    @PatchMapping(value = "/{idTeam}")
    public ResponseEntity<TeamDto> parcialEditPlayer(@PathVariable Long idTeam, @RequestBody Map<Object, Object> teamDtofields){

        return new ResponseEntity<TeamDto>(teamService.parcialEditTeam(idTeam,teamDtofields), HttpStatus.OK);
    }

}
