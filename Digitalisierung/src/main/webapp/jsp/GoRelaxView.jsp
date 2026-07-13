<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>GoRelax</title>
   <link rel="stylesheet" type="text/css" href="../css/GoRelax.css">
<script type="text/javascript" src="../js/Hello.js" ></script>
<script type="text/javascript" src="../js/GoHello.js" ></script>
</head>
<body>

     <header>
        <h1>Willkommen bei GoRelax!</h1>

    
     <nav>
        <ul id="HelloTask">
           	<li id="HelloTask"><a id="HelloTask" href="#" onmouseover="showTooltip(event, 'Die von uns entwickelte App optimiert den gesamten Prozess von der Antragstellung bis zur Genehmigung und erleichtert so die Arbeit für Mitarbeiter und Führungskräfte.')" onmouseout="hideTooltip()">Über uns</a></li>
		    <li id="HelloTask"><a id="HelloTask" href="#" onmouseover="showTooltip(event, 'Im Falle einer technischen Störung kontaktieren Sie den Admin über den Link auf der Starseite.')" onmouseout="hideTooltip()">Kontakt</a></li>
		</ul>
    </nav>
    
    </header>
    <main>
         <nav>
        <ul>
            <li><a href="../jsp/LoginView.jsp">
                <img src="../img/employee icon.png" alt="Employee Portal" class="nav-image">
                Portal Entrance
            </a></li>
            
            <li><a href="../jsp/KontaktAdminView.jsp">
       			 <img src="../img/technician icon.png" alt="Company Logo" class="nav-image">
                Contact Admin
            </a></li>

        </ul>
       
    </nav>
    </main>
    <div id="tooltip" class="tooltip"></div>
    <footer>
    <p>&copy; 2025 GoRelax. All rights reserved. Website Design and developed by MaDyAr.</p>
</footer>

</body>
</html>