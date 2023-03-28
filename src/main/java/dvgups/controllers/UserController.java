package dvgups.controllers;

import dvgups.DBHandler;
import dvgups.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserController {
  private User user;
  List<User> users;

  public UserController() {
    this.users = new ArrayList<>();
  }

  public List<User> getUsers() {
    DBHandler dbHandler = new DBHandler();

    ResultSet resultSet = dbHandler.getUsers();

    try {
      while (resultSet.next()) {
        int idUser = resultSet.getInt("iduser");
        String fName = resultSet.getString("fName");
        String lName = resultSet.getString("lName");
        String pName = resultSet.getString("pName");
        String email = resultSet.getString("email");

        User user = new User(idUser, fName, lName, pName, email, "unknown");
        users.add(user);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    setUserRoles();

    return users;
  }

  public List<User> getUsers(int idUser) {
    DBHandler dbHandler = new DBHandler();

    ResultSet resultSet = dbHandler.getUsers(idUser);

    try {
      while (resultSet.next()) {
        String fName = resultSet.getString("fName");
        String lName = resultSet.getString("lName");
        String pName = resultSet.getString("pName");
        String email = resultSet.getString("email");

        User user = new User(idUser, fName, lName, pName, email, "");
        users.add(user);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    setUserRoles();

    return users;
  }

  public int addUser(User user) {
    DBHandler dbHandler = new DBHandler();

    ResultSet resultSet = dbHandler.addUser(user);

    int idUser = 0;
    try {
      while (resultSet.next()) {
        idUser = resultSet.getInt("iduser");
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return idUser;
  }

  public void addStudent(User user) {
    DBHandler dbHandler = new DBHandler();

    int idUser = addUser(user);
    dbHandler.addStudent(idUser);
  }

  public void addTeacher(User user) {
    DBHandler dbHandler = new DBHandler();

    int idUser = addUser(user);
    dbHandler.addTeacher(idUser);
  }

  private void setUserRoles() {
    for (User user : users) {
      RoleController roleController = new RoleController(user);

      user = roleController.getRoledUser();
    }
  }
}
