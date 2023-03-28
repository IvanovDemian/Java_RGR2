package dvgups;

import dvgups.controllers.GradeController;
import dvgups.models.Grade;
import dvgups.models.User;

import java.sql.*;

public class DBHandler {
  Connection dbConnection;

  public Connection getDbConnection() throws ClassNotFoundException, SQLException {
//    System.out.println("getDbConnection()");

    String connectionStr = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "zachetcom";

    Class.forName("com.mysql.cj.jdbc.Driver");

    dbConnection = DriverManager.getConnection(connectionStr, "root", "");

//    System.out.println("db ok");

    return dbConnection;
  }

  public ResultSet getUsers() {
    ResultSet resultSet = null;

    String select = "SELECT * FROM users";

    try {
      PreparedStatement prSt = getDbConnection().prepareStatement(select);
//      prSt.setInt(1, 2);
      resultSet = prSt.executeQuery();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return resultSet;
  }

  public ResultSet getUsers(int idUser) {
    ResultSet resultSet = null;

    String select = "SELECT * FROM users WHERE iduser = ?";

    try {
      PreparedStatement prSt = getDbConnection().prepareStatement(select);
      prSt.setInt(1, idUser);
      resultSet = prSt.executeQuery();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return resultSet;
  }

  public ResultSet getRole(User user, String subtable) {
    ResultSet resultSet = null;

    String select = "SELECT * FROM " + "users" +
      " LEFT JOIN " + subtable + " ON " + "users" + "." + "iduser" + " = " + subtable + "." + "iduser" +
      " WHERE " + "users" + "." + "iduser" + " =?";

    try {
      PreparedStatement prSt = getDbConnection().prepareStatement(select);
      prSt.setInt(1, user.getIduser());
      resultSet = prSt.executeQuery();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return resultSet;
  }

  public ResultSet addUser(User user) {
    ResultSet resultSet = null;

    String insert = "INSERT INTO users (iduser, fName, lName, pName, email, password) VALUES (null, ?, ?, ?, ?, ?);";
    String select = "SELECT * FROM users ORDER BY iduser DESC LIMIT 1;";

    try {
      PreparedStatement prSt = getDbConnection().prepareStatement(insert);
      prSt.setString(1, user.getfName());
      prSt.setString(2, user.getlName());
      prSt.setString(3, user.getpName());
      prSt.setString(4, user.getEmail());
      prSt.setString(5, user.getPassword());
      prSt.executeUpdate();
//      System.out.println("prSt good");
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    try {
      PreparedStatement prSt = getDbConnection().prepareStatement(select);
      resultSet = prSt.executeQuery();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return resultSet;
  }

  public void addStudent(int idUser) {
    ResultSet resultSet = null;

//    String insert = "INSERT INTO users (iduser, fName, lName, pName, email, password) VALUES (null, ?, ?, ?, ?, ?); " +
//      "SET @lastID = LAST_INSERT_ID(); " +
//      "INSERT INTO students (idstudent, iduser, group_array) VALUES (null, @lastID, '');";

    String insert = "INSERT INTO students (idstudent, iduser, group_array) VALUES (null, ?, '');";

    try {
      PreparedStatement prSt = getDbConnection().prepareStatement(insert);
      prSt.setInt(1, idUser);
      prSt.executeUpdate();
//      System.out.println("prSt good");
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public void addTeacher(int idUser) {
    ResultSet resultSet = null;

    String insert = "INSERT INTO teachers (idteacher, iduser, group_array) VALUES (null, ?, '');";

    try {
      PreparedStatement prSt = getDbConnection().prepareStatement(insert);
      prSt.setInt(1, idUser);
      prSt.executeUpdate();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return;
  }

  public ResultSet getDisciplines() {
    ResultSet resultSet = null;

    String select = "SELECT * FROM discipline";

    try {
      PreparedStatement prSt = getDbConnection().prepareStatement(select);
//      prSt.setInt(1, 2);
      resultSet = prSt.executeQuery();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return resultSet;
  }

  public ResultSet getDisciplines(int idDiscipline) {
    ResultSet resultSet = null;

    String select = "SELECT * FROM discipline WHERE iddiscipline = ?";

    try {
      PreparedStatement prSt = getDbConnection().prepareStatement(select);
      prSt.setInt(1, idDiscipline);
      resultSet = prSt.executeQuery();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return resultSet;
  }

  public ResultSet getGrades() {
    ResultSet resultSet = null;

    String select = "SELECT grade.idgrade, grade.iddiscipline, discipline.title, discipline.grade_type, grade.teacher, grade.date, grade.semester, exam_grade.value, students.iduser FROM grade" +
      " JOIN discipline ON grade.iddiscipline = discipline.iddiscipline" +
      " JOIN students ON grade.iduser = students.idstudent" +
      " JOIN exam_grade ON grade.idgrade = exam_grade.idgrade;";

    try {
      PreparedStatement prSt = getDbConnection().prepareStatement(select);
//      prSt.setInt(1, 2);
      resultSet = prSt.executeQuery();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return resultSet;
  }

  public ResultSet getExams() {
    ResultSet resultSet = null;

    String select = "SELECT grade.idgrade, grade.iddiscipline, discipline.title, discipline.grade_type, grade.teacher, grade.date, grade.semester, exam_grade.value, students.iduser FROM grade" +
      " JOIN discipline ON grade.iddiscipline = discipline.iddiscipline" +
      " JOIN students ON grade.iduser = students.idstudent" +
      " JOIN exam_grade ON grade.idgrade = exam_grade.idgrade" +
      " WHERE discipline.grade_type = 'Экзамен';";

    try {
      PreparedStatement prSt = getDbConnection().prepareStatement(select);
//      prSt.setInt(1, 2);
      resultSet = prSt.executeQuery();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return resultSet;
  }

  public ResultSet getPasses() {
    ResultSet resultSet = null;

    String select = "SELECT grade.idgrade, grade.iddiscipline, discipline.title, discipline.grade_type, grade.teacher, grade.date, grade.semester, exam_grade.value, students.iduser FROM grade" +
      " JOIN discipline ON grade.iddiscipline = discipline.iddiscipline" +
      " JOIN students ON grade.iduser = students.idstudent" +
      " JOIN exam_grade ON grade.idgrade = exam_grade.idgrade" +
      " WHERE discipline.grade_type = 'Зачёт';";

    try {
      PreparedStatement prSt = getDbConnection().prepareStatement(select);
//      prSt.setInt(1, 2);
      resultSet = prSt.executeQuery();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return resultSet;
  }

  public ResultSet addGrade(Grade grade) {
    ResultSet resultSet = null;

    String insert = "INSERT INTO grade (idgrade, teacher, iddiscipline, date, semester, iduser) VALUES (null, ?, ?, ?, ?, ?);";
    String select = "SELECT * FROM grade ORDER BY idgrade DESC LIMIT 1;";

    try {
      PreparedStatement prSt = getDbConnection().prepareStatement(insert);
      prSt.setString(1, grade.getTeacher());
      prSt.setInt(2, grade.getDiscipline().getIdDiscipline());
      prSt.setString(3, grade.getDate());
      prSt.setInt(4, grade.getSemester());
      prSt.setInt(5, grade.getIdUser());
      prSt.executeUpdate();
//      System.out.println("prSt good");
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    try {
      PreparedStatement prSt = getDbConnection().prepareStatement(select);
      resultSet = prSt.executeQuery();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return resultSet;
  }

  public void addExam(int idGrade, String value) {
    ResultSet resultSet = null;

    String insert = "INSERT INTO exam_grade (id, value, idgrade) VALUES (null, ?, ?);";

    try {
      PreparedStatement prSt = getDbConnection().prepareStatement(insert);
      prSt.setString(1, value);
      prSt.setInt(2, idGrade);
      prSt.executeUpdate();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return;
  }

  public void addPass(int idGrade, String value) {
    ResultSet resultSet = null;

    String insert = "INSERT INTO pass_grade (id, value, idgrade) VALUES (null, ?, ?);";

    try {
      PreparedStatement prSt = getDbConnection().prepareStatement(insert);
      prSt.setString(1, value);
      prSt.setInt(2, idGrade);
      prSt.executeUpdate();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return;
  }

  public void updateGrade(int idGrade, String value) {
    ResultSet resultSet = null;

    String insert = "UPDATE exam_grade SET value = ? WHERE idgrade = ?;";

    try {
      PreparedStatement prSt = getDbConnection().prepareStatement(insert);
      prSt.setString(1, value);
      prSt.setInt(2, idGrade);
      prSt.executeUpdate();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return;
  }

  public void deleteGrade(int idGrade) {
    ResultSet resultSet = null;

    String select1 = "SELECT * FROM exam_grade WHERE idgrade = ?";
    try {
      PreparedStatement prSt = getDbConnection().prepareStatement(select1);
      prSt.setInt(1, idGrade);
      resultSet = prSt.executeQuery();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    boolean isExam = false;
    try {
      while (resultSet.next()) {
        isExam = resultSet.getBoolean("idgrade");
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    String select2 = "SELECT * FROM pass_grade WHERE idgrade = ?";
    try {
      PreparedStatement prSt = getDbConnection().prepareStatement(select2);
      prSt.setInt(1, idGrade);
      resultSet = prSt.executeQuery();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    boolean isPass = false;
    try {
      while (resultSet.next()) {
        isPass = resultSet.getBoolean("idgrade");
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    String delete = "";
    if (isExam) {
      delete = "DELETE FROM exam_grade WHERE idgrade = ?;";
      try {
        PreparedStatement prSt = getDbConnection().prepareStatement(delete);
        prSt.setInt(1, idGrade);
        prSt.executeUpdate();
      } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
      }
    } else if (isPass) {
      delete = "DELETE FROM pass_grade WHERE idgrade = ?;";
      try {
        PreparedStatement prSt = getDbConnection().prepareStatement(delete);
        prSt.setInt(1, idGrade);
        prSt.executeUpdate();
      } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
      }
    }

    delete = "DELETE FROM grade WHERE idgrade = ?;";

    try {
      PreparedStatement prSt = getDbConnection().prepareStatement(delete);
      prSt.setInt(1, idGrade);
      prSt.executeUpdate();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return;
  }
}
