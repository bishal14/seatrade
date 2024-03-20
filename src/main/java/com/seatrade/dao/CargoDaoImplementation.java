package com.seatrade.dao;

import com.seatrade.entity.Cargo;
import com.seatrade.entity.Ship;
import com.seatrade.util.database.DatabaseUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CargoDaoImplementation implements GenericDAO<Cargo> {
    private static final String INSERT_CARGOS= "INSERT INTO cargo(sourceHarbour,destinationHarbour,value,fkSourceHarbourId,cargoId)    VALUES    (?,?,?,?,?);";

     private static final String SELECT_CARGO_BY_ID="select * from cargo where cargoId=?";
    private static final String SELECT_ALL_CARGOS="select * from cargo";
    private static final String DELETE_CARGOS="delete from cargo where cargoId=?";
    private static final String UPDATE_CARGO="update cargo set sourceHarbour=?,destinationHarbour=?, value=?,fkSourceHarbourId=? where cargoId=? ";
    private static  final String SELECT_CARGO_BY_FKID_HARBOUR= "SELECT sourceHarbour,destinationHarbour,value,fkSourceHarbourId,cargoId FROM Cargo WHERE fkSourceHarbourId = ?";

    @Override
    public Cargo add(Cargo cargo) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;

        try{
            connection = DatabaseUtility.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_CARGOS);
            preparedStatement.setString(1,cargo.getSourceHarbour());
            preparedStatement.setString(2,cargo.getDestinationHarbour());
            preparedStatement.setDouble(3,cargo.getValue());
            preparedStatement.setInt(4,cargo.getFkSourceHarbourId());
            preparedStatement.setInt(5,cargo.getCargoId());





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
        preparedStatement = connection.prepareStatement(UPDATE_CARGO); // This line was missing

        preparedStatement.setString(1, cargo.getSourceHarbour());
        preparedStatement.setString(2, cargo.getDestinationHarbour());
        preparedStatement.setDouble(3, cargo.getValue());
        preparedStatement.setInt(4, cargo.getFkSourceHarbourId());
        preparedStatement.setInt(5, cargo.getCargoId());
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

         PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try{
            connection = DatabaseUtility.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_CARGO_BY_ID);
            preparedStatement.setInt(1, Integer.parseInt(id.toString()));
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int cargoId = resultSet.getInt("cargoId");
                String sourceHarbour = resultSet.getString("sourceHarbour");
                String destinationHarbour = resultSet.getString("destinationHarbour");
                int sourceHarbourfkId = resultSet.getInt("sourceHarbourfkId");
                double value = resultSet.getDouble("value");
                cargo = new Cargo( sourceHarbour, destinationHarbour , value,sourceHarbourfkId,cargoId);
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
                String sourceHarbour= resultSet.getString(1);
                String destinationHarbour = resultSet.getString(2);
                double value = resultSet.getDouble(3);
                    int fkHarbourId= resultSet.getInt(4);
                int cargoId=resultSet.getInt(5);

                //  int fkShipId= resultSet.getInt(6);
                cargo = new Cargo( sourceHarbour,destinationHarbour,value,fkHarbourId,cargoId );
               cargos.add(cargo);

            }
            return cargos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DatabaseUtility.closeResources(connection,preparedStatement,resultSet);
        }
    }


    public List<Cargo> listByHarnbourNameS(int id)throws   SQLException  {
        List<Cargo> cargos = new ArrayList<>();
        Cargo cargo = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
         try {
            connection = DatabaseUtility.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_CARGO_BY_FKID_HARBOUR);
            preparedStatement.setInt(1, id);
             resultSet = preparedStatement.executeQuery();
             System.out.println("id of harbour  is "+id);


            while (resultSet.next()){
                 String sourceHarbour= resultSet.getString(1);
                String destinationHarbour = resultSet.getString(2);
                double value = resultSet.getDouble(3);
                int fkHarbourId= resultSet.getInt(4);
                int cargoId=resultSet.getInt(5);

                //  int fkShipId= resultSet.getInt(6);
                cargo = new Cargo( sourceHarbour,destinationHarbour,value,fkHarbourId,cargoId );
                System.out.println("cargo id is "+ cargoId);

                cargos.add(cargo);

            }
            return cargos;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }
}
