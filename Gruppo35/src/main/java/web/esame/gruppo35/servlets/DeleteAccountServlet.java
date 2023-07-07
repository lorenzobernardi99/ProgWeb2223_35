package web.esame.gruppo35.servlets;

import web.esame.gruppo35.helperClasses.DatabaseSessionManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteAccountServlet extends HttpServlet {
    Connection connection = null;
    Statement statement = null;
    ResultSet result = null;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        HttpSession session = req.getSession();
        try{
            connection = DatabaseSessionManager.getConnection(session);
            String username= (String) session.getAttribute("username");
            String Query="DELETE  FROM USERS WHERE USERNAME='"+username+"'";
            statement = connection.createStatement();
            int v=statement.executeUpdate(Query);
        }catch (ClassNotFoundException | SQLException | NullPointerException ex){
            System.out.println("Errore :"+ex);
        }
        req.getRequestDispatcher("Logout").forward(req,resp);

    }
}
