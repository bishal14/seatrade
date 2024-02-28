package com.BishalJustin.dao;

import java.util.List;
import  org.example.sea.TransportOrder;

public interface TransportOrderDao {
    void addTransportOrder(TransportOrder transportOrder);
      TransportOrder getTransportOrderById(int id);

    List<TransportOrder> getAllTransportOrderr();
    void  updateTransportOrder( TransportOrder transportOrder );
    void  deleteTransportOrder( TransportOrder transportOrder);

}
