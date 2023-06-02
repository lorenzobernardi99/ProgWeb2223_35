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
                    <a href="#" class="nav-link">Sign in</a>
                    <a href="#" class="nav-link">Login</a>
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