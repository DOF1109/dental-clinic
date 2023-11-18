package com.odontologia.ClinicaOdontologica.controller;

import com.odontologia.ClinicaOdontologica.entity.Paciente;
import com.odontologia.ClinicaOdontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    private PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping("/{id}")
    public Paciente buscarPacientePorID(@PathVariable Integer id){
        return pacienteService.buscarPorId(id);
    }

    @PostMapping
    public  Paciente registrarPaciente(@RequestBody Paciente paciente){
        return pacienteService.guardarPaciente(paciente);
    }

    @PutMapping
    public String actualizarPaciente(@RequestBody Paciente paciente){
        Paciente pacienteBuscado= pacienteService.buscarPorId(paciente.getId());
        if(pacienteBuscado != null) {
            pacienteService.actualizarPaciente(paciente);
            return "Paciente actualizado";
        }else{
            return "Paciente no encontrado";
        }
    }

    @GetMapping
    public String buscarPacientePorCorreo(Model model, @RequestParam("email") String correo){
        Paciente paciente= pacienteService.buscarPorEmail(correo);
        model.addAttribute("nombre",paciente.getNombre());
        model.addAttribute("apellido",paciente.getApellido());
        return "index";
    }

    @GetMapping("/listarPacientes")
    public List<Paciente> listarPacientes(){
        return pacienteService.listarPacientes();
    }

    @DeleteMapping("/{id}")
    public String eliminarPaciente(@PathVariable Integer id){
        Paciente pacienteBuscado = pacienteService.buscarPorId(id);
        if (pacienteBuscado != null){
            pacienteService.eliminarPaciente(id);
            return "Paciente eliminado";
        }
        return "No se encontró el paciente";
    }

}
