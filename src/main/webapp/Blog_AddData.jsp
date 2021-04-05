<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	<style type="text/css">
		body{
		background-color: #25274d;
	}
	.container{
		
	}
	.contact{
		padding: 4%;
		height: 400px;
	}
	.col-md-3{
		background: #ff9b00;
		padding: 4%;
		border-top-left-radius: 0.5rem;
		border-bottom-left-radius: 0.5rem;
	}
	.contact-info{
		margin-top:10%;
	}
	.contact-info img{
		margin-bottom: 15%;
	}
	.contact-info h2{
		margin-bottom: 10%;
	}
	.col-md-9{
		background: #fff;
		padding: 3%;
		border-top-right-radius: 0.5rem;
		border-bottom-right-radius: 0.5rem;
	}
	.contact-form label{
		font-weight:600;
	}
	.contact-form button{
		background: #25274d;
		color: #fff;
		font-weight: 600;
		width: 25%;
	}
	.contact-form button:focus{
		box-shadow:none;
	}
	</style>
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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
	      <a class="navbar-brand" href="index.html">Faculty Portal</a>
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
              <li class="nav-item"><a href="Documents?email=${email}"class="nav-link">Documents</a></li>
	          <li class="nav-item"><a href="Blog?email=${email}"class="nav-link">Blog</a></li>
	          <li class="nav-item"><a href="Chat?email=${email}" class="nav-link">Chat</a></li>
	          <li class="nav-item cta cta-colored"><a href="Notification?email=${email}" class="nav-link"><span class="icon-bell"></span>[0]</a></li><li class="nav-item"><a onclick="return myFunction();" class="nav-link">Sign Out</a></li>
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
    <!-- END nav -->
    <form  action="SaveBlogData" enctype="multipart/form-data" method="post">
		<div class="container contact">
			<div class="row">
				<div class="col-md-3">
					<div class="contact-info">
						<h2>Contact Us</h2>
						<h4>We would love to hear from you !</h4>
					</div>
				</div>
				<div class="col-md-9">
					<div class="contact-form">
						<div class="form-group">
						  <label class="control-label col-sm-2" for="lname">Subject :</label>
						  <div class="col-sm-10">          
							<input type="text" class="form-control" id="Subject" placeholder="" name="Subject" required>
						  </div>
						</div>
					
						<div class="form-group">
						  <label class="control-label col-sm-2" for="comment" >Description:</label>
						  <div class="col-sm-10">
							<textarea class="form-control" rows="5" id="Description" name="Description" required></textarea>
						  </div>
						</div>
						<div class="form-group">
						  <label class="control-label col-sm-2" for="comment">Image :</label>
						  <div class="col-sm-10">
							<input type="file" name="imageFile" accept="image/x-png,image/gif,image/jpeg" />
						  </div>
						</div>
						<div class="form-group">
						  <label class="control-label col-sm-2" for="comment">Doucument :</label>
						 
						  <div class="col-sm-10">
							<input   type="file" name="documentFile"  >
							<input hidden  type="text" class="form-control" id="email" value="${email}" name="email" readonly>
							<input hidden  type="text" class="form-control" id="branch" value="${branch}" name="branch" readonly>
							<input hidden  type="text" class="form-control" id="designation" value="${designation}" name="designation" readonly>
							<input hidden  type="text" class="form-control" id="name" value="${name}" name="name" readonly>
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