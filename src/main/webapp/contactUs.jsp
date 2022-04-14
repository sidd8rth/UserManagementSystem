<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>	
	<title>Contact Page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  	<link rel="icon" href="static/images/contactUs-favicon-img.png">
  	<link href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,700' rel='stylesheet' type='text/css'>
  	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  	<link rel="stylesheet" href="static/css/contactUs.css">
  
</head>
<body style="overflow:hidden;">
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary static-top" style="width: 1536px;height: 76px;">
      <div class="container">
        <a class="navbar-brand" href="#">Contact Us</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav ms-auto">
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/home.jsp">Home</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath}/aboutUs.jsp">About Us</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
        <div class="container-fluid rounded">
            <div class="row px-5">
                <div class="col-sm-6">
                    <div>
                        <h3 class="text-white">Get a quote</h3>
                        <p class="text-secondary">Fill up the form and our Team will get back to you within in 24 hours</p>
                    </div>
                    <div class="links" id="bordering"> <a href="#" class="btn rounded text-white p-3"><i class="fa fa-phone icon text-primary pr-3"></i>+91 7000147524/+91 8982614977</a> <a href="#" class="btn rounded text-white p-3"><i class="fa fa-envelope icon text-primary pr-3"></i>s8173532@gmail.com</a> <a href="#" class="btn rounded text-white p-3"><i class="fa fa-map-marker icon text-primary pr-3"></i> S.G.S.I.T.S, Indore</a> </div>
                </div>
                <div class="col-sm-6 pad">
                    <iframe src="https://docs.google.com/forms/d/e/1FAIpQLSdYQSD69-4fAPlUCDrP6NyLH-D4DgWibkePBqvsjJYRj4gi3Q/viewform?embedded=true" width="500" height="600" frameborder="0" marginheight="0" marginwidth="0">Loading…</iframe>
                </div>
            </div>
        </div>
</body>
</html>