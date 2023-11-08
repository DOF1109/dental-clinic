package com.odontologia.ClinicaOdontologica.dao;

import com.odontologia.ClinicaOdontologica.model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOH2 implements iDao<Odontologo>{
    private static final Logger logger= Logger.getLogger(OdontologoDAOH2.class);
    private static  final String SQL_INSERT ="INSERT INTO ODONTOLOGOS (MATRICULA, NOMBRE, APELLIDO) VALUES(?,?,?)";
    private static final String SQL_SELECT_BY = "SELECT * FROM ODONTOLOGOS WHERE ID=?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM ODONTOLOGOS";
    private static final String SQL_SELECT_BY_MATRICULA ="SELECT * FROM ODONTOLOGOS WHERE MATRICULA=?";
    private static final String SQL_UPDATE ="UPDATE ODONTOLOGOS SET MATRICULA=?, NOMBRE=?, APELLIDO=? WHERE ID =?";
    private static final String SQL_DELETE ="DELETE * FROM ODONTOLOGOS WHERE ID=?";

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Connection connection= null;
        logger.info("Inicio las operaciones de guardado de odontologo");
        try{
            connection = BD.getConnection();

            PreparedStatement psInsert= connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setInt(1, odontologo.getMatricula());
            psInsert.setString(2,odontologo.getNombre());
            psInsert.setString(3, odontologo.getApellido());

            psInsert.execute();
            ResultSet rs= psInsert.getGeneratedKeys();

            while (rs.next()){
                odontologo.setId(rs.getInt(1));
            }
            logger.info("Se guardaron correctamente los datos");
        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            try{
                connection.close();
            }catch (SQLException ex){
                logger.error(ex.getMessage());
            }
        }
        return odontologo;
    }

    @Override
    public Odontologo buscar(Integer id) {
        Connection connection = null;
        Odontologo odontologo = null;
        logger.info("Inicio las operaciones de busqueda de odontologo");
        try{
            // Conectar a la BBDD
            connection = BD.getConnection();

            // Crear una sentencia parametrizada
            PreparedStatement psSearch = connection.prepareStatement(SQL_SELECT_BY);
            psSearch.setInt(1, id);

            // Ejecutar una sentencia SQL con retorno de datos
            ResultSet resultSet = psSearch.executeQuery();

            // Obtengo los resultados
            while (resultSet.next()){
                odontologo = new Odontologo(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );
            }
            logger.info("Se encontraron los datos");
        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            try{
                connection.close();
            }catch (SQLException ex){
                logger.error(ex.getMessage());
            }
        }
        return odontologo;
    }

    @Override
    public void eliminar(Integer id) {
        logger.info("Iniciando las operaciones de eliminacion de un odontologo con ID " + id);
        Connection connection= null;
        try{
            connection= BD.getConnection();

            PreparedStatement psDelete= connection.prepareStatement(SQL_DELETE);
            psDelete.setInt(1,id);

            psDelete.execute();
            logger.info("Odontologo eliminado");
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
    public void actualizar(Odontologo odontologo) {
        logger.info("Iniciando las operaciones de actualizacion un odontologo");
        Connection connection= null;
        try{
            connection= BD.getConnection();

            PreparedStatement psUpdate= connection.prepareStatement(SQL_UPDATE);
            psUpdate.setInt(1, odontologo.getMatricula());
            psUpdate.setString(2, odontologo.getNombre());
            psUpdate.setString(3,odontologo.getApellido());
            psUpdate.setInt(4,odontologo.getId());

            psUpdate.execute();
            logger.info("Datos actualizados correctamente");
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
    public List<Odontologo> buscarTodos() {
        Connection connection = null;
        List<Odontologo> odontologos = new ArrayList<>();
        logger.info("Inicio las operaciones de traer todos los registros");
        try{
            // Conectar a la BBDD
            connection = BD.getConnection();

            // Crear una sentencia
            Statement stmtSearchAll = connection.createStatement();

            // Ejecutar una sentencia SQL con retorno de datos
            ResultSet resultSet = stmtSearchAll.executeQuery(SQL_SELECT_ALL);

            // Obtengo los resultados
            while (resultSet.next()){
                Odontologo odontologo = new Odontologo(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );
                odontologos.add(odontologo);
            }
            logger.info("Se trajeron todos los registros");
        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            try{
                connection.close();
            }catch (SQLException ex){
                logger.error(ex.getMessage());
            }
        }
        return odontologos;
    }

    @Override
    public Odontologo buscarPorString(String valor) {
        return null;
    }

}
