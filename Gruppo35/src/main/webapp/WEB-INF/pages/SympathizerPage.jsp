<%@ page import="web.esame.gruppo35.beans.ActivityBean" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp"%>


<link rel="stylesheet" href="../../style/simpatizzanteStyle.css">
<script src="../../scripts/sympathizer.js"></script>

<jsp:useBean id="activityBeanList" class="web.esame.gruppo35.helperClasses.ActivityBeanList" scope="application"/>

<div id="banner"></div>
<div class="dashboard">
  <div class="element" style="grid-column: span 2;">
    <h2>Dati personali</h2>
    <button type="button" class="button" id="UserData" onclick="getUserData();">Mostra i dati personali</button>
    <div id="tabella" class="tabella"></div>
  </div>
  <div class="element">
    <h2>Attività</h2>
    <button class="button" id="activitiesButton" onclick="getActivities()">Iscriviti alle attività</button>
    <div id="activities"></div>
  </div>
  <div class="element">
    <h2>Elimina account</h2>
    <button class="button" onclick="BannerDelete();">Elimina</button>
  </div>
</div>



<%@ include file="../../footer.jsp"%>