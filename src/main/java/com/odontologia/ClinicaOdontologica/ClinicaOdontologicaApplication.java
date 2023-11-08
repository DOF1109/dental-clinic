package com.odontologia.ClinicaOdontologica;

import com.odontologia.ClinicaOdontologica.dao.BD;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClinicaOdontologicaApplication {

	public static void main(String[] args) {
		BD.crearTabla();
		SpringApplication.run(ClinicaOdontologicaApplication.class, args);
	}

}
