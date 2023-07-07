package web.esame.gruppo35.servlets;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import web.esame.gruppo35.beans.UserBean;
import web.esame.gruppo35.helperClasses.DatabaseSessionManager;
import web.esame.gruppo35.helperClasses.UserRole;

import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetUsersServlet extends HttpServlet {
    // method to retrieve users from DB, based on the request parameter, sending a JSON as a response
    protected void retrieveUsers(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ClassNotFoundException {
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
            UserBean retrievedUser = new UserBean(
                    result.getString(2),
                    result.getString(3),
                    result.getDate(4),
                    result.getString(5),
                    result.getString(6),
                    UserRole.values()[result.getInt(7)],
                    result.getString(8),
                    result.getString(9)
            );
            user_list.add(retrievedUser);
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
            retrieveUsers(request,response);
        } catch (NullPointerException | IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            retrieveUsers(request,response);
        } catch (NullPointerException | IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
