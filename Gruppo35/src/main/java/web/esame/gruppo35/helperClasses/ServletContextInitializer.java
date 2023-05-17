package web.esame.gruppo35.helperClasses;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ServletContextInitializer implements ServletContextListener {

    String address = "Via Sommarive, 5, 38123 Trento TN";
    String addressCoordinates = "46.06691501129916, 11.150292307115517";
    String telephone = "0461281567";
    String sendingConfirmed = "Grazie per averci contattato! Abbiamo ricevuto il tuo messaggio e ti risponderemo al più presto.";
    String reason1 = "Donazione";
    String reason2 = "Informazioni";
    String reason3 = "Altro";
    String[] reasons = {reason1, reason2, reason3};

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        List<String> reasonList = new LinkedList<>(Arrays.asList(reasons));
        context.setAttribute("reasons", reasonList);
        context.setAttribute("address", address);
        context.setAttribute("addressCoordinates", addressCoordinates);
        context.setAttribute("telephone", telephone);
        context.setAttribute("sendingConfirmed", sendingConfirmed);
    }
}