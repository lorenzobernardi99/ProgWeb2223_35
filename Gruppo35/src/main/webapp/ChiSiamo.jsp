
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.html"%>
<head>
    <link rel="stylesheet" href="CSSsheets/ChiSiamoStyle.css">
    <title>Chi Siamo</title>
</head>
<body>

<script>
    function fc(x){
        const element=x;

        switch(element.id){
            case 'li0': document.getElementById("argomento").innerText="Descrizione"; break;
            case 'li1':document.getElementById("argomento").innerText="fondazione";break;
            case 'li2':document.getElementById("argomento").innerText="Storia";break;
            case 'li3': document.getElementById("argomento").innerText="Sedi"; break;
            case 'li4':document.getElementById("argomento").innerText="Riconoscimenti"; break;
            case 'li5':document.getElementById("argomento").innerText="Investimenti";
        }
        document.getElementsByClassName("rettangolo focus")[0].className="rettangolo";
        element.className="rettangolo focus";
    }

</script>
<div id="titoli">
    <ul>
        <li><span class="rettangolo focus" id="li0" onmouseover="fc(this)">&#128311 Descrizione </span></li>
        <li><span class="rettangolo" id="li1" onmouseover="fc(this)">&#128311 Fondazione </span></li>
        <li><span class="rettangolo" id="li2" onmouseover="fc(this)">&#128311 Storia </span></li>
        <li><span class="rettangolo" id="li3" onmouseover="fc(this)">&#128311 Sedi </span></li>
        <li><span class="rettangolo" id="li4" onmouseover="fc(this)">&#128311 Riconoscimenti </span></li>
        <li><span class="rettangolo" id="li5" onmouseover="fc(this)">&#128311 Investimenti</span></li>
    </ul>
</div>

<div id="argomento">
    Descrizione
</div>
</body>
</html>