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
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class SetDonationServlet extends HttpServlet {
    // method to retrieve users from DB, based on the request parameter, sending a JSON as a response
    protected void setDonation(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ClassNotFoundException {
        int loggedUserID = Integer.parseInt(request.getParameter("USER_ID"));
        int amount = Integer.parseInt(request.getParameter("amount"));

        HttpSession session = request.getSession();
        Connection connection;
        String query;
        PreparedStatement stmt;
        String queryResult;

        // retrieve DB connection
        connection = DatabaseSessionManager.getConnection(session);
        // Making the query
        query = "INSERT INTO DONATIONS (CONTRIBUTOR, AMOUNT, DATE) VALUES (?,?,?)";

        stmt = connection.prepareStatement(query);
        stmt.setInt(1, loggedUserID);
        stmt.setInt(2, amount);
        stmt.setDate(3, Date.valueOf(LocalDate.now()));

        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            queryResult = "true";
        } else {
            queryResult = "false";
        }

        // Preparing and sending json response
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        JsonArray array = new JsonArray();
        Gson gson=new Gson();
        String result = gson.toJson(queryResult);
        out.println(result);
        out.flush();
        out.println(array);
        out.flush();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            setDonation(request,response);
        } catch (NullPointerException | IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            setDonation(request,response);
        } catch (NullPointerException | IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
