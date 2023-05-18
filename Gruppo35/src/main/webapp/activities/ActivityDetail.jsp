<%--
  Created by IntelliJ IDEA.
  User: jacobbista
  Date: 14/05/23
  Time: 13:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<jsp:useBean id="activityBean" class="web.esame.gruppo35.beans.ActivityBean" scope="request"/>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Dettaglio <%=activityBean.getName()%> </title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<header>
    <h1>
        <%=activityBean.getName()%>
    </h1>
</header>
<section>
    <img src="<%=activityBean.getImagePath()%>" alt="<%= activityBean.getName()%>"/>
    <h2>
        <%=activityBean.getName()%>
    </h2>
    <p>
        <%=activityBean.getDescription()%>
    </p>
</section>
</body>
</html>