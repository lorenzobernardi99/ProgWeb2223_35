package web.esame.gruppo35.servlets;

import web.esame.gruppo35.beans.UserBean;
import web.esame.gruppo35.helperClasses.DatabaseSessionManager;
import web.esame.gruppo35.helperClasses.UserRole;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidateLoginServlet extends HttpServlet {

    Connection connection = null;
    HttpSession session = null;

    protected void processData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException, NullPointerException {
        if (session.getAttribute("role")!=null) {
            //già autenticato
            RequestDispatcher rd=request.getRequestDispatcher("/Login");
            rd.forward(request, response);
        }

        PreparedStatement stmt;
        ResultSet result;
        UserBean retrievedUser = null;
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        connection = DatabaseSessionManager.getConnection(session);
        String query = "SELECT * FROM USERS WHERE username = ? AND password = ?";
        stmt = connection.prepareStatement(query);
        stmt.setString(1, username);
        stmt.setString(2, password);

        result = stmt.executeQuery();
        if (result.next()) {
            retrievedUser = new UserBean(
                    result.getString("name"),
                    result.getString("surname"),
                    result.getDate("birth_date"),
                    result.getString("email_address"),
                    result.getString("telephone_number"),
                    UserRole.values()[result.getInt("role")],
                    result.getString("username"),
                    result.getString("password")
            );
            retrievedUser.setId(result.getInt("id"));
        }

        if ((retrievedUser != null ? retrievedUser.getRole() : null) == null){
            request.setAttribute("message", "35:Username e/o password errati");
            RequestDispatcher rd=request.getRequestDispatcher("/Login");
            rd.forward(request, response);
        } else {
            session.setAttribute("username", username);
            session.setAttribute("role", retrievedUser.getRole());
            session.setAttribute("USER_ID", retrievedUser.getId());

            boolean urlRewrite = request.getAttribute("URLRewrite") != null;
            String newHref = (urlRewrite) ? ";jsessionid=" + session.getId() : "";

            switch (retrievedUser.getRole()) {
                case AMMINISTRATORE -> response.sendRedirect("Admin" + newHref);
                case ADERENTE, SIMPATIZZANTE -> response.sendRedirect("Member" + newHref);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            session = request.getSession();
            connection = DatabaseSessionManager.getConnection(session);
            processData(request,response);
        } catch (ServletException | ClassNotFoundException | NullPointerException | SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
