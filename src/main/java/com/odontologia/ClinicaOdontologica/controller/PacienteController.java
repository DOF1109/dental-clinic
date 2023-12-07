package com.odontologia.ClinicaOdontologica.controller;

import com.odontologia.ClinicaOdontologica.entity.Paciente;
import com.odontologia.ClinicaOdontologica.exception.ResourceNotFoundException;
import com.odontologia.ClinicaOdontologica.service.PacienteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;
    private static final Logger logger = Logger.getLogger(PacienteController.class);

    @PostMapping
    @Transactional
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente) {
        Optional<Paciente> pacienteExistente = pacienteService.buscarPorEmail(paciente.getEmail());
        if (pacienteExistente.isPresent()) {
            // Ya existe un paciente con el mismo email
            logger.error("Ya existe un paciente con el mismo email");
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(pacienteService.registrarPaciente(paciente));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Paciente>> listarPacientes(){
        return ResponseEntity.ok(pacienteService.listarPacientes());
    }

    @PutMapping
    @Transactional
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPorId(paciente.getId());
        if(pacienteBuscado.isPresent()) {
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("Paciente actualizado");
        }else{
            logger.error("No se encontró el paciente");
            throw new ResourceNotFoundException("No se encontró el paciente");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Paciente>> buscarPorID(@PathVariable Long id) throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPorId(id);
        if(pacienteBuscado.isPresent()) {
            return ResponseEntity.ok(pacienteBuscado);
        }else{
            logger.error("No se encontró el paciente");
            throw new ResourceNotFoundException("No se encontró el paciente");
        }
    }

    @GetMapping("/buscar/{email}")
    public ResponseEntity<Optional<Paciente>> buscarPorEmail(@PathVariable String email) throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPorEmail(email);
        if (pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado);
        }
        logger.error("No se encontró el paciente");
        throw new ResourceNotFoundException("No se encontró el paciente");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(id);
        if (pacienteBuscado.isPresent()){
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok("Paciente eliminado");
        }
        logger.error("No se encontró el paciente");
        throw new ResourceNotFoundException("No se encontró el paciente");
    }

}
