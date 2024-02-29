package com.seatrade.dao;

import java.util.List;

public interface GenericDAO<T> {
    T add(T t);
    T update(T t);

 
    T get(Object id);

    void delete(Object id);

    List<T> listAll();

}
