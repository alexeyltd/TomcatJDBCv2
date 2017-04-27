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


@WebServlet(name = "DeleteServlet", urlPatterns = {"/admin/delete"})
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserService userService = UserServiceImpl.getInstance();


//        request.getAttribute("name");

//        String idString = request.getParameter("id");
//        long id = Long.parseLong(idString);
        long id = Long.parseLong(request.getParameter("idDelete"));

        try {
            userService.deleteUser(id);

            List<User> x = userService.getUsers();
            request.setAttribute("list", x);

            request.getRequestDispatcher("/admin.jsp").forward(request, response);
        } catch (UserDaoJDBCException | UserDaoHibException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/admin.jsp").forward(request, response);
    }
}
