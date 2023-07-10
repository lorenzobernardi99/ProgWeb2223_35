
function getJSessionIdFromUrl() {
    var referrerUrl = document.referrer;
    var jsessionId = "";

    var regex = /(;)jsessionid=([^&]+)/;
    var match = regex.exec(referrerUrl);
    if (match) {
        jsessionId = match[0];
    }
    return jsessionId;
}

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
async function getUserJSON() {
    try {
        let url = "GetUserData" + getJSessionIdFromUrl();

        const response = await fetch(url, {
            method: "POST",
        });

        if (!response.ok) {
            throw new Error("Errore nella richiesta");
        }

        const myJson = await response.json();

        if (myJson == null || Object.keys(myJson).length === 0) {
            document.getElementById("tabella").innerHTML = "<div>Errore nel mostrare i dati</div>";
            return;
        }

        const table = document.createElement("table");
        table.classList.add("user-table");

        const keys = Object.keys(myJson);
        const values = Object.values(myJson);

        // Rimuovi la chiave "id" e il suo valore
        const indexToRemove = keys.indexOf("id");
        if (indexToRemove > -1) {
            keys.splice(indexToRemove, 1);
            values.splice(indexToRemove, 1);
        }

        // Righe delle chiavi
        const keysRow = document.createElement("tr");
        keys.forEach((key) => {
            const th = document.createElement("th");
            th.textContent = key;
            keysRow.appendChild(th);
        });

        // Righe dei valori
        const valuesRow = document.createElement("tr");
        values.forEach((value) => {
            const td = document.createElement("td");
            td.textContent = value;
            valuesRow.appendChild(td);
        });

        // Aggiungi le righe alla tabella
        table.appendChild(keysRow);
        table.appendChild(valuesRow);

        // Aggiungi la tabella all'elemento "tabella"
        const tabella = document.getElementById("tabella");
        tabella.innerHTML = ""; // Rimuovi eventuali contenuti precedenti
        tabella.appendChild(table);
        document.getElementById("UserData").innerText = "Chiudi la finestra";

    } catch (error) {
        console.error("Si è verificato un errore:", error);
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

    let url = "UserActivitiesSubscription" + getJSessionIdFromUrl();

    fetch(url)
        .then(processStatus)
        .then(response => response.json())
        .then(data => {
            if (Object.keys(data).length === 0) {
                let error = document.createElement("p");
                error.innerText = "Errore nel mostrare i dati";
                document.getElementById("activities").appendChild(error);
            } else {
                for (let key in data) {
                    let activityContainer = document.createElement("div");
                    let imageOverlay = document.createElement("div")
                    let imageSkeleton = document.createElement("div");
                    let image = document.createElement("img");
                    let activityInfo = document.createElement("div");
                    let name = document.createElement("p");
                    let button = document.createElement("button");
                    let isSubscribed = false;

                    for (let value in data[key]) {
                        if (value === "name")
                            name.innerText = data[key][value];
                        if (value === "imagePath")
                            image.src = "../../" + data[key][value];
                        if (value === "subscribed")
                            isSubscribed = data[key][value];
                        if (value === "id")
                            activityContainer.id = data[key][value];
                    }

                    activityContainer.classList.add("activity-container");
                    activityInfo.classList.add("activity-info");
                    imageOverlay.classList.add("activity-image-container");
                    imageSkeleton.classList.add("activity-image-skeleton");

                    if (isSubscribed){
                        button.innerText = "Disiscriviti";
                        imageSkeleton.classList.add("is-loading");
                        image.onload = function(){
                            imageSkeleton.classList.remove("is-loading");
                            imageOverlay.classList.add("fade-in");
                        }
                    }else{
                        button.innerText = "Iscriviti";
                        imageSkeleton.classList.add("is-loading");
                        image.onload = function(){
                            imageSkeleton.classList.remove("is-loading");
                        }
                    }

                    button.setAttribute("activityId", activityContainer.id);
                    button.addEventListener("click", function(event) {
                        event.preventDefault();
                        let activityId = this.getAttribute("activityId");
                        subscribe(activityId);
                    });

                    activityInfo.appendChild(name);
                    activityInfo.appendChild(button);
                    imageSkeleton.appendChild(image);
                    imageOverlay.appendChild(imageSkeleton);
                    activityContainer.appendChild(imageOverlay);
                    activityContainer.appendChild(activityInfo);

                    document.getElementById("activities").appendChild(activityContainer);
                    document.getElementById("activities").appendChild(document.createElement("br"));
                }
                document.getElementById("activitiesButton").innerText = "Chiudi la finestra";
            }
        })
        .catch(error => {
            let errorMsg = document.createElement("p");
            errorMsg.innerText = "Errore nel mostrare i dati";
            document.getElementById("activities").appendChild(errorMsg);
            console.error("An error occurred:", error);
        });
}

// method to process response status
function processStatus(response) {
    const ok = 200;

    if (response.redirected){
        window.location.href = response.url;
    }
    if (response.status === ok) {
        return Promise.resolve(response)
    }
    else {
        return Promise.reject(response.status)
    }
}

function subscribe(targetActivityId){

    let url = "UserActivitiesSubscription" + getJSessionIdFromUrl();

    fetch(url, {
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

function updateUI(targetActivityId, isSubscribed) {
    const activityContainer= document.getElementById(targetActivityId);
    const imageOverlay= activityContainer.getElementsByClassName("activity-image-container")[0];
    const button = activityContainer.getElementsByTagName("button")[0];

    if(isSubscribed){
        // just subscribed, add image overlay
        imageOverlay.classList.add("fade-in");
        button.innerText = "Disiscriviti";
    }else {
        // no longer subscribed, trigger overlay fade out
        imageOverlay.classList.remove("fade-in");
        imageOverlay.classList.add("fade-out");
        button.innerText = "Iscriviti";

        setTimeout( function (overlay) {
            overlay.classList.remove("fade-out");
        }, 500, imageOverlay);
    }
}


function showDeleteBanner() {
    document.getElementById("deleteBanner").classList.add("visible");
}
function dismissBanner() {
    document.getElementById("deleteBanner").classList.remove("visible");
}
window.addEventListener("DOMContentLoaded", (event) => {
    document.getElementById("deleteBannerDismissButton").addEventListener("click", function (event) {
        event.preventDefault();
        dismissBanner();
    })
})

