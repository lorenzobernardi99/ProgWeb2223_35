package web.esame.gruppo35.servlets;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import web.esame.gruppo35.helperClasses.ViewsManager;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

public class GetViewsServlet extends HttpServlet {
    // method to retrieve application's view counters and send it as JSON in the response
    protected void retrieveViews(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // retrieve application view's counter
        Map<String, Integer> views = new LinkedHashMap<>();
        views.put("total", ViewsManager.getTotalViews());
        views.put("homepage", ViewsManager.getHomepageViews());
        views.put("whoWeAre", ViewsManager.getWhoWeAreViews());
        views.put("activities", ViewsManager.getActivitiesViews());
        views.put("contactUs", ViewsManager.getContactUsViews());
        views.put("signIn", ViewsManager.getSignInViews());
        views.put("login", ViewsManager.getLoginViews());

        // Preparing and sending json response
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        JsonArray array = new JsonArray();
        Gson gson = new Gson();
        array.add(gson.toJson(views));
        out.println(array);
        out.flush();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            retrieveViews(request,response);
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            retrieveViews(request,response);
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
