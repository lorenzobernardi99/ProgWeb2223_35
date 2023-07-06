<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<link rel="stylesheet" href="../style/admin.css">
<script src="../scripts/adminDashboard.js"></script>
<script src="../scripts/highcharts.js"></script>
<div id="admin-container">
  <div id="operation-container">
    <div id="users-container" class="containers">
      <h2>Utenti</h2>
      <div id="users-button">
        <input type="button" name="allUsers" value="Tutti" onclick="retrieveUsers('users')">
        <input type="button" name="Supporter" value="Simpatizzanti" onclick="retrieveUsers('supporters')">
        <input type="button" name="Member" value="Aderenti" onclick="retrieveUsers('members')">
      </div>
      <table id="users"></table>
    </div>

    <div id="charts">
      <div id="views-container" class="containers">
        <div id="views-button">
          <input type="button" name="Views" value="Visite del sito" onclick="viewsPerPage()">
        </div>
        <span id="total"></span>
        <div id="chart" ></div>
        <div id="reset-button">
          <input type="button" name="resetAll" value="Resetta tutti" onclick="resetViews('total')">
          <input type="button" name="resetHomePage" value="Resetta homepage" onclick="resetViews('homepage')">
          <input type="button" name="resetWhoWeAre" value="Resetta chi siamo" onclick="resetViews('whoWeAre')">
          <input type="button" name="resetActivities" value="Resetta attività" onclick="resetViews('activities')">
          <input type="button" name="resetContactUs" value="Resetta contatti" onclick="resetViews('contactUs')">
          <input type="button" name="resetSignIn" value="Resetta sign in" onclick="resetViews('signIn')">
          <input type="button" name="resetLogin" value="Resetta login" onclick="resetViews('login')">
        </div>
      </div>

      <div id="donations-container" class="containers">
        <div id="donations-button">
          <input type="button" name="Donation" value="Donazioni ricevute" onclick="donationReceived()">
        </div>
        <div id="donationChart" style="width:100%; height:400px;"></div>
      </div>
    </div>
  </div>
</div>
<%@ include file="../footer.jsp"%>