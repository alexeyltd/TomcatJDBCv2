package controllers;

import dao.implementations.Exceptions.UserDaoHibException;
import dao.implementations.Exceptions.UserDaoJDBCException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import model.User;
import services.abstracts.userService.UserService;
import services.implementations.userServiceImpl.UserServiceImpl;

@WebServlet(name = "UpdateServlet", urlPatterns = {"/admin/update"})
public class UpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserService userService = UserServiceImpl.getInstance();

        String idString = request.getParameter("id");
        long id = Long.parseLong(idString);

        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        try {
            userService.updateUser(id, name, login, password, role);

            List<User> x = userService.getUsers();
            request.setAttribute("list", x);

            request.getRequestDispatcher("/admin.jsp").forward(request, response);
        } catch (UserDaoJDBCException | UserDaoHibException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/update.jsp").forward(request, response);

    }
}
