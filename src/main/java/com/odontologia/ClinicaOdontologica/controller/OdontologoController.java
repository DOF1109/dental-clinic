package com.odontologia.ClinicaOdontologica.controller;

import com.odontologia.ClinicaOdontologica.entity.Odontologo;
import com.odontologia.ClinicaOdontologica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
    //relacion de asociacion con el servicio
    private OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping
    public Odontologo registrarOdontologo(@RequestBody Odontologo odontologo){
        return odontologoService.guardarOdontologo(odontologo);
    }

    @GetMapping("/todos")
    public List<Odontologo> buscarTodos(){
        return odontologoService.listarOdontologos();
    }

    @PutMapping
    public String actualizarOdontologo(@RequestBody Odontologo odontologo){
        Odontologo odontologo1= odontologoService.buscarPorID(odontologo.getId());
        if(odontologo1!=null){
            odontologoService.actualizarOdontologo(odontologo);
            return "Odontologo actualizado";
        }
        else{
            return "Odontologo no encontrado";
        }
    }

    @GetMapping("/{id}")
    public Odontologo buscarPorID(@PathVariable Integer id){
        return odontologoService.buscarPorID(id);
    }

}
