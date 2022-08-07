package com.example.houserentingsystem.service;

import java.text.ParseException;
import java.util.List;

public interface GenericCrudServiceImpl<D, ID> {
    D save(D d) throws ParseException;

    List<D> findAll();

    D findById(ID id);

    void deleteById(ID id);
}