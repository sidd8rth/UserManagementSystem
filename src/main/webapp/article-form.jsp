<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
        <html>

        <head>
            <title>Inventory Management Application</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >
        </head>

        <body>

            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                        <a href="https://www.javaguides.net" class="navbar-brand"> Inventory Management App </a>
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
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                        <c:if test="${article != null}">
                            <form action="update" method="post">
                        </c:if>
                        <c:if test="${article == null}">
                            <form action="insert" method="post">
                        </c:if>

                        <caption>
                            <h2>
                                <c:if test="${article != null}">
                                    Edit Article
                                </c:if>
                                <c:if test="${article == null}">
                                    Add New Article
                                </c:if>
                            </h2>
                        </caption>

                        <c:if test="${article != null}">
                            <input type="hidden" name="id" value="<c:out value='${article.id}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>Article Name</label> <input type="text" value="<c:out value='${article.article_name}' />" class="form-control" name="article_name" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Article Quantity</label> <input type="text" value="<c:out value='${article.article_quantity}' />" class="form-control" name="article_quantity">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Article Price</label> <input type="text" value="<c:out value='${article.article_price}' />" class="form-control" name="article_price">
                        </fieldset>

                        <button type="submit" class="btn btn-success">Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </body>

        </html>