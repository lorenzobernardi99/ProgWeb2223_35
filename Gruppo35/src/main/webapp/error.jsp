<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<%@ include file="header.jsp"%>
<link rel="stylesheet" href="style/error.css">
<div id="error-container">
    <p id="error-box">
        <b> <%= request.getAttribute("errorMessage") %> </b>
        <i class="fa-regular fa-face-sad-tear fa-bounce"></i>
    </p>
</div>
<%@include file="footer.jsp"%>
