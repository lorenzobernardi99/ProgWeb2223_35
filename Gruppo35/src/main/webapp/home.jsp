<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html lang="it">
  <link rel="stylesheet" href="style/home.css">
  <script>
    function downloadPDF() {
      window.location.href = 'res/docs/Tum4worldA5.pdf';
    }
  </script>
  <body>

    <div class="top-image">
      <div class="top-text">
        <h1>Tum4World Association</h1>
        <button>Scopri di più</button>
      </div>
    </div>
    <p>
      L'Associazione Tum4World e' un'organizzazione internazionale dedicata alla riduzione e all'eliminazione della fame nel mondo.
      Fondata nel 2023, l'associazione si impegna a combattere l'insicurezza alimentare e a garantire che ogni individuo abbia accesso a cibo nutriente e sufficiente.
    </p>
    <div>
      <h2>Scarica maggiori informazioni:</h2>
      <button onclick="downloadPDF()">Scarica PDF</button>
    </div>

  </body>
  <%@include file="footer.jsp"%>
</html>
