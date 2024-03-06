package com.seatrade.dao;

import com.seatrade.entity.Ship;
import com.seatrade.util.database.DatabaseUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShipDaoImplementation implements GenericDAO<Ship> {

    private static final String INSERT_SHIP="INSERT INTO ship (name,xPosition,yPosition,direction,cost,fkCompanyId) values (?,?,?,?,?,?)";
    private static final String SELECT_SHIP_BY_ID="select name,xPosition,yPosition,direction,cost,fkCompanyId from ship where  shipId=?";
    private static final String SELECT_ALL_SHIP  ="select * from ship";
    private static final String DELETE_SHIP ="delete from ship where shipId=?";
    private static final String UPDATE_SHIP="update ship set name=?,xPosition=?,yPosition=?,direction=?,cost=? ,fkCompanyId=?  where  shipId=?";

    @Override
    public Ship add(Ship ship) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try{
              connection = DatabaseUtility.getConnection();
              preparedStatement = connection.prepareStatement(INSERT_SHIP);
                preparedStatement.setString(1, ship.getName());
                preparedStatement.setInt(2, ship.getxPosition());
                preparedStatement.setInt(3, ship.getyPosition());
                preparedStatement.setString(4, ship.getDirection());
                preparedStatement.setDouble(5, ship.getCost());
                preparedStatement.setInt(6, ship.getFkCompanyId());

                preparedStatement.executeUpdate();
            return ship;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DatabaseUtility.closeResources(connection,preparedStatement,resultSet);
        }
     }

        @Override
    public Ship update(Ship ship) {


        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try{
            connection=DatabaseUtility.getConnection();
              preparedStatement = connection.prepareStatement(UPDATE_SHIP);
            preparedStatement.setString(1, ship.getName());
            preparedStatement.setInt(2, ship.getxPosition());
            preparedStatement.setInt(3, ship.getyPosition());
            preparedStatement.setString(4, ship.getDirection());
            preparedStatement.setDouble(5, ship.getCost());
            preparedStatement.setInt(6, ship.getFkCompanyId());

            preparedStatement.executeUpdate();

            return  ship;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DatabaseUtility.closeResources(connection,preparedStatement,resultSet);
        }

    }


    @Override
    public Ship get(Object id) {
        String idString = id.toString();
        String findById= SELECT_SHIP_BY_ID.concat(idString);
        Ship ship= null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet rs = null;
        try{
            connection = DatabaseUtility.getConnection();
              preparedStatement = connection.prepareStatement(findById);
              rs = preparedStatement.executeQuery();

            while (rs.next()){
                String name=rs.getString(1);
                int xPosition=rs.getInt(2);
                int yPosition =rs.getInt(3);
                String direction =rs.getString(4);
                double cost =rs.getDouble(5);
                int fkCompnayId = rs.getInt(6);
                int shipId=rs.getInt(7);

                ship = new Ship(shipId,xPosition,yPosition,direction,cost,name,fkCompnayId);
            }
            return ship;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DatabaseUtility.closeResources(connection,preparedStatement,rs);
        }

    }

    @Override
    public void delete(Object id) {

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try{
            connection =DatabaseUtility.getConnection();
          preparedStatement = connection.prepareStatement(DELETE_SHIP);
        preparedStatement.setInt(1,Integer.parseInt(id.toString()));
        preparedStatement.executeUpdate();
        } catch (SQLException e) {
        throw new RuntimeException(e);
        }finally {
            DatabaseUtility.closeResources(connection,preparedStatement,resultSet);
        }

    }

    @Override
    public List<Ship> listAll() {
        List<Ship> ships = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet rs = null;
        Ship ship = null;
        try{
            connection =DatabaseUtility.getConnection();
          preparedStatement = connection.prepareStatement(SELECT_ALL_SHIP);
          rs = preparedStatement.executeQuery();
        while (rs.next()){
            String name=rs.getString(1);
            int xPosition=rs.getInt(2);
            int yPosition =rs.getInt(3);
            String direction =rs.getString(4);
            double cost =rs.getDouble(5);
            int fkCompnayId = rs.getInt(6);
            int shipId=rs.getInt(7);

            ship = new Ship(shipId,xPosition,yPosition,direction,cost,name,fkCompnayId);

            ships.add(ship);

        }
        return ships;
        } catch (SQLException e) {
        throw new RuntimeException(e);
        }finally {
            DatabaseUtility.closeResources(connection,preparedStatement,rs);
        }
        }
    }

