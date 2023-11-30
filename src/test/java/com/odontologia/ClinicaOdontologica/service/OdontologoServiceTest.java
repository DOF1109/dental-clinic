package com.odontologia.ClinicaOdontologica.service;

import com.odontologia.ClinicaOdontologica.entity.Odontologo;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    public void registrarOdontologo(){
        Long id = 1L;
        Odontologo odontologo = new Odontologo("MP10", "Lionel", "Messi");
        odontologoService.registrarOdontologo(odontologo);
        assertEquals(id, odontologo.getId());
    }

    @Test
    @Order(2)
    public void buscarOdontologoPorId(){
        Long id = 1L;
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(id);
        assertNotNull(odontologoBuscado);
    }

    @Test
    @Order(3)
    public void listarOdontologos(){
        List<Odontologo> odontologos = odontologoService.listarOdontologos();
        assertEquals(1, odontologos.size());
    }

    @Test
    @Order(4)
    public void actualizarOdontologo(){
        Long id = 1L;
        Odontologo odontologoActualizar = new Odontologo(id, "MP10", "Pepe", "Messi");
        odontologoService.actualizarOdontologo(odontologoActualizar);
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(id);
        assertEquals("Pepe", odontologoBuscado.get().getNombre());
    }

    @Test
    @Order(5)
    public void eliminarOdontologo(){
        Long id = 1L;
        odontologoService.eliminarOdontologo(id);
        Optional<Odontologo> odnotologoEliminado = odontologoService.buscarPorId(id);
        assertFalse(odnotologoEliminado.isPresent());
    }
}
