<%@page import="web.esame.gruppo35.helperClasses.UserRole" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/res/img/favicon.png">
        <link rel="stylesheet" href="style/headStyle.css">
        <link rel="stylesheet" href="style/footStyle.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <title><%=application.getAttribute("organizationName") %></title>
    </head>
    <body>
        <% HttpSession sessione = request.getSession(false);
        boolean urlRewrite = request.getAttribute("URLRewrite") != null;
        String newHref = (urlRewrite) ? ";jsessionid=" + sessione.getId() : "";

        String username = (String) session.getAttribute("username");
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
                    dashboard = "<a href='Adherent" + newHref + "' class='nav-link'>Dashboard</a>";
                    break;
                case SIMPATIZZANTE:
                    dashboard = "<a href='Sympathizer" + newHref + "' class='nav-link'>Dashboard</a>";
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            usernameBox = "<a id='loggedUser'>" + username + "</a>";
            logout = "<a href='Logout" + newHref + "' class='nav-link'>Logout</a>";

        }%>
        <header>
            <nav>
                <a href="Homepage" id="navigationTitle">
                    <img src="res/img/title-black.png" id="titleImg" alt="Tum4Word_Icon">
                </a>
                <div id="navbar">
                    <div id="menu">
                        <div class="logo-links">
                            <a href="Homepage">
                                <img src="res/img/blue-logo.png" id="HomePageImg" alt="Tum4Word_Logo">
                            </a>
                        </div>
                        <div class="nav-links">
                            <%= dashboard%>
                            <a href="WhoWeAre<%=newHref%>" class="nav-link">Chi siamo</a>
                            <a href="Activities<%=newHref%>" class="nav-link">Attività</a>
                            <a href="ContactUs<%=newHref%>" class="nav-link">Contatti</a>
                        </div>
                        <div class="user-links">
                            <%= signin%>
                            <%= login%>
                            <%= usernameBox%>
                            <%= logout%>
                        </div>
                    </div>
                    <div id="motivations">
                        <span id="phrase"></span>
                    </div>
                </div>
            </nav>
        </header>
        <script>
            let motivations = <%= application.getAttribute("motivations")%>;
            let counter = 1;

            populateMotivations();
            setInterval(populateMotivations, 20000)

            function populateMotivations() {
                let element = document.getElementById("phrase");
                element.style.opacity = 0;

                setTimeout(function () {
                    element.innerText = "\"" + motivations[counter] + "\"";
                    element.style.opacity = 1;
                    counter += 1;
                    if (counter === motivations.length) {
                        counter = 0;
                    }
                }, 500);
            }

            window.addEventListener('scroll', function() {
                let title = document.getElementById('navigationTitle');
                let navbar = document.getElementById('navbar');
                let scrollPosition = window.scrollY;

                if (scrollPosition > title.offsetHeight) {
                    navbar.style.position = "fixed";
                    navbar.style.top = "0";
                    navbar.style.zIndex = "5";
                    title.style.marginBottom = navbar.offsetHeight + "px";
                } else {
                    navbar.removeAttribute("style");
                    title.removeAttribute("style");
                }
            });
        </script>