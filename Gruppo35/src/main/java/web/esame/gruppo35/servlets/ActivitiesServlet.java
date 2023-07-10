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

    protected void processData(HttpServletRequest request, HttpServletResponse response) throws NullPointerException, IOException, ServletException{
        ViewsManager.setTotalViews(ViewsManager.getTotalViews() + 1);
        ViewsManager.setActivitiesViews(ViewsManager.getActivitiesViews() + 1);

        // check if there is a parameter for activity details
        if (request.getParameter("id") == null) {
            activityID = -1;
        }else {
            activityID = Integer.parseInt(request.getParameter("id"));
        }

        // check if parameter for activity details is valid
        if (activityID <= numberOfActivities && activityID >= 0) {
            request.setAttribute("activityBean", activityBeanList.get(activityID-1));
            dispatcher = request.getRequestDispatcher("/activityDetail.jsp");
        } else { //either null or activityID > numberOfActivities
            dispatcher = request.getRequestDispatcher("/activities.jsp");
        }
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            processData(request,response);
        } catch (ServletException | NullPointerException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            processData(request,response);
        } catch (ServletException | NullPointerException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    public void destroy() {}
}
