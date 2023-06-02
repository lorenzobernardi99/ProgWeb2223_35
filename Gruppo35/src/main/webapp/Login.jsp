<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<div>
  <h2>Login</h2>
  <form name="login_data" action="ValidateLoginServlet" method="POST" onsubmit="return validateData()">
      <input type="text" id="username" name="username" placeholder="username">
      <input type="password" id="password" name="password" placeholder="password">
      <input type="submit" value="Login">
  </form>
</div>
<script>
  function validateData(){
    let username = document.forms["login_data"]["username"].value;
    let password = document.forms["login_data"]["password"].value;
    if (username === "" || password ==="") {
      alert("Username e password non possono essere vuoti");
      return false;
    }
    else return true;
  }
</script>
<%@include file="footer.jsp"%>