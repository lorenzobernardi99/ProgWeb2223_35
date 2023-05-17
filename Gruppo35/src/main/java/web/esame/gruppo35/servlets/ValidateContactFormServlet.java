package web.esame.gruppo35.servlets;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

public class ValidateContactFormServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // retrieve form parameter
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String sender = request.getParameter("email");
        String subject = request.getParameter("reason");
        String details = request.getParameter("details");

        // compose email
        String messageText = composeEmail(name, surname, subject, details);
        if(validateEmail(sender)){

            // mock configuration
            Properties properties = new Properties();
            properties.put("mail.smtp.host", "test-smtp.example.com");
            properties.put("mail.smtp.port", "25");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "false");

            // mock session
            Session session = Session.getInstance(properties);

            try {
                // mock email
                Message emailMessage = new MimeMessage(session);
                emailMessage.setFrom(new InternetAddress(sender));
                emailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse("tum4world@nessunonoluogonoesiste.com"));
                emailMessage.setSubject(subject);
                emailMessage.setText(messageText);

                // simulation of message sending
                response.getWriter().println("Simulazione di invio email al gestore del sito.");

                // debug
                // System.out.println("Receiver:\n" + "tum4world@nessunonoluogonoesiste.com");
                // System.out.println("Subject:\n" + subject);
                // System.out.println("Message:\n" + messageText);

                RequestDispatcher rd = request.getRequestDispatcher("EmailSent");
                rd.forward(request, response);

            } catch (MessagingException e) {
                request.setAttribute("message", "Un'errore imprevisto non ha permesso l'invio dell' e-mail");
                RequestDispatcher rd = request.getRequestDispatcher("ContactUs");
                rd.forward(request, response);
            }
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

    private String composeEmail(String name, String surname, String subject, String details){
        StringBuilder emailText = new StringBuilder();
        emailText.append("Buongiorno,\n").append("Sono ").append(name)
                .append(" ").append(surname).append(".\n")
                .append("Sono interessato nella vostra associazione, soprattutto in ").append(subject).append(".\n");
        if (!details.equals("")){
            emailText.append(details);
        }
        return emailText.toString();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // redirect to ContactUs
        response.sendRedirect("ContactUs");
    }
}
