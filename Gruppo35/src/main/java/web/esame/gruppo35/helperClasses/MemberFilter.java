package web.esame.gruppo35.helperClasses;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "MemberFilter")
public class MemberFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        HttpSession session = servletRequest.getSession();
        UserRole role = (UserRole) session.getAttribute("role");
        if(role != null){
            if (role == UserRole.SIMPATIZZANTE || role == UserRole.ADERENTE) {
                chain.doFilter(request, response);
            } else {
                System.out.println("non autorizzato");
                //TODO:servletResponse.sendRedirect("error.html");
            }
        } else {
            servletResponse.sendRedirect("Login");
        }
    }

    public void destroy() {
    }
}