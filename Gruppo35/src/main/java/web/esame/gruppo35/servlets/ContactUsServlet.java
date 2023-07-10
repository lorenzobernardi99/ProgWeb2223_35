package web.esame.gruppo35.servlets;

import web.esame.gruppo35.helperClasses.ViewsManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ContactUsServlet extends HttpServlet {

    protected void processData(HttpServletRequest request, HttpServletResponse response) throws NullPointerException, IOException, ServletException{
        ViewsManager.setTotalViews(ViewsManager.getTotalViews() + 1);
        ViewsManager.setContactUsViews(ViewsManager.getContactUsViews() + 1);

        Object message = request.getAttribute("message");
        if (message == null){
            request.setAttribute("message", "");
        }

        RequestDispatcher rd=request.getRequestDispatcher("/contactUs.jsp");
        rd.forward(request, response);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
}
