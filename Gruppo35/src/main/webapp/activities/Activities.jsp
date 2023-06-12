<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%@ page import="web.esame.gruppo35.beans.ActivityBean" %>
<link rel="stylesheet" type="text/css" href="../style/activities.css">
<div id="activities-description">
    <p>
        <%=application.getAttribute("activities_description")%>
    </p>
</div>
<section>
    <article>
        <jsp:useBean id="activityBeanList" class="web.esame.gruppo35.helperClasses.ActivityBeanList" scope="application"/>
        <% for (ActivityBean ab : activityBeanList) {
            if(req=="True"){
        %>
        <div class="ActivitiesLink">
            <a href='Activities;jsessionid=<%=sessione.getId()%>?id=<%=ab.getId()%>'>
                <img src="<%=ab.getImagePath()%>" alt="<%=ab.getName()%>">
            </a>
            <br>
            <h2>
                <a href='Activities;jsessionid=<%=sessione.getId()%>?id=<%=ab.getId()%>'><%=ab.getName()%></a>
            </h2>
        </div>
        <%}else{%>
        <div class="ActivitiesLink">
            <a href='Activities?id=<%=ab.getId()%>'>
                <img src="<%=ab.getImagePath()%>" alt="<%=ab.getName()%>">
            </a>
            <br>
            <h2>
                <a href='Activities?id=<%=ab.getId()%>'><%=ab.getName()%></a>
            </h2>
        </div>
           <%}
        }%>
    </article>
</section>
<div id="credits">
    <figcaption id="caption">
        Foto di
        <a href="https://unsplash.com/@crtvben?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText">Ben Richardson</a>,
        <a href="https://unsplash.com/@brunus?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText">Bruno Martins</a>,
        <a href="https://unsplash.com/pt-br/@shots_of_aspartame?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText">Julia Joppien</a>
        su
        <a href="https://unsplash.com/it/foto/3k3l2brxmwQ?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText">Unsplash</a>
    </figcaption>
</div>
<%@ include file="../footer.jsp"%>
