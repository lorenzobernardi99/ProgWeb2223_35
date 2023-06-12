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
        <%
            String linkParam = (urlRewrite) ? ";jsessionid=" + sessione.getId() + "?id=" : "?id=";
            for (ActivityBean ab : activityBeanList) {
        %>
        <div class="ActivitiesLink">
            <a href="Activities<%=linkParam%><%=ab.getId()%>">
                <img src="<%=application.getAttribute("activitiesImagePath")%><%=ab.getImagePath()%>" alt="<%=ab.getName()%>">
            </a>
            <br>
            <h2>
                <a href="Activities<%=linkParam%><%=ab.getId()%>"</a>
            </h2>
        </div>
        <%}%>
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