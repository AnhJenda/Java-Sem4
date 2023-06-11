<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product List</title>
</head>
<body>

<h1>Product List</h1>
<%
out.println("Your IP address is: " + request.getRemoteAddr());
%>

<p>Num = ${number}</p>

<c:forEach var="item" items="${product}" >
    <p>Id: <c:out value="${item.id}"/> </p>
    <p>Name: <c:out value="${item.name}"/> </p>
    <p>Price: <c:out value="${item.price}"/> </p>
</c:forEach>
</body>
</html>
