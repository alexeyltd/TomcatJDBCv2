package controllers;

import dao.impl.UserDaoHibException;
import dao.impl.UserDaoJDBCException;
import services.userService.UserService;
import services.userServiceImpl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by alexeypavlenko on 22/04/2017.
 */
@WebServlet(name = "CreateServlet", urlPatterns = {"/create"})
public class CreateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserService userService = UserServiceImpl.getInstance();

        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String password = request.getParameter("password");


        try {
            userService.getUserDAO().addUser(name, login, password);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (UserDaoJDBCException e) {
            e.printStackTrace();
        } catch (UserDaoHibException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/create.jsp").forward(request, response);
    }
}
