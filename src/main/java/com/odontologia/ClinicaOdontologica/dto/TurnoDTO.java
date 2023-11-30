package com.odontologia.ClinicaOdontologica.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
// Clase POJO
public class TurnoDTO {
    private Long id;
    private Long pacienteId;
    private Long odontologoId;
    private LocalDate fechaTurno;

    public TurnoDTO() {
    }

    public TurnoDTO(Long id, Long pacienteId, Long odontologoId, LocalDate fechaTurno) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.odontologoId = odontologoId;
        this.fechaTurno = fechaTurno;
    }
}
