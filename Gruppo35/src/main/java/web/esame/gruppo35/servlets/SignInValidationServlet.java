package web.esame.gruppo35.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.spi.AbstractResourceBundleProvider;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class SignInValidationServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:derby://localhost:1527/Tum4WorldDB";
    private static final String DB_USER = "APP";
    private static final String DB_PASSWORD = "admin";
    Connection connection = null;

    @Override
    public void init() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException | NullPointerException | SQLException ex) {
            System.out.println(ex);
        }
    }

    protected boolean checkUsername(String username) throws SQLException {


        String query = "SELECT USERNAME FROM USERS WHERE USERNAME = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        ResultSet result = statement.executeQuery();

        return result.next();
    }

    protected void validateUsername(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            // Ottengo il corpo della richiesta
            StringBuilder requestBody = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }

            // Effettua il parsing del JSON
            Gson gson = new Gson();
            JsonElement jsonElement = gson.fromJson(requestBody.toString(), JsonElement.class);
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            String username = jsonObject.get("username").getAsString();


            boolean exists = checkUsername(username);

            try (PrintWriter out = response.getWriter()) {

                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                String jsonResponse = "{\"exists\": " + exists + "}";
                out.println(jsonResponse);
                out.flush();

                } catch (IOException ex) {
                    System.out.println(ex);
                    response.sendRedirect("error.html");
                }
        } catch (SQLException | NullPointerException ex) {
            System.out.println(ex);
            response.sendRedirect("error.html");
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {

            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String birth = request.getParameter("birth");
            String email = request.getParameter("email");
            String role = request.getParameter("role");
            String prefix = request.getParameter("prefix");
            String phone = request.getParameter("phone");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");

            // check age lambda function
            Predicate<String> checkAge = (_birth) -> {
                LocalDate birthDate = LocalDate.parse(_birth);
                LocalDate currentDate = LocalDate.now();
                Period age = Period.between(birthDate, currentDate);

                int years = age.getYears();

                return years >= 18;
            };
            // check email lambda function
            Predicate<String> checkEmail = (_email) -> {
                String regex = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
                return _email.matches(regex);
            };
            // check phone lambda function
            Predicate<String> checkPhone = (_phone) -> {
                String clearedPhone = _phone.replaceAll("-", "");

                boolean isNumeric = clearedPhone.chars().allMatch(Character::isDigit);

                boolean isValidLength = clearedPhone.length() == 10;

                return isNumeric && isValidLength;
            };
            // check role lambda function
            Predicate<String> checkRole = (_role) -> _role.equalsIgnoreCase("simpatizzante") || _role.equalsIgnoreCase("aderente");

            // check password lambda function
            BiPredicate<String, String> checkPassword = (_password, _confirmPassword) -> {
                String passwordRegex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[jelm])(?=.*[$!?\\w]).{8}$";
                return _password.matches(passwordRegex) && _password.equals(_confirmPassword);
            };

            boolean[] booleanArray = new boolean[8];

            // perform validation using previously declared functions
            booleanArray[0] = !name.isEmpty();
            booleanArray[1] = !surname.isEmpty();
            booleanArray[2] = checkAge.test(birth);
            booleanArray[3] = checkEmail.test(email);
            booleanArray[4] = checkPhone.test(phone);
            booleanArray[5] = checkRole.test(role);
            booleanArray[6] = !checkUsername(username);
            booleanArray[7] = checkPassword.test(password, confirmPassword);

            boolean isValid = true;
            for (boolean value : booleanArray) {
                System.out.println(value);
                if (!value) {
                    isValid = false;
                    break;
                }
            }
            System.out.println(isValid);

            if (isValid) {
                try {
                    Class.forName("org.apache.derby.jdbc.ClientDriver");
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

                try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                    String insertQuery = "INSERT INTO USERS (name, surname, birth_date, email_address, telephone_number, role, username, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement statement = connection.prepareStatement(insertQuery);
                    statement.setString(1, name);
                    statement.setString(2, surname);
                    statement.setDate(3, Date.valueOf(birth));
                    statement.setString(4, email);
                    statement.setString(5, prefix + " " + phone);
                    statement.setInt(6, (role.equalsIgnoreCase("aderente")) ? 1 : 2 );
                    statement.setString(7, username);
                    statement.setString(8, password);

                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        response.sendRedirect("/RegistrationDone.jsp");
                    } else {
                        System.out.println("Errore di inserimento dati nel database");
                        response.sendRedirect("error.html");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Parametri non validi");
                response.sendRedirect("error.html");
            }
        } catch (NullPointerException ex) {
            System.out.println(ex);
            response.sendRedirect("error.html");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestType = request.getHeader("X-Request-Type");

        System.out.println("POST!!!!");

        if ("usernameValidation".equals(requestType)) {
            // handle asynchronous request for username validation coming from javascript
            validateUsername(request, response);
        } else {
            // handle sign in submitted form through validation, registration and redirect
            processRequest(request, response);
        }
    }

    @Override
    public void destroy() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
