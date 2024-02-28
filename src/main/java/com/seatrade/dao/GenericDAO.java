package com.seatrade.dao;

import com.seatrade.entity.Company;

import java.util.List;

public interface GenericDAO<T> {
    T create(T t);
    T update(T t);

 
    T get(Object id);

    void delete(Object id);

    List<T> listAll();

}
