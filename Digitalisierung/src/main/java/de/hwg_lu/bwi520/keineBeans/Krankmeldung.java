package de.hwg_lu.bwi520.keineBeans;

public class Krankmeldung {

	int km_id;
	String namenKrank;
	String erstellt_am;
	String bemerkung;
	String startdatum;
	String enddatum;
	String status;
	String attest;
	
	public Krankmeldung(int km_id, String erstellt_am , String namenKrank, String bemerkung, 
			String startdatum, String enddatum, String attest, String status){
		
		this.km_id=km_id;
		this.erstellt_am=erstellt_am;
		this.namenKrank=namenKrank;
		this.startdatum=startdatum;
		this.enddatum= enddatum;
		this.bemerkung=bemerkung;
		this.attest=attest;
		this.status= status;
	}

	
	
	public int getKm_id() {
		return km_id;
	}

	public void setKm_id(int km_id) {
		this.km_id = km_id;
	}

	public String getNamenKrank() {
		return namenKrank;
	}

	public void setNamenKrank(String namenKrank) {
		this.namenKrank = namenKrank;
	}

	public String getErstellt_am() {
		return erstellt_am;
	}

	public void setErstellt_am(String erstellt_am) {
		this.erstellt_am = erstellt_am;
	}

	public String getBemerkung() {
		return bemerkung;
	}

	public void setBemerkung(String bemerkung) {
		this.bemerkung = bemerkung;
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

	public String getAttest() {
		return attest;
	}

	public void setAttest(String attest) {
		this.attest = attest;
	}

	
}
