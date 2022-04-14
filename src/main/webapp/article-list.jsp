<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
        <html>

        <head>
            <title>Inventory Management</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >
        </head>

        <body style="overflow-x: hidden;">

            <header>
                <nav class="navbar navbar-expand-md navbar-dark bg-primary" >
                    <div>
                        <a href="" class="navbar-brand"> Inventory Management App </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/article/list" class="nav-link">Articles</a></li>
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

            <div class="row">
                <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

                <div class="container">
                    <h3 class="text-center">List of Articles</h3>
                    <hr>
                    <div class="container text-left">

                        <a href="<%=request.getContextPath()%>/article/new" class="btn btn-success">Add New Article</a>
                    </div>
                    <br>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Article Name</th>
                                <th>Article Quantity</th>
                                <th>Article Price</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!--   for (Todo todo: todos) {  -->
                            <c:forEach var="article" items="${listArticle}">

                                <tr>
                                    <td>
                                        <c:out value="${article.id}" />
                                    </td>
                                    <td>
                                        <c:out value="${article.article_name}" />
                                    </td>
                                    <td>
                                        <c:out value="${article.article_quantity}" />
                                    </td>
                                    <td>
                                        <c:out value="${article.article_price}" />
                                    </td>
                                    <td><a href="${pageContext.request.contextPath}/article/edit?id=<c:out value='${article.id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="${pageContext.request.contextPath}/article/delete?id=<c:out value='${article.id}' />">Delete</a></td>
                                </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>

                    </table>
                </div>
            </div>
        </body>

        </html>