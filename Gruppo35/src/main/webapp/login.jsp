<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/login.css">
<script src="${pageContext.request.contextPath}/scripts/login.js"></script>
<div id="login-container">
  <h1>Login</h1>
  <form name="login_data" action="ValidateLogin" method="POST" onsubmit="return validateData()">
      <span id="errorText"><%=request.getAttribute("message")%></span>

      <label for="username">Username:</label>
      <input type="text" id="username" name="username" placeholder="Mario00">

      <label for="password">Password:</label>
      <input type="password" id="password" name="password" placeholder="password">

      <input type="submit" value="Login">
  </form>
</div>
<%@include file="footer.jsp"%>