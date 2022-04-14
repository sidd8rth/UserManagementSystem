<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
        <html>

        <head>
            <title>Distributor Management</title>
            <link rel="icon" href="static/images/home-favicon-img.png">
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >
        </head>

        <body style="overflow-x: hidden;">

            <header>
                <nav class="navbar navbar-expand-md navbar-dark bg-primary" >
                    <div>
                        <a href="#" class="navbar-brand"> Distributor Management App </a>
                    </div>
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

            <div class="row">
                <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

                <div class="container">
                    <h3 class="text-center">List of Distributors</h3>
                    <hr>
                    <div class="container text-left">

                        <a href="<%=request.getContextPath()%>/distributor/new" class="btn btn-success">Add New Distributor</a>
                    </div>
                    <br>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Distributor Name</th>
                                <th>Distributor City</th>
                                <th>Distributor PinCode</th>
                                <th>Distributor UserName</th>
                                <th>Distributor Password</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!--   for (Todo todo: todos) {  -->
                            <c:forEach var="company" items="${listArticle}">

                                <tr>
                                    <td>
                                        <c:out value="${company.id}" />
                                    </td>
                                    <td>
                                        <c:out value="${company.dist_name}" />
                                    </td>
                                    <td>
                                        <c:out value="${company.dist_city}" />
                                    </td>
                                    <td>
                                        <c:out value="${company.dist_pincode}" />
                                    </td>
                                    <td>
                                        <c:out value="${company.dist_username}" />
                                    </td>
                                    <td>
                                        <c:out value="${company.dist_password}" />
                                    </td>
                                    <td>
                                    <a href="${pageContext.request.contextPath}/distributor/edit?id=<c:out value='${company.id}' />">Edit</a> 
                                    &nbsp;&nbsp;&nbsp;&nbsp; 
                                    <a href="${pageContext.request.contextPath}/distributor/delete?id=<c:out value='${company.id}' /> ">Delete</a>
                                     &nbsp;&nbsp;&nbsp;&nbsp;
                                    <a href="${pageContext.request.contextPath}/distributor/show?distName=<c:out value='${company.dist_username}'/> ">Show</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>

                    </table>
                </div>
            </div>
        </body>

        </html>