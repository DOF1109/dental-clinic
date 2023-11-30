package com.odontologia.ClinicaOdontologica.service;

import com.odontologia.ClinicaOdontologica.dto.TurnoDTO;
import com.odontologia.ClinicaOdontologica.entity.Domicilio;
import com.odontologia.ClinicaOdontologica.entity.Odontologo;
import com.odontologia.ClinicaOdontologica.entity.Paciente;
import com.odontologia.ClinicaOdontologica.entity.Turno;
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
public class TurnoServiceTest {
    @Autowired
    TurnoService turnoService;
    @Autowired
    PacienteService pacienteService;
    @Autowired
    OdontologoService odontologoService;

    @Test
    @Order(1)
    public void registrarTurno(){
        Long id = 1L;

        Paciente paciente = new Paciente("Dibu", "Martinez", "Arg23",
                LocalDate.of(2023, 11, 28),
                new Domicilio("Calle dibu", 23, "CABA", "Bs As"),
                "dibu@martinez.com");
        pacienteService.registrarPaciente(paciente);

        Odontologo odontologo = new Odontologo("MP10", "Lionel", "Messi");
        odontologoService.registrarOdontologo(odontologo);

        Turno turno = new Turno(paciente, odontologo, LocalDate.of(2023, 11, 29));
        turnoService.registrarTurno(turno);
        assertEquals(id, turno.getId());
    }

    @Test
    @Order(2)
    public void buscarTurnoPorId(){
        Long id = 1L;
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarPoId(id);
        assertNotNull(turnoBuscado);
    }

    @Test
    @Order(3)
    public void listarTurnos(){
        List<TurnoDTO> turnos = turnoService.listarTurnos();
        assertEquals(1, turnos.size());
    }

    @Test
    @Order(4)
    public void actualizarTurno(){
        Long id = 1L;
        LocalDate expectedDate = LocalDate.of(2024, 1, 1);

        TurnoDTO turnoActualizar = new TurnoDTO(id, id, id, LocalDate.of(2024, 1, 1));

        turnoService.actualizarTurno(turnoActualizar);
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarPoId(id);
        assertEquals(expectedDate, turnoBuscado.get().getFechaTurno());
    }

    @Test
    @Order(5)
    public void eliminarTurno(){
        Long id = 1L;
        turnoService.eliminarTurno(id);
        Optional<TurnoDTO> turnoEliminado = turnoService.buscarPoId(id);
        assertFalse(turnoEliminado.isPresent());
    }
}
