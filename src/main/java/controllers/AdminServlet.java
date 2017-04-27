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

@WebServlet(name = "AdminServlet", urlPatterns = {"/admin"})
public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserService userService = UserServiceImpl.getInstance();

        List<User> x = null;
        try {
            x = userService.getUsers();
        } catch (UserDaoHibException | UserDaoJDBCException e) {
            e.printStackTrace();
        }

        request.setAttribute("list", x);

        request.getRequestDispatcher("/admin.jsp").forward(request, response);
    }
}
