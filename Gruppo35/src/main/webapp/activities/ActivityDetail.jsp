<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="activityBean" class="web.esame.gruppo35.beans.ActivityBean" scope="request"/>
<%@ include file="../header.jsp"%>
<link rel="stylesheet" type="text/css" href="../style/activity.css">
<h1>
    <%=activityBean.getName()%>
</h1>
<section>
    <img src="<%=activityBean.getImagePath()%>" alt="<%= activityBean.getName()%>"/>
    <p>
        <%=activityBean.getDescription()%>
    </p>
</section>
<%@ include file="../footer.jsp"%>