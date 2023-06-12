package web.esame.gruppo35.helperClasses;

import web.esame.gruppo35.beans.ActivityBean;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.*;

public class ServletContextInitializer implements ServletContextListener {
    String[] activity1 = {
            "Attività 1",
            "Raccolta fondi per la lotta alla povertà infantile:" +
                    "In questa attività l'associazione si impegna nella raccolta di fondi per la lotta alla povertà infantile," +
                    "supportando progetti di aiuto a bambini e famiglie in difficoltà economica." +
                    "Attraverso iniziative come eventi di beneficenza, campagne di crowdfunding e partnership con altre organizzazioni," +
                    "l'associazione lavora per garantire a ogni bambino la possibilità di crescere in modo sano e sereno.",
            "res/img/child.jpg"
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
            "res/img/old.jpg"
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
            "res/img/environment.jpg"
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
    Map<String, Integer> views = new LinkedHashMap<>();
    List<String> motivations = new LinkedList<>();
    String activities_description = "L'Associazione Tum4World è un'organizzazione dedicata alla riduzione della fame nel mondo." +
            "Attraverso una serie di attività, l'associazione si impegna a combattere l'insicurezza alimentare e a garantire che ogni individuo abbia accesso a cibo nutriente e sufficiente." +
            "Lavoriamo per sensibilizzare l'opinione pubblica, implementare progetti di sviluppo alimentare e fornire assistenza in situazioni di emergenza." +
            "La nostra missione è contribuire a creare un mondo in cui nessuno debba soffrire la fame.";
    String descrption = "L'Associazione Tum4World è un'organizzazione internazionale dedicata alla riduzione e all'eliminazione della fame nel mondo." +
            "Fondata nel 2023, l'associazione si impegna a combattere l'insicurezza alimentare e a garantire che ogni individuo abbia accesso a cibo nutriente e sufficiente.";
    String foundation = "L'Associazione Tum4World è stata fondata nel 2023 da un gruppo di volontari motivati dalla volontà di combattere la fame nel mondo." +
            "La fondazione è nata con l'obiettivo di creare un impatto duraturo attraverso programmi di sostegno alimentare e iniziative di sensibilizzazione.";
    String history = "Nel corso degli anni, l'Associazione Tum4World ha ampliato la propria portata e l'efficacia delle sue azioni." +
            "Attraverso partnership strategiche, ha implementato progetti di agricoltura sostenibile, educazione alimentare e accesso all'acqua potabile." +
            "La storia dell'associazione è segnata da successi significativi nella lotta contro la fame e l'insicurezza alimentare.";
    String locations = "L'Associazione Tum4World ha stabilito sedi operative in diverse parti del mondo, garantendo una presenza globale per affrontare i problemi legati alla fame." +
            "Queste sedi strategiche fungono da hub per coordinare le attività sul campo, collaborare con partner locali e implementare soluzioni su larga scala.";
    String accolades = "L'impegno e l'efficacia dell'Associazione Tum4World nella lotta contro la fame sono stati riconosciuti a livello internazionale." +
            "L'associazione ha ricevuto numerosi premi e riconoscimenti per i suoi sforzi nel migliorare la sicurezza alimentare e la qualità della vita delle persone." +
            "Questi riconoscimenti rappresentano un incoraggiamento per continuare nella missione di eliminare la fame nel mondo.";
    String investments = "L'Associazione Tum4World si basa su donazioni e investimenti per sostenere le sue iniziative." +
            "Attraverso partnership con organizzazioni, enti governativi e donatori privati, l'associazione riesce a finanziare progetti di largo impatto." +
            "Gli investimenti sono diretti verso la creazione di infrastrutture sostenibili, la formazione delle comunità locali e il supporto a lungo termine per garantire la sicurezza alimentare.";

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
        List<String> reasonList = new LinkedList<>(Arrays.asList(reasons));
        views.put("total", 0);
        views.put("homepage", 0);
        views.put("whoWeAre", 0);
        views.put("activities", 0);
        views.put("contactUs", 0);
        views.put("signIn", 0);
        views.put("login", 0);
        motivations.add("\"Il segreto del successo è nel volerlo abbastanza forte.\" - Albert Einstein");
        motivations.add("\"Ogni giorno è un'opportunità per essere una versione migliore di te stesso.\"");
        motivations.add("\"Non importa quante volte cadi, ma quante volte ti rialzi.\"");
        motivations.add("\"Le sfide sono ciò che rendono la vita interessante; superarle è ciò che la rende significativa.\" - Joshua J. Marine");
        motivations.add("\"Il successo non è la chiave della felicità. La felicità è la chiave del successo. Se ami ciò che fai, avrai successo.\" - Albert Schweitzer");

        context.setAttribute("activityBeanList", activityBeanList);
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
        context.setAttribute("views", views);
        context.setAttribute("activities_description", activities_description);
        context.setAttribute("description", descrption);
        context.setAttribute("foundation", foundation);
        context.setAttribute("history", history);
        context.setAttribute("locations", locations);
        context.setAttribute("accolades", accolades);
        context.setAttribute("investments", investments);
        context.setAttribute("motivations", motivations);
    }
}