package web.esame.gruppo35.servlets;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import web.esame.gruppo35.beans.UserBean;
import web.esame.gruppo35.helperClasses.DatabaseSessionManager;
import web.esame.gruppo35.helperClasses.UserRole;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "GetUsersServlet", value = "/GetUsersServlet")
public class GetUsersServlet extends HttpServlet {
    // method to retrieve users from DB, based on the request parameter, sending a JSON as a response
    protected void retrieveUsers(HttpServletRequest request, HttpServletResponse response){
        String filter = request.getParameter("filter");
        ArrayList<UserBean> user_list = new ArrayList<>();

        HttpSession session = request.getSession();
        Connection connection;
        String query;
        PreparedStatement stmt;
        ResultSet result;

        try {
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
                UserBean retrieved_user = new UserBean();
                retrieved_user.setName(result.getString("name"));
                retrieved_user.setSurname(result.getString("surname"));
                retrieved_user.setBirthDate(result.getDate("birth_date"));
                retrieved_user.setEmailAddress(result.getString("email_address"));
                retrieved_user.setTelephoneNumber(result.getString("telephone_number"));
                retrieved_user.setRole(UserRole.values()[result.getInt("role")]);
                retrieved_user.setUsername(result.getString("username"));
                retrieved_user.setPassword(result.getString("password"));
                user_list.add(retrieved_user);
            }
        } catch (SQLException | NullPointerException | ClassNotFoundException | IllegalArgumentException ex) {
            System.out.println(ex);
            //TODO:response.sendRedirect("error.html");
        }

        // Preparing and sending json response
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter()) {
            JsonArray array = new JsonArray();
            for(UserBean user : user_list) {
                Gson gson = new Gson();
                array.add(gson.toJson(user));
            }
            out.println(array);
            out.flush();
        }
        catch (IOException ex) {
            System.out.println(ex);
            //TODO:response.sendRedirect("error.html");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        retrieveUsers(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        retrieveUsers(request,response);
    }
}
