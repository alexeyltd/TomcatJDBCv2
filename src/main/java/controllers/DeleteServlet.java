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
@WebServlet(name = "DeleteServlet", urlPatterns = {"/delete"})
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserService userService = UserServiceImpl.getInstance();


//        request.getAttribute("name");

//        String idString = request.getParameter("id");
//        long id = Long.parseLong(idString);
        long id = Long.parseLong(request.getParameter("idDelete"));

        try {
            userService.getUserDAO().deleteUser(id);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (UserDaoJDBCException e) {
            e.printStackTrace();
        } catch (UserDaoHibException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
