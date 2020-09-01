package com.company.dao.interfaces;

import com.company.dao.models.Note;

import java.io.IOException;
import java.sql.SQLException;

public interface ICrud<T> {
    boolean update(T n) throws IOException;
    boolean delete(T n) throws IOException;
    void clearAll() throws IOException, SQLException;
}