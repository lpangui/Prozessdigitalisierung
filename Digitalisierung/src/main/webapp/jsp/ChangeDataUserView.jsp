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
<body id="others">
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
        <h1>Datenänderung von Benutzern</h1>
     
   </header>
   	
   	<form action="../jsp/AdminAppl.jsp" method="get" >
	   		<!--  <nav>
	   			 <input type="submit" data-mdb-ripple-init id="btn" class="btn btn-primary btn-block mb-3" name="btnProfilSuper" value="Zum Profil" />
	   		</nav> -->
   		</form>
   
  <div class="tab-content custom-tab-content">
	  <div
	    class="tab-pane fade show active"
	    id="pills-login"
	    role="tabpanel"
	    aria-labelledby="tab-login"
	  >
	  
		<!-- <h3 style= "text-align: center;"> <jsp:getProperty property="messageChangeDataUser" name="myMel"/> </h3>s -->
		
		<jsp:getProperty property="meldungHtmlAdmin" name="myMel"/>
		
	   <form action="../jsp/AdminAppl.jsp" method="get">

	      <!-- First Name input -->
	      
	      <div data-mdb-input-init class="form-outline mb-4">
	        <input type="text"  class="form-control custom-input" name="firstNameChange" value='<jsp:getProperty name="myCDU" property="vorname"/>' required />
	        
	      </div>
		
			<!-- Last Name input -->
			
	      <div data-mdb-input-init class="form-outline mb-4">
	        <input type="text" class="form-control custom-input" name="lastNameChange" value='<jsp:getProperty name="myCDU" property="nachname"/>'  required  />
	        
	      </div>
	
	      <!-- Username input -->
	   
	      <div data-mdb-input-init class="form-outline mb-4">
	        <input type="text" class="form-control custom-input" name="usernameChange" value='<jsp:getProperty name="myCDU" property="username"/>' placeholder= " Kann nicht geändert werden" readonly  />
	        
	      </div>
	
	      <!-- Email input -->
	  
	      <div data-mdb-input-init class="form-outline mb-4">
	        <input type="email" class="form-control custom-input" name="emailChange" value='<jsp:getProperty name="myCDU" property="email"/>' placeholder= " Kann nicht geändert werden" required readonly />
	       
	      </div>
	
	      <!-- Password input -->
	      
	      <div data-mdb-input-init class="form-outline mb-4">
	        <input type="text" class="form-control custom-input" name="passwordChange" value='<jsp:getProperty name="myCDU" property="passwort"/>' required />
	        
	      </div>
      
	      <div data-mdb-input-init class="form-outline mb-4">
	      
	      <select name="rolleChange" class="form-control custom-input" required>
	     	<option value="" disabled selected> <jsp:getProperty name="myCDU" property="rolle" /> Wählen Sie die Rolle </option> 
	        <option value="Mitarbeiter">Mitarbeiter</option>
	        <option value="Personalleiter">Personalleiter</option>
	        <option value="Leiter">Leiter</option>
	      	<option value="Admin">Admin</option> 
	        
	       </select> 
	       	        
	      </div>
	      
	    
	      <div data-mdb-input-init class="form-outline mb-4">
	       
	     <select name="abteilungChange" class="form-control custom-input" required>
	     	<option value="" disabled selected> <jsp:getProperty name="myCDU" property="abteilung" /> Wählen Sie die Abteilung </option> 
	        <option value="IT">IT</option>
	        <option value="Finanz">Finanz</option>
	        <option value="Personal">HR</option>
	        <option value="Controlling">Controlling</option>
	        <option value="Marketing">Marketing</option>
	        <option value="Vertrieb">Vertrieb</option>
	       </select>

	      </div>
	
	
	      <!-- Submit button -->
	      <input type="submit" data-mdb-ripple-init id="btn" class="btn btn-primary btn-block mb-3" name="btnAktualisieren" value="Aktualisieren" /> 
	      
    </form>  
  
    <form action="../jsp/AdminAppl.jsp" method="get"> <input type="submit" id="btn" class="btn btn-primary btn-block mb-4" name= "btnbackTo" 
	      value="Abbrechen"/> </form>
    
  	</div>
  </div>
  
    <footer>
        <p>&copy; 2025 GoRelax. All rights reserved. Website Design and developed by MaDyAr.</p>
  </footer>

</body>
</html>