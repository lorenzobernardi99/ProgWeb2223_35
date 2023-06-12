package web.esame.gruppo35.servlets;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import web.esame.gruppo35.beans.UserBean;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class GetMotivationsServlet extends HttpServlet {

    protected void retrieveMotivations(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletContext context = getServletContext();
        // retrieve application view's counter
        List<String> motivations = (List<String>) context.getAttribute("motivations");

        // Preparing and sending json response
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        JsonArray array = new JsonArray();
        for(String motivation : motivations) {
            Gson gson = new Gson();
            array.add(gson.toJson(motivation));
        }
        out.println(array);
        out.flush();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            retrieveMotivations(request,response);
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            retrieveMotivations(request,response);
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }    }
}
