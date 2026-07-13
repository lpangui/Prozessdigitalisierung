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
<jsp:useBean id="myAdmin" class="de.hwg_lu.bwi520.beans.AdminBean" scope="session" />
<jsp:useBean id="myKrank" class="de.hwg_lu.bwi520.beans.KrankmeldungBean" scope="session" />
<jsp:useBean id="myUrlaub" class="de.hwg_lu.bwi520.beans.UrlaubBean" scope="session" />
<jsp:useBean id="myCDU" class="de.hwg_lu.bwi520.beans.ChangeDataUserBean" scope="session" />
<jsp:useBean id="myPM" class="de.hwg_lu.bwi520.beans.PortalMeldungsBean" scope="session" />
<jsp:useBean id="myGo" class="de.hwg_lu.bwi520.beans.GoRelaxBean" scope="session" />
<%
	if (!myLog.isLoggedIn()) response.sendRedirect("./GoRelaxView.jsp");
%>

    <header>
       <h1 >Krankmeldung erstatten</h1>

     </header>


<div class="tab-content custom-tab-content">
    <div class="tab-pane fade show active" id="pills-login" role="tabpanel" aria-labelledby="tab-login">
    
    		<jsp:getProperty property="meldungHtmlFuerFormulareKrankmeldung" name="myPM"/>
    
    
        <form action="GoRelaxAppl.jsp" method="get">
        	<label for="grund">Personalnummer:</label>
            <input type="text" name="Krankpersonnalnummer" value="<%= myLog.getPersonalnummer()%>" 
            class="form-control custom-input" readonly>

            <label for="startdatum">Startdatum:</label>
            <input type="date" id="startdatum" name="Krankstartdatum" class="form-control custom-input" required><br>
            
            <label for="enddatum">Enddatum:</label>
            <input type="date" id="enddatum" name="Krankenddatum" class="form-control custom-input" ><br>
			
			<label for="attest">Attest vorhanden?</label>
				<select id="attest" name="attest" class="form-control custom-input" required>
				    <option value="Ja">Ja</option>
				    <option value="Nein">Nein</option>
				</select>
				
			<label for="grund">Bemerkung:</label>
		   	<textarea id="Bemerkung" class="form-control custom-input" name="bemerkung" placeholder="z.B.: warum kein Attest vorliegt" rows="7"></textarea>
            
	      <input type="submit" id="btn" class="btn btn-primary btn-block mb-4" name= "krankAbsenden" 
	      value="Absenden"/>
        </form>
        
        <form action="../jsp/GoRelaxAppl.jsp" method="get" > <input type="submit" id="btn" class="btn btn-primary btn-block mb-4" name= "KrankAbbrechen" 
	      value="Zurück"/> </form>
    </div>
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