package web.esame.gruppo35.servlets;

import web.esame.gruppo35.helperClasses.DatabaseSessionManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

public class DeleteAccountServlet extends HttpServlet {

    protected void processData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, NullPointerException{
        HttpSession session = request.getSession();
        Connection connection = DatabaseSessionManager.getConnection(session);
        String username = (String) session.getAttribute("username");

        String query = "DELETE FROM USERS WHERE USERNAME=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1,username);

        statement.executeUpdate();

        request.getRequestDispatcher("/Logout").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            processData(request,response);
        } catch (ServletException | NullPointerException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            processData(request,response);
        } catch (ServletException | NullPointerException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
