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

    protected void processData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        if (session.getAttribute("role")!=null) {
            //già autenticato
            RequestDispatcher rd=request.getRequestDispatcher("Login");
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
                    result.getString(2),
                    result.getString(3),
                    result.getDate(4),
                    result.getString(5),
                    result.getString(6),
                    UserRole.values()[result.getInt(7)],
                    result.getString(8),
                    result.getString(9)
            );
        }

        if ((retrievedUser != null ? retrievedUser.getRole() : null) == null){
            request.setAttribute("message", "35:Username e/o password errati");
            RequestDispatcher rd=request.getRequestDispatcher("Login");
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
        try {
            // redirect to Login
            response.sendRedirect("Login");
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            session = request.getSession();
            connection = DatabaseSessionManager.getConnection(session);
            processData(request,response);
        } catch (ServletException | ClassNotFoundException | NullPointerException | SQLException | IOException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
