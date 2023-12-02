package com.odontologia.ClinicaOdontologica.integration;

import com.odontologia.ClinicaOdontologica.entity.Domicilio;
import com.odontologia.ClinicaOdontologica.entity.Odontologo;
import com.odontologia.ClinicaOdontologica.entity.Paciente;
import com.odontologia.ClinicaOdontologica.entity.Turno;
import com.odontologia.ClinicaOdontologica.service.OdontologoService;
import com.odontologia.ClinicaOdontologica.service.PacienteService;
import com.odontologia.ClinicaOdontologica.service.TurnoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false) // desactiva los filtros de seguridad
public class IntegracionTurnoTest {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private MockMvc mockMvc;

    public void agergarDatosIniciales(){
        Paciente paciente = new Paciente("Dibu", "Martinez", "Arg23",
                LocalDate.of(2023, 11, 28),
                new Domicilio("Calle dibu", 23, "CABA", "Bs As"),
                "dibu@martinez.com");
        pacienteService.registrarPaciente(paciente);

        Odontologo odontologo = new Odontologo("MP10", "Lionel", "Messi");
        odontologoService.registrarOdontologo(odontologo);

        Turno turno = new Turno(paciente, odontologo, LocalDate.of(2023, 11, 29));
        turnoService.registrarTurno(turno);
    }

    @Test
    public void listarTurnosTest() throws Exception{
        agergarDatosIniciales();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/turno/todos")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // Verifico que la respuesta no esté vacia
        assertFalse(result.getResponse().getContentAsString().isEmpty());
    }
}
