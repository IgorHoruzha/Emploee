package com.company.dao.interfaces;

import com.company.dao.models.Note;

import java.io.IOException;

public interface ICrud {
    boolean update(Note n) throws IOException;
    boolean delete(Note n) throws IOException;
    void clearAll() throws IOException;
}