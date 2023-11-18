package com.odontologia.ClinicaOdontologica.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id", referencedColumnName = "id")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "odontologo_id", referencedColumnName = "id")
    private Odontologo odontologo;

    @Column
    private LocalDate fechaTurno;
}
