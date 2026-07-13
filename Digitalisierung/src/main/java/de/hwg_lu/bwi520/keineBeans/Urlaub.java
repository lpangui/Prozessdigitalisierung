package de.hwg_lu.bwi520.keineBeans;

public class Urlaub {

	int antrag_id;
	String namen;
	String erstellt_am;
	String grund;
	String startdatum;
	String enddatum;
	String status;
	
	public Urlaub(int antrag_id, String erstellt_am , String namen, String grund, 
			String startdatum, String enddatum, String status){
		
		this.antrag_id=antrag_id;
		this.erstellt_am=erstellt_am;
		this.namen=namen;
		this.grund=grund;
		this.startdatum=startdatum;
		this.enddatum= enddatum;
		this.status= status;
	}

	public int getAntrag_id() {
		return antrag_id;
	}

	public void setAntrag_id(int antrag_id) {
		this.antrag_id = antrag_id;
	}


	public String getNamen() {
		return namen;
	}

	public void setNamen(String namen) {
		this.namen = namen;
	}

	public String getErstellt_am() {
		return erstellt_am;
	}

	public void setErstellt_am(String erstellt_am) {
		this.erstellt_am = erstellt_am;
	}

	public String getGrund() {
		return grund;
	}

	public void setGrund(String grund) {
		this.grund = grund;
	}

	public String getStartdatum() {
		return startdatum;
	}

	public void setStartdatum(String startdatum) {
		this.startdatum = startdatum;
	}

	public String getEnddatum() {
		return enddatum;
	}

	public void setEnddatum(String enddatum) {
		this.enddatum = enddatum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
}
