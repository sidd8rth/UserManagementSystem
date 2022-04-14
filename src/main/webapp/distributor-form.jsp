<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
        <html>

        <head>
            <title>Distributor Management Application</title>
            <link rel="icon" href="static/images/home-favicon-img.png">
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >
        </head>

        <body>

            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                        <a href="#" class="navbar-brand"> Distributor Management App </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/distributor/list" class="nav-link">Distributors</a></li>
                    </ul>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
				        <ul class="navbar-nav ml-auto">
				          <li class="nav-item">
				            <a class="nav-link"  style="color: #fff;'" href="${pageContext.request.contextPath}/home.jsp">Log Out</a>
				          </li>
				        </ul>
				      </div>
                </nav>
            </header>
            <br>
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                        <c:if test="${company != null}">
                        	<form action="update" method="post">
                        </c:if>
                        <c:if test="${company == null}">
                            <form action="insert" method="post">
                        </c:if>

                        <caption>
                            <h2>
                                <c:if test="${company != null}">
                                    Edit Distributor
                                </c:if>
                                <c:if test="${company == null}">
                                    Add New Distributor
                                </c:if>
                            </h2>
                        </caption>

                        <c:if test="${company != null}">
                            <input type="hidden" name="id" value="<c:out value='${company.id}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>Distributor Name</label> <input type="text" value="<c:out value='${company.dist_name}' />" class="form-control" name="dist_name" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Distributor City</label> <input type="text" value="<c:out value='${company.dist_city}' />" class="form-control" name="dist_city">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Distributor PinCode</label> <input type="text" value="<c:out value='${company.dist_pincode}' />" class="form-control" name="dist_pincode">
                        </fieldset>
						
						<fieldset class="form-group">
                            <label>Distributor UserName</label> <input type="text" value="<c:out value='${company.dist_username}' />" class="form-control" name="dist_username">
                        </fieldset>
                        
                        <fieldset class="form-group">
                            <label>Distributor Password</label> <input type="text" value="<c:out value='${company.dist_password}' />" class="form-control" name="dist_password">
                        </fieldset>
                        <button type="submit" class="btn btn-success">Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </body>

        </html>