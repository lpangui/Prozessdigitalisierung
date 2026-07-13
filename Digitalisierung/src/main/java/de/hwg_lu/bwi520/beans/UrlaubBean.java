package de.hwg_lu.bwi520.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import de.hwg_lu.bwi520.jdbc.NoConnectionException;
import de.hwg_lu.bwi520.jdbc.PostgreSQLAccess;
import de.hwg_lu.bwi520.keineBeans.Urlaub;

public class UrlaubBean {
       
	String AntragsSteller;
	Vector <Urlaub> alleAntraege;
	 
public UrlaubBean() throws NoConnectionException, SQLException {
		this.alleAntraege= new Vector<Urlaub> ();
		this.getAlleAntraegeFromDB(); 
		
} 


public  int personalidInt()throws NoConnectionException, SQLException {
	
	int x=0;
	String sql = "select personalnummer from Mitarbeiter "
						+ "where Benutzername =?";
	System.out.println(sql);
	PreparedStatement prep = new PostgreSQLAccess().getConnection().prepareStatement(sql);
	prep.setString(1,  this.AntragsSteller);
	ResultSet dbRes = prep.executeQuery();
	while(dbRes.next()) { 
		x =dbRes.getInt("personalnummer");
	}
	return x;
}

//Abrufen alle userbezogenen Anträge
public String getAlleUrlaubsantraegeUserFromDB() throws NoConnectionException, SQLException {
    StringBuilder html = new StringBuilder();

    // SQL-Abfrage: Alle Urlaubsanträge + Resturlaub für den eingeloggten Mitarbeiter abrufen
    String sql = "SELECT u.Antrag_id, u.erstellt_am, u.grund, u.startdatum, u.enddatum, u.status, " +
                 "m.urlaubstage " +
                 "FROM URLAUBSANTRAG u " +
                 "JOIN MITARBEITER m ON u.Personalnummer = m.Personalnummer " +
                 "WHERE u.Personalnummer = ? " +
                 "ORDER BY u.Antrag_id ASC";

    System.out.println(sql);

    try (Connection conn = new PostgreSQLAccess().getConnection();
         PreparedStatement prep = conn.prepareStatement(sql)) {

        prep.setInt(1, this.personalidInt()); // Eingeloggte Personalnummer setzen
        ResultSet dbRes = prep.executeQuery();

        // Tabelle mit Kopfzeile erstellen
      

        int resturlaub = 30; // Standardwert für Urlaubstage

        while (dbRes.next()) {
            int Antrag_id = dbRes.getInt("Antrag_id");
            String erstellt_am = dbRes.getString("erstellt_am").trim();
            String grund = dbRes.getString("grund").trim();
            String startdatum = dbRes.getString("startdatum").trim();
            String enddatum = dbRes.getString("enddatum").trim();
            String status = dbRes.getString("status").trim();
            resturlaub = dbRes.getInt("urlaubstage"); // Resturlaub aktualisieren

            html.append("<tr>\n")
                .append("<td>").append(Antrag_id).append("</td>")
                .append("<td>").append(erstellt_am).append("</td>")
                .append("<td>").append(grund).append("</td>")
                .append("<td>").append(startdatum).append("</td>")
                .append("<td>").append(enddatum).append("</td>")
                .append("<td>").append(status).append("</td>")
                .append("</tr>");
        }

        // Resturlaub anzeigen
        html.append("<p><b>Verbleibender Urlaub:</b> ").append(resturlaub).append(" Tage</p>");
    }

    return html.toString();
}

//Leiter
public String getAlleAntraege () {
		
		String html="";
		
		for(Urlaub myUrlaub: this.alleAntraege) {
			
				html+= "<tr>\n";
				html+= " <td>"+ myUrlaub.getAntrag_id() +"</td>";
				html+=	" <td>"+ myUrlaub.getErstellt_am()  +"</td>";
				html+= "<td>"+ myUrlaub.getNamen() +"</td>";
				html+= "<td>"+ myUrlaub.getGrund() +"</td>";
				html+= "<td>"+ myUrlaub.getStartdatum() 	+"</td>";
				html+= "<td>"+ myUrlaub.getEnddatum() 	+"</td>";
				html+= "<td>"+ myUrlaub.getStatus() 	+"</td>";
				html+= "<td>" + "<a href='./UrlaubAppl.jsp?action=genehmigt&"
				
								 + "userid=" + myUrlaub.getAntrag_id() + "'> Genehmigen </a> <br> <a href='./UrlaubAppl.jsp?action=abgelehnt&" + "userid2=" + myUrlaub.getAntrag_id()+ "'> Ablehnen </a>"
								+ " </td>";
		}
		
		html +="</tr>";
		return html;
}
	
	
public void genehmigenAntrag(int antragId) throws SQLException {
    String updateStatusSql = "UPDATE URLAUBSANTRAG SET status = 'Genehmigt' WHERE Antrag_id = ?";
    String getDatesSql = "SELECT startdatum, enddatum, Personalnummer FROM URLAUBSANTRAG WHERE Antrag_id = ?";
    String updateUrlaubstageSql = "UPDATE MITARBEITER SET urlaubstage = urlaubstage - ? WHERE Personalnummer = ?";

    try (Connection connection = new PostgreSQLAccess().getConnection()) {
        connection.setAutoCommit(false); // Transaktion starten
        LocalDate startdatum = null;
        LocalDate enddatum = null;
        int personalnummer = 0;
        // Start- und Enddatum aus der Datenbank abrufen
        try (PreparedStatement prepGetDates = connection.prepareStatement(getDatesSql)) {
            prepGetDates.setInt(1, antragId);
            try (ResultSet rs = prepGetDates.executeQuery()) {
                if (rs.next()) {
                    startdatum = rs.getDate("startdatum").toLocalDate();
                    enddatum = rs.getDate("enddatum").toLocalDate();
                    personalnummer = rs.getInt("Personalnummer");
                } else {
                    throw new SQLException("Antrag mit ID " + antragId + " nicht gefunden!");
                }
            }
        }
        // Werktage berechnen (ohne Wochenenden)
        int werktage = berechneWerktage(startdatum, enddatum);
        if (werktage <= 0) {
            throw new SQLException("Fehler: Keine gültigen Werktage für den Antrag!");
        }
        // Urlaubstage aktualisieren
        try (PreparedStatement prepUpdateUrlaub = connection.prepareStatement(updateUrlaubstageSql)) {
            prepUpdateUrlaub.setInt(1, werktage);
            prepUpdateUrlaub.setInt(2, personalnummer);
            prepUpdateUrlaub.executeUpdate();
        }
      //️Status des Antrags auf "Genehmigt" setzen
        try (PreparedStatement prepUpdateStatus = connection.prepareStatement(updateStatusSql)) {
            prepUpdateStatus.setInt(1, antragId);
            prepUpdateStatus.executeUpdate();
        }
        connection.commit(); // Transaktion abschließen
        System.out.println("Antrag genehmigt! Urlaubstage aktualisiert.");
    } catch (SQLException e) {
        e.printStackTrace();
        throw e; // Fehler weitergeben
    }
    this.alleAntraege.clear();
    this.getAlleAntraegeFromDB();
}
//berechnenWerktage
private int berechneWerktage(LocalDate start, LocalDate end) {
    int werktage = 0;
    while (!start.isAfter(end)) {
        DayOfWeek tag = start.getDayOfWeek();
        if (tag != DayOfWeek.SATURDAY && tag != DayOfWeek.SUNDAY) {
            werktage++;
        }
        start = start.plusDays(1);
    }
    return werktage;
}



public void ablehnenAntrag(int antragId) throws SQLException {
	    // SQL-Query, um den Status auf 'Abgelehnt' zu setzen
	    String sql = "UPDATE URLAUBSANTRAG SET status = 'Abgelehnt' WHERE Antrag_id = ?";
	    System.out.println(sql);
	
	    try (Connection connection = new PostgreSQLAccess().getConnection();
	         PreparedStatement prep = connection.prepareStatement(sql)) {
	
	        prep.setInt(1, antragId); // Antrag-ID setzen
	
	        int rowsUpdated = prep.executeUpdate();
	
	        if (rowsUpdated > 0) {
	            System.out.println("Der Antrag wurde erfolgreich abgelehnt.");
	        } else {
	            System.out.println("Kein Antrag mit dieser ID gefunden.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e; // Fehler weiterwerfen
	    }
	
	    // Aktualisiere die Liste der Anträge
	    this.alleAntraege.clear();
	    this.getAlleAntraegeFromDB();
}
		

	
public void getAlleAntraegeFromDB() throws NoConnectionException, SQLException {
    this.alleAntraege.clear();
    
    String sql = "SELECT U.antrag_id, U.erstellt_am, M.vorname, M.nachname, U.grund, U.startdatum, U.enddatum, U.status " +
                 "FROM URLAUBSANTRAG U " +
                 "JOIN MITARBEITER M ON U.personalnummer = M.personalnummer " +
                 "WHERE U.status = 'In Bearbeitung' " +
                 "ORDER BY U.antrag_id ASC";
    
    System.out.println(sql);
    
    ResultSet dbRes = new PostgreSQLAccess().getConnection().
                      prepareStatement(sql).executeQuery();
    
    while (dbRes.next()) {
        this.alleAntraege.add(
            new Urlaub(
                dbRes.getInt("antrag_id"),
                dbRes.getString("erstellt_am").trim(),
                dbRes.getString("vorname") + " " + dbRes.getString("nachname"),  // Name statt Personalnummer
                dbRes.getString("grund").trim(),
                dbRes.getString("startdatum"),
                dbRes.getString("enddatum"),
                dbRes.getString("status").trim()
            )
        );
    }
}


public String getAlleUrlaubsantraegeUndKrankmeldungenForLeiterFromDB() throws NoConnectionException, SQLException {
    StringBuilder html = new StringBuilder();

    // SQL-Abfrage für Urlaubsanträge
    String sqlUrlaub = "SELECT CONCAT(M.vorname, ' ', M.nachname) AS name, UA.erstellt_am, UA.grund, UA.startdatum, UA.enddatum, UA.status, M.personalnummer " +
                       "FROM URLAUBSANTRAG UA " +
                       "JOIN MITARBEITER M ON UA.Personalnummer = M.Personalnummer " +
                       "ORDER BY UA.Antrag_id ASC";

    // SQL-Abfrage für Krankmeldungen (OHNE COALESCE!)
    String sqlKrankmeldung = "SELECT K.personalnummer, K.startdatum, K.enddatum, K.status " +
                             "FROM KRANKMELDUNG K " +
                             "ORDER BY K.personalnummer ASC, K.startdatum DESC";

    System.out.println(sqlUrlaub);
    System.out.println(sqlKrankmeldung);

    try (Connection conn = new PostgreSQLAccess().getConnection();
         PreparedStatement prepUrlaub = conn.prepareStatement(sqlUrlaub);
         PreparedStatement prepKrank = conn.prepareStatement(sqlKrankmeldung)) {

        ResultSet dbResUrlaub = prepUrlaub.executeQuery();
        ResultSet dbResKrank = prepKrank.executeQuery();

        // Map für Krankmeldungen (Personalnummer -> Liste von Krankmeldungen)
        Map<Integer, List<String>> krankmeldungenMap = new HashMap<>();
        while (dbResKrank.next()) {
            int personalnummer = dbResKrank.getInt("personalnummer");
            String startdatum = dbResKrank.getString("startdatum");

            // **Enddatum prüfen: Falls NULL, setzen wir "-"**
            String enddatum = dbResKrank.getString("enddatum");
            if (enddatum == null || enddatum.trim().isEmpty()) {
                enddatum = "-";
            }

            String status = dbResKrank.getString("status");

            String krankmeldung = "Krank von: " + startdatum + " bis " + enddatum + " (" + status + ")";

            krankmeldungenMap.computeIfAbsent(personalnummer, k -> new ArrayList<>()).add(krankmeldung);
        }

        // Urlaubsanträge verarbeiten und Krankmeldungen hinzufügen
        while (dbResUrlaub.next()) {
            String name = dbResUrlaub.getString("name").trim();
            String erstellt_am = dbResUrlaub.getString("erstellt_am").trim();
            String startdatum = dbResUrlaub.getString("startdatum").trim();
            String enddatum = dbResUrlaub.getString("enddatum").trim();
            String grund = dbResUrlaub.getString("grund").trim();
            String status = dbResUrlaub.getString("status").trim();
            int personalnummer = dbResUrlaub.getInt("personalnummer");

            // Alle Krankmeldungen für diesen Mitarbeiter holen
            List<String> krankmeldungen = krankmeldungenMap.getOrDefault(personalnummer, Collections.singletonList("Nicht krank"));
            String krankmeldungenText = String.join("<br> <br>", krankmeldungen);

            html.append("<tr>")
                .append("<td>").append(name).append("</td>")
                .append("<td>").append(erstellt_am).append("</td>")
                .append("<td>").append(grund).append("</td>")
                .append("<td>").append(startdatum).append("</td>")
                .append("<td>").append(enddatum).append("</td>")
                .append("<td>").append(status).append("</td>")
                .append("<td>").append(krankmeldungenText).append("</td>")
                .append("</tr>");
        }
    }

    return html.toString();
}


public String getAntragsSteller() {
	return AntragsSteller;
}

public void setAntragsSteller(String antragsSteller) {
	AntragsSteller = antragsSteller;
}
public void setAlleAntraege(Vector<Urlaub> alleAntraege) {
	this.alleAntraege = alleAntraege;
}
}