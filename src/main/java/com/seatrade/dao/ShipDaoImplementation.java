package com.seatrade.dao;

import com.seatrade.dao.GenericDAO;
import com.seatrade.entity.Harbour;
import com.seatrade.entity.Ship;
import com.seatrade.util.database.DatabaseUtility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShipDaoImplementation implements GenericDAO<Ship> {

    private static final String INSERT_SHIP="INSERT INTO ship (ship_id,name,cell_id) values (?,?,?)";
    private static final String SELECT_SHIP_BY_ID="select ship_id,name,cell_id where id='";
    private static final String SELECT_ALL_SHIP  ="select * from ship";
    private static final String DELETE_SHIP ="delete from ship where ship_id='";
    private static final String UPDATE_SHIP="update ship set ship_id=',name=', cell_id='";

    @Override
    public Ship create(Ship ship) {
        try {
            PreparedStatement preparedStatement = DatabaseUtility.getConnection().prepareStatement(INSERT_SHIP);
            preparedStatement.setInt(1, ship.getId());
            preparedStatement.setString(2, ship.getName());
            preparedStatement.setInt(3, ship.getCellId());

            preparedStatement.executeUpdate();
            return ship;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
     }

    @Override
    public Ship update(Ship ship) {


        try{
            PreparedStatement preparedStatement = DatabaseUtility.getConnection().prepareStatement(UPDATE_SHIP);
            preparedStatement.setInt(1,ship.getId());
            preparedStatement.setString(2,ship.getName());
            preparedStatement.setInt(3,ship.getCellId());

            preparedStatement.executeUpdate();

            return  ship;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public Ship get(Object id) {
        String idString = id.toString();
        String findById= SELECT_SHIP_BY_ID.concat(idString);
        Ship ship= null;
        try{
            PreparedStatement preparedStatement = DatabaseUtility.getConnection().prepareStatement(findById);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int shipId= rs.getInt(1);
                String shipName=rs.getString(2);
                int cellId=rs.getInt(3);
                ship = new Ship(shipId,shipName,cellId);
            }
            return ship;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Object id) {

        try {
        PreparedStatement preparedStatement = DatabaseUtility.getConnection().prepareStatement(DELETE_SHIP);
        preparedStatement.setInt(1,Integer.parseInt(id.toString()));
        preparedStatement.executeUpdate();
        } catch (SQLException e) {
        throw new RuntimeException(e);
        }

    }

    @Override
    public List<Ship> listAll() {
        List<Ship> ships = new ArrayList<>();
        try{
        PreparedStatement preparedStatement = DatabaseUtility.getConnection().prepareStatement(SELECT_ALL_SHIP);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()){
        int id= rs.getInt(1);
        String name = rs.getString(2);
        int cellId=rs.getInt(3);

            Ship ship = new Ship(id,name,cellId);
            ships.add(ship);
        }
        return ships;
        } catch (SQLException e) {
        throw new RuntimeException(e);
        }
        }
    }

