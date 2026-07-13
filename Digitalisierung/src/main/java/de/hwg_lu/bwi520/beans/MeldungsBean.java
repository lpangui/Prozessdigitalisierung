package de.hwg_lu.bwi520.beans;

public class MeldungsBean {
	String füllePersonalid;
	
	
	String infoMsg;
	String actionMsg;
		
	String adminMsg;
	String actionAdminMsg;
	
	String benutzer;
	
	public MeldungsBean() {
		this.setPortalWelcome();
	
	}
	
	public String getMeldungHtml() {
		String html = "";
		html += "<h3 style= 'text-align: center;'>" + this.getInfoMsg() + "</h3>";
		html += "<h4 style= 'text-align: center;'>" + this.getActionMsg()+ "</h4>";
		return html;
	}
	
	public String getMeldungHtmlAdmin() {
		String html = "";
		html += "<h3 style= 'text-align: center;'>" + this.getAdminMsg() + "</h3>";
		html += "<h4 style= 'text-align: center;'>" + this.getActionAdminMsg()+ "</h4>";
		return html;
	}

	
	public String getAktiveUser() {	
		String html="";
		html += "<h3 style= 'text-align: center;'>" + "Hier sind alle aktiven User" + "</h3>";
		return html;	
	}
	
	public void setLoginFailed() {
		this.setInfoMsg("Anmeldung fehlgeschlagen!");
		this.setActionMsg("Bitte versuchen Sie es noch einmal!");
	};
	public void setLogoutSuccessful() {
		this.setInfoMsg("Abmeldung erfolgreich!");
		this.setActionMsg("Melden Sie sich bitte wieder an!");
	};
	
	public void setPortalWelcome() {
		this.setInfoMsg("Willkommen am Portal!");
		this.setActionMsg("Bitte melden Sie sich an!");
	};
	
	public void setReturnToLogin() {
		this.setInfoMsg("Willkommen zurück!");
		this.setActionMsg("Bitte melden Sie sich an!");
	};


	public String getMessageChangeDataUser() {
		return "Daten von'" + this.getBenutzer() + "' bearbeiten";
	};
	
	public String getFüllePersonalid() {
		return füllePersonalid;
	}

	public void setFüllePersonalid(String füllePerspnalid) {
		this.füllePersonalid = füllePerspnalid;
	}
	
	//Meldung für den Adminstrator
	public void setSuccessfulAktualisierung() {
		this.setAdminMsg("Daten erfolgreich aktualisiert!");
		this.setActionAdminMsg("Bitte informieren Sie '"+ this.getBenutzer() +"' über die Datenänderung.");
	};
	
	public void setWelcomeAktualisierung() {
		this.setAdminMsg("Willkommen!");
		this.setActionAdminMsg("Daten von '" + this.getBenutzer() +"' bearbeiten");
	};
	
	public void setErrorAktualisierung() {
		this.setAdminMsg("Aktualisierung fehlgeschlagen!");
		this.setActionAdminMsg("Versuchen Sie es noch einmal!");
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

	public String getBenutzer() {
		return benutzer;
	}

	public void setBenutzer(String benutzer) {
		this.benutzer = benutzer;
	}

	public String getAdminMsg() {
		return adminMsg;
	}

	public void setAdminMsg(String adminMsg) {
		this.adminMsg = adminMsg;
	}

	public String getActionAdminMsg() {
		return actionAdminMsg;
	}

	public void setActionAdminMsg(String actionAdminMsg) {
		this.actionAdminMsg = actionAdminMsg;
	}


	
}
