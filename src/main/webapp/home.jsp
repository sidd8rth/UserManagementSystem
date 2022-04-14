<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management System</title>
<link rel="icon" href="static/images/home-favicon-img.png">
<link rel="stylesheet" href="static/css/styles.css" >
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>

<body style="background-color: #eee;">
	<!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-primary static-top">
    <div class="container">
      <a class="navbar-brand" href="#">
        <img src="static/images/home-logo.png" alt="..." height="50">
      </a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ms-auto">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="#">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/aboutUs.jsp">About Us</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/contactUs.jsp">Contact Us</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>

    <div class="container" >
      <div class="row" style="margin-top:20vh;">
        <div class="col-6" style="position: relative; left:40vh; width:25%;">
          <div class="card card-body" >
            <h3>For Companies</h3><br>
          <p>Check and update your distributors on our website by simply logging in with your credentials. </p><br>
          <button type="button" onclick="location.href = '${pageContext.request.contextPath}/login-company.jsp';" class="btn btn-outline-primary btn-block">Sign In</button>
          </div>
        </div>
        <div class="col-6" style="position: relative; left:50vh; width:25%;">
        <div class="card card-body" >
          <h3>For Distributors</h3><br>
        <p>Check and update your real-time orders on our website by simply logging in with your credentials.</p><br>
        <button type="button" onclick="location.href = '${pageContext.request.contextPath}/login-distributor.jsp';" class="btn btn-primary btn-block">Sign In</button>
        </div>
        </div>
      </div>
    </div>
</body>
</html>