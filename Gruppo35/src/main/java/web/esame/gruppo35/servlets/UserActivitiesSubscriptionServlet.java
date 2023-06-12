package web.esame.gruppo35.servlets;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import web.esame.gruppo35.beans.ActivityBean;
import web.esame.gruppo35.helperClasses.ActivityBeanList;
import web.esame.gruppo35.helperClasses.DatabaseSessionManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserActivitiesSubscriptionServlet extends HttpServlet {

    protected HttpSession session;

    protected Connection connection;
    protected ResultSet result;
    protected ResultSet result2;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // retrieve activities
        try {
            getActivities(request, response);
        } catch (ServletException | NullPointerException | IOException | SQLException | ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // subscribe to an activity
        try {
            subscribeToActivity(request, response);
        }catch (ServletException | NullPointerException | IOException | SQLException | ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }

    }
    protected void getActivities(HttpServletRequest request, HttpServletResponse response) throws ServletException, NullPointerException, IOException, SQLException, ClassNotFoundException {
        session = request.getSession();

        ActivityBeanList activityBeans = new ActivityBeanList();
        ActivityBean tmpActivityBean;

        connection = DatabaseSessionManager.getConnection(session);

        int USER_ID = (int) session.getAttribute("USER_ID");

        String query = "SELECT * FROM ACTIVITIES";
        PreparedStatement statement = connection.prepareStatement(query);
        result = statement.executeQuery();

        String query2 = "Select ACTIVITY_ID from SUBSCRIPTIONS where USER_ID = ?";
        PreparedStatement statement2 = connection.prepareStatement(query2);
        statement2.setInt(1, USER_ID);
        result2 = statement2.executeQuery();

        List<Integer> subscriptionIds = new ArrayList<>();
        int tmpId;

        while (result2.next()) {
            subscriptionIds.add(result2.getInt("ACTIVITY_ID"));
        }
        result2.close();
        statement2.close();

        while(result.next()){
            tmpActivityBean = new ActivityBean(
                    result.getString("name"),
                    result.getString("description"),
                    getServletContext().getAttribute("activitiesImagePath") + result.getString("image_name")
            );
            tmpId = result.getInt("id");
            tmpActivityBean.setId(tmpId);
            tmpActivityBean.setSubscribed(false);
            for (int id : subscriptionIds){
                if(tmpId == id)
                    tmpActivityBean.setSubscribed(true);
            }
            activityBeans.add(tmpActivityBean);
        }
        statement.close();
        result.close();

        Gson gson = new Gson();
        String json = gson.toJson(activityBeans);

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        // Scrittura del JSON nel corpo della risposta
        PrintWriter out = response.getWriter();
        out.println(json);
        out.flush();
        out.close();
    }

    protected void subscribeToActivity(HttpServletRequest request, HttpServletResponse response) throws ServletException, NullPointerException, IOException, SQLException, ClassNotFoundException {
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

        String activityIdString = jsonObject.get("targetActivityId").getAsString();

        if (activityIdString == null)
            throw new IllegalArgumentException("Activity Id not provided");

        int activityId = Integer.parseInt(activityIdString);

        // Get user ID from session
        HttpSession session = request.getSession();

        connection = DatabaseSessionManager.getConnection(session);

        int USER_ID = (int) session.getAttribute("USER_ID");

        // Check if the user is already subscribed to the activity
        String query = "SELECT * FROM SUBSCRIPTIONS WHERE USER_ID = ? AND ACTIVITY_ID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, USER_ID);
        statement.setInt(2, activityId);
        ResultSet result = statement.executeQuery();

        boolean isSubscribed = result.next();


        if (isSubscribed) {
            // User is already subscribed, delete the subscription
            String deleteQuery = "DELETE FROM SUBSCRIPTIONS WHERE USER_ID = ? AND ACTIVITY_ID = ?";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
            deleteStatement.setInt(1, USER_ID);
            deleteStatement.setInt(2, activityId);
            deleteStatement.executeUpdate();
        } else {
            // User is not subscribed, add the subscription
            String insertQuery = "INSERT INTO SUBSCRIPTIONS (USER_ID, ACTIVITY_ID) VALUES (?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setInt(1, USER_ID);
            insertStatement.setInt(2, activityId);
            insertStatement.executeUpdate();
        }

        result.close();
        statement.close();

        // create subscription state response
        JsonObject responseJson = new JsonObject();
        responseJson.addProperty("isSubscribed", isSubscribed);
        // Add other response data if needed

        // Set response content type and write the JSON response
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        out.println(responseJson);
        out.flush();
        out.close();

    }
}
