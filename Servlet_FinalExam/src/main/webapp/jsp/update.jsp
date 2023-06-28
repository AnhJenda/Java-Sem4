<%@ page import="com.example.servlet_finalexam.entity.Employee" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/19/2023
  Time: 8:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/admin.jsp"%>
<%@include file="../common/taglib.jsp"%>
<html>
<head>
    <title>Create Employee</title>
</head>
<body>
    <form method="post" action="${pageContext.request.contextPath}/employees/update?id=${employee.id}">
    <div class="form-group">
    <label for="exampleInputEmail1">Employee name</label>
    <input type="text" name="fullname" value="${employee.fullname}" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter fullname">
    </div>
    <div class="form-group">
    <label for="exampleInputPassword1">Birthday</label>
    <input type="date" name="birthday" value="${employee.birthday}" class="form-control" id="exampleInputPassword1" placeholder="Enter birthday">
    </div>
    <div class="form-group">
    <label for="exampleInputAddress1">Address</label>
    <input type="text" name="address" value="${employee.address}" class="form-control" id="exampleInputAddress1"  placeholder="Enter address">
    </div>
    <div class="form-group">
    <label for="exampleInputposition1">Position</label>
    <input type="text" name="position" value="${employee.position}" class="form-control" id="exampleInputposition1" placeholder="Enter position">
    </div>
    <div class="form-group">
    <label for="exampleInputdepartment1">Department</label>
    <input type="text" name="department" value="${employee.department}" class="form-control" id="exampleInputdepartment1" placeholder="Enter department">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
    <button type="reset" class="btn btn-danger">Reset</button>
    </form>
</body>
</html>
