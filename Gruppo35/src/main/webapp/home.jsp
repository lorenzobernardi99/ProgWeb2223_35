<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<body>
  <div class="top-image">
    <div class="top-text">
      <h1><%=application.getAttribute("organizationName") %> Association</h1>
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
  <script>
    function downloadPDF() {
      window.location.href = 'res/docs/tum4world.pdf';
    }
  </script>
<%@include file="footer.jsp"%>