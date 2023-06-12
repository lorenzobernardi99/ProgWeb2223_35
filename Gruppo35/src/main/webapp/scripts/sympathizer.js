function getUserData(){
    let table=document.getElementById("tabella");
    if(table.childNodes.length>0){
        while (table.childNodes.length) {
            table.removeChild(table.childNodes[0]);
        }
        document.getElementById("UserData").innerText="Mostra i dati personali";
    }else{
        getUserJSON();
    }
}
function getUserJSON(){
    let URL="GetUserData";
    let xhttp= new XMLHttpRequest();
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

var activeState = false;

function getActivities(){
    let activities= document.getElementById("activities");
    if(activeState){
        activeState = false;
        while (activities.firstChild) {
            activities.removeChild(activities.firstChild);
        }
        document.getElementById("activitiesButton").innerText="Iscriviti alle attività";
    }else{
        activeState = true;
        getActivitiesJSON();
    }
}
function getActivitiesJSON() {
    // get and insert

    let URL= "UserActivitiesSubscription";
    let xhttp= new XMLHttpRequest();
    xhttp.open("GET", URL, true);
    xhttp.responseType="json";

    xhttp.onreadystatechange = function(){
        let done=4;
        let ok=200;
        if(xhttp.readyState===done && xhttp.status===ok){
            let myJson = this.response;
            if(myJson==null || Object.keys(myJson).length === 0) {//Controllo se il JSON è null, oppure vuoto.
                let error=document.createElement("p");
                error.innerText = "Errore nel mostrare i dati";
                document.getElementById("activities").appendChild(error);
            }else{
                for (let key in myJson) {
                    let tmpActivityElement = document.createElement("div");
                    let name = document.createElement("p");
                    let image = document.createElement("img");
                    let checkboxSpan = document.createElement("span");
                    let checkbox = document.createElement("input");
                    let checkboxLabel = document.createElement("label");
                    let activityId = "0";
                    for(let value in myJson[key]){
                        if (value === "name")
                            name.innerText = myJson[key][value];
                        if (value === "imagePath")
                            image.src = "../../" + myJson[key][value];
                        if (value === "subscribed")
                            checkbox.checked = myJson[key][value];
                        if (value === "id")
                            activityId = myJson[key][value];
                    }
                    checkbox.id = activityId;
                    checkbox.type = "checkbox";
                    checkboxLabel.htmlFor = checkbox.id;
                    checkboxLabel.innerText = checkbox.checked ? "Iscritto" : "Iscriviti";
                    checkbox.setAttribute("activityId", activityId);
                    checkbox.addEventListener('change', function (event){
                        event.preventDefault();
                    });
                    checkboxSpan.appendChild(checkboxLabel);
                    checkboxSpan.appendChild(checkbox);
                    tmpActivityElement.appendChild(name);
                    tmpActivityElement.appendChild(image);
                    tmpActivityElement.appendChild(checkboxSpan);
                    tmpActivityElement.setAttribute("activityId", activityId);
                    tmpActivityElement.onclick = function() {
                        updateUI(tmpActivityElement.getAttribute("activityId"), "toggle");
                        subscribe(tmpActivityElement.getAttribute("activityId"));
                    };
                    tmpActivityElement.classList.add("activity");
                    document.getElementById("activities").appendChild(tmpActivityElement);
                    document.getElementById("activities").appendChild(document.createElement("br"));
                }
                document.getElementById("activitiesButton").innerText="Chiudi la finestra";
            }
        }
    }
    xhttp.send();
}

function processStatus(response) {
    let ok = 200;
    if (response.status === ok) {
        return Promise.resolve(response);
    } else {
        return Promise.reject(response.status);
    }
}

function subscribe(targetActivityId){
    fetch("UserActivitiesSubscription", {
        method: "POST",
        body: JSON.stringify({ targetActivityId }),
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then(processStatus)
        .then(response => response.json())
        .then(data => {
            const { isSubscribed } = data;
            updateUI(targetActivityId, isSubscribed);
        })
        .catch(error => console.error("An error occurred:", error));
}

function updateUI(targetActivityId, subscription) {
    if(subscription === "toggle"){
        let currentState = document.getElementById(targetActivityId.toString()).checked;
        document.getElementById(targetActivityId.toString()).checked = !currentState;
        const label = document.querySelector(`label[for="${targetActivityId.toString()}"]`);
        if(currentState){
            label.innerText = "Iscritto";
        } else {
            label.innerText = "Iscriviti";
        }
    }else{
        /* TODO: Should update the GUI accordingly to server response.
             Currently disabled cause of initial instability.
        document.getElementById(targetActivityId.toString()).checked = subscription;
        const label = document.querySelector(`label[for="${targetActivityId.toString()}"]`);
        if (subscription) {
            label.innerText = "Iscritto";
        } else {
            label.innerText = "Iscriviti";
        }
         */
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