package com.company;

public class NoteDAO implements IFile {
    @Override
    public void write() {
        System.out.println("data were written to file");
    }

    @Override
    public void read() {
        System.out.println("reading data from file...");
    }

    @Override
    public void openStream() {

    }

    @Override
    public void closeStream() {

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void clearAll() {

    }
}
