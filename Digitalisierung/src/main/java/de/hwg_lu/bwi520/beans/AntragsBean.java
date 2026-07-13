package de.hwg_lu.bwi520.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import de.hwg_lu.bwi520.jdbc.NoConnectionException;
import de.hwg_lu.bwi520.jdbc.PostgreSQLAccess;

public class AntragsBean {

	int personalnummer;
	String startdatum;
	String enddatum;
	String grund; 

	public AntragsBean() {
		this.enddatum="";
		this.startdatum="";
		this.grund="";
		this.personalnummer=0;
	
	}

	//Resturlaub des Mitarbeiters abfragen
	
	public boolean kannUrlaubBeantragen() throws SQLException {
	    String resturlaubSql = "SELECT urlaubstage FROM MITARBEITER WHERE Personalnummer = ?";

	    try (Connection connection = new PostgreSQLAccess().getConnection();
	         PreparedStatement prepResturlaub = connection.prepareStatement(resturlaubSql)) {

	        // 1. Resturlaub des Mitarbeiters abrufen
	        prepResturlaub.setInt(1, personalnummer);
	        ResultSet rs = prepResturlaub.executeQuery();
	        int resturlaub = 0;
	        if (rs.next()) {
	            resturlaub = rs.getInt("urlaubstage");
	        }

	        // 2. Urlaubsdauer in Java berechnen
	        LocalDate start = LocalDate.parse(this.startdatum);
	        LocalDate end = LocalDate.parse(this.enddatum);
	        int urlaubsdauer = (int) ChronoUnit.DAYS.between(start, end) + 1;

	        // 3. Prüfen, ob Resturlaub ausreicht
	        return resturlaub >= urlaubsdauer;
	    }
	}
	
	
	public void insertUrlaubsAntrag() throws NoConnectionException, SQLException {
	    String checkSql = "SELECT COUNT(*) FROM URLAUBSANTRAG " +
	                      "WHERE personalnummer = ? " +
	                      "AND status = 'Genehmigt' " + 
	                      "AND (startdatum <= ? AND enddatum >= ?)";

	    String insertSql = "INSERT INTO URLAUBSANTRAG (personalnummer, grund, startdatum, enddatum) " +
	                       "VALUES (?, ?, ?, ?)";

	    Connection dbConn = new PostgreSQLAccess().getConnection();
	    
	    try (PreparedStatement checkPrep = dbConn.prepareStatement(checkSql)) {
	        checkPrep.setInt(1, this.personalnummer);
	        checkPrep.setDate(2, java.sql.Date.valueOf(this.enddatum));  // Prüfe, ob das neue Enddatum innerhalb eines genehmigten Urlaubs liegt
	        checkPrep.setDate(3, java.sql.Date.valueOf(this.startdatum)); // Prüfe, ob das neue Startdatum innerhalb eines genehmigten Urlaubs liegt
	        
	        ResultSet rs = checkPrep.executeQuery();
	        rs.next();
	        int count = rs.getInt(1);
	        
	        if (count > 0) {
	            System.out.println("Fehler: Es gibt bereits einen genehmigten Urlaubsantrag im gewählten Zeitraum!");
	            return;
	        }
	    }

	    try (PreparedStatement prep = dbConn.prepareStatement(insertSql)) {
	        prep.setInt(1, this.personalnummer);
	        prep.setString(2, this.grund);
	        prep.setDate(3, java.sql.Date.valueOf(this.startdatum));
	        prep.setDate(4, java.sql.Date.valueOf(this.enddatum));
	        prep.executeUpdate();
	        System.out.println("Antrag erfolgreich eingereicht");
	    }
	}

	
	
	
	public int getPersonalnummer() {
		return personalnummer;
	}

	public void setPersonalnummer(int personalnummer) {
		this.personalnummer = personalnummer;
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

	public String getGrund() {
		return grund;
	}

	public void setGrund(String grund) {
		this.grund = grund;
	}
	
	
	
	
}
