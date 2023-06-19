<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/18/2023
  Time: 10:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/admin.jsp"%>
<%@include file="/common/taglib.jsp"%>
<html>
<head>
    <title>Product Details</title>
</head>
<body>
<table class="table">
    <thead class="thead-dark">
    <tr>
        <th scope="col">Name</th>
        <th scope="col">Price</th>
        <th scope="col">Length</th>
        <th scope="col">Width</th>
        <th scope="col">Height</th>
        <th scope="col">Actions</th>
    </tr>
    </thead>
    <tbody>
    <td>${product.name}</td>
    <td>${product.price}</td>
    <td>${product.length}</td>
    <td>${product.width}</td>
    <td>${product.height}</td>
    <td>
        <ul class="flex gap-[20px] items-center">
            <li><button><a href="#">Edit</a></button></li>
            <li><button><a href="#">Delete</a></button></li>
        </ul>
    </td>

</table>
</body>
</html>
