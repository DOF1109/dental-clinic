package com.odontologia.ClinicaOdontologica.service;

import com.odontologia.ClinicaOdontologica.dto.TurnoDTO;
import com.odontologia.ClinicaOdontologica.entity.Odontologo;
import com.odontologia.ClinicaOdontologica.entity.Paciente;
import com.odontologia.ClinicaOdontologica.entity.Turno;
import com.odontologia.ClinicaOdontologica.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    @Autowired
    private TurnoRepository turnoRepository;

    public TurnoDTO registrarTurno(Turno turno){
        turnoRepository.save(turno);
        return turnoATurnoDTO(turno);
    }

    public void actualizarTurno(TurnoDTO turnoDTO){
        turnoRepository.save(turnoDTOATurno(turnoDTO));
    }

    public void eliminarTurno(Long id){
        turnoRepository.deleteById(id);
    }

    public List<TurnoDTO> listarTurnos(){
        List<TurnoDTO> turnoDTOs = new ArrayList<>();
        List<Turno> turnos = turnoRepository.findAll();
        for (Turno turno : turnos) {
            turnoDTOs.add(turnoATurnoDTO(turno));
        }
        return turnoDTOs;
    }

    public Optional<TurnoDTO> buscarPoId(Long id){
        Optional<Turno> turnoBuscado = turnoRepository.findById(id);
        if (turnoBuscado.isPresent())
            return Optional.of(turnoATurnoDTO(turnoBuscado.get()));
        return Optional.empty();
    }

    private TurnoDTO turnoATurnoDTO(Turno turno){
        TurnoDTO respuesta= new TurnoDTO();
        respuesta.setId(turno.getId());
        respuesta.setPacienteId(turno.getPaciente().getId());
        respuesta.setOdontologoId(turno.getOdontologo().getId());
        respuesta.setFechaTurno(turno.getFechaTurno());
        return  respuesta;
    }

    private Turno turnoDTOATurno(TurnoDTO turnoDTO){
        Turno turno = new Turno();
        Odontologo odontologo = new Odontologo();
        Paciente paciente = new Paciente();
        odontologo.setId(turnoDTO.getOdontologoId());
        paciente.setId(turnoDTO.getPacienteId());
        turno.setId(turnoDTO.getId());
        turno.setFechaTurno(turnoDTO.getFechaTurno());
        turno.setOdontologo(odontologo);
        turno.setPaciente(paciente);
        return turno;
    }
}
