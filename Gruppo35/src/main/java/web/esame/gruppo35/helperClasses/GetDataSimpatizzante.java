package web.esame.gruppo35.helperClasses;

import com.google.gson.*;
import web.esame.gruppo35.beans.UserBean;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class GetDataSimpatizzante extends HttpServlet {
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
        UserBean User=new UserBean();
        try{
            connection = DatabaseSessionManager.getConnection(session);
            String username= (String) session.getAttribute("username");
            String Query="SELECT * FROM USERS WHERE USERNAME='"+username+"'";
            statement = connection.createStatement();
            result = statement.executeQuery(Query);
            while(result.next()){
                User.setName(result.getString(2));
                User.setSurname(result.getString(3));
                User.setBirthDate(result.getDate(4));
                User.setEmailAddress(result.getString(5));
                User.setTelephoneNumber(result.getString(6));
                User.setRole(UserRole.values()[result.getInt(7)]);
                User.setUsername(result.getString(8));
                String newpass="";
                for(int i=0; i<result.getString(9).length();i++){
                    newpass+='*';
                }
                User.setPassword(newpass);
            }
        }catch (ClassNotFoundException | SQLException | NullPointerException ex){
            System.out.println("Errore :"+ex);
        }
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        try(PrintWriter out = resp.getWriter()){
            Gson gson=new Gson();
            String oggetto=gson.toJson(User);
            out.println(oggetto);
            out.flush();
        }catch(IOException ex){
            System.out.println("Errore :"+ex);
        }

    }
}
