package web.esame.gruppo35.servlets;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import web.esame.gruppo35.helperClasses.DatabaseSessionManager;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

public class GetDonationsServlet extends HttpServlet {
    // method to retrieve the total of donations grouped by month and send it as JSON in the response
    protected void processData(HttpServletRequest request, HttpServletResponse response) throws NullPointerException, ServletException, SQLException, ClassNotFoundException, IOException, IllegalStateException {
        Map<String, Integer> donation_list = new LinkedHashMap<>();
        // initialize donation_list
        donation_list.put("gennaio", 0);
        donation_list.put("febbraio", 0);
        donation_list.put("marzo", 0);
        donation_list.put("aprile", 0);
        donation_list.put("maggio", 0);
        donation_list.put("giugno", 0);
        donation_list.put("luglio", 0);
        donation_list.put("agosto", 0);
        donation_list.put("settembre", 0);
        donation_list.put("ottobre", 0);
        donation_list.put("novembre", 0);
        donation_list.put("dicembre", 0);

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
            String month = switch (result.getInt("month")){
                case 1 -> "gennaio";
                case 2 -> "febbraio";
                case 3 -> "marzo";
                case 4 -> "aprile";
                case 5 -> "maggio";
                case 6 -> "giugno";
                case 7 -> "luglio";
                case 8 -> "agosto";
                case 9 -> "settembre";
                case 10 -> "ottobre";
                case 11 -> "novembre";
                case 12 -> "dicembre";
                default -> throw new IllegalStateException("Unexpected value: " + result.getInt("month"));
            };
            Integer total_donations = result.getInt("total_donations");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            processData(request,response);
        } catch (ServletException | NullPointerException | ClassNotFoundException | SQLException | IllegalStateException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            processData(request,response);
        } catch (ServletException | NullPointerException | ClassNotFoundException | SQLException | IllegalStateException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
