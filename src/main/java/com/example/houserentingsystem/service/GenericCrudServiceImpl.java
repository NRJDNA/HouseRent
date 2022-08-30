package com.example.houserentingsystem.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface GenericCrudServiceImpl<D, ID> {
    D save(D d) throws ParseException, IOException;

    List<D> findAll() throws IOException;

    D findById(ID id) throws IOException;

    void deleteById(ID id);
}
