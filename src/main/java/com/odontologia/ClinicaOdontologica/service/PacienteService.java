package com.odontologia.ClinicaOdontologica.service;

import com.odontologia.ClinicaOdontologica.dao.PacienteDAOH2;
import com.odontologia.ClinicaOdontologica.dao.iDao;
import com.odontologia.ClinicaOdontologica.model.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {
    private iDao<Paciente> pacienteiDao;
    @Autowired
    public PacienteService() {
        pacienteiDao= new PacienteDAOH2();
    }
    /* public PacienteService(iDao<Paciente> pacienteiDao) {
        this.pacienteiDao = pacienteiDao;
    }*/

    //se generan manuales los metodos
    public Paciente guardarPaciente(Paciente paciente){
        return pacienteiDao.guardar(paciente);
    }

    public Paciente buscarPorId(Integer id){
        return pacienteiDao.buscar(id);
    }

    public void eliminarPaciente(Integer id){
        pacienteiDao.eliminar(id);
    }

    public void actualizarPaciente(Paciente paciente){
        pacienteiDao.actualizar(paciente);
    }

    public List<Paciente> listarPacientes(){
        return pacienteiDao.buscarTodos();
    }

    public Paciente buscarPorEmail(String correo){
        return pacienteiDao.buscarPorString(correo);
    }
}
