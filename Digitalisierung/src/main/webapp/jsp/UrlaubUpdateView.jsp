<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="de.hwg_lu.bwi520.beans.LoginBean"%>
<%@page import="de.hwg_lu.bwi520.beans.MeldungsBean"%>
<%@page import="de.hwg_lu.bwi520.beans.AntragsBean"%>
<%@page import="de.hwg_lu.bwi520.beans.GoRelaxBean"%>
<%@page import="de.hwg_lu.bwi520.beans.AdminBean"%>
<%@page import="de.hwg_lu.bwi520.beans.UrlaubBean"%>
<%@page import="de.hwg_lu.bwi520.beans.ChangeDataUserBean"%>
<%@page import="de.hwg_lu.bwi520.beans.KrankmeldungBean"%>
<%@page import="de.hwg_lu.bwi520.beans.PortalMeldungsBean"%>

<%@page import="java.sql.SQLException"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Leiter-Portal</title>
<link rel="stylesheet" type="text/css" href="../css/GoRelax.css">
<script type="text/javascript" src="../js/Hello.js" ></script>
<script type="text/javascript" src="../js/GoHello.js" ></script>
</head>
<body id=portalnone>
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
	if (!myLog.isLoggedIn()) response.sendRedirect("./GoRelaxView.jsp");
%>

    <header id="main-content">
        <h1>Übersicht über Mitarbeiterabwesenheiten</h1>

   </header>
 
      <form action="../jsp/UrlaubAppl.jsp" method="get">
    	<table class="styled-table">
			  <tr>
			  	<th>eingereicht von</th>
			  	<th>eingereicht am</th>
			    <th>Grund</th>
			    <th>vom</th>
			    <th>bis</th>
			    <th>Status</th>
			    <th>Krank?</th>
			    <th> </th>
			  </tr>
			  <jsp:getProperty name="myUrlaub" property="alleUrlaubsantraegeUndKrankmeldungenForLeiterFromDB" />
		</table> <br> <br>
		<input type='submit' name='zurueck3' id="btn" class="btn btn-primary btn-block mb-4" value='Zurück' class="inputlogin">
	   </form>
    

</body>
</html>