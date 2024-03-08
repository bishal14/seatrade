package com.seatrade.dao;

import com.seatrade.dao.GenericDAO;
import com.seatrade.entity.Harbour;
import com.seatrade.util.database.DatabaseUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HarbourDaoImplementation implements GenericDAO<Harbour> {

    private static final String INSERT_HARBOUR="INSERT INTO harbour(xPosition,yPosition,direction,name) values (?,?,?,?)";
    private static final String SELECT_HARBOUR_BY_ID="select xPosition,yPosition,direction,name from harbour where harbourId=?";
    private static final String SELECT_ALL_HARBOUR  ="select * from harbour";
    private static final String DELETE_HARBOUR ="delete from harbour where harbourId=?";
    private static final String UPDATE_HARBOUR="update harbour set xPosition=?,yPosition=?,direction=?,name=? where harbourId=?";

    @Override
    public Harbour add(Harbour harbour) {

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try{
            connection = DatabaseUtility.getConnection();
              preparedStatement = connection.prepareStatement(INSERT_HARBOUR);
             preparedStatement.setInt(1, harbour.getxPosition());
             preparedStatement.setInt(2,harbour.getyPosition());
            preparedStatement.setString(3,harbour.getDirection());
            preparedStatement.setString(4,harbour.getName());


            preparedStatement.executeUpdate();
            return harbour;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DatabaseUtility.closeResources(connection,preparedStatement,resultSet);
        }
    }

    @Override
    public Harbour update(Harbour harbour) {

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try{
            connection =DatabaseUtility.getConnection();
              preparedStatement = connection.prepareStatement(UPDATE_HARBOUR);
            preparedStatement.setInt(1, harbour.getxPosition());
            preparedStatement.setInt(2, harbour.getyPosition());
            preparedStatement.setString(3, harbour.getDirection());
            preparedStatement.setString(4, harbour.getName());
            preparedStatement.setInt(5, harbour.getHarbourId());

            preparedStatement.executeUpdate();

            return  harbour;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DatabaseUtility.closeResources(connection,preparedStatement,resultSet);
        }

     }

    @Override
    public Harbour get(Object id) {

        Harbour harbour = null;

            PreparedStatement preparedStatement = null;
            Connection connection = null;
            ResultSet resultSet = null;
        try {
            connection = DatabaseUtility.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_HARBOUR_BY_ID);
            preparedStatement.setInt(1, Integer.parseInt(id.toString()));
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int harbourId = resultSet.getInt("id"); // Assuming the column name is "id"
                int xPosition = resultSet.getInt("xPosition");
                int yPosition = resultSet.getInt("yPosition");
                String direction = resultSet.getString("direction");
                String name = resultSet.getString("name");
                harbour = new Harbour(  xPosition, yPosition, direction, name,harbourId); // Adjust constructor as needed
            }
            return harbour;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseUtility.closeResources(connection, preparedStatement, resultSet);
        }
     }

    @Override
    public void delete(Object id) {

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try{
            connection = DatabaseUtility.getConnection();
          preparedStatement = connection.prepareStatement(DELETE_HARBOUR);
        preparedStatement.setInt(1,Integer.parseInt(id.toString()));
        preparedStatement.executeUpdate();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }finally {
        DatabaseUtility.closeResources(connection,preparedStatement,resultSet);
    }
    }

    @Override
    public List<Harbour> listAll() {

        List<Harbour> harbours = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try{
            connection = DatabaseUtility.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_HARBOUR);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int harbourID = rs.getInt(1);
                int xPosition= rs.getInt(2);
                int yPosition =rs.getInt(3);
                String direction =rs.getString(4);
                String name = rs.getString(5);
  Harbour harbour = new Harbour(xPosition,yPosition,direction,name,harbourID);
            harbours.add(harbour);
            }
            return harbours;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DatabaseUtility.closeResources(connection,preparedStatement,resultSet);
        }
     }
}
