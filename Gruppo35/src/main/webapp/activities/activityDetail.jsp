<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="activityBean" class="web.esame.gruppo35.beans.ActivityBean" scope="request"/>
<%@ include file="../header.jsp"%>
<link rel="stylesheet" type="text/css" href="../style/activity.css">
<div id="section-container">
    <h1>
        <%=activityBean.getName()%>
    </h1>
    <section>
        <img src="<%=application.getAttribute("activitiesImagePath")%><%=activityBean.getImagePath()%>" alt="<%= activityBean.getName()%>"/>
        <p>
            <%=activityBean.getDescription()%>
        </p>
    </section>
</div>
<%@ include file="../footer.jsp"%>