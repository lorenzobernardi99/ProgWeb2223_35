package web.esame.gruppo35.servlets;

import web.esame.gruppo35.helperClasses.DatabaseSessionManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        // Counter for page views
        Map<String, Integer> views = (Map<String, Integer>) context.getAttribute("views");
        int newTotal = views.get("total") + 1;
        int newLogin = views.get("login") + 1;
        views.replace("total", newTotal);
        views.replace("login", newLogin);
        context.setAttribute("views", views);

        if (request.getAttribute("message") == null)
            request.setAttribute("message","");
        RequestDispatcher rd=request.getRequestDispatcher("Login.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
