package dvgups.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dvgups.controllers.UserController;
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
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(urlPatterns = {"/users/*"})
public class UsersServlet extends HttpServlet {
  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    log("Method init");
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    resp.getWriter().write("Method service enter\n");
    super.service(req, resp);
//    resp.getWriter().write("Method service exit\n");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    UserController userController = new UserController();

    List<User> users;

    String pathInfo = req.getPathInfo();
    String userId = null;
    if (pathInfo != null && !pathInfo.equals("/")) {
      userId = pathInfo.substring(1);
      users = userController.getUsers(Integer.parseInt(userId));
    } else {
      users = userController.getUsers();
    }

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json = gson.toJson(users);

    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");
    resp.getWriter().write(json);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String fName = req.getParameter("fName");
    String lName = req.getParameter("lName");
    String pName = req.getParameter("pName");
    String email = req.getParameter("pName");
    String password = req.getParameter("password");
    String role = req.getParameter("role");

    UserController userController = new UserController();

    if (role.equals("student")) {
      userController.addStudent(new User(1, fName, lName, pName, email, password, role));
    } else if (role.equals("teacher")) {
      userController.addTeacher(new User(1, fName, lName, pName, email, password, role));
    } else {
      System.out.println("role is invalid");
    }

    resp.setContentType("text/html");
    PrintWriter out = resp.getWriter();
    out.println("fName " + fName);
    out.println("lName " + lName);
    out.println("pName " + pName);
    out.println("email " + email);
    out.println("password " + password);
  }

  @Override
  public void destroy() {
    log("Method destroy");
  }
}
