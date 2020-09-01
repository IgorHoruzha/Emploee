package com.company.dao.interfaces.File;

import com.company.dao.interfaces.ICrud;
import com.company.dao.models.Note;

import java.io.IOException;
import java.util.List;

public interface IFile extends ICrud<Note> {
    void write(Note n) throws IOException, ClassNotFoundException;
    List<Note> read(String fileName) throws IOException, ClassNotFoundException;
    void openReadStream(String fileName) throws IOException;
    void closeReadStream() throws IOException;

    void openWriteStream(String fileName) throws IOException;
    void closeWriteStream() throws IOException;
}
