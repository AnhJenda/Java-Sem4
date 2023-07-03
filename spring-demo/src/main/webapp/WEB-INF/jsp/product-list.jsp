<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/7/2023
  Time: 8:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/admin.jsp"%>
<html>
<head>
    <title>Product List</title>
</head>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<body>
<div class="container">
<button><a href="create">Create new book</a></button>
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Price</th>
            <th scope="col">Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${products}">
            <tr>
                <td>${item.name}</td>

                <td>${item.price}</td>
                <td>
                    <ul style="list-style-type: none; display: flex; align-items: center;padding: 0px;">
                        <li><a href="product?id=${item.id}">Details</a></li>
                        <li><a href="product/update?id=${item.id}">Update</a></li>
                        <li><a href="product/delete?id=${item.id}">Delete</a></li>
                    </ul>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
