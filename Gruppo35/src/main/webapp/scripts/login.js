function validateData(){
    let username = document.forms["login_data"]["username"].value;
    let password = document.forms["login_data"]["password"].value;
    if (username === "" || password === "") {
        if(username === ""){
            document.getElementById('username').classList.add("error");
        }
        if(password === ""){
            document.getElementById('password').classList.add("error");
        }
        document.getElementById("errorText").innerText = "Username e password non possono essere vuoti";
        document.getElementById("errorText").classList.add("error-visible");
        return false;
    }
    else return true;
}