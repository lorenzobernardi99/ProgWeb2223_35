let motivations = [];
let counter = 1;

retrieveMotivations();

// method to process response status
function processStatus(response) {
    var ok = 200;
    if (response.status === ok) {
        return Promise.resolve(response)
    }
    else {
        return Promise.reject(response.status)
    }
}

// method that retrieves motivational phrases
function retrieveMotivations(){
    // Preparing request
    let url = "GetMotivations";

    // Making request
    fetch(url)
        .then(processStatus)
        .then(function (response){ return response.json(); })
        .then(function (response){
            // Getting returned array of cities
            let my_JSON_array = response;

            // Finding table to fill in
            let span = document.getElementById("motivations");

            if (my_JSON_array.length > 0) {

                // Creating table rows
                for (let i = 0; i < my_JSON_array.length; i++) {
                    let current_JSON_object  = JSON.parse(my_JSON_array[i]);
                    motivations.push(current_JSON_object);
                }

            }
            else {
                // Displaying error if the list is empty
                motivations.push("nessuna frase motivazionale trovata");
            }
        })
        .then(function (){
            populateMotivations();
            setInterval(populateMotivations, 20000)
        })
        .catch(error => document.getElementById("motivations").innerHTML = "Error " + error);
}

function populateMotivations(){
    let element = document.getElementById("phrase");
    element.innerText = motivations[counter];
    counter += 1;
    if (counter === motivations.length) {
        counter = 0;
    }
}