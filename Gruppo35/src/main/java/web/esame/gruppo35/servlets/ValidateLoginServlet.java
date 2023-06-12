package web.esame.gruppo35.servlets;

import web.esame.gruppo35.beans.UserBean;
import web.esame.gruppo35.helperClasses.DatabaseSessionManager;
import web.esame.gruppo35.helperClasses.UserRole;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "ValidateLoginServlet", value = "/ValidateLogin")
public class ValidateLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (session.getAttribute("username")!=null) {
            //già autenticato
            RequestDispatcher rd=request.getRequestDispatcher("Login");
            rd.forward(request, response);
        }

        Connection connection;
        Statement stmt;
        ResultSet result;
        UserBean retrievedUser = new UserBean();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            connection = DatabaseSessionManager.getConnection(session);
            String query = "SELECT * FROM USERS WHERE username = '" + username + "' AND password = '" + password +"'";
            stmt = connection.createStatement();
            result = stmt.executeQuery(query);
            if (result.next()) {
                retrievedUser.setId(result.getInt("id"));
                retrievedUser.setName(result.getString("name"));
                retrievedUser.setSurname(result.getString("surname"));
                retrievedUser.setBirthDate(result.getDate("birth_date"));
                retrievedUser.setEmailAddress(result.getString("email_address"));
                retrievedUser.setTelephoneNumber(result.getString("telephone_number"));
                retrievedUser.setRole(UserRole.values()[result.getInt("role")]);
                retrievedUser.setUsername(result.getString("username"));
                retrievedUser.setPassword(result.getString("password"));
            }
        } catch (ClassNotFoundException | NullPointerException | SQLException e) {
            e.printStackTrace();
            //response.sendRedirect("Error.html");
        }

        if (retrievedUser.getRole() == null){
            request.setAttribute("message", "Username e/o password errati");
            RequestDispatcher rd=request.getRequestDispatcher("Login");
            rd.forward(request, response);
        } else {
            session.setAttribute("username", username);
            session.setAttribute("role", retrievedUser.getRole());
            session.setAttribute("USER_ID", retrievedUser.getId());
            switch (retrievedUser.getRole()) {
                // TODO: da modificare con le diverse pagine dei diversi profili
                case AMMINISTRATORE -> response.sendRedirect("Homepage");
                case ADERENTE -> response.sendRedirect("ContactUs");
                case SIMPATIZZANTE -> response.sendRedirect("SignIn");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // redirect to Login
        response.sendRedirect("Login");
    }
}
