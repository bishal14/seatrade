package com.seatrade.dao;

import java.util.List;

public interface GenericDAO<T> {
    T create(T t);
    T update(T t);
    T get(T t);

    void delete(T t);

    List<T> listAll();

}
