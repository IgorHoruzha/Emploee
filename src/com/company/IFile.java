package com.company;

public interface IFile extends ICrud {
    void write();
    void read();
    void openStream();
    void closeStream();
}
