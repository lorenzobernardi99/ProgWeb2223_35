package web.esame.gruppo35.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberServlet extends HttpServlet {
    protected void processData(HttpServletRequest request, HttpServletResponse response) throws ServletException, NullPointerException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/memberPage.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            processData(request,response);
        } catch (ServletException | NullPointerException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            processData(request,response);
        } catch (ServletException | NullPointerException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
