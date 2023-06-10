package web.esame.gruppo35.helperClasses;

import web.esame.gruppo35.beans.ActivityBean;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ServletContextInitializer implements ServletContextListener {
    String[] activity1 = {
            "Attività 1",
            "Raccolta fondi per la lotta alla povertà infantile:" +
                    "In questa attività l'associazione si impegna nella raccolta di fondi per la lotta alla povertà infantile," +
                    "supportando progetti di aiuto a bambini e famiglie in difficoltà economica." +
                    "Attraverso iniziative come eventi di beneficenza, campagne di crowdfunding e partnership con altre organizzazioni," +
                    "l'associazione lavora per garantire a ogni bambino la possibilità di crescere in modo sano e sereno.",
            "res/img/activity1.jpg"
    };
    String[] activity2 = {
            "Attività 2",
            "Assistenza agli anziani soli:" +
                    "In questa attività l'associazione si dedica all'assistenza degli anziani soli e in difficoltà." +
                    "Attraverso visite domiciliari, chiamate telefoniche, attività di supporto psicologico e sociale," +
                    "l'associazione cerca di offrire un aiuto concreto e un sostegno emotivo a coloro" +
                    "che si trovano in situazioni di isolamento o di difficoltà." +
                    "L'obiettivo è quello di garantire una maggiore qualità di vita agli anziani" +
                    "e di promuovere una società più solidale e inclusiva.",
            "res/img/activity2.jpg"
    };
    String[] activity3 = {
            "Attività 3",
            "Protezione dell'ambiente e del territorio:" +
                    "In questa attività l'associazione si occupa della tutela dell'ambiente e del territorio," +
                    "svolgendo attività di sensibilizzazione, monitoraggio e difesa dei beni naturali e culturali." +
                    "Attraverso iniziative come pulizia delle spiagge, raccolta differenziata," +
                    "progetti di educazione ambientale e supporto a comunità locali," +
                    "l'associazione lavora per promuovere uno sviluppo sostenibile" +
                    "e un uso consapevole delle risorse del nostro pianeta.",
            "res/img/activity3.jpg"
    };

    String organizationName = "Tum4World";
    String country = "Italy";
    String road = "Via Sommarive";
    String postalCode = "35000";
    String city = "Trento";
    String address = "Via Sommarive, 5, 35000 Trento TN";
    String addressCoordinates = "46.06691501129916, 11.150292307115517";
    String telephone = "0461281567";
    String sendingConfirmed = "Grazie per averci contattato! Abbiamo ricevuto il tuo messaggio e ti risponderemo al più presto.";
    String registrationDone = "Grazie per esserti registrato! Abbiamo ricevuto i tuoi dati correttamente e ora potrai accedere al portale con le tue credenziali.";
    String reason1 = "Donazione";
    String reason2 = "Informazioni";
    String reason3 = "Altro";
    String[] reasons = {reason1, reason2, reason3};

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        ActivityBean[] activityBeans = {
                new ActivityBean(0, activity1[0], activity1[1], activity1[2]),
                new ActivityBean(0, activity2[0], activity2[1], activity2[2]),
                new ActivityBean(0, activity3[0], activity3[1], activity3[2]),
        };
        ActivityBeanList activityBeanList = new ActivityBeanList();
        activityBeanList.add(activityBeans[0]);
        activityBeanList.add(activityBeans[1]);
        activityBeanList.add(activityBeans[2]);
        context.setAttribute("activityBeanList", activityBeanList);

        List<String> reasonList = new LinkedList<>(Arrays.asList(reasons));
        context.setAttribute("reasons", reasonList);
        context.setAttribute("country", country);
        context.setAttribute("organizationName", organizationName);
        context.setAttribute("postalCode", postalCode);
        context.setAttribute("city", city);
        context.setAttribute("road", road);
        context.setAttribute("address", address);
        context.setAttribute("addressCoordinates", addressCoordinates);
        context.setAttribute("telephone", telephone);
        context.setAttribute("sendingConfirmed", sendingConfirmed);
        context.setAttribute("registrationDone", registrationDone);

    }
}
