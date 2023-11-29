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

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    public void registrarPaciente(){
        Paciente paciente = new Paciente("Dibu", "Martinez", "Arg23", LocalDate.of(2023, 11, 28), new Domicilio("Calle dibu", 23, "CABA", "Bs As"), "dibu@martinez.com");
        pacienteService.registrarPaciente(paciente);
        assertEquals(1L, paciente.getId());
    }

}
