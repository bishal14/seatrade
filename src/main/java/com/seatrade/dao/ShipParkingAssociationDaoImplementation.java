package com.seatrade.dao;

import com.seatrade.entity.Cargo;
import com.seatrade.entity.ShipHarbourAssociation;
import com.seatrade.util.database.DatabaseUtility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShipParkingAssociationDaoImplementation implements GenericDAO<ShipHarbourAssociation>{
    private static final String INSERT_SHIP_HARBOUR_ASSOCIATION="INSERT INTO shipharbourassociation(ship_harbour_id,fk_ship_id,fk_harbour_id) values (?,?,?)";
    private static final String SELECT_SHIP_HARBOUR_ASSOCIATION_BY_ID="select fk_ship_id,fk_harbour_id where ship_harbour_id='";
    private static final String SELECT_ALL_SHIP_HARBOUR_ASSOCIATION  ="select * from shipharbourassociation";
    private static final String DELETE_SHIP_HARBOUR_ASSOCIATION ="delete from shipharbourassociation where ship_harbour_id='";
    private static final String UPDATE_SHIP_HARBOUR_ASSOCIATION="update shipharbourassociation set ship_harbour_id=',fk_ship_id=', fk_harbour_id='";

    @Override
    public ShipHarbourAssociation add(ShipHarbourAssociation shipHarbourAssociation) {

        try {
            PreparedStatement preparedStatement = DatabaseUtility.getConnection().prepareStatement(INSERT_SHIP_HARBOUR_ASSOCIATION);
            preparedStatement.setInt(2, shipHarbourAssociation.getFkShipId());
            preparedStatement.setInt(3, shipHarbourAssociation.getFkHarbourId());

            preparedStatement.executeUpdate();
            return shipHarbourAssociation;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ShipHarbourAssociation update(ShipHarbourAssociation shipHarbourAssociation) {
        try{
        PreparedStatement preparedStatement = DatabaseUtility.getConnection().prepareStatement(UPDATE_SHIP_HARBOUR_ASSOCIATION);
            preparedStatement.setInt(1, shipHarbourAssociation.getShipHarbourAssociationId());

            preparedStatement.setInt(2, shipHarbourAssociation.getFkShipId());
        preparedStatement.setInt(3, shipHarbourAssociation.getFkHarbourId());
        preparedStatement.executeUpdate();

        return  shipHarbourAssociation;
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    }

    @Override
    public ShipHarbourAssociation get(Object id) {


        ShipHarbourAssociation shipHarbourAssociation = null;

        String idString = SELECT_SHIP_HARBOUR_ASSOCIATION_BY_ID.concat(id.toString());

        try{
            PreparedStatement preparedStatement= DatabaseUtility.getConnection().prepareStatement(idString);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int shipharbourid= resultSet.getInt(1);
                int shipId= resultSet.getInt(2);
                int harbourId = resultSet.getInt(3);

                shipHarbourAssociation = new ShipHarbourAssociation(shipharbourid,shipId,harbourId);
            }
            return shipHarbourAssociation;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Object id) {

        String idString = DELETE_SHIP_HARBOUR_ASSOCIATION.concat(id.toString());

        try {
            PreparedStatement preparedStatement = DatabaseUtility.getConnection().prepareStatement(idString);
            preparedStatement.setInt(1,Integer.parseInt(id.toString()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<ShipHarbourAssociation> listAll() {
        List<ShipHarbourAssociation> shipHarbourAssociations = new ArrayList<>();
        try{

            PreparedStatement preparedStatement = DatabaseUtility.getConnection().prepareStatement(SELECT_ALL_SHIP_HARBOUR_ASSOCIATION);
            ResultSet rs =preparedStatement.executeQuery();

            while (rs.next()){
                int ship_harbour_id = rs.getInt(1);
                int harbourId= rs.getInt(3);
                int shipId=rs.getInt(2);
                 ShipHarbourAssociation shipHarbourAssociation = new ShipHarbourAssociation(ship_harbour_id,shipId,  harbourId);
                shipHarbourAssociations.add(shipHarbourAssociation);

            }
            return shipHarbourAssociations;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
