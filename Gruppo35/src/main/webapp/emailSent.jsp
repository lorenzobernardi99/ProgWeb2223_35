<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<link rel="stylesheet" href="style/emailSent.css">
<div id="email-sent">
  <p id="email-message">
    <%=application.getAttribute("sendingConfirmed")%>
  </p>
</div>
<%@include file="footer.jsp"%>
