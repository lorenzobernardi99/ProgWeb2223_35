<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<body>
    <div>
      <p>
        <%=application.getAttribute("sendingConfirmed")%>
      </p>
    </div>
<%@include file="footer.jsp"%>
