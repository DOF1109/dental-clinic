package com.odontologia.ClinicaOdontologica.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.odontologia.ClinicaOdontologica.entity.Domicilio;
import com.odontologia.ClinicaOdontologica.entity.Odontologo;
import com.odontologia.ClinicaOdontologica.entity.Paciente;
import com.odontologia.ClinicaOdontologica.service.OdontologoService;
import com.odontologia.ClinicaOdontologica.service.PacienteService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc(addFilters = false)
public class IntegracionPacienteTest {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    public void registrarPaciente() throws Exception {
        Paciente pacientePayload = new Paciente("Dibu", "Martinez", "Arg23",
                LocalDate.of(2023, 11, 28),
                new Domicilio("Calle dibu", 23, "CABA", "Bs As"),
                "dibu@martinez.com");

        Paciente pacienteResponse = new Paciente(1L, "Dibu", "Martinez", "Arg23",
                LocalDate.of(2023, 11, 28),
                new Domicilio(1L, "Calle dibu", 23, "CABA", "Bs As"),
                "dibu@martinez.com");

        String pacienteJson = objectMapper.writeValueAsString(pacientePayload);
        String pacienteResp = objectMapper.writeValueAsString(pacienteResponse);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/paciente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pacienteJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Assertions.assertEquals(pacienteResp, result.getResponse().getContentAsString());
    }

    @Test
    @Order(2)
    public void listarPacientes() throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/paciente/todos")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();

        Assertions.assertFalse(result.getResponse().getContentAsString().isEmpty());
    }

    @Test
    @Order(3)
    public void actualizarPaciente() throws Exception {
        Long id = 1L;
        Paciente pacientePayload = new Paciente(id, "Emiliano", "Martinez", "Arg23",
                LocalDate.of(2023, 11, 28),
                new Domicilio(id, "Calle dibu", 23, "CABA", "Bs As"),
                "dibu@martinez.com");

        String pacienteJson = objectMapper.writeValueAsString(pacientePayload);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/paciente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pacienteJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Assertions.assertEquals("Paciente actualizado", result.getResponse().getContentAsString());
    }

    @Test
    @Order(4)
    public void buscarPorID() throws Exception {
        Long id = 1L;
        Paciente pacienteResponse = new Paciente(1L, "Emiliano", "Martinez", "Arg23",
                LocalDate.of(2023, 11, 28),
                new Domicilio(1L, "Calle dibu", 23, "CABA", "Bs As"),
                "dibu@martinez.com");

        String pacienteResp = objectMapper.writeValueAsString(pacienteResponse);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/paciente/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Assertions.assertEquals(pacienteResp, result.getResponse().getContentAsString());
    }

    @Test
    @Order(5)
    public void eliminarPaciente() throws Exception {
        Long id = 1L;

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/paciente/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Assertions.assertEquals("Paciente eliminado", result.getResponse().getContentAsString());
    }
}
