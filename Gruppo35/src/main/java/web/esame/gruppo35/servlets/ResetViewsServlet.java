package web.esame.gruppo35.servlets;

import web.esame.gruppo35.helperClasses.ViewsManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ResetViewsServlet", value = "/ResetViewsServlet")
public class ResetViewsServlet extends HttpServlet {
    // method to reset a specific or all application's view counters
    protected void resetViews(HttpServletRequest request, HttpServletResponse response) throws NullPointerException, ClassCastException, UnsupportedOperationException, IOException{
        String field = request.getParameter("field");

        switch (field) {
            case "total" -> {
                ViewsManager.setTotalViews(0);
                ViewsManager.setHomepageViews(0);
                ViewsManager.setWhoWeAreViews(0);
                ViewsManager.setActivitiesViews(0);
                ViewsManager.setContactUsViews(0);
                ViewsManager.setSignInViews(0);
                ViewsManager.setLoginViews(0);
            }
            case "homepage" -> {
                ViewsManager.setTotalViews(ViewsManager.getTotalViews() - ViewsManager.getHomepageViews());
                ViewsManager.setHomepageViews(0);
            }
            case "whoWeAre" -> {
                ViewsManager.setTotalViews(ViewsManager.getTotalViews() - ViewsManager.getWhoWeAreViews());
                ViewsManager.setWhoWeAreViews(0);
            }
            case "activities" -> {
                ViewsManager.setTotalViews(ViewsManager.getTotalViews() - ViewsManager.getActivitiesViews());
                ViewsManager.setActivitiesViews(0);
            }
            case "contactUs" -> {
                ViewsManager.setTotalViews(ViewsManager.getTotalViews() - ViewsManager.getContactUsViews());
                ViewsManager.setContactUsViews(0);
            }
            case "signIn" -> {
                ViewsManager.setTotalViews(ViewsManager.getTotalViews() - ViewsManager.getSignInViews());
                ViewsManager.setSignInViews(0);
            }
            case "login" -> {
                ViewsManager.setTotalViews(ViewsManager.getTotalViews() - ViewsManager.getLoginViews());
                ViewsManager.setLoginViews(0);
            }
        }

        PrintWriter out = response.getWriter();
        out.write(field + "reset successfully");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            resetViews(request, response);
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            resetViews(request, response);
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
