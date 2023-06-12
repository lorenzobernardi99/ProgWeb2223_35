package web.esame.gruppo35.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import web.esame.gruppo35.helperClasses.DatabaseSessionManager;
import web.esame.gruppo35.helperClasses.UserRole;

public class SignInValidationServlet extends HttpServlet {
    Connection connection = null;

    protected boolean checkUsername(String username) throws SQLException {
        String query = "SELECT USERNAME FROM USERS WHERE USERNAME = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        ResultSet result = statement.executeQuery();
        return result.next();
    }

    protected void validateUsername(HttpServletRequest request, HttpServletResponse response) throws IOException, NullPointerException, SQLException {
        // Obtain body's request
        StringBuilder requestBody = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }

        // JSON parsing
        Gson gson = new Gson();
        JsonElement jsonElement = gson.fromJson(requestBody.toString(), JsonElement.class);
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        String username = jsonObject.get("username").getAsString();

        boolean exists = checkUsername(username);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        String jsonResponse = "{\"exists\": " + exists + "}";
        out.println(jsonResponse);
        out.flush();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException, IllegalArgumentException{
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
        Predicate<String> checkRole = (_role) -> _role.equalsIgnoreCase(String.valueOf(UserRole.SIMPATIZZANTE)) || _role.equalsIgnoreCase(String.valueOf(UserRole.ADERENTE));
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
            if (!value) {
                isValid = false;
                break;
            }
        }

        if (isValid) {
            String insertQuery = "INSERT INTO USERS (name, surname, birth_date, email_address, telephone_number, role, username, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setDate(3, Date.valueOf(birth));
            statement.setString(4, email);
            statement.setString(5, prefix + " " + phone);
            statement.setInt(6, UserRole.valueOf(role.toUpperCase()).ordinal());
            statement.setString(7, username);
            statement.setString(8, password);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Insertion completed: " + rowsInserted + " rows affected");
                response.sendRedirect("/registrationDone.jsp");
            } else {
                throw new SQLException("Errore di inserimento dati nel database");
            }
        } else {
            if (!booleanArray[6]){
                    request.setAttribute("errorMessage", "35: Username '" + username + "' già in uso!");
                    request.getRequestDispatcher("/SignIn").forward(request, response);
            }else {
                throw new IllegalArgumentException("Parametri non validi");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // redirect to SignIn
            response.sendRedirect("SignIn");
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String requestType = request.getHeader("X-Request-Type");

        try {
            connection = DatabaseSessionManager.getConnection(session);
            if ("usernameValidation".equals(requestType)) {
                // handle asynchronous request for username validation coming from javascript
                validateUsername(request, response);
            } else {
                // handle sign in submitted form through validation, registration and redirect
                processRequest(request, response);
            }
        } catch (ServletException | ClassNotFoundException | NullPointerException | SQLException | IOException | IllegalArgumentException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
