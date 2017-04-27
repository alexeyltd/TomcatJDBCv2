package controllers;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by alexeypavlenko on 27/04/2017.
 */
@WebFilter("/admin/*")
public class BlockFilter implements Filter {

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletResponse response = (HttpServletResponse) resp;


        if ("admin".equals(LoginFilter.role)) {
            chain.doFilter(req, resp);
        }
        else {
            response.sendRedirect("/");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
