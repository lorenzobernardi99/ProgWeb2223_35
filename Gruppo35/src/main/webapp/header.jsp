<%@ page import="web.esame.gruppo35.helperClasses.UserRole" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/res/img/favicon.png">
        <link rel="stylesheet" href="style/headStyle.css">
        <link rel="stylesheet" href="style/footStyle.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <script src="${pageContext.request.contextPath}/scripts/header.js"></script>
        <title><%=application.getAttribute("organizationName") %></title>
    </head>
    <body>
        <% HttpSession sessione = request.getSession(false);
        boolean urlRewrite = request.getAttribute("URLRewrite") != null;
        String newHref = (urlRewrite) ? ";jsessionid=" + sessione.getId() : ""; %>
        <header>
            <nav>
                <a href="Homepage">
                    <img src="res/img/logo-no-background.png" class="img" alt="Tum4Word_Icon">
                </a>
                <div id="menu">
                    <div class="nav-links">
                        <a href="Homepage<%=newHref%>" class="nav-link">Home Page</a>
                        <a href="WhoWeAre<%=newHref%>" class="nav-link">Chi siamo</a>
                        <a href="Activities<%=newHref%>" class="nav-link">Attività</a>
                        <a href="ContactUs<%=newHref%>" class="nav-link">Contatti</a>
                    </div>
                    <div class="user-links">
                        <% String username = (String) session.getAttribute("username");
                            UserRole role = (UserRole) session.getAttribute("role");
                            String signin = "";
                            String login = "";
                            String dashboard= "";
                            String usernameBox = "";
                            String logout = "";
                            if (username == null) {
                                signin = "<a href='SignIn" + newHref + "' class='nav-link'>Sign in</a>";
                                login = "<a href='Login" + newHref + "' class='nav-link'>Login</a>";
                            }
                            else{
                                switch (role) {
                                    case AMMINISTRATORE:
                                        dashboard = "<a href='Admin" + newHref + "' class='nav-link'>Dashboard</a>";
                                        break;
                                    case ADERENTE:
                                        dashboard = "<a href='ContactUs" + newHref + "' class='nav-link'>Dashboard</a>";
                                        break;
                                    case SIMPATIZZANTE:
                                        dashboard = "<a href='SignIn" + newHref + "' class='nav-link'>Dashboard</a>";
                                        break;
                                    default:
                                        throw new IllegalArgumentException();
                                }
                                usernameBox = "<a id='loggedUser'>" + username + "</a>";
                                logout = "<a href='Logout" + newHref + "' class='nav-link'>Logout</a>";

                            }%>
                        <%= signin%>
                        <%= login%>
                        <%= usernameBox%>
                        <%= dashboard%>
                        <%= logout%>
                    </div>
                </div>
                <div id="motivations">
                    <span id="phrase"></span>
                </div>
            </nav>
        </header>