package com.odontologia.ClinicaOdontologica.service;

import com.odontologia.ClinicaOdontologica.entity.Domicilio;
import com.odontologia.ClinicaOdontologica.entity.Paciente;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    public void registrarPaciente(){
        Long id = 1L;
        Paciente paciente = new Paciente("Dibu", "Martinez", "Arg23",
                LocalDate.of(2023, 11, 28),
                new Domicilio("Calle dibu", 23, "CABA", "Bs As"),
                "dibu@martinez.com");
        pacienteService.registrarPaciente(paciente);
        assertEquals(id, paciente.getId());
    }

    @Test
    @Order(2)
    public void buscarPacientePorId(){
        Long id = 1L;
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(id);
        assertNotNull(pacienteBuscado);
    }

    @Test
    @Order(3)
    public void listarPacientes(){
        List<Paciente> pacientes = pacienteService.listarPacientes();
        assertEquals(1, pacientes.size());
    }

    @Test
    @Order(4)
    public void actualizarPaciente(){
        Long id = 1L;
        Paciente pacienteActualizar = new Paciente(id, "Emiliano", "Martinez", "Arg23",
                LocalDate.of(2023, 11, 28),
                new Domicilio("Calle dibu", 23, "CABA", "Bs As"),
                "dibu@martinez.com");
        pacienteService.actualizarPaciente(pacienteActualizar);
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(id);
        assertEquals("Emiliano", pacienteBuscado.get().getNombre());
    }

    @Test
    @Order(5)
    public void eliminarPaciente(){
        Long id = 1L;
        pacienteService.eliminarPaciente(id);
        Optional<Paciente> pacienteEliminado = pacienteService.buscarPorId(id);
        assertFalse(pacienteEliminado.isPresent());
    }

}
