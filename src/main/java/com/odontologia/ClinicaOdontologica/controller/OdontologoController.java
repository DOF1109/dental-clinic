package com.odontologia.ClinicaOdontologica.controller;

import com.odontologia.ClinicaOdontologica.entity.Odontologo;
import com.odontologia.ClinicaOdontologica.exception.ResourceNotFoundException;
import com.odontologia.ClinicaOdontologica.service.OdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
    @Autowired
    private OdontologoService odontologoService;
    private static final Logger logger = Logger.getLogger(OdontologoController.class);

    @PostMapping
    @Transactional
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.registrarOdontologo(odontologo));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Odontologo>> listarTodos(){
        return ResponseEntity.ok(odontologoService.listarOdontologos());
    }

    @PutMapping
    @Transactional
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(odontologo.getId());
        if(odontologoBuscado.isPresent()){
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("Odontologo actualizado");
        }
        logger.error("No se encontró el odontologo");
        throw new ResourceNotFoundException("No se encontró el odontologo");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Odontologo>> buscarPorID(@PathVariable Long id) throws ResourceNotFoundException{
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(id);
        if(odontologoBuscado.isPresent()){
            return ResponseEntity.ok(odontologoBuscado);
        }
        logger.error("No se encontró el odontologo");
        throw new ResourceNotFoundException("No se encontró el odontologo");
    }

    @GetMapping("/buscar/{matricula}")
    public ResponseEntity<Optional<Odontologo>> buscarPorMatricula(@PathVariable String matricula) throws ResourceNotFoundException{
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorMatricula(matricula);
        if(odontologoBuscado.isPresent()){
            return ResponseEntity.ok(odontologoBuscado);
        }
        logger.error("No se encontró el odontologo");
        throw new ResourceNotFoundException("No se encontró el odontologo");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException{
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(id);
        if(odontologoBuscado.isPresent()){
            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.ok("Odontologo eliminado");
        }
        logger.error("No se encontró el odontologo");
        throw new ResourceNotFoundException("No se encontró el odontologo");
    }

}
