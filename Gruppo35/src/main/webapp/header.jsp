<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
    <head>
        <link rel="stylesheet" href="style/headStyle.css">
        <link rel="stylesheet" href="style/footStyle.css">
        <title><%=application.getAttribute("organizationName") %></title>
    </head>
    <body>
        <header>
            <nav>
                <a href="Homepage" class="logo-link">
                    <img src="res/img/tum4world.jpg" class="img" alt="Tum4Word_Icon">
                    <span class="textLogo"><b><%=application.getAttribute("organizationName") %></b></span>
                </a>
                <div class="nav-links">
                    <a href="Homepage" class="nav-link">Home Page</a>
                    <a href="WhoWeAre" class="nav-link">Chi siamo</a>
                    <a href="Activities" class="nav-link">Attività</a>
                    <a href="ContactUs" class="nav-link">Contatti</a>
                </div>
                <div class="user-links">
                    <a href="SignIn" class="nav-link">Sign in</a>
                    <a href="#" class="nav-link">Login</a>
                </div>
            </nav>
        </header>
