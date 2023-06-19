<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/19/2023
  Time: 7:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/admin.jsp"%>
<%@include file="../common/taglib.jsp"%>
<html>
<head>
    <title>Employee List</title>
</head>
<body>
<div class="container-fluid" style="margin-top: 40px;">
    <div>
        <button class="btn btn-primary">
            <a style="text-decoration: none; color: white" href="${pageContext.request.contextPath}/employees/create">Create new employee</a>
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
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${employees}">
            <tr>
                <td>${item.fullname}</td>
                <td>${item.birthday}</td>
                <td>${item.address}</td>
                <td>${item.position}</td>
                <td>${item.department}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
