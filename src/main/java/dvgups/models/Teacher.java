package dvgups.models;

public class Teacher extends User {
  public Teacher (User user) {
    super(user.getIduser(), user.getfName(), user.getlName(), user.getpName(), user.getEmail(), "teacher");
    user.setRole("teacher");
  }
}