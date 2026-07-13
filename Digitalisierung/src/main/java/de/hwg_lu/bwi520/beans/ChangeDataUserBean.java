package de.hwg_lu.bwi520.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.hwg_lu.bwi520.jdbc.NoConnectionException;
import de.hwg_lu.bwi520.jdbc.PostgreSQLAccess;

public class ChangeDataUserBean {
	

	String vorname;
	String nachname;
	String username;
	String email;
	String passwort;
	String rolle;
	String abteilung;
	

	public ChangeDataUserBean() {
		this.vorname="";
		this.nachname="";
		this.username=""; 
		this.email="";
		this.passwort="";
		this.rolle="";
		this.abteilung="";
	}

	
	public void enterDataUser(String benutzername) throws NoConnectionException, SQLException {
		String sql = "SELECT vorname, nachname, email, benutzername, password, rolle, abteilung from mitarbeiter where "
				+ "active=true and benutzername=?";
		System.out.println(sql);

		PreparedStatement prep = new PostgreSQLAccess().
				 getConnection().
				 prepareStatement(sql);
		prep.setString(1, benutzername);
		
		 ResultSet dbRes = prep.executeQuery();
		 
		 	while(dbRes.next()) {
		 		this.vorname		= dbRes.getString("vorname");
		 		this.nachname		= dbRes.getString("nachname");
		 		this.email		= dbRes.getString("email");
		 		this.username		= dbRes.getString("benutzername");
		 		this.passwort		= dbRes.getString("password");
		 		this.rolle		= dbRes.getString("rolle");
		 		this.abteilung		= dbRes.getString("abteilung");
		 	
		 	}
		
		 	System.out.println("Daten von " + benutzername + "werden gerade angezeigt");
		
	}
	
	public void changeDataUser (String changeVorname ,String changeNachname, String changeEmail, String changeUsername,  
			 String changePasswort, String changeRolle, String changeAbteilung) throws SQLException {
		
		String sql = "UPDATE Mitarbeiter SET vorname=?, nachname=?, email=?, benutzername=?, "
	               + "password=?, rolle=?, abteilung=? WHERE benutzername = ?";
	    
	    System.out.println(sql);
	    
	    // Prüfen, ob die neue Rolle "Admin" ist (Vergleich ist nicht case-sensitiv)
	   // boolean isAdmin = changeRolle.equalsIgnoreCase("Admin");
	    
	    Connection dbConn = new PostgreSQLAccess().getConnection();
	    PreparedStatement prep = dbConn.prepareStatement(sql);
	    
	    prep.setString(1, changeVorname);
	    prep.setString(2, changeNachname);
	    prep.setString(3, changeEmail);
	    prep.setString(4, changeUsername);
	    prep.setString(5, changePasswort);
	    prep.setString(6, changeRolle);
	    prep.setString(7, changeAbteilung);
	  //  prep.setBoolean(8, isAdmin);  // Admin-Spalte setzen
	    prep.setString(8, changeUsername); // WHERE-Bedingung

	    // Statement ausführen
	    int rowsAffected = prep.executeUpdate();
	    
	    if (rowsAffected > 0) {
	        System.out.println("Daten des Benutzers wurden erfolgreich geändert.");
	    } else {
	        System.out.println("Fehler: Kein Benutzer mit diesem Benutzernamen gefunden.");
	    }
	    
	}
	

	public String getVorname() {
		return vorname;
	}


	public void setVorname(String vorname) {
		this.vorname = vorname;
	}


	public String getNachname() {
		return nachname;
	}


	public void setNachname(String nachname) {
		this.nachname = nachname;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswort() {
		return passwort;
	}


	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}


	public String getRolle() {
		return rolle;
	}


	public void setRolle(String rolle) {
		this.rolle = rolle;
	}


	public String getAbteilung() {
		return abteilung;
	}


	public void setAbteilung(String abteilung) {
		this.abteilung = abteilung;
	}

	
	
}
