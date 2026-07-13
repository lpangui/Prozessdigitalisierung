package de.hwg_lu.bwi520.keineBeans;

public class Kontakt {

	String name;
	String email;
	String message;
	String status;
	String gesendet_am;
	
	public Kontakt(String name, String email, String gesendet_am, String message, String status) {
		this.name=name;
		this.email=email;
		this.gesendet_am=gesendet_am;
		this.message=message;
		this.status=status;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public String getGesendet_am() {
		return gesendet_am;
	}


	public void setGesendet_am(String gesendet_am) {
		this.gesendet_am = gesendet_am;
	}
	
}
