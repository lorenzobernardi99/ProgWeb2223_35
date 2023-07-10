package web.esame.gruppo35.servlets;

import web.esame.gruppo35.helperClasses.ViewsManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomePageServlet extends HttpServlet {

    protected void processData(HttpServletRequest request, HttpServletResponse response) throws ServletException, NullPointerException, IOException {

        ViewsManager.setTotalViews(ViewsManager.getTotalViews() + 1);
        ViewsManager.setHomepageViews(ViewsManager.getHomepageViews() + 1);

        RequestDispatcher dispatcher= request.getRequestDispatcher("/homepage.jsp");
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
