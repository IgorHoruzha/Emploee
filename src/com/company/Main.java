package com.company;

public class Main {
    public static void main(String[] args) {
//        FrontEndDeveloper fed = new FrontEndDeveloper(Level.SENIOR);
//        fed.work();
//        fed.develop();
//        fed.eat();
//        fed.rest();
        //...

        NoteDAO noteDAO = new NoteDAO();
        noteDAO.write();
        noteDAO.read();
    }
}
