package web.esame.gruppo35.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {

    HttpSession session = null;

    protected void processData(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // log out through session invalidating
        session = request.getSession();
        session.invalidate();
        response.sendRedirect("Homepage");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            processData(request,response);
        } catch (NullPointerException | IOException | ServletException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            processData(request,response);
        } catch (NullPointerException | IOException | ServletException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
