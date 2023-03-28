package dvgups.models;

public class Discipline {
  private int idDiscipline;
  private String title;
  private String gradeType;

  public Discipline(int idDiscipline, String title, String gradeType) {
    this.idDiscipline = idDiscipline;
    this.title = title;
    this.gradeType = gradeType;
  }

  public int getIdDiscipline() {
    return idDiscipline;
  }

  public void setIdDiscipline(int idDiscipline) {
    this.idDiscipline = idDiscipline;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getGradeType() {
    return gradeType;
  }

  public void setGradeType(String gradeType) {
    this.gradeType = gradeType;
  }
}

