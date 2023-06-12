<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<link rel="stylesheet" href="style/whoWeAre.css">
<div id="container">
    <table>
        <tr>
            <td id="description-td" onclick="changeText(this)">
                <span>Descrizione</span>
            </td>
            <td id="foundation-td" onclick="changeText(this)">
                <span>Fondazione</span>
            </td>
            <td id="history-td" onclick="changeText(this)">
                <span>Storia</span>
            </td>
        </tr>
        <tr>
            <td id="locations-td" onclick="changeText(this)">
                <span>Sedi</span>
            </td>
            <td id="accolades-td" onclick="changeText(this)">
                <span>Riconoscimenti</span>
            </td>
            <td id="investments-td" onclick="changeText(this)">
                <span>Investimenti</span>
            </td>
        </tr>
    </table>
    <div id="information-box">
        <p id="information">Clicca su una voce per saperne di più</p>
    </div>
</div>
<div id="credits">
    <figcaption id="caption">
        Foto di
        <a href="https://unsplash.com/@earl_plannerzone?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText">Earl Wilcox</a>,
        <a href="https://unsplash.com/@wegenerb?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText">bill wegener</a>,
        <a href="https://unsplash.com/@zeak?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText">Dmitrij Paskevic</a>,
        <a href="https://unsplash.com/@yibeigeng?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText">Yibei Geng</a>,
        <a href="https://unsplash.com/@tylagalo?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText">Tyler Lagalo</a>,
        <a href="https://unsplash.com/@clarke_designs_photography?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText">Sam Clarke</a>,
        su
        <a href="https://unsplash.com/it/foto/3k3l2brxmwQ?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText">Unsplash</a>
    </figcaption>
</div>
<script>
    function changeText(x){
        var text;
        switch(x.id){
            case 'description-td': text = "<%=application.getAttribute("description")%>";
                break;
            case 'foundation-td': text = "<%=application.getAttribute("foundation")%>";
                break;
            case 'history-td': text = "<%=application.getAttribute("history")%>";
                break;
            case 'locations-td': text = "<%=application.getAttribute("locations")%>";
                break;
            case 'accolades-td': text = "<%=application.getAttribute("accolades")%>";
                break;
            case 'investments-td': text = "<%=application.getAttribute("investments")%>";
                break;
        }
        document.getElementById("information").innerText = text;
    }
</script>
<%@ include file="footer.jsp"%>