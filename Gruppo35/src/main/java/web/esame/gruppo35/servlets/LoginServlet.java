package web.esame.gruppo35.servlets;

import web.esame.gruppo35.helperClasses.UserRole;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class LoginServlet extends HttpServlet {

    protected void processData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        HttpSession session = request.getSession();
        UserRole role = (UserRole) session.getAttribute("role");

        // Counter for page views
        Map<String, Integer> views = (Map<String, Integer>) context.getAttribute("views");
        int newTotal = views.get("total") + 1;
        int newLogin = views.get("login") + 1;
        views.replace("total", newTotal);
        views.replace("login", newLogin);
        context.setAttribute("views", views);

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
