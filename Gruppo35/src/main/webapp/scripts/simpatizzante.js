function buttonpressed(){
    let table=document.getElementById("tabella");
    if(table.childNodes.length>0){
        while (table.childNodes.length) {
            table.removeChild(table.childNodes[0]);
        }
        document.getElementById("UserData").innerText="Mostra i dati personali";
    }else{
        getJSON();
    }
}
function getJSON(){
    let URL="GetData";
    let xhttp=new XMLHttpRequest();
    xhttp.open("POST", URL, true);
    xhttp.responseType="json";

    xhttp.onreadystatechange=function(){
        let done=4;
        let ok=200;
        if(xhttp.readyState==done && xhttp.status==ok){
            let myJson=this.response;
            var element=document.createElement("div");
            if(myJson==null || Object.keys(myJson).length === 0) {//Controllo se il JSON è null, oppure vuoto.
                element.innerText = "Errore nel mostrare i dati";
                document.getElementById("tabella").appendChild(element);
            }else{
                var tabella = "<table border='1'>";
                JSON.stringify(myJson,replacer);
                for (var key in myJson) {
                    var element = myJson[key];
                    tabella += "<tr>";
                    tabella += "<td>"+key+"</td>";
                    tabella += "<td>"+element+"</td>";
                    tabella += "</tr>";
                }
                tabella += "</table>";
                document.getElementById("tabella").innerHTML = tabella;
                document.getElementById("UserData").innerText="Chiudi la finestra";
            }
        }
    }
    xhttp.send();
}
function replacer(key, value){
    if(key=="ID" || key=="role"){
        return undefined;
    }else{
        return value;
    }
}

function BannerDelete(){
    var div="<div class='banner'>";
    div+="<p>Sei sicuro di voler cancellare il tuo account?</p>";
    div+="<button class='button' onclick='goBack()'>Annulla operazione</button>";
    div+="<form>";
    div+="<button type='submit' formaction='deleteAccount' class='button'>Cancella Account</button>";
    div+="</form>";
    div+="</div>";
    document.getElementById("banner").innerHTML=div;
}
function goBack(){
    let element=document.getElementById("banner");
    element.removeChild(element.childNodes[0]);
}