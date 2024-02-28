package com.BishalJustin.dao;

import java.util.List;
import sea.Cargo;

public interface CargoDao {
    void addCargo(Cargo cargo);
    Cargo getCargoById(int id);

    List<Cargo> getAllCargos();
    void  updateCargo(Cargo cargo);
    void  deleteCargo(Cargo cargo);
}
