package web.esame.gruppo35.servlets;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Pattern;

public class ValidateContactFormServlet extends HttpServlet {

    protected void processData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NullPointerException, MessagingException {
        // retrieve form parameter
        String name_surname = request.getParameter("name-surname");
        String sender = request.getParameter("email");
        String subject = request.getParameter("reason");
        String details = request.getParameter("details");

        // compose email
        String messageText = composeEmail(name_surname, subject, details);
        if(validateEmail(sender)){

            // mock configuration
            Properties properties = new Properties();
            properties.put("mail.smtp.host", "test-smtp.example.com");
            properties.put("mail.smtp.port", "25");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "false");

            // mock session
            Session session = Session.getInstance(properties);

            // mock email
            Message emailMessage = new MimeMessage(session);
            emailMessage.setFrom(new InternetAddress(sender));
            emailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse("tum4world@nessunonoluogonoesiste.com"));
            emailMessage.setSubject(subject);
            emailMessage.setText(messageText);

            // simulation of message sending
            response.getWriter().println(emailMessage);

            response.sendRedirect("emailSent.jsp");
        }
        else {
            request.setAttribute("message", "Indirzzo e-mail non valido");
            RequestDispatcher rd = request.getRequestDispatcher("ContactUs");
            rd.forward(request, response);
        }
    }

    private boolean validateEmail(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }

    private String composeEmail(String name_surname, String subject, String details){
        StringBuilder emailText = new StringBuilder();
        emailText.append("Buongiorno,\n").append("Sono ").append(name_surname).append(".\n")
                .append("Sono interessato nella vostra associazione, soprattutto in ").append(subject).append(".\n");
        if (!details.equals("")){
            emailText.append(details);
        }
        return emailText.toString();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            processData(request,response);
        } catch (ServletException | NullPointerException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (MessagingException e){
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
        }
    }
}
