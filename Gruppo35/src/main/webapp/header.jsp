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
        <header>
            <nav>
                <a href="Homepage">
                    <img src="res/img/logo-no-background.png" class="img" alt="Tum4Word_Icon">
                </a>
                <div id="menu">
                    <div class="nav-links">
                        <a href="Homepage" class="nav-link">Home Page</a>
                        <a href="WhoWeAre" class="nav-link">Chi siamo</a>
                        <a href="Activities" class="nav-link">Attività</a>
                        <a href="ContactUs" class="nav-link">Contatti</a>
                    </div>
                    <div class="user-links">
                        <% String username = (String) session.getAttribute("username");
                            String signin = "";
                            String login = "";
                            String usernameBox = "";
                            String logout = "";
                            if (username == null) {
                                signin = "<a href='SignIn' class='nav-link'>Sign in</a>";
                                login = "<a href='Login' class='nav-link'>Login</a>";
                            }
                            else{
                                usernameBox = "<a id='loggedUser'>" + username + "</a>";
                                logout = "<a href='Logout' class='nav-link'>Logout</a>";
                            }%>
                        <%= signin%>
                        <%= login%>
                        <%= usernameBox%>
                        <%= logout%>
                    </div>
                </div>
                <div id="motivations">
                    <span id="phrase"></span>
                </div>
            </nav>
        </header>
            <%
        HttpSession sessione=request.getSession(false);
      String req=(String) request.getAttribute("URLRewrite");
        if(req=="True"){%>
        <script>
            var elements=document.getElementsByClassName("nav-link");
            var n=elements.length;
            for(let i=0; i<n;i++){
                var hrefP=elements.item(i).getAttribute("href");
                var newHref=hrefP+";jsessionid=<%=sessione.getId()%>";
                elements.item(i).setAttribute("href",newHref);
            }
            elements=document.getElementsByClassName("logo-link");
            var hrefP=elements.item(0).getAttribute("href");
            var newHref=hrefP+";jsessionid=<%=sessione.getId()%>";
            elements.item(0).setAttribute("href",newHref);
        </script>
<%}%>