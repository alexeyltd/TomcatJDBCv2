package controllers;

import model.User;
import services.abstracts.userService.UserService;
import services.implementations.userServiceImpl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by alexeypavlenko on 26/04/2017.
 */
@WebFilter("/LoginFilter")
public class LoginFilter implements Filter {

    public static String role = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        UserService userService = UserServiceImpl.getInstance();

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = userService.getOneUser(login, password);

        if ("user".equals(user.getRole())) {
//            filterChain.doFilter(request, response);
            role = "user";
            response.sendRedirect("/user");
        }
        else if ("admin".equals(user.getRole())) {
//            filterChain.doFilter(request, response);
            role = "admin";
            response.sendRedirect("/admin");
        }
        else {
            response.sendRedirect("/");
        }

    }

    @Override
    public void destroy() {

    }
}
