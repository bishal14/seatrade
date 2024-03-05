package com.seatrade.dao;

import com.seatrade.entity.Cargo;
import com.seatrade.entity.Company;
import com.seatrade.util.database.DatabaseUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyDaoImplementation implements GenericDAO<Company> {


    private static final String INSERT_COMPANY="insert into company(name,height,width ,companyBalance) values(?,?,?,?);";
    private static final String SELECT_COMPANY_ID="select * from company where companyId='";
    private static final String SELECT_ALL_COMPANY="select * from company";
    private static final String DELETE_COMPANY="delete from company where companyId='";
    private static final String UPDATE_COMPANY="update company set  name=', companyId=',company_balance='";
    @Override
    public Company add(Company company) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try{
            connection = DatabaseUtility.getConnection();
             preparedStatement = connection.prepareStatement(INSERT_COMPANY);
            preparedStatement.setString(1,company.getName());
            preparedStatement.setInt(2,5);
            preparedStatement.setInt(3,5);
             preparedStatement.setDouble(4,20000);
             preparedStatement.executeUpdate();
            return  company;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseUtility.closeResources(connection,preparedStatement,resultSet);
        }
        }


    @Override
    public Company update(Company company) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try{
            connection = DatabaseUtility.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_COMPANY);
            preparedStatement.setString(1,company.getName());
            preparedStatement.setInt(2,company.getCompanyId());
            preparedStatement.setDouble(3,company.getCompanyBalance());

            return company;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseUtility.closeResources(connection,preparedStatement,resultSet);
        }
        }




    @Override
    public Company get(Object id) throws SQLException {
        Company company = null;
        String idString = SELECT_COMPANY_ID.concat(id.toString());
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try{
            connection = DatabaseUtility.getConnection();
            preparedStatement = connection.prepareStatement(idString);
              resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String companyName=resultSet.getString(1);
                int companyId=resultSet.getInt(2);
                double balance = resultSet.getInt(3);
                company = new Company(companyName,companyId,balance);

            }
            return company;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DatabaseUtility.closeResources(connection,preparedStatement,resultSet);
        }


     }

    @Override
    public void delete(Object id) {
        Company company = null;
        String delete = DELETE_COMPANY.concat(id.toString());
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseUtility.getConnection();
            preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setInt(2, Integer.parseInt(id.toString()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseUtility.closeResources(connection, preparedStatement, resultSet);
        }


    }


    @Override
    public List<Company> listAll() throws SQLException {
        List<Company> companies = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet rs = null;
        try{
            connection = DatabaseUtility.getConnection();
          preparedStatement = DatabaseUtility.getConnection().prepareStatement(SELECT_ALL_COMPANY);
          rs =preparedStatement.executeQuery();

        while (rs.next()){
            String name=rs.getString(1);
            int id = rs.getInt(2);
            double balance = rs.getDouble(3);
            Company company = new Company(name,id,balance);

            companies.add(company);

        }
        return companies;
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }finally {
            DatabaseUtility.closeResources(connection, preparedStatement, rs);
        } }
}
