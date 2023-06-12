package web.esame.gruppo35.servlets;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import web.esame.gruppo35.helperClasses.DatabaseSessionManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet(name = "GetDonationsServlet", value = "/GetDonationsServlet")
public class GetDonationsServlet extends HttpServlet {
    // method to retrieve the total of donations grouped by month and send it as JSON in the response
    protected void retrieveDonations(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, IOException {
        Map<Integer, Float> donation_list = new LinkedHashMap<>();
        // initialize donation_list
        for (int i = 1; i <= 12; i++) {
            donation_list.put(i, 0F);
        }

        HttpSession session = request.getSession();
        Connection connection;
        String query;
        Statement stmt;
        ResultSet result;

        connection = DatabaseSessionManager.getConnection(session);
        // Making the query
        query = "SELECT MONTH(DATE) AS month, SUM(AMOUNT) AS total_donations FROM DONATIONS WHERE YEAR(DATE) = YEAR(CURRENT_DATE) GROUP BY MONTH(DATE)";
        stmt = connection.createStatement();
        result = stmt.executeQuery(query);

        // Getting results and saving it in an array of beans
        while (result.next()) {
            int month = result.getInt("month");
            Float total_donations =result.getFloat("total_donations");
            donation_list.put(month, total_donations);
        }

        // Preparing and sending json response
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        JsonArray array = new JsonArray();
        Gson gson = new Gson();
        array.add(gson.toJson(donation_list));
        out.println(array);
        out.flush();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            retrieveDonations(request, response);
        } catch (NullPointerException | IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            retrieveDonations(request, response);
        } catch (NullPointerException | IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }    }
}
