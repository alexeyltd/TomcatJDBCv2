package controllers;

import dao.implementations.Exceptions.UserDaoHibException;
import dao.implementations.Exceptions.UserDaoJDBCException;
import model.User;
import services.abstracts.userService.UserService;
import services.implementations.userServiceImpl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CreateServlet", urlPatterns = {"/admin/create"})
public class CreateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserService userService = UserServiceImpl.getInstance();

        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String role = request.getParameter("role");


        try {
            userService.addUser(name, login, password, role);

            List<User> x = userService.getUsers();
            request.setAttribute("list", x);

            request.getRequestDispatcher("/admin.jsp").forward(request, response);
        } catch (UserDaoJDBCException | UserDaoHibException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/create.jsp").forward(request, response);
    }
}
