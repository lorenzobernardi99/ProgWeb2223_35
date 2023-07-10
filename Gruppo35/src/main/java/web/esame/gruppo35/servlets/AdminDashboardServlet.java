package web.esame.gruppo35.servlets;


import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AdminDashboardServlet extends HttpServlet {

    protected void processData(HttpServletRequest request, HttpServletResponse response) throws NullPointerException, IOException, ServletException{
        RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/adminPage.jsp");
        rd.forward(request, response);
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
