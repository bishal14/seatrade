package com.seatrade.dao;

import com.seatrade.entity.Cargo;
import com.seatrade.entity.Company;
import com.seatrade.util.database.DatabaseUtility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyDaoImplementation implements GenericDAO<Company> {


    private static final String INSERT_COMPANY="insert into company(name, company_id,company_balance) values(?,?);";
    private static final String SELECT_COMPANY_ID="select * from company where company_id='";
    private static final String SELECT_ALL_COMPANY="select * from company";
    private static final String DELETE_COMPANY="delete from company where company_id='";
    private static final String UPDATE_COMPANY="update company set company_id=', name=', company_balance='";
    @Override
    public Company add(Company company) {

        try{
            PreparedStatement preparedStatement = DatabaseUtility.getConnection().prepareStatement(INSERT_COMPANY);
            preparedStatement.setString(1,company.getName());
            preparedStatement.setInt(2,company.getCompanyId());
            preparedStatement.setDouble(3,company.getCompanyBalance());

            preparedStatement.executeUpdate();
            return  company;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
     }

    @Override
    public Company update(Company company) {

        try{
            PreparedStatement preparedStatement = DatabaseUtility.getConnection().prepareStatement(UPDATE_COMPANY);
            preparedStatement.setString(1,company.getName());
            preparedStatement.setInt(2,company.getCompanyId());
            preparedStatement.setDouble(3,company.getCompanyBalance());

            return company;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

     }

    @Override
    public Company get(Object id)   {
        Company company = null;
        String idString = SELECT_COMPANY_ID.concat(id.toString());
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DatabaseUtility.getConnection().prepareStatement(idString);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String companyName=resultSet.getString(1);
                int companyId=resultSet.getInt(2);
                company = new Company(companyName,companyId);

            }
            return company;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


     }

    @Override
    public void delete(Object id) {
        Company company = null;
        String delete = DELETE_COMPANY.concat(id.toString());


            try {
                PreparedStatement preparedStatement = DatabaseUtility.getConnection().prepareStatement(delete);
                preparedStatement.setInt(2,Integer.parseInt(id.toString()));
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }



    }

    @Override
    public List<Company> listAll()  {
        List<Company> companies = new ArrayList<>();
        try{
        PreparedStatement preparedStatement = DatabaseUtility.getConnection().prepareStatement(SELECT_ALL_COMPANY);
        ResultSet rs =preparedStatement.executeQuery();

        while (rs.next()){
            String name=rs.getString(1);
            int id = rs.getInt(2);
            Company company = new Company(name,id);

            companies.add(company);

        }
        return companies;
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
}
