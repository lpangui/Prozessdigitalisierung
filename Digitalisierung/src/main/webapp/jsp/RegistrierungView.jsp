<%@page import="de.hwg_lu.bwi520.beans.LoginBean"%>
<%@page import="de.hwg_lu.bwi520.beans.MeldungsBean"%>
<%@page import="de.hwg_lu.bwi520.beans.PortalMeldungsBean"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Account-Manager</title>

<link rel="stylesheet" type="text/css" href="../css/GoRelax.css">
<script type="text/javascript" src="../js/Hello.js" ></script>
<script type="text/javascript" src="../js/GoHello.js" ></script>
</head>
<body id="others">

<jsp:useBean id="myPM" class="de.hwg_lu.bwi520.beans.PortalMeldungsBean" scope="session" />
<jsp:useBean id="myMel" class="de.hwg_lu.bwi520.beans.MeldungsBean" scope="session" />
<jsp:useBean id="myLog" class="de.hwg_lu.bwi520.beans.LoginBean" scope="session" />


<header id="main-content">
        <h1>Registrierungsseite</h1>

</header>
   
  <div class="tab-content custom-tab-content">
	  <div
	    class="tab-pane fade show active"
	    id="pills-login"
	    role="tabpanel"
	    aria-labelledby="tab-login"
	  >
	  
		<jsp:getProperty property="meldungHtml" name="myPM"/>

	   <form action="../jsp/GoRelaxAppl.jsp" method="get" onsubmit="return checkInput(this)">
	      
	
	      <!-- First Name input -->
	      <div id="errorFirstname" class="fehlerfeld"></div>
	      <div data-mdb-input-init class="form-outline mb-4">
	        <input type="text" id="registerFirstName" class="form-control custom-input" name="firstNameReg" value="" 
	        placeholder="First name" required onkeyup="checkFirstname(this.value)" />
	        
	      </div>
	
			
			<!-- Last Name input -->
			<div id="errorLastname" class="fehlerfeld"></div>
	      <div data-mdb-input-init class="form-outline mb-4">
	        <input type="text" id="registerLastName" class="form-control custom-input" name="lastNameReg" value="" 
	        placeholder="Last name" required  onkeyup="checkLastname(this.value)"/>
	        
	      </div>
	      
	      <!-- Username input -->
	      <div id="errorUsername" class="fehlerfeld"></div>
	      <div data-mdb-input-init class="form-outline mb-4">
	        <input type="text" id="registerUsername" class="form-control custom-input" name="usernameReg" value="" 
	        placeholder="Username z.B. vorname.nachname" required onkeyup="checkUsername(this.value)" />
	        
	      </div>
	
	      <!-- Email input -->
	      <div id="errorEmail" class="fehlerfeld"></div>
	      <div data-mdb-input-init class="form-outline mb-4">
	        <input type="email" id="registerEmail" class="form-control custom-input" placeholder="Email" name="emailReg" 
	        value="" required onkeyup="checkEmail(this.value)"/>
	       
	      </div>
	
	      <!-- Password input -->
	      <div id="errorPassword" class="fehlerfeld" ></div>
	      <div data-mdb-input-init class="form-outline mb-4">
	        <input type="password" id="registerPassword" class="form-control custom-input" name="passwordReg" value="" 
	        placeholder="Password" required onkeyup="checkPassword(this.value)" />
	        
	      </div>
	
	      <div data-mdb-input-init class="form-outline mb-4">
	      
	      <select name="rolle" class="form-control custom-input" required>
	     	<option value="" disabled selected>Wählen Sie Ihre Rolle</option> 
	        <option value="Mitarbeiter">Mitarbeiter</option>
	        <option value="Personalleiter">Personalleiter</option>
	        <option value="Leiter">Leiter</option>
	        
	       </select> 
	      </div>
	      
	     
	      <div data-mdb-input-init class="form-outline mb-4">
	      
	      <select name="abteilung" class="form-control custom-input" required>
	     	<option value="" disabled selected>Wählen Sie Ihre Abteilung</option> 
	        <option value="IT">IT</option>
	        <option value="Finanz">Finanz</option>
	        <option value="Personal">HR</option>
	        <option value="Controlling">Controlling</option>
	        <option value="Marketing">Marketing</option>
	        <option value="Vertrieb">Vertrieb</option>
	       </select>
	        
	      </div>
	
	
	      <!-- Submit button -->
	      <input type="submit" data-mdb-ripple-init id="btn" class="btn btn-primary btn-block mb-3" name="btnReg" value="User anlegen" 
	      									onclick="setButtonClicked(this.name)"/> 
	      
    </form>
  
    <form action="../jsp/GoRelaxAppl.jsp" method="get"> <input type="submit" id="btn" class="btn btn-primary btn-block mb-4" name= "btnZurueck" 
	      value="Zurück"/> </form>
    
  	</div>
  </div>

<footer>
        <p>&copy; 2025 GoRelax. All rights reserved. Website Design and developed by MaDyAr.</p>
</footer>
</body>
</html>