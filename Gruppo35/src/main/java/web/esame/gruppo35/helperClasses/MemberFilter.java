package web.esame.gruppo35.helperClasses;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MemberFilter implements Filter {
    public void init(FilterConfig config){}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException {

        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        HttpSession session = servletRequest.getSession();
        UserRole role = (UserRole) session.getAttribute("role");

        if(role != null){
            if (role == UserRole.SIMPATIZZANTE || role == UserRole.ADERENTE) {
                try {
                    chain.doFilter(request, response);
                } catch (ServletException e) {
                    e.printStackTrace();
                    servletResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
            } else {
                servletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        } else {
            servletResponse.sendRedirect("/Login");
        }
    }

    public void destroy() {}
}
