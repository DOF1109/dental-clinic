package com.odontologia.ClinicaOdontologica.dao;

import com.odontologia.ClinicaOdontologica.model.Domicilio;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

public class DomicilioDAOH2 implements iDao<Domicilio>{
    private static final Logger logger = Logger.getLogger(DomicilioDAOH2.class);
    private static final String SQL_INSERT ="INSERT INTO DOMICILIOS (CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES (?,?,?,?)";
    private static final String SQL_SELECT_BY ="SELECT * FROM DOMICILIOS WHERE ID=?";
    private static final String SQL_DELETE = "DELETE FROM DOMICILIOS WHERE ID=?";
    private static final String SQL_UPDATE = "UPDATE DOMICILIOS SET CALLE=?, NUMERO=?, LOCALIDAD=?, PROVINCIA=? WHERE ID=?";

    @Override
    public  Domicilio guardar(Domicilio domicilio) {
        logger.info("Guardando domicilio del paciente");
        Connection connection = null;
        try{
            connection = BD.getConnection();

            PreparedStatement psInsert= connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, domicilio.getCalle());
            psInsert.setInt(2,domicilio.getNumero());
            psInsert.setString(3, domicilio.getLocalidad());
            psInsert.setString(4, domicilio.getProvincia());

            psInsert.execute();
            ResultSet rs= psInsert.getGeneratedKeys();
            while (rs.next()){
                domicilio.setId(rs.getInt(1));
            }
            logger.info("Domicilio guardado");
        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            try{
                connection.close();
            }catch (SQLException ex){
                logger.error(ex.getMessage());
            }
        }
        return domicilio;
    }

    @Override
    public Domicilio buscar(Integer id) {
        Connection connection = null;
        Domicilio domicilio = null;
        logger.info("Iniciando las operaciones de buscar un domicilio con ID " + id);
        try{
            connection= BD.getConnection();

            PreparedStatement psSelectOne= connection.prepareStatement(SQL_SELECT_BY);
            psSelectOne.setInt(1,id);

            ResultSet rs= psSelectOne.executeQuery();
            while (rs.next()){
                domicilio= new Domicilio(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5));
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            try{
                connection.close();
            }catch (SQLException ex){
                logger.error(ex.getMessage());
            }
        }
        return domicilio;
    }

    @Override
    public void eliminar(Integer id) {
        Connection connection = null;
        logger.info("Inicio las operaciones de eliminacion de domicilio");
        try{
            connection = BD.getConnection();

            PreparedStatement psDelete = connection.prepareStatement(SQL_DELETE);
            psDelete.setInt(1,id);

            psDelete.execute();

            logger.info("Se eliminaron correctamente los datos del domicilio");
        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            try{
                connection.close();
            }catch (SQLException ex){
                logger.error(ex.getMessage());
            }
        }
    }

    @Override
    public void actualizar(Domicilio domicilio) {
        logger.info("Iniciando las operaciones de actualizacion de un domicilio");
        Connection connection= null;
        try{
            connection= BD.getConnection();

            PreparedStatement psUpdate= connection.prepareStatement(SQL_UPDATE);
            psUpdate.setString(1, domicilio.getCalle());
            psUpdate.setInt(2, domicilio.getNumero());
            psUpdate.setString(3, domicilio.getLocalidad());
            psUpdate.setString(4, domicilio.getProvincia());
            psUpdate.setInt(5, domicilio.getId());

            psUpdate.execute();
            logger.info("Domicilio actualizado correctamente");
        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            try{
                connection.close();
            }catch (SQLException ex){
                logger.error(ex.getMessage());
            }
        }
    }

    @Override
    public List<Domicilio> buscarTodos() {
        return null;
    }

    @Override
    public Domicilio buscarPorString(String valor) {
        return null;
    }

}
