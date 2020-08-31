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

    String fileName1="./NotesGood.json";
    String fileName2="./NotesWithoutId.json";
    String fileName3="./NotesWithoutMessage.json";
    FileWriter oos;
    FileReader ois;

    @Override
    public void write(Note n) throws IOException {
        String fileName = getFileNameByObject(n);

        ArrayList<Note> notes = read(fileName);
        notes.add(n);
        writeAll(notes,fileName);

    }

    public  void writeAll(ArrayList<Note> notes, String fileName ) throws IOException {
        openWriteStream(fileName);
        Gson gson = new Gson();
        String json = gson.toJson(notes);
        oos.write(json);
        oos.close();
        closeWriteStream();
    }

    @Override
    public ArrayList<Note> read(String fileName)  {
        ArrayList<Note> notes=new ArrayList<Note>();
        try {
            openReadStream(fileName);

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
    public void openReadStream(String fileName) throws IOException {
         ois = new FileReader(fileName);
    }

    @Override
    public void closeReadStream() throws IOException {
        ois.close();
    }

    @Override
    public void openWriteStream(String fileName) throws IOException {
        oos = new FileWriter(fileName);
    }

    @Override
    public void closeWriteStream() throws IOException {
        oos.close();
    }

    @Override
    public boolean update(Note n) throws IOException {

        String fileName = getFileNameByObject(n);

        ArrayList<Note> notes = read(fileName);
        int index= notes.indexOf(n);

        if (index!=-1) {
            Note currentNote = notes.get(index);
            currentNote.setMessage(n.getMessage());
            writeAll(notes,fileName);
            return  true;
        }

        return false;
    }

    @Override
    public boolean delete(Note n) throws IOException {
        String fileName = getFileNameByObject(n);

        ArrayList<Note> notes = read(fileName);
        int index= notes.indexOf(n);

        if (index!=-1) {
            notes.remove(index);
            writeAll(notes,fileName);

            return  true;
        }

        return false;
    }

    private String getFileNameByObject(Note n) {
        String fileName;

        if (n.getId() < 0) {
            fileName = fileName2;
        } else if (n.getMessage() == null) {
            fileName = fileName3;
        } else {
            fileName = fileName1;
        }
        return fileName;
    }

    @Override
    public void clearAll() throws IOException {
        writeAll(new ArrayList<Note>(), fileName1);
        writeAll(new ArrayList<Note>(), fileName2);
        writeAll(new ArrayList<Note>(), fileName3);
    }
}
