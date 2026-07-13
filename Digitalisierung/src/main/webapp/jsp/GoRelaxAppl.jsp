<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="de.hwg_lu.bwi520.beans.LoginBean"%>
<%@page import="de.hwg_lu.bwi520.beans.MeldungsBean"%>
<%@page import="de.hwg_lu.bwi520.beans.AntragsBean"%>
<%@page import="de.hwg_lu.bwi520.beans.GoRelaxBean"%>
<%@page import="de.hwg_lu.bwi520.beans.AdminBean"%>
<%@page import="de.hwg_lu.bwi520.beans.UrlaubBean"%>
<%@page import="de.hwg_lu.bwi520.beans.KrankmeldungBean"%>
<%@page import="de.hwg_lu.bwi520.beans.ChangeDataUserBean"%>
<%@page import="de.hwg_lu.bwi520.beans.PortalMeldungsBean"%>

<%@page import="java.sql.SQLException"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GoRelaxAppl</title>
</head>
<body>
<jsp:useBean id="myLog" class="de.hwg_lu.bwi520.beans.LoginBean" scope="session" />
<jsp:useBean id="myMel" class="de.hwg_lu.bwi520.beans.MeldungsBean" scope="session" />
<jsp:useBean id="myAnt" class="de.hwg_lu.bwi520.beans.AntragsBean" scope="session" />
<jsp:useBean id="myAdmin" class="de.hwg_lu.bwi520.beans.AdminBean" scope="session" />
<jsp:useBean id="myUrlaub" class="de.hwg_lu.bwi520.beans.UrlaubBean" scope="session" />
<jsp:useBean id="myKrank" class="de.hwg_lu.bwi520.beans.KrankmeldungBean" scope="session" />
<jsp:useBean id="myCDU" class="de.hwg_lu.bwi520.beans.ChangeDataUserBean" scope="session" />
<jsp:useBean id="myPM" class="de.hwg_lu.bwi520.beans.PortalMeldungsBean" scope="session" />
<jsp:useBean id="myGo" class="de.hwg_lu.bwi520.beans.GoRelaxBean" scope="session" />


<%
//Parameter für Login
String usernameLog   = request.getParameter("usernameLog");
String passwordLog = request.getParameter("passwordLog");


//Parameter für Registrierung
String firstNameReg= request.getParameter("firstNameReg");
String lastNameReg= request.getParameter("lastNameReg");
String usernameReg= request.getParameter("usernameReg");
String emailReg= request.getParameter("emailReg");
String passwordReg= request.getParameter("passwordReg");
String rolle= request.getParameter("rolle");
String abteilung= request.getParameter("abteilung");

//Submits Buttons
String backToTask = request.getParameter("backtotask");
String losgehts = request.getParameter("losgehts");
String abmelden = request.getParameter("btnAbmelden");


String register= request.getParameter("btnReg");
String zurueckLog = request.getParameter("btnZurueck");

//Parameter für den Antrag
String startdatum   = request.getParameter("startdatum");
String enddatum   = request.getParameter("enddatum");
String grund   = request.getParameter("grund");
String	antragAbsenden   = request.getParameter("antragAbsenden");
//String	antragAbbrechen44   = request.getParameter("antragAbbrechen44");
String  personalnummerString  = request.getParameter("personnalnummer");

//Parameter für Krankmeldung
String Krankstartdatum   = request.getParameter("Krankstartdatum");
String Krankenddatum   = request.getParameter("Krankenddatum");
String bemerkung   = request.getParameter("bemerkung");
String attest   = request.getParameter("attest");
String  KrankpersonalnummerString  = request.getParameter("Krankpersonnalnummer");
//Buttons für Krankmeldung
String	krankAbsenden   = request.getParameter("krankAbsenden");
String	KrankAbbrechen   = request.getParameter("KrankAbbrechen");



String rolleZuordnung="";
if(losgehts == null) losgehts = "";
if(backToTask == null) backToTask="";
if(register == null) register="";
if(zurueckLog == null) zurueckLog="";
if(abmelden == null) abmelden="";
if (antragAbsenden == null) antragAbsenden = "";
//if (antragAbbrechen44 == null) antragAbbrechen44 = "";
if (krankAbsenden == null) krankAbsenden = "";
if (KrankAbbrechen == null) KrankAbbrechen = "";


if (losgehts.equals("Los geht's")) {
	myLog.setBenutzername(usernameLog);
    myLog.setPassword(passwordLog);
   // myObjekt.setFüllePersonalid(usernameLog);
    myUrlaub.setAntragsSteller(usernameLog);
    myKrank.setKrankSteller(usernameLog);

    try {    
        boolean gefunden = myLog.checkUsernamePassword();
        
        if (gefunden) {
            myLog.setLoggedIn(true);
            rolleZuordnung = myLog.checkRolle();
            System.out.println(rolleZuordnung);

            // Retrieve Mitarbeiter_id and store it in the session
            int mitarbeiterId = myLog.getMitarbeiterId();
            session.setAttribute("Mitarbeiter_id", mitarbeiterId);
        
            if (rolleZuordnung.equals("Mitarbeiter")) {
                response.sendRedirect("./MitarbeiterView.jsp");
            } else if (rolleZuordnung.equals("Leiter")) {
                response.sendRedirect("./LeiterView.jsp");
            } else if (rolleZuordnung.equals("Admin")) {
                response.sendRedirect("./AdminView.jsp");
            } else if (rolleZuordnung.equals("Personalleiter")) {
                response.sendRedirect("./HRView.jsp");
            }
        } else {
            myLog.setLoggedIn(false);
           	myMel.setLoginFailed();
            response.sendRedirect("./LoginView.jsp");
        }
    } catch (SQLException se) {
        se.printStackTrace();
        response.sendRedirect("./LoginView.jsp");
    }
} else if (backToTask.equals("Zurück")) {
    response.sendRedirect("./GoRelaxView.jsp");
    
} else if (abmelden.equals("Abmelden")) {
   	myMel.setLogoutSuccessful();
    response.sendRedirect("./LoginView.jsp");
    
}else if(register.equals("User anlegen")){

	myGo.setVorname(firstNameReg);
	myGo.setNachname(lastNameReg);
	myGo.setEmail(emailReg);
	myGo.setUsername(usernameReg);
	myGo.setPasswort(passwordReg);
	myGo.setRolle(rolle);
	myGo.setAbteilung(abteilung);
	
	try{	
		boolean hatGeklappt = myGo.insertAccountIfNotExists();
		if (hatGeklappt) myPM.setRegSuccessful(usernameReg);
		else myPM.setAccountAlreadyExists(usernameReg, emailReg);
	}catch(SQLException se){
		se.printStackTrace();
		myPM.setDBError();
	}
	
	myAdmin.getMitarbeiterFromDB();
	response.sendRedirect("./RegistrierungView.jsp");
	
}else if(zurueckLog.equals("Zurück")){
	
	myMel.setReturnToLogin();
	response.sendRedirect("./LoginView.jsp");	
	
}else if(antragAbsenden.equals("Absenden")){
	
	int personalnummer = 0;
	try{
		personalnummer = Integer.parseInt(personalnummerString);
	}catch(Exception nfe){
		nfe.printStackTrace();
	}
	
	myAnt.setEnddatum(enddatum);
	myAnt.setStartdatum(startdatum);
	myAnt.setPersonalnummer(personalnummer);
	myAnt.setGrund(grund);
	
	if(myAnt.kannUrlaubBeantragen()){
		
		myAnt.insertUrlaubsAntrag();
		myUrlaub.getAlleAntraegeFromDB();
		myKrank.getAlleKrankmeldungenFromDB();
		
		myPM.setSuccessfulFormularUA();
	}else{
		
		myPM.setErrorFormularUA();
	}	
	

	response.sendRedirect("./AntragView.jsp");
	
}else if(krankAbsenden.equals("Absenden")){
	
	int Krankpersonalnummer = 0;
	try{
		Krankpersonalnummer = Integer.parseInt(KrankpersonalnummerString);
	}catch(Exception nfe){
		nfe.printStackTrace();
	}
	
	try{
		
		myKrank.setEnddatum(Krankenddatum);
		myKrank.setStartdatum(Krankstartdatum);
		myKrank.setPersonalnummer(Krankpersonalnummer);
		myKrank.setBemerkung(bemerkung);
		myKrank.setAttest(attest);
		
		myKrank.insertKrankmeldung(); //falls Methode fehlschlägt.
		myPM.setSuccessfulFormularKM();
		myUrlaub.getAlleAntraegeFromDB();
		myKrank.getAlleKrankmeldungenFromDB();
		
	}catch(Exception e){
		
		myPM.setErrorFormularKM();
		e.printStackTrace();
	}

	response.sendRedirect("./KrankmeldungView.jsp");
	
}else if(KrankAbbrechen.equals("Zurück")){

	myPM.setFormularWelcomeKM();
	response.sendRedirect("./MitarbeiterView.jsp");	
	
}
else{
	myLog.setLoggedIn(false);
    response.sendRedirect("./GoRelaxView.jsp");
}

%>
</body>
</html>