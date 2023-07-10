package web.esame.gruppo35.servlets;

import com.google.gson.*;
import web.esame.gruppo35.beans.UserBean;
import web.esame.gruppo35.helperClasses.DatabaseSessionManager;
import web.esame.gruppo35.helperClasses.UserRole;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


public class GetUserDataServlet extends HttpServlet {
    protected void processData(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ClassNotFoundException, SQLException, NullPointerException{

        HttpSession session = request.getSession();
        Connection connection;
        ResultSet result;
        UserBean user = null;

        connection = DatabaseSessionManager.getConnection(session);
        String username= (String) session.getAttribute("username");
        String query="SELECT * FROM USERS WHERE USERNAME = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        result = statement.executeQuery();
        while(result.next()){
            user = new UserBean(
                    result.getString("name"),
                    result.getString("surname"),
                    result.getDate("birth_date"),
                    result.getString("email_address"),
                    result.getString("telephone_number"),
                    UserRole.values()[result.getInt("role")],
                    result.getString("username"),
                    result.getString("password")
            );
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        String object = gson.toJson(user);
        out.println(object);
        out.flush();
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
