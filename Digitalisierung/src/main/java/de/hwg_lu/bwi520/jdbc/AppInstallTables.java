  package de.hwg_lu.bwi520.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class AppInstallTables {

	Connection dbConn;
	
	public static void main(String[] args) throws SQLException {
		AppInstallTables myApp = new AppInstallTables();
		myApp.dbConn = new PostgreSQLAccess().getConnection();
		myApp.doSomething();
	}

	public void doSomething() throws SQLException {
		this.executeUpdateWithoutParms("DROP TABLE IF EXISTS MeldungAdmin cascade");
		this.executeUpdateWithoutParms("DROP  TABLE IF EXISTS URLAUBSANTRAG cascade");
		this.executeUpdateWithoutParms("DROP  TABLE IF EXISTS Mitarbeiter cascade");
		this.executeUpdateWithoutParms("DROP TABLE IF EXISTS KRANKMELDUNG cascade");
		this.createTableMitarbeiter();
		this.insertDataMitarbeiter();
		this.createTableUrlaubsantrag();
		this.insertDataUrlaubsantrag();
		this.createTableMeldungAdmin();
		this.insertDataMeldungAdmin();
		this.createTabelleKrankmeldung();
		//this.insertDataKrankmeldungen();
	
	}
	
	public void executeUpdateWithoutParms(String sql) throws SQLException{
		System.out.println(sql);
		this.dbConn.prepareStatement(sql).executeUpdate();
	}
	public void createTableMitarbeiter() throws SQLException {
		this.executeUpdateWithoutParms(
				"CREATE TABLE Mitarbeiter (" +
						"Personalnummer SERIAL NOT NULL primary key," +
                        "Vorname VARCHAR(100) NOT NULL," +
                        "Nachname VARCHAR(100) NOT NULL," +
                        "Email VARCHAR(100)NOT NULL," +
                        "Benutzername VARCHAR(100) NOT NULL unique," + 
                        "Password VARCHAR(255) NOT NULL," +
                        "Rolle VARCHAR(50) NOT NULL, " +
                        "Abteilung VARCHAR(100) NOT NULL,"+
                        "urlaubstage INT DEFAULT 30," +
                        "active  boolean not null default true," +
                        "admin  boolean not null default false)"
                       
		);		
	}
	
	
	public void insertDataMitarbeiter() throws SQLException {
		this.executeUpdateWithoutParms(
				"INSERT INTO Mitarbeiter (Vorname, Nachname, Email, Benutzername, Password, Rolle, Abteilung, active, admin) VALUES " +
					  
					    "('Dylan', 'Pafoule', 'dylan.pafoule@madyar.de', 'dylan.pafoule', 'password12345', 'Admin', 'IT', TRUE, TRUE), " +
					    "('Rita', 'Lang', 'rita.lang@madyar.de', 'rita.lang', 'adminpass7890', 'Leiter', 'Leitung', TRUE, FALSE), " +
					    "('Ben', 'Schmidt', 'ben.schmidt@madyar.de', 'ben.schmidt', 'mysecurepass', 'Mitarbeiter', 'R&D', FALSE, FALSE), " +
					    "('Ariane', 'Sime', 'ariane.sime@madyar.de', 'ariane.sime', '12345678', 'Personalleiter', 'HR', TRUE, FALSE), " +
					    "('Eva', 'Wagner', 'eva.wagner@madyar.de', 'eva.wagner', 'pass7891011', 'Mitarbeiter', 'Vertrieb', TRUE, FALSE), " +
					    "('Petra', 'Müller', 'petra.mueller@madyar.de', 'petra.mueller', 'securepass2023', 'Mitarbeiter', 'Controlling', TRUE, FALSE), " +
					    "('Thomas', 'Schneider', 'thomas.schneider@madyar.de', 'thomas.schneider', 'securepass2023', 'Mitarbeiter', 'Finanz', FALSE, FALSE), " +
					    "('Mona', 'Schulz', 'mona.schulz@madyar.de', 'mona.schulz', 'password2024', 'Mitarbeiter', 'R&D', FALSE, FALSE), " +
					    "('Stefan', 'Meier', 'stefan.meier@madyar.de', 'stefan.meier', 'simplepass', 'Mitarbeiter', 'Marketing', FALSE, FALSE)" 
			);
		
	}
	
	public void createTableUrlaubsantrag() throws SQLException {
		this.executeUpdateWithoutParms(
				"CREATE TABLE URLAUBSANTRAG (" +
					    "Antrag_id SERIAL NOT NULL PRIMARY KEY, " +    
					    "erstellt_am TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP, " +
					  	"Personalnummer INT NOT NULL, " +
					    "grund TEXT, " +
					    "startdatum DATE NOT NULL, " +
					    "enddatum DATE NOT NULL, " +
					    "status VARCHAR(100) NOT NULL DEFAULT 'In Bearbeitung'," +
					    "FOREIGN KEY (personalnummer) REFERENCES MITARBEITER(Personalnummer) ON DELETE CASCADE)"
                      
		);
	}
	
	public void insertDataUrlaubsantrag() throws SQLException {
		this.executeUpdateWithoutParms(
				"INSERT INTO URLAUBSANTRAG (Personalnummer, grund, startdatum, enddatum, status) VALUES " +
			             "('3', 'Erholungsurlaub', '2025-06-10', '2025-06-20', 'In Bearbeitung'), " +
			             "('6', 'Erholungsurlaub', '2025-06-10', '2025-06-20', 'In Bearbeitung')"

			); 

	}

	public void createTableMeldungAdmin() throws SQLException {
		this.executeUpdateWithoutParms(
				"CREATE TABLE MeldungAdmin (" +
					    "Meldung_id SERIAL NOT NULL PRIMARY KEY, " +
					    "name VARCHAR(100) NOT NULL, " +
					    "email VARCHAR(150) NOT NULL, " +
					    "message TEXT NOT NULL, " +
					    "status VARCHAR(50) NOT NULL DEFAULT 'Offen', " +
					    "gesendet_am TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP" +
					    ")"
                      
		);
	}
	
	public void insertDataMeldungAdmin() throws SQLException {
		this.executeUpdateWithoutParms(
				"INSERT INTO MeldungAdmin (name, email, message, status, gesendet_am) VALUES " +
					    "('Lukas Weber', 'lukas.weber@madyar.de', 'Ich habe Schwierigkeiten bei der Integration des neuen Moduls.', 'Offen', CURRENT_TIMESTAMP), " +
					    "('Julia Fischer', 'julia.fischer@madyar.de', 'Das System ist sehr langsam, könnten wir eine Performance-Analyse durchführen?', 'Offen', CURRENT_TIMESTAMP), " +
					    "('Niklas Braun', 'niklas.braun@madyar.de', 'Es gibt ein Problem mit der Benutzerberechtigung in der App.', 'Offen', CURRENT_TIMESTAMP), " +
					    "('Sandra Hoffmann', 'sandra.hoffmann@madyar.de', 'Die automatische Datenbank-Sicherung funktioniert nicht ordnungsgemäß.', 'Offen', CURRENT_TIMESTAMP), " +
					    "('Felix Zimmermann', 'felix.zimmermann@madyar.de', 'Der Kalender zeigt falsche Termine an, nachdem das Update installiert wurde.', 'Offen', CURRENT_TIMESTAMP)"
			); 

	}

	public void createTabelleKrankmeldung() throws SQLException {
		this.executeUpdateWithoutParms(

				"CREATE TABLE KRANKMELDUNG ("
			    + "km_id SERIAL PRIMARY KEY, "
			    + "personalnummer INT NOT NULL, "
			    + "erstattet_am TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP, "
			    + "startdatum DATE NOT NULL, "
			    + "enddatum DATE, " //nicht not null weil der Mitarbeiter nicht wissen könnte wann er zurückkommt.
			    + "status VARCHAR(20) DEFAULT 'Offen', "
			    + "bemerkung TEXT, "
			    + "attest BOOLEAN DEFAULT FALSE, "
			    + "FOREIGN KEY (personalnummer) REFERENCES MITARBEITER(Personalnummer) ON DELETE CASCADE ) "
			    
			); 

	}
	
	/*public void insertDataKrankmeldungen() throws SQLException {
		this.executeUpdateWithoutParms(
				""
			); 

	}*/
	

}
