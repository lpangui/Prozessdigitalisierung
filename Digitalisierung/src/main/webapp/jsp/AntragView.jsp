<%@page import="de.hwg_lu.bwi520.beans.LoginBean"%>
<%@page import="de.hwg_lu.bwi520.beans.MeldungsBean"%>
<%@page import="de.hwg_lu.bwi520.beans.AntragsBean"%>
<%@page import="de.hwg_lu.bwi520.beans.PortalMeldungsBean"%>



<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mitarbeiterportal</title>
<link rel="stylesheet" type="text/css" href="../css/GoRelax.css">
<script type="text/javascript" src="../js/Hello.js" ></script>
<script type="text/javascript" src="../js/GoHello.js" ></script>
</head>
<body id="others">

<jsp:useBean id="myLog" class="de.hwg_lu.bwi520.beans.LoginBean" scope="session" />
<jsp:useBean id="myMel" class="de.hwg_lu.bwi520.beans.MeldungsBean" scope="session" />
<jsp:useBean id="myAnt" class="de.hwg_lu.bwi520.beans.AntragsBean" scope="session" />
<jsp:useBean id="myPM" class="de.hwg_lu.bwi520.beans.PortalMeldungsBean" scope="session" />


<%
	if (!myLog.isLoggedIn()) response.sendRedirect("./GoRelaxView.jsp");
%> 

 <% 
    if (request.getParameter("antragAbbrechen") != null) {
        response.sendRedirect("GoRelaxView.jsp"); // FEHLER! Weil HTML bereits begonnen hat.
    }
%>

    <header>
       <h1 >Urlaubsantragsformular</h1>

     </header>

<!-- Meldung wird hier angezeigt -->
<div id="meldung" style="display:none; padding:10px; margin-top:10px; border-radius:5px;"></div>

<div class="tab-content custom-tab-content">
    <div class="tab-pane fade show active" id="pills-login" role="tabpanel" aria-labelledby="tab-login">
    
    <jsp:getProperty property="meldungHtmlFuerFormulare" name="myPM"/>
    
        <form action="GoRelaxAppl.jsp" method="get">
        	<label for="grund">Personalnummer:</label>
            <input type="text" name="personnalnummer" value="<%= myLog.getPersonalnummer()%>" 
            class="form-control custom-input" readonly>
            
            <label for="grund">Urlaubsgrund (optional):</label>
            <input type="text" id="grund" name="grund" class="form-control custom-input" placeholder="z.B. Erholung" ><br>
            
            <label for="startdatum">Startdatum:</label>
            <input type="date" id="startdatum" name="startdatum" class="form-control custom-input" required><br>
            
            <label for="enddatum">Enddatum:</label>
            <input type="date" id="enddatum" name="enddatum" class="form-control custom-input" required><br>

	      <input type="submit" id="btn" class="btn btn-primary btn-block mb-4" name= "antragAbsenden" 
	      value="Absenden"/>
        </form>
        
    </div>
    
            <form action="../jsp/UrlaubAppl.jsp" method="get" > <input type="submit" id="btn" class="btn btn-primary btn-block mb-4" name= "antragAbbrechen" 
	      value="Zurück"/> </form>
	     
</div>

  <footer>
        <p>&copy; 2025 GoRelax. All rights reserved. Website Design and developed by MaDyAr.</p>
  </footer>
 
<script>
       document.addEventListener("DOMContentLoaded", function () {
        let today = new Date().toISOString().split("T")[0]; // Heutiges Datum im YYYY-MM-DD Format
        document.getElementById("startdatum").setAttribute("min", today);
        document.getElementById("enddatum").setAttribute("min", today);
    });

    // Sicherstellen, dass das Enddatum nicht vor dem Startdatum liegt
    document.getElementById("startdatum").addEventListener("change", function () {
        let startDate = this.value;
        document.getElementById("enddatum").setAttribute("min", startDate);
    }); 
</script>

 
</body>
</html>