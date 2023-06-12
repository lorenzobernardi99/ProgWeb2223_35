<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="style/CookiesPolicy.css">
<% String URL=String.valueOf(request.getAttribute("URL"));%>
<div id="banner">
    <p class="text">
        Questo sito fa utilizzo di cookie di navigazione per migliorare la fruibilità da parte del navigatore.
        Puoi liberamente rifiutare o revocare il tuo consenso in qualsiasi momento,
        personalizzando le tue preferenze. Cliccando sul pulsante "Acconsento ai cookies"
        acconsenti all'uso di tali tecnologie per tutte le finalità indicate.
        Per ulteriori informazioni, consulta la nostra informativa sulla
        privacy.
    </p>
    <form class="policy-form">
        <button type="submit" formaction="<%= URL %>" name="FormAccept" value="false" class="but refuse" onclick="document.body.classList.remove('disabled')"> Rifiuto </button>
        <button type="submit" formaction="<%= URL %>" name="FormAccept" value="true" class="but accept" onclick="document.body.classList.remove('disabled')"> Acconsento ai cookies </button>
    </form>
</div>