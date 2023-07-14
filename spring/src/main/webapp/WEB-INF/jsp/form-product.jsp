<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../common/admin.jsp" %>
<html xmlns:th="https://www.thymeleaf.org">
<head>
    <title>Product List</title>
</head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<body>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <h2>Product</h2>
            <hr>
<%--            <form:form action="${product/save}" method="post" modelAttribute="productDto">--%>
<%--                <form:label path="isbn">ISBN: </form:label> <form:input type="text" path="isbn"/>--%>
<%--                <form:label path="name">Book Name: </form:label> <form:input type="text" path="name"/>--%>
<%--                <form:label path="author">Author Name: </form:label> <form:input path="author"/>--%>
<%--                <input type="submit" value="submit"/>--%>
<%--            </form:form>--%>
            <form:form action="${product/save}" method="post" modelAttribute="productDto">
                <form:input type="hidden" path="id" />
                <div class="form-group">
                    <form:label path="name">Product Name:</form:label>
                    <form:input  class="form-control" path="name" />
                </div>
                <div class="form-group">
                    <form:label path="price">Price:</form:label>
                    <form:input class="form-control" path="price" />
                </div>

                <button type="submit" class="btn btn-primary">Save</button>
            </form:form>

            <!-- Back to Product List -->
            <a href="products" class="btn btn-default">Back to Product List</a>
        </div>
    </div>
</div>
</body>
</html>
