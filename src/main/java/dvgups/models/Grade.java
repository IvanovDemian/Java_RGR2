package dvgups.models;

public class Grade {
  private int idGrade;
  private String teacher;
  private Discipline discipline;
  private int idDiscipline;
  private String date;
  private String gradeType;
  protected String value;
  private int semester;
  private int idUser;

  protected Grade(int idGrade, Discipline discipline, String value) {
    this.idGrade = idGrade;
    this.discipline = discipline;
    this.value = value;
  }

  public Grade(int idGrade, Discipline discipline, String teacher, String date, int semester, String value, int idUser) {
    this.idGrade = idGrade;
    this.discipline = discipline;
    this.teacher = teacher;
    this.date = date;
    this.semester = semester;
    this.value = value;
    this.idUser = idUser;
  }

  public int getIdGrade() {
    return idGrade;
  }

  public void setIdGrade(int idGrade) {
    this.idGrade = idGrade;
  }

  public String getTeacher() {
    return teacher;
  }

  public void setTeacher(String teacher) {
    this.teacher = teacher;
  }

  public Discipline getDiscipline() {
    return discipline;
  }

  public void setDiscipline(Discipline discipline) {
    this.discipline = discipline;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public int getSemester() {
    return semester;
  }

  public void setSemester(int semester) {
    this.semester = semester;
  }

  public int getIdDiscipline() {
    return idDiscipline;
  }

  public void setIdDiscipline(int idDiscipline) {
    this.idDiscipline = idDiscipline;
  }

  public String getGradeType() {
    return gradeType;
  }

  public void setGradeType(String gradeType) {
    this.gradeType = gradeType;
  }

  public int getIdUser() {
    return idUser;
  }

  public void setIdUser(int idUser) {
    this.idUser = idUser;
  }
}
