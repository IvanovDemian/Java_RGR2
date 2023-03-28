package dvgups.models;

public class Student extends User {
  public Student (User user) {
    super(user.getIduser(), user.getfName(), user.getlName(), user.getpName(), user.getEmail(), "student");
    user.setRole("student");
  }

}

