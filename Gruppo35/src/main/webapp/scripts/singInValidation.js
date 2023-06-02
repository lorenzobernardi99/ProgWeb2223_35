let error = false;

function formatPrefix(prefix) {
    let cleanedValue = prefix.value.replace(/\D/g, ""); // Remove any non-digit characters except "+"
    prefix.value = "+" + cleanedValue.slice(0, 2); // Add "+" and keep only the first two digits
}

function formatPhoneNumber(phone) {
    let formattedNumber = "";

    // Remove any non-digit characters from the input value
    let cleanedValue = phone.value.replace(/\D/g, "");

    // Format the phone number
    if (cleanedValue.length > 0) {
        formattedNumber = cleanedValue.match(/(\d{0,3})(\d{0,3})(\d{0,4})/);
        formattedNumber = formattedNumber[1] + (formattedNumber[2] ? "-" + formattedNumber[2] : "") + (formattedNumber[3] ? "-" + formattedNumber[3] : "");
    }

    // Set the formatted value back to the input field
    phone.value = formattedNumber;
}

function validateData() {

    let booleanArray = new Array(8).fill(false);

    let name = document.getElementById("name");
    let surname = document.getElementById("surname")
    let birth = document.getElementById("birth");
    let email = document.getElementById("email");
    let phone = document.getElementById("phone");
    let role = document.getElementById("role");
    let username = document.getElementById("username");
    let password = document.getElementById("password");
    let confirmPassword = document.getElementById("confirmPassword");

    booleanArray[0] = isInputNotEmpty(name);
    booleanArray[1] = isInputNotEmpty(surname);
    booleanArray[2] = checkAge(birth);
    booleanArray[3] = checkEmail(email);
    booleanArray[4] = checkPhone(phone);
    booleanArray[5] = checkRole(role);
    booleanArray[6] = checkUsername(username);
    booleanArray[7] = checkPassword(password, confirmPassword);

    console.log(booleanArray[2]);

    if (booleanArray.every((value) => value)) {
        error = false;
        return true;
    }else{
        if(!error)
            showError();
        error = true;
        return false;
    }
}

// support function
function isEmpty(string) {
    return string.trim() === '';
}

function isInputNotEmpty(input) {
    let text = input.value
    if (isEmpty(text)) {
        input.classList.add("error");
        return false;
    } else {
        input.classList.remove("error");
        return true;
    }
}

function validateEmail(email) {
    const regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return regex.test(email)
}

function checkAge(birth) {
    let selectedDate = new Date(birth.value);
    let currentDate = new Date();

    console.log(selectedDate);
    console.log(currentDate);

    if(!isInputNotEmpty(birth))
        return false;

    // Calculate age difference in years
    let ageDiff = currentDate.getFullYear() - selectedDate.getFullYear();

    console.log(ageDiff);
    console.log("-------------------------------------------------------");

    // Check if the selected date makes the person underage
    if (ageDiff < 18) {
        // underage
        birth.classList.add("error");
        return false;
    } else {
        // adults
        birth.classList.remove("error");
        return true;
    }
}

function checkEmail(input) {
    let email = input.value;
    //let errorLabel = input.parentElement.querySelector('.errorLabel');

    // Perform email validation here
    let isValidEmail = validateEmail(email);

    if (isValidEmail) {
        //errorLabel.classList.remove('active');
        input.classList.remove('error');
    } else {
        //errorLabel.classList.add('active');
        input.classList.add('error');
    }
    return isValidEmail
}

function checkPhone(input) {
    let phone = input.value;
    if(phone.length < 12) {
        input.classList.add("error");
        return false;
    }else {
        input.classList.remove("error");
        return true;
    }
}

function checkRole(input) {
    let role = input.value;
    if(role == null || role === "seleziona") {
        input.classList.add("error");
        return false;
    }else {
        input.classList.remove("error");
        return true;
    }

}

// to be replaced with AJAX/AJAJ validation
function checkUsername(input) {
    return isInputNotEmpty(input)
}

function checkPassword(password, confirmPassword) {

    const passwordRegex = /^(?=.*[A-Z])(?=.*[0-9])(?=.*[jelm])(?=.*[$!?\w]).{8}$/;

    if(!isInputNotEmpty(password)){
        return false;
    }

    if(!isInputNotEmpty(confirmPassword)){
        return false;
    }

    if (!passwordRegex.test(password.value)) {
        //alert("La password non soddisfa i requisiti richiesti. Per favore, riprova.");
        password.classList.add("error");
        return false;
    }else {
        password.classList.remove("error");
    }

    if (password.value !== confirmPassword.value) {
        //alert("Le password non corrispondono. Per favore, riprova.");
        confirmPassword.classList.add("error");
        password.classList.add("error");
        return false;
    }else {
        confirmPassword.classList.remove("error");
        password.classList.remove("error");
    }

    return true;
}

function generateRandomPassword() {
    const requiredLetters = ['j', 'e', 'l', 'm'];
    const numbers = '0123456789';
    const requiredSymbols = ['$', '!', '?'];
    const letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

    let password = '';

    // Generate the 4 required letters
    for (let i = 0; i < 4; i++) {
        password += requiredLetters[i];
    }

    const randomLetter1 = letters[Math.floor(Math.random() * letters.length)];
    const randomLetter2 = letters[Math.floor(Math.random() * letters.length)];
    password += randomLetter1;
    password += randomLetter2;

    // randomly make one uppercase if there is not one already
    const randomIndex = Math.floor(Math.random() * password.length);

    password =
        password.substring(0, randomIndex) +
        password[randomIndex].toUpperCase() +
        password.substring(randomIndex + 1);


    // Generate a random number
    const randomNum = numbers[Math.floor(Math.random() * numbers.length)];
    password += randomNum;

    // Add a random symbol from the symbols array
    const randomSymbol = requiredSymbols[Math.floor(Math.random() * requiredSymbols.length)];
    password += randomSymbol;

    // Convert password to an array and shuffle it
    const passwordArray = password.split('');
    for (let i = passwordArray.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [passwordArray[i], passwordArray[j]] = [passwordArray[j], passwordArray[i]];
    }

    // Convert the shuffled array back to a string
    password = passwordArray.join('');

    return password;
}

// toggle password visibility
document.querySelectorAll('.toggle-password').forEach(function(element) {
    element.addEventListener('click', function() {
        const targetId = this.getAttribute('data-target');
        const targetInput = document.querySelector('#' + targetId);
        const type = targetInput.getAttribute('type') === 'password' ? 'text' : 'password';
        targetInput.setAttribute('type', type);

        if (type === 'text') {
            this.classList.remove('fa-eye');
            this.classList.add('fa-eye-slash');
        } else {
            this.classList.remove('fa-eye-slash');
            this.classList.add('fa-eye');
        }
    });
});

document.querySelectorAll('input').forEach(function(element) {
    element.addEventListener('focusout', function() {
        validateData();
    });
});

document.getElementById("generatePassword").addEventListener('click', function() {
    document.getElementById("password").value = generateRandomPassword();
    if(!document.getElementById("togglePassword").classList.contains("fa-eye-slash")) {
        document.getElementById("togglePassword").click();
    }
})

function showError(){
    // Display an error message
    let errorMessage = document.createElement("span");
    errorMessage.className = "error-message";
    errorMessage.textContent = "Perfavore compila tutti i campi.";
    //document.body.appendChild(errorMessage);
    document.getElementById("errorText").appendChild(errorMessage);
}
