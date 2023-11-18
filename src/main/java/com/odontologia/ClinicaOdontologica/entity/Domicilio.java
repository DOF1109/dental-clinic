package com.odontologia.ClinicaOdontologica.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column
    private String calle;

    @Column
    private Integer numero;

    @Column
    private String localidad;

    @Column
    private String provincia;

    /*
    @Override
    public String toString() {
        return "Domicilio{" +
                "id=" + id +
                ", calle='" + calle + '\'' +
                ", numero=" + numero +
                ", localidad='" + localidad + '\'' +
                ", provincia='" + provincia + '\'' +
                '}';
    }
     */
}
