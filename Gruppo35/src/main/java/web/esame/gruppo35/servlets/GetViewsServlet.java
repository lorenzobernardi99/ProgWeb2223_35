package web.esame.gruppo35.servlets;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "GetViewsServlet", value = "/GetViewsServlet")
public class GetViewsServlet extends HttpServlet {
    // method to retrieve application's view counters and send it as JSON in the response
    protected void retrieveViews(HttpServletRequest request, HttpServletResponse response){
        ServletContext context = getServletContext();
        // retrieve application view's counter
        Map<String, Integer> views = (Map<String, Integer>) context.getAttribute("views");

        // Preparing and sending json response
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter()) {
            JsonArray array = new JsonArray();
            Gson gson = new Gson();
            array.add(gson.toJson(views));
            out.println(array);
            out.flush();
        }
        catch (IOException ex) {
            System.out.println(ex);
            //TODO:response.sendRedirect("error.html");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        retrieveViews(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        retrieveViews(request,response);
    }
}
