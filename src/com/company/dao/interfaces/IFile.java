package com.company.dao.interfaces;

import com.company.dao.models.Note;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface IFile extends ICrud {
    void write(Note n) throws IOException, ClassNotFoundException;
    List<Note> read() throws IOException, ClassNotFoundException;
    void openReadStream() throws IOException;
    void closeReadStream() throws IOException;

    void openWriteStream() throws IOException;
    void closeWriteStream() throws IOException;
}
