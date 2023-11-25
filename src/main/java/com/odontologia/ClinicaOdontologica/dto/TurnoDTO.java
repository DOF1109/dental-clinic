package com.odontologia.ClinicaOdontologica.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
// Clase POJO
public class TurnoDTO {
    private Long id;
    private LocalDate fechaTurno;
    private Long pacienteId;
    private Long odontologoId;
}
