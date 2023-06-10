package web.esame.gruppo35.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "ResetViewsServlet", value = "/ResetViewsServlet")
public class ResetViewsServlet extends HttpServlet {
    // method to reset a specific or all application's view counters
    protected void resetViews(HttpServletRequest request, HttpServletResponse response){
        String field = request.getParameter("field");
        ServletContext context = getServletContext();
        Map<String, Integer> views = (Map<String, Integer>) context.getAttribute("views");

        try {
            if(views.get(field).equals(views.get("total"))){
                views.replaceAll((k, v) -> 0);
            } else {
                int newTotal = views.get("total") - views.get(field);
                views.put(field, 0);
                views.put("total", newTotal);
            }
        }
        catch (NullPointerException | ClassCastException | UnsupportedOperationException ex){
            System.out.println(ex);
            //TODO:response.sendRedirect("error.html");
        }

        try (PrintWriter out = response.getWriter()) {
            out.write(field + "reset successfully");
        }
        catch (IOException ex) {
            System.out.println(ex);
            //TODO:response.sendRedirect("error.html");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        resetViews(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        resetViews(request, response);
    }
}
