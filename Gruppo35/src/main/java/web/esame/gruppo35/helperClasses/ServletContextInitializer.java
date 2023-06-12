package web.esame.gruppo35.helperClasses;

import web.esame.gruppo35.beans.ActivityBean;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class ServletContextInitializer implements ServletContextListener {
    String[] activity1 = {
            "Raccolta fondi per la lotta alla povertà infantile",
            "In questa attività l'associazione si impegna nella raccolta di fondi per la lotta alla povertà infantile," +
                    "supportando progetti di aiuto a bambini e famiglie in difficoltà economica." +
                    "Attraverso iniziative come eventi di beneficenza, campagne di crowdfunding e partnership con altre organizzazioni," +
                    "l'associazione lavora per garantire a ogni bambino la possibilità di crescere in modo sano e sereno.",
            "child.jpg"
    };
    String[] activity2 = {
            "Assistenza agli anziani soli",
            "In questa attività l'associazione si dedica all'assistenza degli anziani soli e in difficoltà." +
                    "Attraverso visite domiciliari, chiamate telefoniche, attività di supporto psicologico e sociale," +
                    "l'associazione cerca di offrire un aiuto concreto e un sostegno emotivo a coloro" +
                    "che si trovano in situazioni di isolamento o di difficoltà." +
                    "L'obiettivo è quello di garantire una maggiore qualità di vita agli anziani" +
                    "e di promuovere una società più solidale e inclusiva.",
            "old.jpg"
    };
    String[] activity3 = {
            "Protezione dell'ambiente e del territorio",
                    "In questa attività l'associazione si occupa della tutela dell'ambiente e del territorio," +
                    "svolgendo attività di sensibilizzazione, monitoraggio e difesa dei beni naturali e culturali." +
                    "Attraverso iniziative come pulizia delle spiagge, raccolta differenziata," +
                    "progetti di educazione ambientale e supporto a comunità locali," +
                    "l'associazione lavora per promuovere uno sviluppo sostenibile" +
                    "e un uso consapevole delle risorse del nostro pianeta.",
            "environment.jpg"
    };

    String activitiesImagePath = "res/img/";
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
                new ActivityBean(activity1[0], activity1[1], activity1[2]),
                new ActivityBean(activity2[0], activity2[1], activity2[2]),
                new ActivityBean(activity3[0], activity3[1], activity3[2]),
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
        motivations.add("\"Il segreto del successo è nel volerlo abbastanza forte.\"");
        motivations.add("\"Ogni giorno è un'opportunità per essere una versione migliore di te stesso.\"");
        motivations.add("\"Non importa quante volte cadi, ma quante volte ti rialzi.\"");
        motivations.add("\"Le sfide sono ciò che rendono la vita interessante; superarle è ciò che la rende significativa.\"");
        motivations.add("\"Il successo non è la chiave della felicità. La felicità è la chiave del successo. Se ami ciò che fai, avrai successo.\"");

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
        context.setAttribute("activitiesImagePath", activitiesImagePath);

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/Tum4WorldDB", "APP", "admin");

            String insertQuery = "INSERT INTO ACTIVITIES (name, description, image_name) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(insertQuery);

            String checkQuery = "SELECT COUNT(*) FROM ACTIVITIES WHERE name = ?";
            PreparedStatement checkStatement = connection.prepareStatement(checkQuery);

            int rowsInserted = 0;
            for (ActivityBean ab : activityBeans){
                checkStatement.setString(1, ab.getName());
                ResultSet resultSet = checkStatement.executeQuery();
                resultSet.next();
                int count = resultSet.getInt(1);
                if (count==0){
                    // activity does not exist, proceed to create
                    statement.setString(1, ab.getName());
                    statement.setString(2, ab.getDescription());
                    statement.setString(3, ab.getImagePath());
                    rowsInserted += statement.executeUpdate();
                }
            }
            if (rowsInserted > 0) {
                System.out.println("Insertion completed: " + rowsInserted + " rows affected");
            } else {
                System.out.println("Nessun dato inserito nel database: errore durante la procedura o dati già presenti ");
            }

            String checkQuery2 = "SELECT COUNT(*) FROM USERS WHERE username = ?";
            PreparedStatement checkStatement2 = connection.prepareStatement(checkQuery2);
            checkStatement2.setString(1, "admin");
            ResultSet resultSet2 = checkStatement2.executeQuery();
            resultSet2.next();
            int count2 = resultSet2.getInt(1);
            if (count2 == 0){
                // admin does not exist, creating
                String insertQuery2 = "INSERT INTO USERS (name, surname, birth_date, email_address, telephone_number, role, username, password) VALUES (?,?,?,?,?,?,?,?)";
                PreparedStatement statement2 = connection.prepareStatement(insertQuery2);
                statement2.setString(1, "amministratore");
                statement2.setString(2, "Tum4World");
                String dateStr = "2023-06-12";
                LocalDate date = LocalDate.parse(dateStr);
                Date sqlDate = Date.valueOf(date);
                statement2.setDate(3, sqlDate);
                statement2.setString(4, "tum4world@nessunonoluogonoesiste.com");
                statement2.setString(5, "222-222-2222");
                statement2.setInt(6, 0);
                statement2.setString(7, "admin");
                statement2.setString(8, "35Adm1n!");
                statement2.executeUpdate();
            }
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}