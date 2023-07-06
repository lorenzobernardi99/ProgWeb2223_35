package web.esame.gruppo35.servlets;

import web.esame.gruppo35.helperClasses.ActivityBeanList;
import web.esame.gruppo35.helperClasses.ViewsManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ActivitiesServlet extends HttpServlet {
    private int numberOfActivities;
    private ActivityBeanList activityBeanList;
    RequestDispatcher dispatcher;
    int activityID;

    public void init() {
        activityBeanList = (ActivityBeanList) getServletContext().getAttribute("activityBeanList");
        numberOfActivities = activityBeanList.size();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        ViewsManager.setTotalViews(ViewsManager.getTotalViews() + 1);
        ViewsManager.setActivitiesViews(ViewsManager.getActivitiesViews() + 1);


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
            dispatcher = request.getRequestDispatcher("/activities/activityDetail.jsp");
        } else { //either null or activityID > numberOfActivities
            dispatcher = request.getRequestDispatcher("/activities/activities.jsp");
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
