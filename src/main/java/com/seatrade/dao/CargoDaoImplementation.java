package com.seatrade.dao;

import com.seatrade.entity.Cargo;
import com.seatrade.util.database.DatabaseUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CargoDaoImplementation implements GenericDAO<Cargo> {
    private static final String INSERT_CARGOS= "INSERT INTO cargo(sourceHarbour,destinationHarbour,value)    VALUES    (?,?,?);";

     private static final String SELECT_CARGO_BY_ID="select * from cargo where cargoId='";
    private static final String SELECT_ALL_CARGOS="select * from cargo";
    private static final String DELETE_CARGOS="delete from cargo where cargoId='";
    private static final String UPDATE_CARGO="update cargo set sourceHarbour=',destinationHarbour=', value=', where cargoId=' ";
    @Override
    public Cargo add(Cargo cargo) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;

        try{
            connection = DatabaseUtility.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_CARGOS);
            preparedStatement.setString(2,cargo.getSourceHarbour());
            preparedStatement.setString(3,cargo.getDestinationHarbour());
            preparedStatement.setDouble(4,cargo.getValue());

            preparedStatement.executeUpdate();
            return cargo;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
             DatabaseUtility.closeResources(connection, preparedStatement,resultSet);

    }
    }

    @Override
    public Cargo update(Cargo cargo) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
    try{
        connection = DatabaseUtility.getConnection();

        preparedStatement = connection.prepareStatement(UPDATE_CARGO);
        preparedStatement.setInt(1,cargo.getCargoId());
        preparedStatement.setString(2,cargo.getSourceHarbour());
        preparedStatement.setString(3,cargo.getDestinationHarbour());
        preparedStatement.setDouble(4,cargo.getValue());

        preparedStatement.executeUpdate();
        return cargo;
    }catch (SQLException e) {
        throw new RuntimeException(e);
    } finally {
        DatabaseUtility.closeResources(connection,preparedStatement,resultSet);
    }


    }


    @Override
    public Cargo get(Object id) {
         Cargo cargo = null;

        String idString = SELECT_CARGO_BY_ID.concat(id.toString());
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try{
            connection = DatabaseUtility.getConnection();

              preparedStatement= connection.prepareStatement(idString);
            preparedStatement.setInt(1,Integer.parseInt(id.toString()));
              resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
               String sourceHarbour= resultSet.getString(2);
               String destinationHarbour = resultSet.getString(3);
               double value = resultSet.getDouble(4);
           //    int fkTransportOrderId= resultSet.getInt(5);
             //  int fkShipId= resultSet.getInt(6);
               cargo = new Cargo( sourceHarbour,destinationHarbour,value );
             }
            return cargo;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DatabaseUtility.closeResources(connection,preparedStatement,resultSet);
        }
    }


    @Override
    public void delete(Object cargoId) {
        String idString = DELETE_CARGOS.concat(cargoId.toString());

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try{
            connection = DatabaseUtility.getConnection();
            preparedStatement = connection.prepareStatement(idString);
            preparedStatement.setInt(1,Integer.parseInt(cargoId.toString()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DatabaseUtility.closeResources(connection,preparedStatement,resultSet);
        }

    }


    @Override
    public List<Cargo> listAll() {

        List<Cargo> cargos = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        Cargo cargo= null;
        try{
            connection = DatabaseUtility.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_CARGOS);
            resultSet =preparedStatement.executeQuery();

            while (resultSet.next()){
                String sourceHarbour= resultSet.getString(2);
                String destinationHarbour = resultSet.getString(3);
                double value = resultSet.getDouble(4);
                //    int fkTransportOrderId= resultSet.getInt(5);
                //  int fkShipId= resultSet.getInt(6);
                cargo = new Cargo( sourceHarbour,destinationHarbour,value );
               cargos.add(cargo);

            }
            return cargos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DatabaseUtility.closeResources(connection,preparedStatement,resultSet);
        }
    }
}
