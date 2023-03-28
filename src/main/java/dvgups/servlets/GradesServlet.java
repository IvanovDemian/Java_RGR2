package dvgups.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dvgups.controllers.DisciplineController;
import dvgups.controllers.GradeController;
import dvgups.controllers.UserController;
import dvgups.models.Discipline;
import dvgups.models.Grade;
import dvgups.models.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/grades/*"})
  public class GradesServlet extends HttpServlet {
  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    resp.getWriter().write("Method service enter\n");
    super.service(req, resp);
//    resp.getWriter().write("Method service exit\n");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    GradeController gradeController = new GradeController();

    List<Grade> grades = new ArrayList<>();

    String pathInfo = req.getPathInfo();
    String gradeType = null;
    if (pathInfo != null && !pathInfo.equals("/")) {
      gradeType = pathInfo.substring(1);
      grades = gradeController.getGrades(gradeType);
    } else {
      grades = gradeController.getGrades();
    }

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json = gson.toJson(grades);

    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");
    resp.getWriter().write(json);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String idDiscipline = req.getParameter("idDiscipline");
    String title = req.getParameter("title");
    String gradeType = req.getParameter("gradeType");
    String teacher = req.getParameter("teacher");
    String date = req.getParameter("date");
    String value = req.getParameter("value");
    String semester = req.getParameter("semester");
    String idUser = req.getParameter("iduser");

    GradeController gradeController = new GradeController();

    if (gradeType.equals("Экзамен")) {
      gradeController.addExam(new Grade(1, new Discipline(Integer.parseInt(idDiscipline), title, gradeType), teacher, date, Integer.parseInt(semester), value, Integer.parseInt(idUser)));
    } else if (gradeType.equals("Зачёт")) {
      gradeController.addPass(new Grade(1, new Discipline(Integer.parseInt(idDiscipline), title, gradeType), teacher, date, Integer.parseInt(semester), value, Integer.parseInt(idUser)));
    } else {
      System.out.println("gadeType is invalid");
    }

    resp.setContentType("text/html");
    PrintWriter out = resp.getWriter();
    out.println("idDiscipline " + idDiscipline);
    out.println("value " + value);
//    out.println("pName " + pName);
//    out.println("email " + email);
//    out.println("password " + password);
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    GradeController gradeController = new GradeController();

    String pathInfo = req.getPathInfo();
    String gradeId = null;
    if (pathInfo != null && !pathInfo.equals("/")) {
      gradeId = pathInfo.substring(1);
      String value = req.getParameter("value");
      gradeController.updateGrade(Integer.parseInt(gradeId), value);
    }
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    GradeController gradeController = new GradeController();

    String pathInfo = req.getPathInfo();
    String gradeId = null;
    if (pathInfo != null && !pathInfo.equals("/")) {
      gradeId = pathInfo.substring(1);
      gradeController.deleteGrade(Integer.parseInt(gradeId));
    }
  }

  @Override
  public void destroy() {
    log("Method destroy");
  }
}
