<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="de.hwg_lu.bwi520.beans.LoginBean"%>
<%@page import="de.hwg_lu.bwi520.beans.MeldungsBean"%>
<%@page import="de.hwg_lu.bwi520.beans.AntragsBean"%>
<%@page import="de.hwg_lu.bwi520.beans.GoRelaxBean"%>
<%@page import="de.hwg_lu.bwi520.beans.PortalMeldungsBean"%>

<%@page import="java.sql.SQLException"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mitarbeiter-Portal</title>
<link rel="stylesheet" type="text/css" href="../css/GoRelax.css">
<script type="text/javascript" src="../js/Hello.js" ></script>
<script type="text/javascript" src="../js/GoHello.js" ></script>
</head>
<body>
<jsp:useBean id="myLog" class="de.hwg_lu.bwi520.beans.LoginBean" scope="session" />
<jsp:useBean id="myMel" class="de.hwg_lu.bwi520.beans.MeldungsBean" scope="session" />
<jsp:useBean id="myAnt" class="de.hwg_lu.bwi520.beans.AntragsBean" scope="session" />
<jsp:useBean id="myPM" class="de.hwg_lu.bwi520.beans.PortalMeldungsBean" scope="session" />
<jsp:useBean id="myGo" class="de.hwg_lu.bwi520.beans.GoRelaxBean" scope="session" />

<%
	if (!myLog.isLoggedIn()) response.sendRedirect("./GoRelaxView.jsp");
%>

 <header>
        <h1>Mitarbeiter Portal</h1>
   
    
      <nav>
        <ul id="HelloTask">
           
             <li id="HelloTask"><a id="HelloTask" href="#" onmouseover="showTooltip(event, 'Die von uns entwickelte App optimiert den gesamten Prozess von der Antragstellung bis zur Genehmigung und erleichtert so die Arbeit für Mitarbeiter und Führungskräfte.')" onmouseout="hideTooltip()">Über uns</a></li>
		    <li id="HelloTask"><a id="HelloTask" href="#" onmouseover="showTooltip(event, 'Im Falle einer technischen Störung kontaktieren Sie den Admin über den Link auf der Starseite.')" onmouseout="hideTooltip()">Kontakt</a></li>
				 <li class="profile-menu">
                <form action="../jsp/GoRelaxAppl.jsp" method="get" > <input type="submit" id="btn" class="btn btn-primary btn-block mb-4" name= "btnAbmelden" 
	      value="Abmelden"/> </form>
                
            </li>
        </ul>
        
    </nav>
     </header>
    
    <main>
    	<nav>
        	<ul>
            	<li><a href="AntragView.jsp">Urlaubsantrag einreichen</a></li>
            	<li><a href="KrankmeldungView.jsp">Krankmeldung erstatten</a></li>
            	 <li><a href="AntragsStatusView.jsp">Aktuellen Status abrufen</a></li>
            	
        	</ul>
        	<jsp:getProperty property="infoMitarbeiterhtml" name="myLog"/>
   	 	</nav>
    </main>
    
    <div id="tooltip" class="tooltip"></div>
    <footer>
        <p>&copy; 2025 GoRelax. All rights reserved. Website Design and developed by MaDyAr.</p>
    </footer>

</body>
</html>