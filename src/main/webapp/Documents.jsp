<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
		
	    <link href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700,800&display=swap" rel="stylesheet">
	    <link href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i&display=swap" rel="stylesheet">
	    <link href="https://fonts.googleapis.com/css?family=Amatic+SC:400,700&display=swap" rel="stylesheet">
	
	    <link rel="stylesheet" href="css/open-iconic-bootstrap.min.css">
	    <link rel="stylesheet" href="css/animate.css">
	    
	    <link rel="stylesheet" href="css/owl.carousel.min.css">
	    <link rel="stylesheet" href="css/owl.theme.default.min.css">
	    <link rel="stylesheet" href="css/magnific-popup.css">
	
	    <link rel="stylesheet" href="css/aos.css">
	
	    <link rel="stylesheet" href="css/ionicons.min.css">
	
	    <link rel="stylesheet" href="css/bootstrap-datepicker.css">
	    <link rel="stylesheet" href="css/jquery.timepicker.css">
	
	    
	    <link rel="stylesheet" href="css/flaticon.css">
	    <link rel="stylesheet" href="css/icomoon.css">
	    <link rel="stylesheet" href="css/style.css">
	    
	  	<script src="https://apis.google.com/js/platform.js" async defer></script>
    	<meta name="google-signin-client_id" content="647658882271-f7rg54qt1mqv546ef9aft9mejh7ri150.apps.googleusercontent.com">
	
</head>
<body>
	<script> 
		    window.onload = function exampleFunction() { 
		    	var x = localStorage.getItem("googleid");
		   		if (x == null){
		   			window.location.href = "index.jsp";
		   	    }
		    } 
	</script>
	<div class="py-1 bg-primary">
    	<div class="container">
    		<div class="row no-gutters d-flex align-items-start align-items-center px-md-0">
	    		<div class="col-lg-12 d-block">
		    		<div class="row d-flex">
		    			<div class="col-md pr-4 d-flex topper align-items-center">
					    	<div class="icon mr-2 d-flex justify-content-center align-items-center"><span class="icon-phone2"></span></div>
						    <span class="text">+ 1235 2355 98</span>
					    </div>
					    <div class="col-md-5 pr-4 d-flex topper align-items-center text-lg-right">
						    <span class="text">Welcome To Portal</span>
					    </div>
				    </div>
			    </div>
		    </div>
		  </div>
    </div>
    <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
	    <div class="container">
	      <a class="navbar-brand" href="index.html">Faculty  Portal</a>
	      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="oi oi-menu"></span> Menu
	      </button>

	      <div class="collapse navbar-collapse" id="ftco-nav">
	        <ul class="navbar-nav ml-auto">
	          <li class="nav-item active"><a href="Home?email=${email}" class="nav-link">Home</a></li>
	           <li class="nav-item dropdown">
	              <a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Leave </a>
	              <div class="dropdown-menu" aria-labelledby="dropdown04">
	              	<a class="dropdown-item" href="GoToLeavePage?type=Casual&email=${email}">Casual Leave</a>
	              	<a class="dropdown-item" href="GoToLeavePage?type=Medical&email=${email}">Medical Leave</a>
	                <a class="dropdown-item" href="GoToLeavePage?type=Sick&email=${email}">Sick Leave</a>
	                <a class="dropdown-item" href="GoToLeavePage?type=Earned&email=${email}">Earned Leave</a>
	               <a class="dropdown-item" href="GoToLeavePage?type=Outdoor&email=${email}">Outdoor Duty</a>
					<a class="dropdown-item" href="Sanction?email=${email}">Requests</a>
	              </div>
              </li>
	          <li class="nav-item"><a href="Blog?email=${email}"class="nav-link">Blog</a></li>
	          <li class="nav-item"><a href="Chat?email=${email}" class="nav-link">Chat</a></li>
	         <li class="nav-item cta cta-colored"><a  href="Notification?email=${email}" class="nav-link"><span class="icon-bell"></span>[0]</a></li> <li class="nav-item"><a onclick="return myFunction();" class="nav-link">Sign Out</a></li>
	        	<div hidden  class="g-signin2" data-onsuccess="onSignIn" id="myP" method="POST"></div>
				<script type="text/javascript">
			  	    function myFunction() {
			  	    	var auth2 = gapi.auth2.getAuthInstance();
					    auth2.signOut().then(function () {
					      console.log('User signed out.');
					      localStorage.clear();
					      window.location.href = "index.jsp";
					    });
			      }
			   </script>
	        </ul>
	      </div>
	    </div>
	  </nav>
	<form  action="uploadsinglefile" enctype="multipart/form-data" method="post">
		<div class="container contact">
			<div class="row">
				<div class="col-md-9">
						<div class="form-group">
						  <label class="control-label col-sm-2" for="comment">Doucument :</label>
						  <div class="col-sm-10">
							<input   type="file" name="file"  required>
						  </div>
						</div>
						
						<div class="form-group">        
						  <div class="col-sm-offset-2 col-sm-10">
						  
							<button type="submit" class="btn btn-default">Submit</button>
						  </div>
						</div>
					</div>
				</div>
			</div>
	 	 </div>
	</form>
	<table>
	  <tr>
	    <th>File Name</th>
	    <th>Download Link</th>
	  </tr>
	  <c:forEach items="${Entity_UploadFileResponse}" var="data">
		  <tr>
		    <td>${data.fileName}</td>
		    <td><a href="download-file?fileName=${data.fileName}&email=${email}">Download Link</a></td>
		  </tr>			
	  </c:forEach>

	</table>
  <script src="js/jquery.min.js"></script>
  <script src="js/jquery-migrate-3.0.1.min.js"></script>
  <script src="js/popper.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/jquery.easing.1.3.js"></script>
  <script src="js/jquery.waypoints.min.js"></script>
  <script src="js/jquery.stellar.min.js"></script>
  <script src="js/owl.carousel.min.js"></script>
  <script src="js/jquery.magnific-popup.min.js"></script>
  <script src="js/aos.js"></script>
  <script src="js/jquery.animateNumber.min.js"></script>
  <script src="js/bootstrap-datepicker.js"></script>
  <script src="js/scrollax.min.js"></script>
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
  <script src="js/google-map.js"></script>
  <script src="js/main.js"></script>	
	
</body>
</html>