package com.seatrade.dao;

import com.seatrade.dao.GenericDAO;
import com.seatrade.entity.Cargo;
import com.seatrade.entity.Company;
import com.seatrade.entity.CompanyApp;
import com.seatrade.entity.Harbour;
import com.seatrade.util.database.DatabaseUtility;
import org.hibernate.annotations.processing.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CargoDaoImplementation implements GenericDAO<Cargo> {
    private static final String INSERT_CARGOS= "INSERT INTO cargo(cargo_id,fk_harbour_source_id, fk_harbour_destination_id, value,fk_transport_order_id,fk_ship_id)    VALUES    (?,?,?, ?,?,?);";

     private static final String SELECT_CARGO_BY_ID="select * from cargo where cargo_id ='";
    private static final String SELECT_ALL_CARGOS="select * from cargo";
    private static final String DELETE_CARGOS="delete from cargo where cargo_id='";
    private static final String UPDATE_CARGO="update cargo set cargo_id=',harbour_source_id=', harbour_destination_id=', value=' where id =',fk_transport_order_id=', fk_ship_id='";
    @Override
    public Cargo add(Cargo cargo) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;

        try{
            connection = DatabaseUtility.getConnection();
              preparedStatement = connection.prepareStatement(INSERT_CARGOS);
            preparedStatement.setInt(2,cargo.getSourceHarbourId());
            preparedStatement.setInt(3,cargo.getDestinationHarbourId());
            preparedStatement.setDouble(4,cargo.getValue());
            preparedStatement.setInt(5,cargo.getFkTransportId());
            preparedStatement.setInt(6,cargo.getFkShipId());
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
        preparedStatement.setInt(1,cargo.getId());
        preparedStatement.setInt(2,cargo.getSourceHarbourId());
        preparedStatement.setInt(3,cargo.getDestinationHarbourId());
        preparedStatement.setDouble(4,cargo.getValue());
        preparedStatement.setInt(5,cargo.getFkTransportId());
        preparedStatement.setInt(6,cargo.getFkShipId());
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
               int sourceHarbourId= resultSet.getInt(2);
               int destinationHarbourId = resultSet.getInt(3);
               double value = resultSet.getDouble(4);
               int fkTransportOrderId= resultSet.getInt(5);
               int fkShipId= resultSet.getInt(6);
               cargo = new Cargo(Integer.parseInt(id.toString()), sourceHarbourId,destinationHarbourId,value,fkTransportOrderId,fkShipId);
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
              preparedStatement = DatabaseUtility.getConnection().prepareStatement(idString);
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
        ResultSet rs = null;
        try{
            connection = DatabaseUtility.getConnection();
              preparedStatement = DatabaseUtility.getConnection().prepareStatement(SELECT_ALL_CARGOS);
              rs =preparedStatement.executeQuery();

            while (rs.next()){
                int id = rs.getInt(1);
                int sourceHarbourId= rs.getInt(2);
                int destinationHarbourId=rs.getInt(3);
                double value = rs.getDouble(4);
                int fkTransportOrderId= rs.getInt(5);
                int fkShipId= rs.getInt(6);
                Cargo cargo = new Cargo(id,sourceHarbourId,destinationHarbourId,value,fkTransportOrderId,fkShipId);
                cargos.add(cargo);

            }
            return cargos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DatabaseUtility.closeResources(connection,preparedStatement,rs);
        }
    }
}
