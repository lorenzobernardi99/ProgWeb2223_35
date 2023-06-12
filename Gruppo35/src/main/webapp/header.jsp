<%@ page import="java.util.Objects" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
    <head>
        <link rel="stylesheet" href="style/headStyle.css">
        <link rel="stylesheet" href="style/footStyle.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <title><%=application.getAttribute("organizationName") %></title>
    </head>
    <body>
        <% HttpSession sessione = request.getSession(false);
        boolean urlRewrite = request.getAttribute("URLRewrite") != null;
        String newHref = (urlRewrite) ? ";jsessionid=" + sessione.getId() : ""; %>
        <header>
            <nav>
                <a href="Homepage<%=newHref%>" class="logo-link">
                    <img src="res/img/tum4world.jpg" class="img" alt="Tum4Word_Icon">
                    <span class="textLogo"><b><%=application.getAttribute("organizationName") %></b></span>
                </a>
                <div class="nav-links">
                    <a href="Homepage<%=newHref%>" class="nav-link">Home Page</a>
                    <a href="WhoWeAre<%=newHref%>" class="nav-link">Chi siamo</a>
                    <a href="Activities<%=newHref%>" class="nav-link">Attività</a>
                    <a href="ContactUs<%=newHref%>" class="nav-link">Contatti</a>
                </div>
                <div class="user-links">
                    <% String username = (String) session.getAttribute("username");
                        String signin = "";
                        String login = "";
                        String logout = "";
                        if (username == null) {
                            signin = "<a href='SignIn" + newHref + "' class='nav-link'>Sign in</a>";
                            login = "<a href='Login" + newHref + "' class='nav-link'>Login</a>";
                        }
                        else
                            logout = "<a href='Logout" + newHref + "' class='nav-link'>Logout</a>";%>
                    <%= signin%>
                    <%= login%>
                    <%= logout%>
                </div>
            </nav>
        </header>