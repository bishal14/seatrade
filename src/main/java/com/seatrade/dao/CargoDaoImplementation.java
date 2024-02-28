package com.seatrade.dao;

import com.seatrade.dao.GenericDAO;
import com.seatrade.entity.Cargo;
import com.seatrade.entity.Harbour;
import com.seatrade.util.database.DatabaseUtility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CargoDaoImplementation implements GenericDAO<Cargo> {
    private static final String INSERT_CARGOS= "INSERT INTO cargo(cargo_id,harbour_source_id, harbour_destination_id, value)  (name, weight) VALUES    (?,?,?, ?);";

     private static final String SELECT_CARGO_BY_ID="select * from cargo where id ='";
    private static final String SELECT_ALL_CARGOS="select * from cargo";
    private static final String DELETE_CARGOS="delete from cargo where id='";
    private static final String UPDATE_CARGO="update cargo set cargo_id=',harbour_source_id=', harbour_destination_id=', value=' where id ='";
    @Override
    public Cargo create(Cargo cargo) {

        try{
            PreparedStatement preparedStatement = DatabaseUtility.getConnection().prepareStatement(INSERT_CARGOS);
            preparedStatement.setInt(1,cargo.getId());
            preparedStatement.setInt(2,cargo.getSourceHarbourId());
            preparedStatement.setInt(2,cargo.getDestinationHarbourId());
            preparedStatement.setDouble(4,cargo.getValue());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cargo;

    }

    @Override
    public Cargo update(Cargo cargo) {
    try{
        PreparedStatement preparedStatement = DatabaseUtility.getConnection().prepareStatement(UPDATE_CARGO);
        preparedStatement.setInt(2,cargo.getSourceHarbourId());
        preparedStatement.setInt(3,cargo.getDestinationHarbourId());
        preparedStatement.setDouble(4,cargo.getValue());

        preparedStatement.executeUpdate();
        return cargo;
    }catch (SQLException e) {
        throw new RuntimeException(e);
    }


    }

    @Override
    public Cargo get(Object id) {
         HarbourDaoImplementation harbourDaoImplementation = new HarbourDaoImplementation();
        Cargo cargo = null;

        String idString = SELECT_CARGO_BY_ID.concat(id.toString());

        try{
            PreparedStatement preparedStatement= DatabaseUtility.getConnection().prepareStatement(idString);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
               int sourceHarbourId= resultSet.getInt(2);
               int destinationHarbourId = resultSet.getInt(3);
               double value = resultSet.getDouble(4);
               cargo = new Cargo(sourceHarbourId,destinationHarbourId,value);
             }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cargo;
    }


    @Override
    public void delete(Object cargoId) {

        try {
            PreparedStatement preparedStatement = DatabaseUtility.getConnection().prepareStatement(DELETE_CARGOS);
            preparedStatement.setInt(1,Integer.parseInt(cargoId.toString()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public List<Cargo> listAll() {

        List<Cargo> cargos = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = DatabaseUtility.getConnection().prepareStatement(SELECT_ALL_CARGOS);
            ResultSet rs =preparedStatement.executeQuery();

            while (rs.next()){
                int id = rs.getInt(1);
                int sourceHarbourId= rs.getInt(2);
                int destinationHarbourId=rs.getInt(3);
                double value = rs.getDouble(4);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
