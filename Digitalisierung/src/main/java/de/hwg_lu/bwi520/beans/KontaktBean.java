package de.hwg_lu.bwi520.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import de.hwg_lu.bwi520.jdbc.NoConnectionException;
import de.hwg_lu.bwi520.jdbc.PostgreSQLAccess;
import de.hwg_lu.bwi520.keineBeans.Kontakt;

public class KontaktBean {

	String email;
	String name;
	String message;
	
	Vector <Kontakt> alleMessageAdmin;
	
	
	public KontaktBean() throws NoConnectionException, SQLException {
		this.email="";
		this.name="";
		this.message="";
		
		this.alleMessageAdmin= new Vector<Kontakt> ();
		this.getMeldungFromDB();
	}

	public void insertMessageForAdmin() throws NoConnectionException, SQLException {
		//in Datenbank-Tabelle Account
		//mit PreparedStatements
		
		String sql= "insert into MeldungAdmin "
				+ "(name, email, message) "
				+ "values (?,?,?)";
		System.out.println(sql);
		
		//JDBC: Connection, Statement-Objekt, Daten reinschreiben, execute
		Connection dbConn = new PostgreSQLAccess().getConnection();
		PreparedStatement prep = dbConn.prepareStatement(sql);
		
		prep.setString(1, this.name);
		prep.setString(2, this.email);
		prep.setString(3, this.message);
	
		prep.executeUpdate();
		System.out.println("Message erfolgreich hinterlegt");
		
		
	}

	//lade alle Message von Admin
	public void getMeldungFromDB() throws NoConnectionException, SQLException {
		
		this.alleMessageAdmin.clear(); //erst Vector Pflege
		
		String sql = "SELECT name, email, gesendet_am, message, status from MeldungAdmin "
				+ "ORDER BY CASE WHEN status = 'Offen' THEN 1 ELSE 2 END, gesendet_am ASC";
		System.out.println(sql);
		ResultSet dbRes = new PostgreSQLAccess().getConnection().
							prepareStatement(sql).executeQuery();
		while (dbRes.next()) {
			this.alleMessageAdmin.add(
				new Kontakt(
					dbRes.getString("name").trim(),
					dbRes.getString("email").trim(),
					dbRes.getString("gesendet_am").trim(),
					dbRes.getString("message").trim(),
					dbRes.getString("status").trim()
				)
			);
		}
	}
	
	//Ausgabe aller Meldungen an Admin
	public String getAlleMeldungen () {
			
			String html="";
			
			for(Kontakt myMeldung: this.alleMessageAdmin) {
				
					html+= "<tr>\n";
					html+=	" <td>"+ myMeldung.getName()  +"</td>";
					html+= "<td>"+ myMeldung.getEmail() +"</td>";
					html+= "<td>"+ myMeldung.getGesendet_am() +"</td>";
					html+= "<td> <div class='message-box'>"+ myMeldung.getMessage() +"</td>";
					html+= "<td>"+ myMeldung.getStatus() 	+"</td>";
					html+= "<td>" + "<a href='./AdminAppl.jsp?action=erledigen&"
					
									 + "userid5=" + myMeldung.getEmail() + "'> Erledigen? </a>"
									+ " </td>";
			}
			
			html +="</tr>";
			return html;
	}
		
	//update status der Meldung
	
	public void changeStatus (String email) throws SQLException {

		String sql = "UPDATE MeldungAdmin SET status = 'schon erledigt' WHERE email=? ";

		System.out.println(sql);
		PreparedStatement prep = new PostgreSQLAccess().
					getConnection().
					prepareStatement(sql);
					prep.setString(1, email);
					prep.executeUpdate();

		System.out.println("Der Status der Meldung wurde erfolgreich geändert.");

		// bis hierhin DB-Pflege
		// jetzt noch Hauptspeicher bzw. Vector-Pflege
		this.alleMessageAdmin.clear();
		this.getMeldungFromDB();

		}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
