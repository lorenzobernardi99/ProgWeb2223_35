<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <body>
    <div>
      <p>
        <%=application.getAttribute("sendingConfirmed")%>
      </p>
    </div>
  </body>
  <%@include file="footer.jsp"%>
</html>
