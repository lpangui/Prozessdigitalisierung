
<%@page import="de.hwg_lu.bwi520.beans.LoginBean"%>
<%@page import="de.hwg_lu.bwi520.beans.MeldungsBean"%>
<%@page import="de.hwg_lu.bwi520.beans.AntragsBean"%>
<%@page import="de.hwg_lu.bwi520.beans.KontaktBean"%>
<%@page import="de.hwg_lu.bwi520.beans.PortalMeldungsBean"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User-Portal</title>
<link rel="stylesheet" type="text/css" href="../css/GoRelax.css">
<script type="text/javascript" src="../js/Hello.js" ></script>
<script type="text/javascript" src="../js/GoHello.js" ></script>
</head>
<body id="others">

<jsp:useBean id="myLog" class="de.hwg_lu.bwi520.beans.LoginBean" scope="session" />
<jsp:useBean id="myMel" class="de.hwg_lu.bwi520.beans.MeldungsBean" scope="session" />
<jsp:useBean id="myPM" class="de.hwg_lu.bwi520.beans.PortalMeldungsBean" scope="session" />
<jsp:useBean id="myAnt" class="de.hwg_lu.bwi520.beans.AntragsBean" scope="session" />
<jsp:useBean id="myM" class="de.hwg_lu.bwi520.beans.KontaktBean" scope="session" />



    <header>
       <h1 >Kontaktformular</h1>

     </header>
     
    <div class="tab-content custom-tab-content">
    <div class="tab-pane fade show active" id="pills-login" role="tabpanel" aria-labelledby="tab-login">
        
        <jsp:getProperty property="meldungHtmlFuerFormulareKontakt" name="myPM"/>
        
        <form action="AdminAppl.jsp" method="get">
        	<label for="kontakt">Name:</label>
            <input type="text" name="vorUNDnachname" value="" class="form-control custom-input" placeholder="Vor- und Nachname bitte" required>
            
            <label for="kontakt">E-Mail:</label>
            <input type="email" name="emailKontaktAdmin" value="" class="form-control custom-input" placeholder="Adresse E-mail eintippen" required><br>
            
			<label for="kontakt">Message:</label>
		   <textarea id="contactMessage" class="form-control custom-input" name="message" placeholder="Seien Sie bitte kurz" rows="10" required></textarea>
		        
	      <input type="submit" id="btn" class="btn btn-primary btn-block mb-4" name= "formularAbsenden" 
	      value="Absenden"/>
        </form>
        
        <form action="../jsp/AdminAppl.jsp" method="get" > <input type="submit" id="btn" class="btn btn-primary btn-block mb-4" name= "formularAbbrechenZurueck" 
	      value="Zurück"/> </form>
    </div>
</div> 
     
     
   <footer>
        <p>&copy; 2025 GoRelax. All rights reserved. Website Design and developed by MaDyAr.</p>
  </footer>    
</body>
</html>