package com.odontologia.ClinicaOdontologica.controller;

import com.odontologia.ClinicaOdontologica.dto.TurnoDTO;
import com.odontologia.ClinicaOdontologica.entity.Odontologo;
import com.odontologia.ClinicaOdontologica.entity.Paciente;
import com.odontologia.ClinicaOdontologica.entity.Turno;
import com.odontologia.ClinicaOdontologica.exception.BadRequestException;
import com.odontologia.ClinicaOdontologica.exception.ResourceNotFoundException;
import com.odontologia.ClinicaOdontologica.service.OdontologoService;
import com.odontologia.ClinicaOdontologica.service.PacienteService;
import com.odontologia.ClinicaOdontologica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turno")
public class TurnoController {
    private TurnoService turnoService;
    private OdontologoService odontologoService;
    private PacienteService pacienteService;

    @Autowired
    public TurnoController(TurnoService turnoService, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoService = turnoService;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<TurnoDTO> registrarTurno(@RequestBody Turno turno) throws BadRequestException{
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(turno.getPaciente().getId());
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(turno.getOdontologo().getId());
        if(pacienteBuscado.isPresent() && odontologoBuscado.isPresent())
            return ResponseEntity.ok(turnoService.registrarTurno(turno));
        throw new BadRequestException("No se encontró paciente y/o odontologo");
    }

    @GetMapping("/todos")
    public ResponseEntity<List<TurnoDTO>> listarTurnos(){
        return ResponseEntity.ok(turnoService.listarTurnos());
    }

    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody TurnoDTO turnoDTO) throws BadRequestException, ResourceNotFoundException {
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarPoId(turnoDTO.getId());
        if (turnoBuscado.isPresent()){
            Optional<Paciente> paciente = pacienteService.buscarPorId(turnoDTO.getPacienteId());
            Optional<Odontologo> odontologo = odontologoService.buscarPorId(turnoDTO.getOdontologoId());
            if (paciente.isPresent() && odontologo.isPresent()){
                turnoService.actualizarTurno(turnoDTO);
                return ResponseEntity.ok("Turno actualizado");
            }
            throw new BadRequestException("No se encontró paciente y/o odontologo");
        }
        throw new ResourceNotFoundException("No se encontró el turno");
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> buscarPorId(@PathVariable Long id) throws ResourceNotFoundException{
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarPoId(id);
        if (turnoBuscado.isPresent())
            return ResponseEntity.ok(turnoBuscado.get());
        throw new ResourceNotFoundException("No se encontró el turno");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException{
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarPoId(id);
        if (turnoBuscado.isPresent()){
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Turno eliminado");
        }
        throw new ResourceNotFoundException("No se encontró el turno");
    }

}
