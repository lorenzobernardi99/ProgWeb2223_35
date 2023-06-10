package web.esame.gruppo35.servlets;

import web.esame.gruppo35.helperClasses.ActivityBeanList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

//@WebServlet(name = "activitiesServlet", value = "/activities-servlet")
public class ActivitiesServlet extends HttpServlet {
    private int numberOfActivities;
    private ActivityBeanList activityBeanList;
    ServletContext servletContext;
    RequestDispatcher dispatcher;
    int activityID;

    public void init() {
        servletContext = getServletContext();
        activityBeanList = (ActivityBeanList) getServletContext().getAttribute("activityBeanList");
        numberOfActivities = activityBeanList.size();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Counter for page views
        Map<String, Integer> views = (Map<String, Integer>) servletContext.getAttribute("views");
        int newTotal = views.get("total") + 1;
        int newActivities = views.get("activities") + 1;
        views.replace("total", newTotal);
        views.replace("activities", newActivities);
        servletContext.setAttribute("views", views);

        response.setContentType("text/html");

        // check if there is a parameter for activity details
        if (request.getParameter("id") == null) {
            activityID = -1;
        }else {
            activityID = Integer.parseInt(request.getParameter("id"));
        }

        // check if parameter for activity details is valid
        if (activityID <= numberOfActivities && activityID >= 0) {
            request.setAttribute("activityBean", activityBeanList.get(activityID-1));
            dispatcher = request.getRequestDispatcher("/activities/ActivityDetail.jsp");
        } else { //either null or activityID > numberOfActivities
            dispatcher = request.getRequestDispatcher("/activities/Activities.jsp");
        }
        dispatcher.forward(request, response);
    }

    public void destroy() {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
