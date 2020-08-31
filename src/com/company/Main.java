package com.company;

import com.company.dao.accessobjects.NoteDAO;
import com.company.dao.models.Note;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        NoteDAO noteDAO = new NoteDAO();

        try {

            //============================================= Read all
            noteDAO.write(new Note(1, "Message1"));
            noteDAO.write(new Note(-1, "Message2"));
            noteDAO.write(new Note(3, null));
            noteDAO.write(new Note(4, "Message4"));

            ArrayList<Note> allNotes = noteDAO.read("./NotesGood.json");
            for (Note item : allNotes) {
                System.out.println(item.toString());
            }
            System.out.println();

            //============================================= Update one
            noteDAO.update(new Note(2, "Hello"));

            allNotes = noteDAO.read("./NotesGood.json");
            for (Note value : allNotes) {
                System.out.println(value.toString());
            }
            System.out.println();

            //============================================= Delete one

            noteDAO.delete(new Note(4, ""));

            allNotes = noteDAO.read("./NotesGood.json");
            for (Note note : allNotes) {
                System.out.println(note.toString());
            }
            System.out.println();
            //============================================= Clear all

           // noteDAO.clearAll();
            allNotes = noteDAO.read("./NotesGood.json");
            for (Note allNote : allNotes) {
                System.out.println(allNote.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
