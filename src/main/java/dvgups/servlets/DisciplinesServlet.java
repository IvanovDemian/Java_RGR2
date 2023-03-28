package dvgups.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dvgups.controllers.DisciplineController;
import dvgups.controllers.UserController;
import dvgups.models.Discipline;
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

@WebServlet(urlPatterns = {"/disciplines/*"})
public class DisciplinesServlet extends HttpServlet {
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
    DisciplineController disciplineController = new DisciplineController();

    List<Discipline> disciplines = new ArrayList<>();

    String pathInfo = req.getPathInfo();
    String disciplineId = null;
    if (pathInfo != null && !pathInfo.equals("/")) {
      disciplineId = pathInfo.substring(1);
      disciplines = disciplineController.getDisciplines(Integer.parseInt(disciplineId));
    } else {
      disciplines = disciplineController.getDisciplines();
    }

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json = gson.toJson(disciplines);

    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");
    resp.getWriter().write(json);
  }

  @Override
  public void destroy() {
    log("Method destroy");
  }
}