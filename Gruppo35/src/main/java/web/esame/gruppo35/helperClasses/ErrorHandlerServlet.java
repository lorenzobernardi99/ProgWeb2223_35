package web.esame.gruppo35.helperClasses;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorHandlerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Logica di gestione degli errori
        int statusCode = response.getStatus();

        String errorMessage = switch (statusCode) {
            case 400 -> "Errore 400 - Richiesta non valida";
            case 403 -> "Errore 403 - Accesso vietato";
            case 404 -> "Errore 404 - Risorsa non trovata";
            case 500 -> "Errore 500 - Errore interno del server";
            default -> "Si è verificato un errore";
        };

        request.setAttribute("errorMessage", errorMessage);

        try {
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
            response.sendRedirect("/error.jsp");
        }
    }
}
