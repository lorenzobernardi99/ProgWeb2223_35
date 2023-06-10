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

<div id="container">
  <h2>Operazioni Disponibili</h2>
  <input type="button" name="allUsers" value="Tutti gli utenti" onclick="retrieveUsers('users')">
  <br><br>
  <table id="users"></table>
  <br><br>
  <input type="button" name="Supporter" value="Simpatizzanti" onclick="retrieveUsers('supporters')">
  <br><br>
  <table id="supporters"></table>
  <br><br>
  <input type="button" name="Member" value="Aderenti" onclick="retrieveUsers('members')">
  <br><br>
  <table id="members"></table>
  <br><br>
  <input type="button" name="Views" value="Visite per pagina" onclick="viewsPerPage()">
  <h3 id="total"></h3>
  <div id="chart" style="width:100%; height:400px;"></div>
  <input type="button" name="resetAll" value="Resetta tutti" onclick="resetViews('<%=keys[0]%>')">
  <input type="button" name="resetHomePage" value="Resetta homepage" onclick="resetViews('<%=keys[1]%>')">
  <input type="button" name="resetWhoWeAre" value="Resetta chi siamo" onclick="resetViews('<%=keys[2]%>')">
  <input type="button" name="resetActivities" value="Resetta attività" onclick="resetViews('<%=keys[3]%>')">
  <input type="button" name="resetContactUs" value="Resetta contatti" onclick="resetViews('<%=keys[4]%>')">
  <input type="button" name="resetSignIn" value="Resetta sign in" onclick="resetViews('<%=keys[5]%>')">
  <input type="button" name="resetLogin" value="Resetta login" onclick="resetViews('<%=keys[6]%>')">
  <br><br>
  <input type="button" name="Donation" value="Donazioni ricevute" onclick="donationReceived()">
  <div id="donationChart" style="width:100%; height:400px;"></div>
</div>
<script src="../scripts/adminDashboard.js"></script>
<script src="../scripts/highcharts.js"></script>
<%@ include file="../footer.jsp"%>