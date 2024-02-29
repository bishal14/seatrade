package com.seatrade.dao;

import com.seatrade.entity.ShipHarbourAssociation;
import com.seatrade.entity.TransportOrder;
import com.seatrade.util.database.DatabaseUtility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransportOrderDaoImplementation implements GenericDAO<TransportOrder> {

    private static final String INSERT_TRANSPORT_ORDER="INSERT INTO transportorder(transporter_id,fk_company_id,fk_ship_id) values (?,?,?)";
    private static final String SELECT_TRANSPORT_ORDE_BY_ID="select fk_company_id,fk_ship_id  where transporter_id='";
    private static final String SELECT_ALL_TRANSPORT_ORDER  ="select * from transportorder";
    private static final String DELETE_TRANSPORT_ORDER ="delete from transportorder where transporter_id='";
    private static final String UPDATE_TRANSPORT_ORDE="update transportorder set transporter_id=',k_company_id=', fk_ship_id='";

    @Override
    public TransportOrder add(TransportOrder transportOrder) {
        try {
            PreparedStatement preparedStatement = DatabaseUtility.getConnection().prepareStatement(INSERT_TRANSPORT_ORDER);
            preparedStatement.setInt(1, transportOrder.getTransportOrderId());
            preparedStatement.setInt(2, transportOrder.getForeignKeyCompanyId());
            preparedStatement.setInt(3, transportOrder.getForeignKeyShipId());

            preparedStatement.executeUpdate();
            return transportOrder;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TransportOrder update(TransportOrder transportOrder) {
        try{
            PreparedStatement preparedStatement = DatabaseUtility.getConnection().prepareStatement(UPDATE_TRANSPORT_ORDE);
            preparedStatement.setInt(2, transportOrder.getForeignKeyCompanyId());
            preparedStatement.setInt(3, transportOrder.getForeignKeyShipId());
            preparedStatement.executeUpdate();

            return  transportOrder;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public TransportOrder get(Object id) {
        TransportOrder transportOrder = null;

        String idString = SELECT_TRANSPORT_ORDE_BY_ID.concat(id.toString());

        try{
            PreparedStatement preparedStatement= DatabaseUtility.getConnection().prepareStatement(idString);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int companyId= resultSet.getInt(2);
                int shipId = resultSet.getInt(3);

                transportOrder = new TransportOrder(companyId,shipId);
            }
            return transportOrder;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Object id) {
        String idString = DELETE_TRANSPORT_ORDER.concat(id.toString());

        try {
            PreparedStatement preparedStatement = DatabaseUtility.getConnection().prepareStatement(idString);
            preparedStatement.setInt(1,Integer.parseInt(id.toString()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<TransportOrder> listAll() {
        List<TransportOrder> transportOrders = new ArrayList<>();
        try{

            PreparedStatement preparedStatement = DatabaseUtility.getConnection().prepareStatement(SELECT_ALL_TRANSPORT_ORDER);
            ResultSet rs =preparedStatement.executeQuery();

            while (rs.next()){
                int transport_order_id= rs.getInt(1);
                int companyId= rs.getInt(2);
                int shipId=rs.getInt(3);

                TransportOrder transportOrder = new TransportOrder(transport_order_id,companyId,shipId);
                transportOrders.add(transportOrder);

            }
            return transportOrders;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
