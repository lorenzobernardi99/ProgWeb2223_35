<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ page import="java.util.List" %>
<link rel="stylesheet" href="style/contactUs.css">
<script src="${pageContext.request.contextPath}/scripts/contactUs.js"></script>
<div id="container">
    <div id="contact-us">
        <h1>
            Contattaci
        </h1>
        <p id="error-message">
            <%=request.getAttribute("message")%>
        </p>
        <form action="ValidateContactForm" method="post" onsubmit="return validateForm()">
            <label for="name-surname">Nome e Cognome:</label>
            <input type="text" id="name-surname" name="name-surname" placeholder="Mario Rossi">

            <label for="email">E-mail:</label>
            <input type="email" id="email" name="email" placeholder="example@gmail.com">

            <label for="reason">Perchè vorresti contattarci?</label>
            <select name="reason" id="reason">
                <% List<String> reasons = (List<String>) application.getAttribute("reasons");
                    for (String reason : reasons) { %>
                    <option value="<%= reason %>"><%= reason %></option>
                <% } %>
            </select>

            <label for="details">Potresti fornirci qualche dettaglio in più: </label>
            <textarea id="details" name="details"></textarea>

            <input type="submit" value="Invia">
            <input type="reset" value="Reset">
        </form>
    </div>
    <div id="contacts">
        <h1>
            I nostri contatti
        </h1>
        <h3>
            Indirizzo:
            <a href="https://www.google.com/maps/search/?api=1&query=<%=application.getAttribute("addressCoordinates")%>" target="_blank">
                <%=application.getAttribute("address")%>
            </a>
        </h3>
        <h3>
            Numero di telefono:
            <a href="tel:<%=application.getAttribute("telephone")%>">
                <%=application.getAttribute("telephone")%>
            </a>
        </h3>
    </div>
</div>
<%@include file="footer.jsp"%>
