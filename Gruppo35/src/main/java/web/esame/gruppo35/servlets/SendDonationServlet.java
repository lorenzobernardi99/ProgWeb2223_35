package web.esame.gruppo35.servlets;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import web.esame.gruppo35.helperClasses.DatabaseSessionManager;

import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDate;

public class SendDonationServlet extends HttpServlet {
    // method to retrieve users from DB, based on the request parameter, sending a JSON as a response
    protected void setDonation(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ClassNotFoundException {
        // Obtain body's request
        StringBuilder requestBody = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }

        // JSON parsing
        Gson gson = new Gson();
        JsonElement jsonElement = gson.fromJson(requestBody.toString(), JsonElement.class);
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        int amount = jsonObject.get("donationAmount").getAsInt();

        HttpSession session = request.getSession();
        Connection connection;
        String query;
        PreparedStatement stmt;
        boolean queryResult;
        int loggedUserID = (int) session.getAttribute("USER_ID");

        // retrieve DB connection
        connection = DatabaseSessionManager.getConnection(session);
        // Making the query
        query = "INSERT INTO DONATIONS (CONTRIBUTOR, AMOUNT, DATE) VALUES (?,?,?)";

        stmt = connection.prepareStatement(query);
        stmt.setInt(1, loggedUserID);
        stmt.setInt(2, amount);
        stmt.setDate(3, Date.valueOf(LocalDate.now()));

        int rowsAffected = stmt.executeUpdate();

        // if query outcome is successful -> true, otherwise false
        queryResult = rowsAffected > 0;

        // Preparing and sending json response
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        // create donation state response
        JsonObject responseJson = new JsonObject();
        responseJson.addProperty("isSuccessful", queryResult);

        // Set response content type and write the JSON response
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        out.println(responseJson);
        out.flush();
        out.close();
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
