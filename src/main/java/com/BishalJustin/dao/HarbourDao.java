package com.BishalJustin.dao;

import java.util.List;
import org.example.sea.Harbour;
public interface HarbourDao {
    void addHarbour(Harbour harbour);
    Harbour  getHarbourById(int id);

    List<Harbour> getAllHarbour();
    void  updateHarbour(Harbour harbour );
    void  deleteHarbour(Harbour harbour);
}
