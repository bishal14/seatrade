package com.BishalJustin.dao;
import  sea.Ship;

import java.util.List;

public interface ShipDao {
    void addShip(Ship ship);
    Ship getShipById(int id);

    List<Ship> getAllShips();
    void  updateShip(Ship ship);
    void  deleteShip(Ship ship);
}
