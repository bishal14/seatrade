package com.seatrade.dao;

import com.seatrade.dao.GenericDAO;
import com.seatrade.entity.Harbour;
import com.seatrade.util.database.DatabaseUtility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HarbourDaoImplementation implements GenericDAO<Harbour> {

    private static final String INSERT_HARBOUR="INSERT INTO harbour(harbour_id,name,cellId) values (?,?,?)";
    private static final String SELECT_HARBOUR_BY_ID="select harbour_id,name,cell_id where id='";
    private static final String SELECT_ALL_HARBOUR  ="select * from harbour";
    private static final String DELETE_HARBOUR ="delete from harbour where id='";
    private static final String UPDATE_HARBOUR="update harbour set id=',name=', id='";

    @Override
    public Harbour create(Harbour harbour) {

        try {
            PreparedStatement preparedStatement = DatabaseUtility.getConnection().prepareStatement(INSERT_HARBOUR);
            preparedStatement.setInt(1, harbour.getId());
            preparedStatement.setString(2, harbour.getName());
            preparedStatement.setInt(3, harbour.getCellId());

            preparedStatement.executeUpdate();
            return harbour;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Harbour update(Harbour harbour) {
        try{
            PreparedStatement preparedStatement = DatabaseUtility.getConnection().prepareStatement(UPDATE_HARBOUR);
            preparedStatement.setInt(1,harbour.getId());
            preparedStatement.setString(2,harbour.getName());
            preparedStatement.setInt(3,harbour.getCellId());

            preparedStatement.executeUpdate();

            return  harbour;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

     }

    @Override
    public Harbour get(Object id) {
        String idString = id.toString();
        String findById= SELECT_HARBOUR_BY_ID.concat(idString);
        Harbour harbour = null;
        try{
            PreparedStatement preparedStatement = DatabaseUtility.getConnection().prepareStatement(findById);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int harbourId= rs.getInt(1);
                String harbourName=rs.getString(2);
                int cellId=rs.getInt(3);
                harbour = new Harbour(harbourId,harbourName,cellId);
            }
            return harbour;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
     }

    @Override
    public void delete(Object id) {
    try {
        PreparedStatement preparedStatement = DatabaseUtility.getConnection().prepareStatement(DELETE_HARBOUR);
        preparedStatement.setInt(1,Integer.parseInt(id.toString()));
        preparedStatement.executeUpdate();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }

    @Override
    public List<Harbour> listAll() {

        List<Harbour> harbours = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = DatabaseUtility.getConnection().prepareStatement(SELECT_ALL_HARBOUR);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int id= rs.getInt(1);
                String name = rs.getString(2);
                int cellId=rs.getInt(3);

                Harbour harbour = new Harbour(id,name,cellId);
                harbours.add(harbour);
            }
            return harbours;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
     }
}
