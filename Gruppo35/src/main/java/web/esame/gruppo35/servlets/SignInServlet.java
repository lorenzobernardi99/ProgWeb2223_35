package web.esame.gruppo35.servlets;

import web.esame.gruppo35.helperClasses.ViewsManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {

    protected void processData(HttpServletRequest request, HttpServletResponse response) throws ServletException, NullPointerException, IOException {
        ViewsManager.setTotalViews(ViewsManager.getTotalViews() + 1);
        ViewsManager.setSignInViews(ViewsManager.getSignInViews() + 1);

        // simply provide the jsp page
        request.getRequestDispatcher("/signIn.jsp").forward(request, response);
    }

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws IOException {
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
