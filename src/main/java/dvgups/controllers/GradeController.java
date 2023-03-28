package dvgups.controllers;

import dvgups.DBHandler;
import dvgups.models.Discipline;
import dvgups.models.Grade;
import dvgups.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GradeController {

  List<Grade> grades;

  public GradeController() {
    this.grades = new ArrayList<>();
  }

  public List<Grade> getGrades() {
    DBHandler dbHandler = new DBHandler();

    ResultSet resultSet = dbHandler.getGrades();

    try {
      while (resultSet.next()) {
        int idGrade = resultSet.getInt("idgrade");
        int idDiscipline = resultSet.getInt("iddiscipline");
        String title = resultSet.getString("title");
        String gradeType = resultSet.getString("grade_type");
        String teacher = resultSet.getString("teacher");
        String date = resultSet.getString("date");
        int semester = resultSet.getInt("semester");
        String value = resultSet.getString("value");
        int idUser = resultSet.getInt("iduser");


        Grade grade = new Grade(idGrade, new Discipline(idDiscipline, title, gradeType), teacher, date, semester, value, idUser);
        grades.add(grade);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return grades;
  }

  public List<Grade> getGrades(String gradeType) {
    DBHandler dbHandler = new DBHandler();

    ResultSet resultSet = null;
    if (gradeType.equals("exams")) {
      resultSet = dbHandler.getExams();
    } else if (gradeType.equals("passes")) {
      resultSet = dbHandler.getPasses();
    }

    try {
      if (resultSet != null) {
        while (resultSet.next()) {
          int idGrade = resultSet.getInt("idgrade");
          int idDiscipline = resultSet.getInt("iddiscipline");
          String title = resultSet.getString("title");
          String grade_Type = resultSet.getString("grade_type");
          String teacher = resultSet.getString("teacher");
          String date = resultSet.getString("date");
          int semester = resultSet.getInt("semester");
          String value = resultSet.getString("value");
          int idUser = resultSet.getInt("iduser");


          Grade grade = new Grade(idGrade, new Discipline(idDiscipline, title, grade_Type), teacher, date, semester, value, idUser);
          grades.add(grade);
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return grades;
  }

  public int addGrade(Grade grade) {
    DBHandler dbHandler = new DBHandler();

    ResultSet resultSet = dbHandler.addGrade(grade);

    int idGrade = 0;
    try {
      while (resultSet.next()) {
        idGrade = resultSet.getInt("idgrade");
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return idGrade;
  }

  public void addExam(Grade grade) {
    DBHandler dbHandler = new DBHandler();

    int idGrade = addGrade(grade);
    dbHandler.addExam(idGrade, grade.getValue());
  }

  public void addPass(Grade grade) {
    DBHandler dbHandler = new DBHandler();

    int idGrade = addGrade(grade);
    dbHandler.addPass(idGrade, grade.getValue());
  }

  public void updateGrade(int idGrade, String value) {
    DBHandler dbHandler = new DBHandler();

    dbHandler.updateGrade(idGrade, value);
  }

  public void deleteGrade(int idGrade) {
    DBHandler dbHandler = new DBHandler();

    dbHandler.deleteGrade(idGrade);
  }
}
