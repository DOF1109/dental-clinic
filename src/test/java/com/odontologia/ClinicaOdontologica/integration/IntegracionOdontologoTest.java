package com.odontologia.ClinicaOdontologica.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.odontologia.ClinicaOdontologica.entity.Odontologo;
import com.odontologia.ClinicaOdontologica.service.OdontologoService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc(addFilters = false)
public class IntegracionOdontologoTest {
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    public void registrarOdontologo() throws Exception {
        Odontologo odontologoPayload = new Odontologo("MP10", "Lionel", "Messi");
        Odontologo odontologoResponse = new Odontologo(1L, "MP10", "Lionel", "Messi");

        String odontologoJson = objectMapper.writeValueAsString(odontologoPayload); // deserealiza el objeto java
        String odontologoResp = objectMapper.writeValueAsString(odontologoResponse);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/odontologo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(odontologoJson))
                .andExpect(MockMvcResultMatchers.status().isOk()) // Verificar que la respuesta sea HTTP 200 Ok
                .andReturn();

        Assertions.assertEquals(odontologoResp, result.getResponse().getContentAsString());
    }

    @Test
    @Order(2)
    public void listarTodos() throws Exception{
        Odontologo odontologo = new Odontologo("MP10", "Lionel", "Messi");
        odontologoService.registrarOdontologo(odontologo);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/odontologo/todos")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();

        Assertions.assertFalse(result.getResponse().getContentAsString().isEmpty());
    }

    @Test
    @Order(3)
    public void actualizarOdontologo() throws Exception {
        Long id = 1L;
        Odontologo odontologoPayload = new Odontologo(id, "MP10", "Pepe", "Messi");

        String odontologoJson = objectMapper.writeValueAsString(odontologoPayload);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/odontologo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(odontologoJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Assertions.assertEquals("Odontologo actualizado", result.getResponse().getContentAsString());
    }

    @Test
    @Order(4)
    public void buscarPorID() throws Exception {
        Long id = 1L;
        Odontologo odontologoResponse = new Odontologo(id, "MP10", "Pepe", "Messi");

        String odontologoResp = objectMapper.writeValueAsString(odontologoResponse);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/odontologo/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Assertions.assertEquals(odontologoResp, result.getResponse().getContentAsString());
    }

    @Test
    @Order(5)
    public void eliminarOdontologo() throws Exception {
        Long id = 1L;

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/odontologo/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Assertions.assertEquals("Odontologo eliminado", result.getResponse().getContentAsString());
    }
}
