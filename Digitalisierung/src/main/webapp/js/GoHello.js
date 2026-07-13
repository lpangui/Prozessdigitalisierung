//Überprüfung mithilfe von JavaScript ob alle Registrierungsfelder ok sind.

function setButtonClicked(btnName){
	document.buttonClicked = btnName;
}


function checkInput(myForm){
	if (document.buttonClicked === "losgehts") return true;
	let myFirstname = myForm.firstNameReg.value;
	let myLastname = myForm.lastNameReg.value;
	let myUsername = myForm.usernameReg.value;
	let myEmail    = myForm.emailReg.value;
	let myPassword = myForm.passwordReg.value;
	
	let firstNameOk =  this.checkFirstname(myFirstname);
	let lastNameOk =  this.checkLastname(myLastname);
	let nicknameOk =  this.checkUsername(myUsername);
	let emailOk    =  this.checkEmail(myEmail);
	let passwordOk =  this.checkPassword(myPassword);
	

	//Formular nur abschicken, wenn alle Tests erfolgreich
	if (firstNameOk && lastNameOk && nicknameOk && emailOk && passwordOk){
		//alert("Account '" + myUsername + "' wurde erfolgreich angelegt. Jetzt können Sie sich einloggen.")
		return true;
	} else {
		return false;
		}
	
}

function checkFirstname(firstNameReg){
	let myFehlerfeldDiv = document.getElementById("errorFirstname");
	
	if (firstNameReg){
		myFehlerfeldDiv.innerText = "";
		return true;
	}else{
		myFehlerfeldDiv.innerText = "Den Vorname nicht vergessen";
		return false;
	}
}

function checkLastname(lastNameReg){
	let myFehlerfeldDiv = document.getElementById("errorLastname");
	
	if (lastNameReg){
		myFehlerfeldDiv.innerText = "";
		return true;
	}else{
		myFehlerfeldDiv.innerText = "Bitte den Nachname eintippen";
		return false;
	}
}


function checkUsername(usernameReg){
	let myFehlerfeldDiv = document.getElementById("errorUsername");

	if (usernameReg){
		myFehlerfeldDiv.innerText = "";
		return true;
	}else{
		myFehlerfeldDiv.innerText = "Es fehlt noch den Benutzername";
		return false;
	}
}

function checkEmail(emailReg){
	let myFehlerfeldDiv = document.getElementById("errorEmail");
	if (emailReg){
		return true;
	} else{
		myFehlerfeldDiv.innerText = "Vergessen Sie bitte nicht die E-Mail";
		return false;
	}
}

function checkPassword(passwordReg){
	let myFehlerfeldDiv = document.getElementById("errorPassword");

	
	if (!passwordReg){
		myFehlerfeldDiv.innerText = "Das Passwort muss ausgefüllt werden";
		return false;
	}else if(passwordReg.length < 7){
		myFehlerfeldDiv.innerText = "Das Passwort muss mindestens 7 Zeichen lang sein";
		return false;
	}else{
		myFehlerfeldDiv.innerText = "";
		return true;
	}
	
}