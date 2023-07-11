<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% UserRole loggedRole = (UserRole) session.getAttribute("role");
  boolean isAdherent = loggedRole == UserRole.ADERENTE;
  if(isAdherent){ %>
    <link rel="stylesheet" href="../style/adherentStyle.css">
    <script src="../scripts/donationScript.js"></script>
<%}else{%>
   <link rel="stylesheet" href="../style/sympathizerStyle.css">
<%}%>

<%@ include file="../header.jsp"%>
<script src="../scripts/memberDashboard.js<%=newHref%>"></script>

<jsp:useBean id="activityBeanList" class="web.esame.gruppo35.helperClasses.ActivityBeanList" scope="application"/>

<div id="dashboard">
  <div id="operationContainer">

    <div class="element">
      <h2>Dati personali</h2>
      <button id="UserData" onclick="getUserData();">Mostra i dati personali</button>
      <div id="tabella" class="tabella user-table"></div>
    </div>

    <div class="element">
      <h2>Attività</h2>
      <button id="activitiesButton" onclick="getActivities()">Iscriviti alle attività</button>
      <div id="activities"></div>
    </div>

    <% if(isAdherent){%>
      <div class="element" style="grid-column: span 1;" id="donationContainer">
        <h2>Sostienici con una donazione!</h2>
        <label id="donationLabel">
          <input type="text" id="donationAmount" class="donation" placeholder="Inserisci l'importo in euro" maxlength="9">
        </label>
        <button id="donateButton" class="donate" onclick="sendDonation()">Dona</button>
        <div id="donationOutcome" class="donation-outcome">
          <h2 id="donationOutcomeMessageSuccess">
            Abbiamo ricevuto la tua donazione.
            <br>
            Grazie per il tuo contributo!
            <i class="fa-regular fa-face-laugh-beam fa-beat-fade"></i>
            <br>
            Il tuo aiuto significa molto per noi.
          </h2>
          <h2 id="donationOutcomeMessageError">
            Qualcosa è andato storto.
            <i class="fa-regular fa-face-surprise fa-shake"></i>
            <br>
            La invitiamo a riprovare.
          </h2>
        </div>
      </div>
    <%}%>
    <div id="deleteAccount" class="element">
      <h2>Elimina account</h2>
      <button class="button" onclick="showDeleteBanner();">Elimina</button>
      <div id="deleteBanner">
        <p>Sei sicuro di voler cancellare il tuo account?</p>
        <form action="DeleteAccount<%=newHref%>">
          <button id="deleteAccountButton" type="submit" class="button">Cancella Account</button>
          <button id="deleteBannerDismissButton" class="button">Annulla operazione</button>
        </form>
      </div>

    </div>

  </div>
</div>



<%@ include file="../footer.jsp"%>