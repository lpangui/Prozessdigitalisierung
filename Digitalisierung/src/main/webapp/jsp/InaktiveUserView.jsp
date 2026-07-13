<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="de.hwg_lu.bwi520.beans.LoginBean"%>
<%@page import="de.hwg_lu.bwi520.beans.MeldungsBean"%>
<%@page import="de.hwg_lu.bwi520.beans.AntragsBean"%>
<%@page import="de.hwg_lu.bwi520.beans.GoRelaxBean"%>
<%@page import="de.hwg_lu.bwi520.beans.AdminBean"%>
<%@page import="de.hwg_lu.bwi520.beans.ChangeDataUserBean"%>

<%@page import="de.hwg_lu.bwi520.beans.PortalMeldungsBean"%>

<%@page import="java.sql.SQLException"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin-Portal</title>
<link rel="stylesheet" type="text/css" href="../css/GoRelax.css">
<script type="text/javascript" src="../js/Hello.js" ></script>
<script type="text/javascript" src="../js/GoHello.js" ></script>
</head>
<body id="portalnone">
<jsp:useBean id="myLog" class="de.hwg_lu.bwi520.beans.LoginBean" scope="session" />
<jsp:useBean id="myMel" class="de.hwg_lu.bwi520.beans.MeldungsBean" scope="session" />
<jsp:useBean id="myAnt" class="de.hwg_lu.bwi520.beans.AntragsBean" scope="session" />
<jsp:useBean id="myAdmin" class="de.hwg_lu.bwi520.beans.AdminBean" scope="session" />
<jsp:useBean id="myCDU" class="de.hwg_lu.bwi520.beans.ChangeDataUserBean" scope="session" />
<jsp:useBean id="myPM" class="de.hwg_lu.bwi520.beans.PortalMeldungsBean" scope="session" />
<jsp:useBean id="myGo" class="de.hwg_lu.bwi520.beans.GoRelaxBean" scope="session" />

<%
	if (!myLog.isLoggedIn()) response.sendRedirect("./GoRelaxView.jsp");
%>

<header id="main-content">
        <h1>Verwaltung von inaktiven Usern</h1>

  </header>
  	<form action="../jsp/AdminAppl.jsp" method="get" >
	   		<nav>
	   			 <input type="submit" data-mdb-ripple-init id="btn" class="btn btn-primary btn-block mb-3" name="btnProfilSuper" value="Zum Profil" />
	   		</nav>
   		</form>
    
	<div id="benutzerModal" class="benutzer-modal">
		    <div class="benutzer-modal-inhalt">
		      	
		      	<h3 style="text-align: center;">Hier werden alle inaktiven Benutzer angezeigt</h3>
		      	
			      	<form action="../jsp/AdminAppl.jsp" method="get">
				    	<table class="styled-table">
							  <tr>
							  	<th>Personalnummer</th>
							    <th>Vorname</th>
							    <th>Nachname</th>
							    <th>Benutzername</th>
							    <th>Email</th>
							    <th>Rolle</th>
							    <th></th>
							  </tr>
							<jsp:getProperty name="myAdmin" property="alleBenutzerInaktiv" /> 
						</table> <br> <br>
						
						<input type='submit' name='backToVerwaltung' id="btn" class="btn btn-primary btn-block mb-4" value='Zurück' class="inputlogin">
		   			</form>
		     
		    </div>
  	</div>
	   

</body>
</html>