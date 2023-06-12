<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/registrationDone.css">
<div id="registration-box">
    <p id="registration-message">
        <%=application.getAttribute("registrationDone")%>
    </p>
</div>
<%@include file="footer.jsp"%>
