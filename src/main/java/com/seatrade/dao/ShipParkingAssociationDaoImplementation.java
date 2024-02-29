package com.seatrade.dao;

public class ShipParkingAssociationDaoImplementation {
    private static final String INSERT_SHIP_HARBOUR_ASSOCIATION="INSERT INTO harbour(harbour_id,name,cellId) values (?,?,?)";
    private static final String SELECT_SHIP_HARBOUR_ASSOCIATION_BY_ID="select harbour_id,name,cell_id where id='";
    private static final String SELECT_ALL_SHIP_HARBOUR_ASSOCIATION  ="select * from harbour";
    private static final String DELETE_SHIP_HARBOUR_ASSOCIATION ="delete from harbour where id='";
    private static final String UPDATE_SHIP_HARBOUR_ASSOCIATION="update harbour set id=',name=', id='";
}
