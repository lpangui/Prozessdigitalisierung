<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="de.hwg_lu.bwi520.beans.LoginBean"%>
<%@page import="de.hwg_lu.bwi520.beans.MeldungsBean"%>
<%@page import="de.hwg_lu.bwi520.beans.AntragsBean"%>
<%@page import="de.hwg_lu.bwi520.beans.GoRelaxBean"%>
<%@page import="de.hwg_lu.bwi520.beans.AdminBean"%>
<%@page import="de.hwg_lu.bwi520.beans.ChangeDataUserBean"%>
<%@page import="de.hwg_lu.bwi520.beans.PortalMeldungsBean"%>
<%@page import="de.hwg_lu.bwi520.beans.KontaktBean"%>

<%@page import="java.sql.SQLException"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AdminAppl</title>
</head>
<body>
<jsp:useBean id="myLog" class="de.hwg_lu.bwi520.beans.LoginBean" scope="session" />
<jsp:useBean id="myMel" class="de.hwg_lu.bwi520.beans.MeldungsBean" scope="session" />
<jsp:useBean id="myAnt" class="de.hwg_lu.bwi520.beans.AntragsBean" scope="session" />
<jsp:useBean id="myAdmin" class="de.hwg_lu.bwi520.beans.AdminBean" scope="session" />
<jsp:useBean id="myM" class="de.hwg_lu.bwi520.beans.KontaktBean" scope="session" />
<jsp:useBean id="myCDU" class="de.hwg_lu.bwi520.beans.ChangeDataUserBean" scope="session" />
<jsp:useBean id="myPM" class="de.hwg_lu.bwi520.beans.PortalMeldungsBean" scope="session" />
<jsp:useBean id="myGo" class="de.hwg_lu.bwi520.beans.GoRelaxBean" scope="session" />


<%
//Parameter für die links
String action = request.getParameter("action");

String userid = request.getParameter("userid");
String userid2 = request.getParameter("userid2");
String userid3 = request.getParameter("userid3");
String userid4 = request.getParameter("userid4");

//Parameter für KontaktAdmin

String NameKontakt= request.getParameter("vorUNDnachname");
String emailKontakt= request.getParameter("emailKontaktAdmin");
String messageKontakt = request.getParameter("message");

String userid5 = request.getParameter("userid5");

//Butoons
String btnAbsendenMsg = request.getParameter("formularAbsenden");
String btnZurückKontakt = request.getParameter("formularAbbrechenZurueck");
String zurueck4 = request.getParameter("zurueck4");

//Parameter für die Buttons
String btnZurueck = request.getParameter("zurueck");
String btnProfilSuper = request.getParameter("btnProfilSuper");
String btnbackTo = request.getParameter("btnbackTo");
String btnAktualisieren = request.getParameter("btnAktualisieren");
String backToVerwaltung = request.getParameter("backToVerwaltung");

//Parameter für Update von User-Daten
String firstNameChange= request.getParameter("firstNameChange");
String lastNameChange= request.getParameter("lastNameChange");
String usernameChange= request.getParameter("usernameChange");
String emailChange= request.getParameter("emailChange");
String passwordChange= request.getParameter("passwordChange");
String rolleChange= request.getParameter("rolleChange");
String abteilungChange= request.getParameter("abteilungChange");

if(action ==  null) action="";
if(btnZurueck ==  null) btnZurueck="";
if(btnProfilSuper ==  null) btnProfilSuper="";
if(btnbackTo ==  null) btnbackTo="";
if(btnAktualisieren ==  null) btnAktualisieren="";
if(backToVerwaltung ==  null) backToVerwaltung="";
if(btnAbsendenMsg ==  null) btnAbsendenMsg="";
if(btnZurückKontakt ==  null) btnZurückKontakt="";
if(zurueck4 ==  null) zurueck4="";




if(action.equals("aktiv")){
	
	myAdmin.userStatusAktiv(userid3);
	response.sendRedirect("./InaktiveUserView.jsp");
	
}else if(action.equals("inaktiv")){
	
	myAdmin.userStatusInaktiv(userid);

	response.sendRedirect("./AccountManagementView.jsp");
	
}else if(action.equals("delete")){
	
	myAdmin.deleteOneAccount(userid4);
	response.sendRedirect("./InaktiveUserView.jsp");
	
}else if(action.equals("bearbeiten")){
	
	myMel.setBenutzer(userid2);
	myCDU.enterDataUser(userid2);
	myMel.setWelcomeAktualisierung();
	
	response.sendRedirect("./ChangeDataUserView.jsp");
	
}else if(btnZurueck.equals("Zurück")){
	
	response.sendRedirect("./AdminView.jsp");
	
}else if(btnProfilSuper.equals("Zum Profil")){
	
	response.sendRedirect("./AdminView.jsp");
	
} else if(btnbackTo.equals("Abbrechen")){
	
	myMel.setWelcomeAktualisierung();
	response.sendRedirect("./AccountManagementView.jsp");
	
}else if(btnAktualisieren.equals("Aktualisieren")){

	try{
	
		myCDU.changeDataUser(firstNameChange, lastNameChange, emailChange, usernameChange, 
				passwordChange, rolleChange, abteilungChange);
			
		
		myAdmin.getMitarbeiterFromDB();
		myMel.setSuccessfulAktualisierung();
		
		System.out.println("Vorname: " + firstNameChange);
		System.out.println("Nachname: " + lastNameChange);
		System.out.println("E-Mail: " + emailChange);
		System.out.println("Benutzername: " + usernameChange);
		System.out.println("Passwort: " + passwordChange);
		System.out.println("Rolle: " + rolleChange);
		System.out.println("Abteilung: " + abteilungChange);
		
	}catch(Exception e){
		
		e.printStackTrace();
		myMel.setErrorAktualisierung();
		
	}

	
	response.sendRedirect("./ChangeDataUserView.jsp");
	
}else if(backToVerwaltung.equals("Zurück")){
	
	
	response.sendRedirect("./AccountManagementView.jsp");
	
}else if(btnAbsendenMsg.equals("Absenden")){
	
	try{
		
		myM.setName(NameKontakt);
		myM.setEmail(emailKontakt);
		myM.setMessage(messageKontakt);
		
		myM.insertMessageForAdmin();
		myM.getMeldungFromDB();
		myPM.setSuccessfulFormularKontakt();
		
	}catch(Exception e){
		
		myPM.setErrorFormularKontakt();
		e.printStackTrace();
	}

	response.sendRedirect("./KontaktAdminView.jsp");
	
}else if(btnZurückKontakt.equals("Zurück")){
	
	myPM.setFormularWelcomeKontakt();
	response.sendRedirect("./GoRelaxView.jsp");
	
}else if(action.equals("erledigen")){
	
	myM.changeStatus(userid5);
	response.sendRedirect("./MeldungView.jsp");
	
}else if(zurueck4.equals("Zurück")){
	
	response.sendRedirect("./AdminView.jsp");
	
}else{
	myLog.setLoggedIn(false);
    response.sendRedirect("./GoRelaxView.jsp");
}

%>
</body>
</html>