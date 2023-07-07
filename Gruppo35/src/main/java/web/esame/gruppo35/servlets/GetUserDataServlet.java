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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Connection connection;
        ResultSet result;
        UserBean user = null;
        try{
            connection = DatabaseSessionManager.getConnection(session);
            String username= (String) session.getAttribute("username");
            String query="SELECT * FROM USERS WHERE USERNAME = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            result = statement.executeQuery();
            while(result.next()){
                user = new UserBean(
                        result.getString(2),
                        result.getString(3),
                        result.getDate(4),
                        result.getString(5),
                        result.getString(6),
                        UserRole.values()[result.getInt(7)],
                        result.getString(8),
                        result.getString(9)
                );
            }
        }catch (ClassNotFoundException | SQLException | NullPointerException ex){
            System.out.println("Errore :" + ex);
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try(PrintWriter out = response.getWriter()){
            Gson gson = new Gson();
            String object = gson.toJson(user);
            out.println(object);
            out.flush();
        }catch(IOException ex){
            System.out.println("Errore :" + ex);
        }

    }
}
