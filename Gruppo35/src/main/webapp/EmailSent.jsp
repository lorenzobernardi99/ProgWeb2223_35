<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Email sent</title>
  </head>
  <body>
  <!--TODO:
        - mancano header e footer
  -->
  <div>
    <p>
      <%=application.getAttribute("sendingConfirmed")%>
    </p>
  </div>
  </body>
</html>
