<%@page import="de.hwg_lu.bwi520.beans.LoginBean"%>
<%@page import="de.hwg_lu.bwi520.beans.MeldungsBean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Portal</title>
<link rel="stylesheet" type="text/css" href="../css/GoRelax.css">
<script type="text/javascript" src="../js/Hello.js" ></script>
<script type="text/javascript" src="../js/GoHello.js" ></script>
</head>
<body id="others">

<jsp:useBean id="myLog" class="de.hwg_lu.bwi520.beans.LoginBean" scope="session" />
<jsp:useBean id="myMel" class="de.hwg_lu.bwi520.beans.MeldungsBean" scope="session" />

    <header>
        <h1>Login Portal</h1>

     </header>


<!--  <ul class="nav nav-pills nav-justified mb-3" id="ex1" role="tablist">
  
  
   <li class="nav-item" role="presentation">
    <a class="nav-link custom-nav-link" href="#" onclick="showModal()">Registrieren</a>
   </li>
  
</ul> -->
<!-- Pills navs -->

<!-- Pills content -->
<div class="tab-content custom-tab-content">
  <div
    class="tab-pane fade show active"
    id="pills-login"
    role="tabpanel"
    aria-labelledby="tab-login"
  >
    <form action="../jsp/GoRelaxAppl.jsp" method="get" onkeyup="checkFirstname(this.value)">
      	
		<div style="text-align: center;"> <jsp:getProperty property="meldungHtml" name="myMel"/></div>
			
      <!-- Username input -->
      <div data-mdb-input-init class="form-outline mb-4">
        <input type="text" id="loginUserame" class="form-control custom-input" name="usernameLog" value="" placeholder="Benutzername" required/>
       
      </div>

      <!-- Password input -->
      <div data-mdb-input-init class="form-outline mb-4">
	        <input type="password" id="loginPassword" class="form-control custom-input" name="passwordLog" value="" placeholder="Passwort" required />
	        
			<!-- Kontakt zu Admin -->
	     <p class="fehlerfeld">Noch kein Konto? <a href="../jsp/RegistrierungView.jsp">Jetzt registrieren!</a></p>
	
	      <!-- Submit button -->
	      <input type="submit" id="btn" class="btn btn-primary btn-block mb-4" name= "losgehts" 
	      value="Los geht's" onclick="setButtonClicked(this.name)"> 
		</div>
      
    </form>
    
    <form action="../jsp/GoRelaxAppl.jsp" method="get" > <input type="submit" id="btn" class="btn btn-primary btn-block mb-4" name= "backtotask" 
	      value="Zurück"/> </form>
    
  </div>
     		
      
  </div>


   <footer>
       <p>&copy; 2025 GoRelax. All rights reserved. Website Design and developed by MaDyAr.</p>
    </footer>
    
</body>
</html>