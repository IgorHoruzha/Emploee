package com.company.dao.accessobjects;

import com.company.dao.interfaces.dataBase.IDataBase;
import com.company.dao.models.Note;
import com.company.dao.models.User;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class UsersDAO implements IDataBase {

    private final String url = "jdbc:postgresql://postgresql-12298-0.cloudclusters.net:12298/Users";
    private final String user = "admin";
    private final String password = "123456789";
    Connection conn = null;
    @Override
    public void Connect() throws SQLException {
            conn = DriverManager.getConnection(url, user, password);
    }

    @Override
    public void Disconnect() {
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
public  void CreateTable(){
    try {
        Connect();
        Statement st = conn.createStatement();
        boolean rs = st.execute("CREATE  TABLE IF NOT EXISTS accounts (\n" +
                "\tuser_id serial PRIMARY KEY,\n" +
                "\tusername VARCHAR ( 50 )  NOT NULL\n" +
                ");");

        Disconnect();
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
}

    @Override
    public boolean Insert(User user) {
        try {
            Connect();
            Statement st = conn.createStatement();
            boolean rs = st.execute("INSERT INTO accounts(user_id,username) VALUES("+user.getId()+",'"+user.getName()+"')");
            Disconnect();
            return  rs;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return  false;
    }

    @Override
    public ArrayList<User> SelectAll() {
        ArrayList<User> users= new ArrayList<User>();
        try {
            Connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT user_id, username FROM accounts");
            while (rs.next()) {
                int id=rs.getInt(1);
                String name=rs.getString(2);
                User currentUser= new User(id,name);
                users.add(currentUser);
            }
            Disconnect();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return  users;
    }


    @Override
    public boolean update(User user) throws IOException {
        try {
            Connect();
            Statement st = conn.createStatement();
            boolean rs = st.execute("UPDATE  accounts SET  username = '"+ user.getName()+"' WHERE user_id = "+user.getId() );

            Disconnect();
            return true;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(User user) throws IOException {
        try {
            Connect();
            Statement st = conn.createStatement();
            boolean rs = st.execute("DELETE FROM  accounts WHERE user_id = "+user.getId() );

            Disconnect();
            return true;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return false;
    }

    @Override
    public void clearAll() throws SQLException {

            Connect();
            Statement st = conn.createStatement();
            boolean rs = st.execute("DELETE FROM accounts;" );

            Disconnect();



    }
}
