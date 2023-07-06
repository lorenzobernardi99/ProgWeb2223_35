package web.esame.gruppo35.servlets;

import web.esame.gruppo35.helperClasses.UserRole;
import web.esame.gruppo35.helperClasses.ViewsManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    protected void processData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserRole role = (UserRole) session.getAttribute("role");

        ViewsManager.setTotalViews(ViewsManager.getTotalViews() + 1);
        ViewsManager.setLoginViews(ViewsManager.getLoginViews() + 1);

        if(role!=null){
            //already logged in
            switch (role) {
                // TODO: da modificare con le diverse pagine dei diversi profili
                case AMMINISTRATORE -> response.sendRedirect("Admin");
                case ADERENTE -> response.sendRedirect("ContactUs");
                case SIMPATIZZANTE -> response.sendRedirect("SignIn");
            }
        } else {
            if (request.getAttribute("message") == null)
                request.setAttribute("message", "");
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            processData(request,response);
        } catch (ServletException | NullPointerException | IOException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            processData(request,response);
        } catch (ServletException | NullPointerException | IOException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
