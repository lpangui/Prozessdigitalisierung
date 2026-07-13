package de.hwg_lu.bwi520.beans;

public class PortalMeldungsBean {
	
	String infoMsg;
	String actionMsg;
	
	String erfolgMsg;
	String handelnMsg;
	
	String krankMsg;
	String krankActionMsg;
	
	String kontaktMsg;
	String kontaktActionMsg;
	
	public PortalMeldungsBean() {
		this.setPortalWelcome();
		this.setFormularWelcomeKontakt();
		this.setFormularWelcomeUA();
		this.setFormularWelcomeKM();
		
	}
	

	
	public String getMeldungHtml() {
		String html = "";
		html += "<h3 style= 'text-align: center;'>" + this.getInfoMsg() + "</h3>";
		html += "<h4 style= 'text-align: center;'>" + this.getActionMsg()+ "</h4>";
		return html;
	}
	
	public String getMeldungHtmlFuerFormulare() {
		String html = "";
		html += "<h3 style= 'text-align: center;'>" + this.getErfolgMsg() + "</h3>";
		html += "<h4 style= 'text-align: center;'>" + this.getHandelnMsg()+ "</h4>";
		return html;
	}
	
	public String getMeldungHtmlFuerFormulareKrankmeldung() {
		String html = "";
		html += "<h3 style= 'text-align: center;'>" + this.getKrankMsg() + "</h3>";
		html += "<h4 style= 'text-align: center;'>" + this.getKrankActionMsg()+ "</h4>";
		return html;
	}
	
	public String getMeldungHtmlFuerFormulareKontakt() {
		String html = "";
		html += "<h3 style= 'text-align: center;'>" + this.getKontaktMsg() + "</h3>";
		html += "<h4 style= 'text-align: center;'>" + this.getKontaktActionMsg()+ "</h4>";
		return html;
	}
	
	
	//Message für Formulare
	public void setFormularWelcomeUA() {  
		this.setErfolgMsg("Willkommen!");
		this.setHandelnMsg("Bitte füllen Sie das Formular richtig aus!");
	};
	
	public void setSuccessfulFormularUA() {
		this.setErfolgMsg("Formular erfolgreich eingereicht!");
		this.setHandelnMsg("Statusänderung erfolgt gleich!");
	};
	
	public void setErrorFormularUA() {
		this.setErfolgMsg("Einreichen fehlgeschlagen!");
		this.setHandelnMsg("Achtung: Zeitraum überschneidet sich!");
	};
	
	public void setFormularWelcomeKM() {
		this.setKrankMsg("Willkommen!");
		this.setKrankActionMsg("Bitte füllen Sie das Formular richtig aus!");
	};
	
	public void setSuccessfulFormularKM() {
		this.setKrankMsg("Formular erfolgreich eingereicht!");
		this.setKrankActionMsg("Statusänderung erfolgt gleich!");
	};
	public void setErrorFormularKM() {
		this.setErfolgMsg("Einreichen fehlgeschlagen!");
		this.setHandelnMsg("Versuchen Sie noch einmal!");
	};
	
	//Message für Admin kontaktieren
	
	public void setFormularWelcomeKontakt() {
		this.setKontaktMsg("Willkommen!");
		this.setKontaktActionMsg("Bitte füllen Sie das Formular richtig aus!");
	};
	
	public void setSuccessfulFormularKontakt() {
		this.setKontaktMsg("Formular erfolgreich eingereicht!");
		this.setKontaktActionMsg("Ihre Anfrage wird durch den Admin bearbeitet");
	};
	public void setErrorFormularKontakt() {
		this.setKontaktMsg("Einreichen fehlgeschlagen!");
		this.setKontaktActionMsg("Versuchen Sie es noch einmal!");
	};
	
	//Message für die Registrierung
	public void setPortalWelcome() {
		this.setInfoMsg("Willkommen bei der Registrierung!");
		this.setActionMsg("Bitte füllen Sie die Felder richtig aus!");
	};
	
	
	public void setRegSuccessful(String username) {
		this.setInfoMsg("Account " + username + " erfolgreich angelegt!");
		this.setActionMsg("Gehen Sie zur Anmeldung auf die Loginseite zurück!");
	};
	
	public void setAccountAlreadyExists(String username, String email) {
		this.setInfoMsg("Benutzer " + username + " mit der Email: "+  email +" existiert bereits");
		this.setActionMsg("Bitte überprüfen Sie nochmal Ihre Angaben!");
	};
	public void setDBError() {
		this.setInfoMsg("Es ist ein Datenbankfehler aufgetreten");
		this.setActionMsg("Bitte wiederholen Sie den Vorgang!");
	};
	
	
	public String getInfoMsg() {
		return infoMsg;
	}

	public void setInfoMsg(String infoMsg) {
		this.infoMsg = infoMsg;
	}

	public String getActionMsg() {
		return actionMsg;
	}

	public void setActionMsg(String actionMsg) {
		this.actionMsg = actionMsg;
	
	}

	public String getErfolgMsg() {
		return erfolgMsg;
	}

	public void setErfolgMsg(String erfolgMsg) {
		this.erfolgMsg = erfolgMsg;
	}

	public String getHandelnMsg() {
		return handelnMsg;
	}

	public void setHandelnMsg(String handelnMsg) {
		this.handelnMsg = handelnMsg;
	}

	public String getKrankMsg() {
		return krankMsg;
	}

	public void setKrankMsg(String krankMsg) {
		this.krankMsg = krankMsg;
	}

	public String getKrankActionMsg() {
		return krankActionMsg;
	}

	public void setKrankActionMsg(String krankActionMsg) {
		this.krankActionMsg = krankActionMsg;
	}

	public String getKontaktMsg() {
		return kontaktMsg;
	}

	public void setKontaktMsg(String kontaktMsg) {
		this.kontaktMsg = kontaktMsg;
	}

	public String getKontaktActionMsg() {
		return kontaktActionMsg;
	}

	public void setKontaktActionMsg(String kontaktActionMsg) {
		this.kontaktActionMsg = kontaktActionMsg;
	}
	
	
}
