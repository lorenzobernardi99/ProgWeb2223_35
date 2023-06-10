package web.esame.gruppo35.servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class SignInServlet extends HttpServlet {

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        // Counter for page views
        Map<String, Integer> views = (Map<String, Integer>) context.getAttribute("views");
        int newTotal = views.get("total") + 1;
        int newSignIn = views.get("signIn") + 1;
        views.replace("total", newTotal);
        views.replace("signIn", newSignIn);
        context.setAttribute("views", views);

        // simply provide the jsp page
        request.getRequestDispatcher("/SignIn.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
