package com.company.dao.accessobjects;

import com.company.dao.interfaces.IFile;
import com.company.dao.models.Note;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

public class NoteDAO implements IFile {

    String fileName="./Notes.json";
    FileWriter oos;
    FileReader ois;


    @Override
    public void write(Note n) throws IOException {
        ArrayList<Note> notes = read();
        notes.add(n);
        writeAll(notes);

    }

    public  void writeAll(ArrayList<Note> notes ) throws IOException {
        openWriteStream();
        Gson gson = new Gson();
        String json = gson.toJson(notes);
        oos = new FileWriter(fileName);
        oos.write(json);
        oos.close();
        closeWriteStream();
    }

    @Override
    public ArrayList<Note> read()  {
        ArrayList<Note> notes=new ArrayList<Note>();
        try {
            openReadStream();

            Scanner myReader = new Scanner(ois);
            StringBuilder data= new StringBuilder();
            while (myReader.hasNextLine()) {
                data.append(myReader.nextLine());
            }
            Gson gson = new Gson();
            Type datasetListType = new TypeToken<ArrayList<Note> >() {}.getType();
            notes=gson.fromJson(data.toString(), datasetListType);
            closeReadStream();
        } catch (IOException e) {
            return  notes;
        }

        return notes;
    }

    @Override
    public void openReadStream() throws IOException {
         ois = new FileReader(fileName);
    }

    @Override
    public void closeReadStream() throws IOException {
        ois.close();
    }

    @Override
    public void openWriteStream() throws IOException {
        oos = new FileWriter(fileName);
    }

    @Override
    public void closeWriteStream() throws IOException {
        oos.close();
    }

    @Override
    public boolean update(Note n) throws IOException {
        ArrayList<Note> notes = read();
        int index= notes.indexOf(n);

        if (index!=-1) {
            Note currentNote = notes.get(index);
            currentNote.setMessage(n.getMessage());
            writeAll(notes);
            return  true;
        }

        return false;
    }

    @Override
    public boolean delete(Note n) throws IOException {
        ArrayList<Note> notes = read();
        int index= notes.indexOf(n);

        if (index!=-1) {
            notes.remove(index);
            writeAll(notes);

            return  true;
        }

        return false;
    }

    @Override
    public void clearAll() throws IOException {

        writeAll(new ArrayList<Note>());
    }
}
