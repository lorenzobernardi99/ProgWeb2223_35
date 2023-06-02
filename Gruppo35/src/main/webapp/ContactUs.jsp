<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ page import="java.util.List" %>
    <div>
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
    <div>
        <h1>
            Contattaci
        </h1>
        <form action="ValidateContactForm" method="post" onsubmit="return validateEmail()">
            <label for="name">Nome:</label>
                <input type="text" id="name" name="name" required="required">
            <br>
            <label for="surname">Cognome:</label>
                <input type="text" id="surname" name="surname" required="required">
            <br>
            <label for="email">E-mail:</label>
                <input type="email" id="email" name="email" required="required">
            <br>
            <label>
                Perchè vorresti contattarci?
                <select name="reason">
                    <% List<String> reasons = (List<String>) application.getAttribute("reasons");
                        for (String reason : reasons) { %>
                        <option value="<%= reason %>"><%= reason %></option>
                    <% } %>
                </select>
            </label>
            <br>
            <label for="details">Potresti fornirci qualche dettaglio in più: </label>
                <textarea id="details" name="details"></textarea>
            <br>
            <input type="submit" value="Invia">
            <input type="reset" value="Cancella">
        </form>
        <%=request.getAttribute("message")%>
    </div>
    <script>
        function validateEmail() {
            let email = document.getElementById('email');
            const regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
            if(regex.test(email.value)){
                return true
            }
            else{
                email.style.border = "1px solid red"
                return false
            }
        }
    </script>
<%@include file="footer.jsp"%>
