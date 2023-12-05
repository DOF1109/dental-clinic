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
    private String cedula;
    private Long odontologoId;
    private String matricula;
    private String odontologoNombre;
    private String odontologoApellido;
    private LocalDate fechaTurno;
    public TurnoDTO() {
    }
    public TurnoDTO(Long id, Long pacienteId, String pacienteNombre, String pacienteApellido, String cedula, Long odontologoId, String matricula, String odontologoNombre, String odontologoApellido, LocalDate fechaTurno) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.pacienteNombre = pacienteNombre;
        this.pacienteApellido = pacienteApellido;
        this.cedula = cedula;
        this.odontologoId = odontologoId;
        this.matricula = matricula;
        this.odontologoNombre = odontologoNombre;
        this.odontologoApellido = odontologoApellido;
        this.fechaTurno = fechaTurno;
    }
}


