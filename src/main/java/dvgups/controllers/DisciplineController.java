package dvgups.controllers;

import dvgups.DBHandler;
import dvgups.models.Discipline;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisciplineController {
  List<Discipline> disciplines;

  public DisciplineController() {
    this.disciplines = new ArrayList<>();
  }

  public List<Discipline> getDisciplines() {
    DBHandler dbHandler = new DBHandler();

    ResultSet resultSet = dbHandler.getDisciplines();

    try {
      while (resultSet.next()) {
        int idDiscipline = resultSet.getInt("iddiscipline");
        String title = resultSet.getString("title");
        String gradeType = resultSet.getString("grade_type");

        Discipline user = new Discipline(idDiscipline, title, gradeType);
        disciplines.add(user);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return disciplines;
  }

  public List<Discipline> getDisciplines(int idUser) {
    DBHandler dbHandler = new DBHandler();

    ResultSet resultSet = dbHandler.getDisciplines(idUser);

    try {
      while (resultSet.next()) {
        int idDiscipline = resultSet.getInt("iddiscipline");
        String title = resultSet.getString("title");
        String gradeType = resultSet.getString("grade_type");

        Discipline user = new Discipline(idDiscipline, title, gradeType);
        disciplines.add(user);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return disciplines;
  }
}

