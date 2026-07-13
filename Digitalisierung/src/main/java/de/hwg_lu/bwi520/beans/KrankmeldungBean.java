package de.hwg_lu.bwi520.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import de.hwg_lu.bwi520.jdbc.NoConnectionException;
import de.hwg_lu.bwi520.jdbc.PostgreSQLAccess;
import de.hwg_lu.bwi520.keineBeans.Krankmeldung;

public class KrankmeldungBean {

	int personalnummer;
	String startdatum;
	String enddatum;
	String bemerkung;
	String attest;
	String krankSteller;
	
	Vector <Krankmeldung> alleKrankmeldungen;
	
	public KrankmeldungBean() throws NoConnectionException, SQLException {
		this.attest="";
		this.enddatum="";
		this.startdatum="";
		this.bemerkung="";
		this.personalnummer=0;
		
		this.alleKrankmeldungen= new Vector<Krankmeldung> ();
		this.getAlleKrankmeldungenFromDB();
	
	
	}

	
	
	public void insertKrankmeldung() throws NoConnectionException, SQLException {
	    String sql = "INSERT INTO Krankmeldung (personalnummer, startdatum, enddatum, bemerkung, attest) VALUES (?, ?, ?, ?, ?)";
	    System.out.println(sql);

	   Connection dbConn = new PostgreSQLAccess().getConnection();
	         PreparedStatement prep = dbConn.prepareStatement(sql);

	        prep.setInt(1, this.personalnummer);

	        LocalDate parsedStartdatum = LocalDate.parse(this.startdatum);
	        prep.setDate(2, java.sql.Date.valueOf(parsedStartdatum));

	        // Enddatum (Wenn nicht leer, ansonsten NULL setzen)
	        if (this.enddatum != null && !this.enddatum.trim().isEmpty()) {
	            prep.setDate(3, java.sql.Date.valueOf(LocalDate.parse(this.enddatum)));
	        } else {
	            prep.setNull(3, java.sql.Types.DATE); // Falls kein Enddatum, wird NULL gespeichert
	        }

	        prep.setString(4, this.bemerkung);

	        boolean attestWert = this.attest != null && this.attest.trim().equalsIgnoreCase("Ja");
	        prep.setBoolean(5, attestWert);

	        // Ausführen der Anfrage
	        prep.executeUpdate();
	        System.out.println("Krankmeldung erfolgreich eingereicht");
	   
	    }
	
	
public void getAlleKrankmeldungenFromDB() throws NoConnectionException, SQLException {
	
    this.alleKrankmeldungen.clear();

    String sql = "SELECT K.km_id, K.erstattet_am, M.vorname, M.nachname, K.startdatum, K.enddatum, K.status, K.bemerkung, K.attest " +
                 "FROM KRANKMELDUNG K " +
                 "JOIN MITARBEITER M ON K.personalnummer = M.personalnummer "
                 + "Where K.status ='Offen' " + 
                 "ORDER BY K.km_id ASC";  

    System.out.println(sql);
    
    ResultSet dbRes = new PostgreSQLAccess().getConnection()
                      .prepareStatement(sql).executeQuery();

    while (dbRes.next()) {
        this.alleKrankmeldungen.add(
            new Krankmeldung(
                dbRes.getInt("km_id"),
                dbRes.getString("erstattet_am").trim(),
                dbRes.getString("vorname") + " " + dbRes.getString("nachname"),
                dbRes.getString("startdatum"),  
                dbRes.getString("enddatum"),
                dbRes.getString("bemerkung"), 
                dbRes.getString("attest").equals("true") ? "Ja" : "Nein", // Attest vorhanden oder nicht
                dbRes.getString("status").trim()
            )
        );
    }
}
	


public String getAlleEingereichtenKrankmeldungen () {
	
	String html="";
	
	for(Krankmeldung myKrank: this.alleKrankmeldungen) {
		
			html+= "<tr>\n";
			html+= " <td>"+ myKrank.getKm_id() +"</td>";
			html+=	" <td>"+ myKrank.getErstellt_am()  +"</td>";
			html+= "<td>"+ myKrank.getNamenKrank() +"</td>";
			html+= "<td> <div class='message-box'>"+ myKrank.getBemerkung() +"</td>";
			html+= "<td>"+ myKrank.getStartdatum() 	+"</td>";
			html+= "<td>"+ myKrank.getEnddatum() 	+"</td>";
			
			html+= "<td>"+ myKrank.getAttest() 	+"</td>";
			html+= "<td>"+ myKrank.getStatus() 	+"</td>";
			html+= "<td>" + "<a href='./UrlaubAppl.jsp?action=krankmeldunggenehmigt&"
			
							 + "userid9=" + myKrank.getKm_id() + "'> Genehmigen </a> <br> <a href='./UrlaubAppl.jsp?action=krankmeldungabgelehnt&" + "userid56=" + myKrank.getKm_id()+ "'> Ablehnen </a>"
							+ " </td>";
	}
	
	html +="</tr>";
	return html;
}

//rufe bei Login die Personal_ID ab
public  int personalidInt()throws NoConnectionException, SQLException {
	
	int x=0;
	String sql = "select personalnummer from Mitarbeiter "
						+ "where Benutzername =?";
	System.out.println(sql);
	PreparedStatement prep = new PostgreSQLAccess().getConnection().prepareStatement(sql);
	prep.setString(1,  this.krankSteller);
	ResultSet dbRes = prep.executeQuery();
	while(dbRes.next()) { 
		x =dbRes.getInt("personalnummer");
	}
	return x;
}

public String getUserKrankmeldungenFromDB() throws NoConnectionException, SQLException {
    StringBuilder html = new StringBuilder();

    String sql = "SELECT K.km_id, K.erstattet_am, K.startdatum, K.enddatum, K.status, K.bemerkung, K.attest " +
                 "FROM KRANKMELDUNG K " +
                 "JOIN MITARBEITER M ON K.personalnummer = M.personalnummer " +
                 "WHERE K.personalnummer = ? " +  
                 "ORDER BY K.km_id ASC";

    System.out.println(sql);

    try (Connection conn = new PostgreSQLAccess().getConnection();
         PreparedStatement prep = conn.prepareStatement(sql)) {
        
        prep.setInt(1, this.personalidInt());
        ResultSet dbRes = prep.executeQuery();

        while (dbRes.next()) {
            int krank_id = dbRes.getInt("km_id");
            String erstattet_am = dbRes.getString("erstattet_am").trim();
            String startdatum = dbRes.getString("startdatum").trim();
            String enddatum = dbRes.getString("enddatum");

            // Falls das Enddatum NULL oder leer ist, auf "-" setzen
            if (enddatum == null || enddatum.trim().isEmpty()) {
                enddatum = "-";
            }

            String bemerkung = dbRes.getString("bemerkung").trim();
            boolean attestBool = dbRes.getBoolean("attest");
            String attest = attestBool ? "Ja" : "Nein";  // Attest anzeigen (Ja/Nein)
            String status = dbRes.getString("status").trim();

            // Tabelle mit den Krankmeldungen
            html.append("<tr>")
                .append("<td>").append(krank_id).append("</td>")
                .append("<td>").append(erstattet_am).append("</td>")
                .append("<td>").append(startdatum).append("</td>")
                .append("<td>").append(enddatum).append("</td>")  // Hier wird das Enddatum angezeigt
                .append("<td> <div class='message-box'>").append(bemerkung).append("</div></td>")
                .append("<td>").append(attest).append("</td>")
                .append("<td>").append(status).append("</td>")
                .append("</tr>");
        }

    }

    return html.toString();
}

public void genehmigenKrankmeldung(int km_Id) throws SQLException {

	String sql = "UPDATE krankmeldung SET status = 'Genehmigt' WHERE km_id = ?";
    System.out.println(sql);

    try (Connection connection = new PostgreSQLAccess().getConnection();
         PreparedStatement prep = connection.prepareStatement(sql)) {

        prep.setInt(1, km_Id); 

        int rowsUpdated = prep.executeUpdate();

        if (rowsUpdated > 0) {
            System.out.println("Krankmeldung wurde erfolgreich genehmigt.");
        } else {
            System.out.println("Keine Krankmeldung mit dieser ID gefunden.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw e; // Fehler weiterwerfen
    }

    // Aktualisiere die Liste der Anträge
    this.alleKrankmeldungen.clear();
    this.getAlleKrankmeldungenFromDB();
}


public void ablehnenKrankmeldung(int km_Id) throws SQLException {

	String sql = "UPDATE krankmeldung SET status = 'Abgelehnt' WHERE km_id = ?";
    System.out.println(sql);

    try (Connection connection = new PostgreSQLAccess().getConnection();
         PreparedStatement prep = connection.prepareStatement(sql)) {

        prep.setInt(1, km_Id); 

        int rowsUpdated = prep.executeUpdate();

        if (rowsUpdated > 0) {
            System.out.println("Krankmeldung wurde erfolgreich abgelehnt.");
        } else {
            System.out.println("Keine Krankmeldung mit dieser ID gefunden.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw e; // Fehler weiterwerfen
    }

    // Aktualisiere die Liste der Anträge
    this.alleKrankmeldungen.clear();
    this.getAlleKrankmeldungenFromDB();
}
	
public String getAlleUrlaubsantraegeUndKrankmeldungenForHR() throws NoConnectionException, SQLException {
    StringBuilder html = new StringBuilder();

    // SQL-Abfrage für Urlaubsanträge und Resturlaub
    String sql = "SELECT UA.Personalnummer, CONCAT(M.vorname, ' ', M.nachname) AS name, UA.erstellt_am, UA.grund, UA.startdatum, UA.enddatum, UA.status, M.urlaubstage " +
                 "FROM URLAUBSANTRAG UA " +
                 "JOIN MITARBEITER M ON UA.Personalnummer = M.Personalnummer " +
                 "ORDER BY UA.Antrag_id ASC";  

    // SQL-Abfrage für Krankmeldungen
    String krankmeldungSql = "SELECT K.personalnummer, K.startdatum, K.enddatum, K.status " +
                             "FROM KRANKMELDUNG K " +
                             "ORDER BY K.personalnummer ASC, K.startdatum DESC";

    System.out.println(sql);
    System.out.println(krankmeldungSql);

    try (Connection conn = new PostgreSQLAccess().getConnection();
         PreparedStatement prepUrlaub = conn.prepareStatement(sql);
         PreparedStatement prepKrank = conn.prepareStatement(krankmeldungSql)) {

        ResultSet dbResUrlaub = prepUrlaub.executeQuery();
        ResultSet dbResKrank = prepKrank.executeQuery();

        // Map für Krankmeldungen (Personalnummer -> Liste von Krankmeldungen)
        Map<Integer, List<String>> krankmeldungenMap = new HashMap<>();
        while (dbResKrank.next()) {
            int personalnummer = dbResKrank.getInt("personalnummer");
            String startdatum = dbResKrank.getString("startdatum");
            String enddatum = dbResKrank.getString("enddatum");
            String status = dbResKrank.getString("status");

            // **Falls `enddatum` NULL ist, setzen wir "-"**
            if (enddatum == null || enddatum.trim().isEmpty()) {
                enddatum = "-";
            }

            String krankmeldung = "Krank von: " + startdatum + " bis " + enddatum + " (" + status + ")";

            krankmeldungenMap.computeIfAbsent(personalnummer, k -> new ArrayList<>()).add(krankmeldung);
        }

        // Tabelle mit Urlaubsanträgen und Krankmeldungen erstellen
        while (dbResUrlaub.next()) {
            String name = dbResUrlaub.getString("name").trim();
            String erstellt_am = dbResUrlaub.getString("erstellt_am").trim();
            String grund = dbResUrlaub.getString("grund").trim();
            String startdatum = dbResUrlaub.getString("startdatum").trim();
            String enddatum = dbResUrlaub.getString("enddatum").trim();
            String status = dbResUrlaub.getString("status").trim();
            int resturlaub = dbResUrlaub.getInt("urlaubstage");
            int personalnummer = dbResUrlaub.getInt("Personalnummer");

            // Alle Krankmeldungen für diesen Mitarbeiter holen
            List<String> krankmeldungen = krankmeldungenMap.getOrDefault(personalnummer, Collections.singletonList("Nicht krank"));
            String krankmeldungenText = String.join("<br> <br>", krankmeldungen);

            html.append("<tr>\r\n")
                .append("<td>").append(name).append("</td>\r\n") 
                .append("<td>").append(erstellt_am).append("</td>\r\n")
                .append("<td>").append(grund).append("</td>\r\n")
                .append("<td>").append(startdatum).append("</td>\r\n")
                .append("<td>").append(enddatum).append("</td>\r\n")
                .append("<td>").append(status).append("</td>\r\n")
                .append("<td>").append(krankmeldungenText).append("</td>\r\n")  // Mehrere Krankmeldungen pro Mitarbeiter
                .append("<td>").append("Resturlaub: ").append(resturlaub).append(" Tage").append("</td>\r\n")
                .append("</tr>\r\n");
        }
    }

    return html.toString(); 
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

	public String getBemerkung() {
		return bemerkung;
	}

	public void setBemerkung(String bemerkung) {
		this.bemerkung = bemerkung;
	}

	public String getAttest() {
		return attest;
	}

	public void setAttest(String attest) {
		this.attest = attest;
	}

	public Vector<Krankmeldung> getAlleKrankmeldungen() {
		return alleKrankmeldungen;
	}

	public void setAlleKrankmeldungen(Vector<Krankmeldung> alleKrankmeldungen) {
		this.alleKrankmeldungen = alleKrankmeldungen;
	}



	public String getKrankSteller() {
		return krankSteller;
	}



	public void setKrankSteller(String krankSteller) {
		this.krankSteller = krankSteller;
	}

	
}
