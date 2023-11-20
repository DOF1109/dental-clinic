package com.odontologia.ClinicaOdontologica.service;

import com.odontologia.ClinicaOdontologica.entity.Turno;
import com.odontologia.ClinicaOdontologica.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    @Autowired
    private TurnoRepository turnoRepository;

    public Turno registrarTurno(Turno turno){
        return turnoRepository.save(turno);
    }

    public void actualizarTurno(Turno turno){
        turnoRepository.save(turno);
    }

    public void eliminarTurno(Long id){
        turnoRepository.deleteById(id);
    }

    public List<Turno> listarTurnos(){
        return turnoRepository.findAll();
    }

    public Optional<Turno> buscarPoId(Long id){
        return turnoRepository.findById(id);
    }

}
