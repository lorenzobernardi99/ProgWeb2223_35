function validateForm(){
    let name_surname = document.getElementById('name-surname').value;
    let email = document.getElementById('email').value;
    if(name_surname === "" || email === ""){
        if(name_surname === ""){
            document.getElementById('name-surname').classList.add("error");
        }
        if(email === ""){
            document.getElementById('email').classList.add("error");
        }
        document.getElementById("error-message").innerText = "'Nome e Cognome' ed 'Email' sono campi obbligatori";
        document.getElementById("error-message").classList.add("error-visible");
        return false;
    } else {
        return validateEmail();
    }
}

function validateEmail() {
    let email = document.getElementById('email');
    const regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    if(regex.test(email.value)){
        return true
    }
    else{
        email.classList.add("error");
        document.getElementById("error-message").innerText = "L'email inserita non è scritta corretamente";
        document.getElementById("error-message").classList.add("error-visible");
        return false
    }
}