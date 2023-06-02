<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/signInStyle.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<body>
<div id="signInContainer">
  <h1>Registrazione</h1>
  <i id="errorText"></i>
  <form id="signInForm" method="post" action="SignIn" onsubmit="return validateData()">
    <label for="name">Nome:</label>
    <input type="text" id="name" name="name" placeholder="Mario">

    <label for="surname">Cognome: </label>
    <input type="text" id="surname" name="surname" placeholder="Rossi">

    <label for="birth">Data di nascita (GG/MM/AAAA):</label>
    <input type="date" id="birth" name="birth" min="1900-01-01" max="2023-01-01">
    <span class="validity"></span>

    <div class="email-input-container">
      <span class="email-labels">
        <label for="email">Indirizzo email:</label>
        <label for="email" class="errorLabel">Indirizzo email non valido</label>
      </span>
      <input type="email" id="email" name="email" placeholder="example@gmail.com">
    </div>

    <label for="phone">Numero di telefono:</label>
    <div class="phone-input-container">
      <input type="tel" id="prefix" oninput="formatPrefix(this)" value="+39">
      <input type="tel" id="phone" name="phone" oninput="formatPhoneNumber(this)" placeholder="222-333-4444">
    </div>

    <label for="role">Ruolo:</label>
    <select id="role" name="role">
      <option value="seleziona" selected>Seleziona un ruolo</option>
      <option value="simpatizzante">Simpatizzante</option>
      <option value="aderente">Aderente</option>
    </select>

    <label for="username">Username:</label>
    <input type="text" id="username" name="username" placeholder="es: mariorossi0" autocomplete="new-username">

    <label for="password">Password:</label>
    <div class="password-input">
      <input type="password" id="password" name="password" maxlength="8" autocomplete="new-password">
      <span id="generatePassword" class="generate-password fa-solid fa-arrows-rotate"></span>
      <span id="togglePassword" data-target="password" class="toggle-password fas fa-eye"></span>
    </div>

    <div class="password-instructions">
      <p><em>La password deve soddisfare i seguenti requisiti:</em></p>
      <ul>
        <li>Essere lunga esattamente 8 caratteri</li>
        <li>Contenere la prima lettera dei nomi (J, E, L, M)</li>
        <li>Contenere almeno un carattere numerico</li>
        <li>Contenere almeno una lettera maiuscola</li>
        <li>Contenere almeno un carattere speciale tra $, !, e ?</li>
      </ul>
    </div>

    <label for="confirmPassword">Conferma password:</label>
    <div class="password-input">
      <input type="password" id="confirmPassword" maxlength="8" autocomplete="new-password">
      <span id="togglePassword2" data-target="confirmPassword" class="toggle-password fas fa-eye"></span>
    </div>

    <label for="rememberMe">
      <input type="checkbox" id="rememberMe" name="rememberMe" checked>
      Remember me
    </label>


    <p>Creando un account accetti i nostri
      <b style="cursor: pointer; text-decoration: underline">Termini e Privacy</b>
    </p>

    <input type="submit" value="Registrati">
    <input type="reset" value="Reset">
  </form>
</div>

<script src="${pageContext.request.contextPath}/scripts/singInValidation.js"></script>

<%@ include file="footer.jsp"%>

