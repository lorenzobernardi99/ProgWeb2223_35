<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%@ page import="web.esame.gruppo35.beans.ActivityBean" %>
<body>
<section>
    <article>
        <jsp:useBean id="activityBeanList" class="web.esame.gruppo35.helperClasses.ActivityBeanList" scope="application"/>
        <%
            String linkParam = (urlRewrite) ? ";jsessionid=" + sessione.getId() + "?id=" : "?id=";
            for (ActivityBean ab : activityBeanList) {
        %>
        <div>
            <a class="ActivitiesLink" href="Activities<%=linkParam%><%=ab.getId()%>">
                <img src="<%=ab.getImagePath()%>" alt="<%=ab.getName()%>">
            </a>
            <br>
            <h2>
                <a class="ActivitiesLink" href="Activities<%=linkParam%><%=ab.getId()%>"><%=ab.getName()%></a>
            </h2>
        </div>
        <% } %>
    </article>
</section>
<%@ include file="../footer.jsp"%>