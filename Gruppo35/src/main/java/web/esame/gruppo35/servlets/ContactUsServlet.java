// CONTATTI
/*La pagina contiene indirizzo e numero di telefono e un form dove si possono
inserire i propri dati ed essere successivamente ricontattati dall’associazione. Il form ha i
seguenti campi: (1) nome e cognome, (2) indirizzo email, (3) una combo box dove sono
riportati i motivi di contatto tra i quali il visitatore può scegliere (se il visitatore non trova
un motivo adeguato sceglie l’opzione “altro”), (4) una casella di testo multilinea dove il
visitatore può ulteriormente esplicitare dettagli della sua richiesta, (5) un pulsante per
inviare i dati, (6) un pulsante di reset che ripulisce tutti i campi.*/

package web.esame.gruppo35.servlets;

import web.esame.gruppo35.helperClasses.UserRole;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Map;

public class ContactUsServlet extends HttpServlet {
    protected void processData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        // Counter for page views
        Map<String, Integer> views = (Map<String, Integer>) context.getAttribute("views");
        int newTotal = views.get("total") + 1;
        int newContactUs = views.get("contactUs") + 1;
        views.replace("total", newTotal);
        views.replace("contactUs", newContactUs);
        context.setAttribute("views", views);

        Object message = request.getAttribute("message");
        if (message == null){
            request.setAttribute("message", "");
        }

        RequestDispatcher rd=request.getRequestDispatcher("contactUs.jsp");
        rd.forward(request, response);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            processData(request,response);
        } catch (ServletException | NullPointerException | IOException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            processData(request,response);
        } catch (ServletException | NullPointerException | IOException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
