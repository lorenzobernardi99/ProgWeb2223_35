window.addEventListener("DOMContentLoaded", (event) => {
    const donationInput = document.getElementById("donationAmount");
    donationInput.addEventListener("input", function(event) {
        donationInputParser(donationInput, event);
    });

    donationInput.addEventListener("keydown", function(event) {
        if (event.key === "Enter") {
            sendDonation(donationInput.value);
        }
    });

});


function donationInputParser(input) {
    // Remove non-numeric characters from the input value
    input.value = input.value.replace(/\D/g, "");
}

function sendDonation(){

    let donation = document.getElementById("donationAmount");
    let donationAmount = donation.value;
    let donationLabel = document.getElementById("donationLabel");

    const errorMessage = document.getElementById("donationErrorMessage");

    if (isNaN(parseInt(donationAmount))){
        if (errorMessage == null) {
            const error = document.createElement("p");
            error.innerHTML = "Inserisci un importo in euro valido.";
            error.id = "donationErrorMessage";
            // Ottenere il primo figlio esistente
            const firstChild = donationLabel.firstChild;
            // Inserire il nuovo elemento come primo figlio
            donationLabel.insertBefore(error, firstChild);
        }
        return;
    }


    if (errorMessage != null){
        donationLabel.removeChild(errorMessage);
    }

    fetch("SendDonation", {
        method : "POST",
        body: JSON.stringify({ donationAmount }),
        headers: {
            "Content-Type": "application/json",
        },
    }).then(processStatus)
        .then(response => response.json())
        .then(data => {
            const { isSuccessful } = data;
            showOutcome(isSuccessful);
        })
        .catch(error => {
            console.error("An error occurred:", error);
            showOutcome(false);
        });
}

function showOutcome(isSuccessful){

    let donationOutcome = document.getElementById("donationOutcome");
    let donateButton = document.getElementById("donateButton");
    let donationInput = document.getElementById("donationAmount");
    let outcomeMessage;
    let outcomeId = isSuccessful ? "donationOutcomeMessageSuccess" : "donationOutcomeMessageError";

    outcomeMessage = document.getElementById(outcomeId);
    donationOutcome.classList.add("visible");
    outcomeMessage.classList.add("visible");
    donateButton.isClickable = false;
    donationInput.value = "";

    setTimeout(function (){
        donationOutcome.classList.add("fade-out");
        setTimeout(function (){
            outcomeMessage.classList.remove("visible");
            donationOutcome.classList.remove("visible");
            donationOutcome.classList.remove("fade-out");
            donateButton.isClickable = true;
        }, 400);
    },3000);
}
