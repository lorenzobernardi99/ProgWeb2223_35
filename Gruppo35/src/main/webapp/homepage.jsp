<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/homepage.css">
<div class="content-container">
  <div class="content-opacity">
    <div class="logo">
      <img src="${pageContext.request.contextPath}/res/img/logo-no-background.png" alt="Tum4World">
    </div>
    <div class="description">
    <p>
      L'Associazione Tum4World e' un'organizzazione internazionale dedicata alla riduzione e all'eliminazione della fame nel mondo.
      Fondata nel 2023, l'associazione si impegna a combattere l'insicurezza alimentare e a garantire che ogni individuo abbia accesso a cibo nutriente e sufficiente.
    </p>
    </div>
    <figure>
      <figcaption id="caption">
        Foto di
        <a href="https://unsplash.com/@jmuniz?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText">Joel Muniz</a>
        su
        <a href="https://unsplash.com/it/foto/3k3l2brxmwQ?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText">Unsplash</a>
      </figcaption>
    </figure>
  </div>
</div>
<div class="download-brochure-container">
  <h2>Scarica maggiori informazioni:</h2>
  <button id="downloadButton" onclick="downloadPDF()">Scarica volantino</button>
</div>
  <script>
    function downloadPDF() {
      window.location.href = 'res/docs/tum4world.pdf';
    }
  </script>
<%@include file="footer.jsp"%>