package com.odontologia.ClinicaOdontologica.test;

import org.junit.jupiter.api.Test;
import com.odontologia.ClinicaOdontologica.service.OdontologoService;

public class ClienteTest {
    /***** Instancia del servicio con la implementacion de la BBDD a utilizar *****/
    OdontologoService odontologoService = new OdontologoService();

    @Test
    public void guardarYListarOdontologos(){
        /* DADO
        odontologoService.guardarOdontologo(new Odontologo("11111", "Jorgito", "Pereira"));
        odontologoService.guardarOdontologo(new Odontologo("22222"", "Patroclo", "Araya"));
        odontologoService.guardarOdontologo(new Odontologo("33333", "Daniel", "Araya"));
        odontologoService.guardarOdontologo(new Odontologo("44444, "Pancracio", "Messi"));
        odontologoService.guardarOdontologo(new Odontologo("55555", "Jean", "Larez"));

        // CUANDO
        List<Odontologo> odontologoList = odontologoService.listarOdontologos();

        // ENTONCES
        Assertions.assertEquals(5, odontologoList.size());
         */
    }
}
