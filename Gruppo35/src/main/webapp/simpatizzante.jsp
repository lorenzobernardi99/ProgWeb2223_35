<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>


<link rel="stylesheet" href="style/simpatizzanteStyle.css">
<script src="./scripts/simpatizzante.js"></script>

<div id="banner"></div>
<div class="dashboard">
  <div class="element" style="grid-column: span 2;">
    <h2>Dati personali</h2>
    <button type="button" class="button" id="UserData" onclick="buttonpressed();">Mostra i dati personali</button>
    <div id="tabella" class="tabella"></div>
  </div>
  <div class="element">
    <h2>Attività</h2>
    <button class="button">Iscriviti alle attività</button>
  </div>
  <div class="element">
    <h2>Logout</h2>
    <button class="button" onclick="BannerDelete();">Cancella il profilo</button>
  </div>
  <div class="element" style="grid-column: span 2;">
    <h2>Sostienici con una donazione!</h2>
    <label><input type="text" class="donation" placeholder="Inserisci l'importo"></label>
    <button class="button don" >Dona</button>
  </div>
</div>



<%@ include file="footer.jsp"%>