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
<title>UrlaubAppl</title>
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
String action = request.getParameter("action");
String antragId = request.getParameter("userid"); // Für Genehmigen
String antragId2 = request.getParameter("userid2"); // Für Ablehnen


//parameter für die Buttons
String btnZurueck = request.getParameter("zurueck3");
String btnZurueck2 = request.getParameter("zurueck2");

//Ausführung von Links in Krankmeldungsverwaltung
String krankId = request.getParameter("userid9"); // Für Genehmigen
String krankId2 = request.getParameter("userid56"); // Für Ablehnen
String btnZurueckKrank = request.getParameter("zurueck7");

//String antragAbbrechen44= request.getParameter("antragAbbrechen");

String btnZurueck11 = request.getParameter("zurueck11");
String btnZurueck13 = request.getParameter("zurueck13");


if(action ==  null) action="";
if(btnZurueck ==  null) btnZurueck="";
if(btnZurueck2 ==  null) btnZurueck2="";
if(btnZurueckKrank ==  null) btnZurueckKrank="";
if(btnZurueck11 ==  null) btnZurueck11="";
if(btnZurueck13 ==null) btnZurueck13="";
//f(antragAbbrechen44==null) antragAbbrechen44="";

String antragAbbrechen = request.getParameter("antragAbbrechen");

    if (antragAbbrechen != null) {
        response.sendRedirect("MitarbeiterView.jsp"); // Hier Zielseite anpassen
        return; // Beendet die Verarbeitung der Seite
    }



if(action.equals("genehmigt")){
	
	int antrag_int = 0; 
	try{
		antrag_int = Integer.parseInt(antragId);
	}catch(NumberFormatException nfe){
		nfe.printStackTrace();
	}
	
	
	myUrlaub.genehmigenAntrag(antrag_int);
	response.sendRedirect("./UrlaubView.jsp");
	
}else if(action.equals("abgelehnt")){
	
	int antrag_int2 = 0; 
	try{
		antrag_int2 = Integer.parseInt(antragId2);
	}catch(NumberFormatException nfe){
		nfe.printStackTrace();
	}
	
	myUrlaub.ablehnenAntrag(antrag_int2);
	response.sendRedirect("./UrlaubView.jsp");

}else if(btnZurueck.equals("Zurück")){
	
	//myUrlaub.getAlleAntraegeFromDB();
	//myKrank.getAlleKrankmeldungenFromDB();
	response.sendRedirect("./LeiterView.jsp");

}else if(btnZurueck2.equals("Zurück")){
	
	//myUrlaub.getAlleAntraegeFromDB();
	//myKrank.getAlleKrankmeldungenFromDB();
	response.sendRedirect("./MitarbeiterView.jsp");

}else if(action.equals("krankmeldunggenehmigt")){
	
	int krank = 0; 
	try{
		krank = Integer.parseInt(krankId);
	}catch(NumberFormatException nfe){
		nfe.printStackTrace();
	}
	
	myKrank.genehmigenKrankmeldung(krank);
	response.sendRedirect("./KMVerwaltungView.jsp");

}else if(action.equals("krankmeldungabgelehnt")){
	
	int krank = 0; 
	try{
		krank = Integer.parseInt(krankId2);
	}catch(NumberFormatException nfe){
		nfe.printStackTrace();
	}
	
	myKrank.ablehnenKrankmeldung(krank);
	response.sendRedirect("./KMVerwaltungView.jsp");

}else if(btnZurueckKrank.equals("Zurück")){
	
	//myUrlaub.getAlleAntraegeFromDB();
	//myKrank.getAlleKrankmeldungenFromDB();
	response.sendRedirect("./HRView.jsp");

}else if(btnZurueck11.equals("Zurück")){
		
	response.sendRedirect("./MitarbeiterView.jsp");

}else if(btnZurueck13.equals("Zurück")){
	
	response.sendRedirect("./HRView.jsp");

}/*else if(antragAbbrechen44.equals("Zurück")){
	

	response.sendRedirect("./MitarbeiterView.jsp");	
	
}*/ else{
	
	myLog.setLoggedIn(false);
    response.sendRedirect("./GoRelaxView.jsp");
}


%>
</body>
</html>