<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/18/2023
  Time: 10:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/admin.jsp"%>
<%@include file="../common/taglib.jsp"%>
<html>
<head>
    <title>Product List</title>
</head>
<body>
    <div class="container-fluid" style="margin-top: 40px;">
        <div>
            <button class="btn btn-primary">
                <a style="text-decoration: none; color: white" href="${pageContext.request.contextPath}/products/create">Create new product</a>
            </button>
        </div>
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
            <c:forEach var="item" items="${products}">
                <tr>
                    <td>${item.name}</td>
                    <td><fmt:formatNumber value="${item.price}" type="number" pattern="#,##0.00" /></td>
                    <td>${item.length}</td>
                    <td>${item.width}</td>
                    <td>${item.height}</td>
                    <td>
                        <ul style="list-style-type: none; display: flex; align-items: center; gap: 2px;">
                            <li><button class="btn btn-secondary"><a class="text-white" style="text-decoration: none;" href="${pageContext.request.contextPath}/products?id=${item.id}">Details</a></button></li>
                            <li><button class="btn btn-primary"><a class="text-white" style="text-decoration: none;" href="#">Edit</a></button></li>
                            <li><button class="btn btn-danger"><a class="text-white" style="text-decoration: none;" href="#">Delete</a></button></li>
                        </ul>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
