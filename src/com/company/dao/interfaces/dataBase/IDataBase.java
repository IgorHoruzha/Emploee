package com.company.dao.interfaces.dataBase;

import com.company.dao.interfaces.ICrud;
import com.company.dao.models.Note;
import com.company.dao.models.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IDataBase extends ICrud<User> {
  void Connect() throws SQLException;
  void  Disconnect();
  boolean Insert(User user);
  ArrayList<User> SelectAll();
}
