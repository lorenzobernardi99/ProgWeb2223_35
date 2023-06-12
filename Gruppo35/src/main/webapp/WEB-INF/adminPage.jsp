<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.LinkedHashMap" %>
<%@ include file="../header.jsp"%>

<% // views map
  LinkedHashMap<String, Object> viewsMap = (LinkedHashMap<String, Object>) application.getAttribute("views");

  // keys array
  String[] keys = null;
  if (viewsMap != null && !viewsMap.isEmpty()) {
    keys = viewsMap.keySet().toArray(new String[0]);
  }
  assert keys != null;%>
<link rel="stylesheet" href="../style/admin.css">
<script src="../scripts/adminDashboard.js"></script>
<script src="../scripts/highcharts.js"></script>
<div id="admin-container">
  <h2>Operazioni Disponibili</h2>
  <div id="operation-container">
    <div id="users-button">
      <input type="button" name="allUsers" value="Utenti registrati" onclick="retrieveUsers('users')">
      <input type="button" name="Supporter" value="Solo simpatizzanti" onclick="retrieveUsers('supporters')">
      <input type="button" name="Member" value="Solo aderenti" onclick="retrieveUsers('members')">
    </div>
    <table id="users"></table>

    <div id="views-button">
      <input type="button" name="Views" value="Visite del sito" onclick="viewsPerPage()">
    </div>
    <h3 id="total"></h3>
    <div id="chart" ></div>
    <div id="reset-button">
      <input class="reset" type="button" name="resetAll" value="Resetta tutti" onclick="resetViews('<%=keys[0]%>')">
      <input class="reset" type="button" name="resetHomePage" value="Resetta homepage" onclick="resetViews('<%=keys[1]%>')">
      <input class="reset" type="button" name="resetWhoWeAre" value="Resetta chi siamo" onclick="resetViews('<%=keys[2]%>')">
      <input class="reset" type="button" name="resetActivities" value="Resetta attività" onclick="resetViews('<%=keys[3]%>')">
      <input class="reset" type="button" name="resetContactUs" value="Resetta contatti" onclick="resetViews('<%=keys[4]%>')">
      <input class="reset" type="button" name="resetSignIn" value="Resetta sign in" onclick="resetViews('<%=keys[5]%>')">
      <input class="reset" type="button" name="resetLogin" value="Resetta login" onclick="resetViews('<%=keys[6]%>')">
    </div>

    <div id="donations-button">
      <input type="button" name="Donation" value="Donazioni ricevute" onclick="donationReceived()">
    </div>
    <div id="donationChart" style="width:100%; height:400px;"></div>
  </div>
</div>
<%@ include file="../footer.jsp"%>