package web.esame.gruppo35.servlets;

import java.io.*;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
//@WebServlet(name = "WhoWeAre", value = "/WhoWeAre")

public class WhoWeAreServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        processData(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processData(request,response);
    }

    protected void processData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        // Counter for page views
        Map<String, Integer> views = (Map<String, Integer>) context.getAttribute("views");
        int newTotal = views.get("total") + 1;
        int newWhoWeAre = views.get("whoWeAre") + 1;
        views.replace("total", newTotal);
        views.replace("whoWeAre", newWhoWeAre);
        context.setAttribute("views", views);

        RequestDispatcher dispatcher= request.getRequestDispatcher("ChiSiamo.jsp");
        dispatcher.forward(request, response);
    }
}
