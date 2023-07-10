package web.esame.gruppo35.servlets;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetUsersServlet extends HttpServlet {
    // method to retrieve users from DB, based on the request parameter, sending a JSON as a response
    protected void processData(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ClassNotFoundException, NullPointerException, ServletException, IllegalArgumentException {

        String filter = request.getParameter("filter");
        ArrayList<UserBean> user_list = new ArrayList<>();

        HttpSession session = request.getSession();
        Connection connection;
        String query;
        PreparedStatement stmt;
        ResultSet result;

        // retrieve DB connection
        connection = DatabaseSessionManager.getConnection(session);
        // Making the query
        query = switch (filter) {
            case "users" -> "SELECT * FROM USERS";
            case "members", "supporters" -> "SELECT * FROM USERS WHERE ROLE=?";
            default -> throw new IllegalArgumentException("Invalid filter value");
        };

        stmt = connection.prepareStatement(query);
        if (filter.equals("members")) {
            stmt.setInt(1, UserRole.ADERENTE.ordinal());
        }else if (filter.equals("supporters")) {
            stmt.setInt(1, UserRole.SIMPATIZZANTE.ordinal());
        }
        result = stmt.executeQuery();

        // Getting results and saving it in an array of beans
        while (result.next()) {
            UserBean user = new UserBean(
                result.getString("name"),
                result.getString("surname"),
                result.getDate("birth_date"),
                result.getString("email_address"),
                result.getString("telephone_number"),
                UserRole.values()[result.getInt("role")],
                result.getString("username"),
                result.getString("password")
            );
            user_list.add(user);
        }

        // Preparing and sending json response
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        JsonArray array = new JsonArray();
        for(UserBean user : user_list) {
            Gson gson = new Gson();
            array.add(gson.toJson(user));
        }
        out.println(array);
        out.flush();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            processData(request,response);
        } catch (ServletException | NullPointerException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (IllegalArgumentException e){
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            processData(request,response);
        } catch (ServletException | NullPointerException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (IllegalArgumentException e){
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
