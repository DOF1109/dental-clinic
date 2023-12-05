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
    private String pacienteNombre;
    private String pacienteApellido;
    private String pacienteCedula;
    private Long odontologoId;
    private String odontologoMatricula;
    private String odontologoNombre;
    private String odontologoApellido;
    private LocalDate fechaTurno;
    public TurnoDTO() {
    }
    public TurnoDTO(Long id, Long pacienteId, String pacienteNombre, String pacienteApellido, String pacienteCedula, Long odontologoId, String odontologoMatricula, String odontologoNombre, String odontologoApellido, LocalDate fechaTurno) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.pacienteNombre = pacienteNombre;
        this.pacienteApellido = pacienteApellido;
        this.pacienteCedula = pacienteCedula;
        this.odontologoId = odontologoId;
        this.odontologoMatricula = odontologoMatricula;
        this.odontologoNombre = odontologoNombre;
        this.odontologoApellido = odontologoApellido;
        this.fechaTurno = fechaTurno;
    }
}


