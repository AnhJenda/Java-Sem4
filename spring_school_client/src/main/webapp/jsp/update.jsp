<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/9/2023
  Time: 7:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/admin.jsp"%>
<%@include file="../common/taglib.jsp"%>
<html>
<head>
    <title>Create</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
<h1>Update Employee</h1>
<form method="post" action="/employee/update">
    <input type="hidden" name="id" value="${employee.id}">
    <div class="form-group">
        <label for="exampleInputEmail1">Employee new name</label>
        <input type="text" name="name" value="${employee.name}" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter name">
    </div>
    <div class="form-group">
        <label for="exampleInputPassword1">Create By</label>
        <input type="text" name="createBy" value="${employee.createBy}" class="form-control" id="exampleInputPassword1" placeholder="update by">
    </div>
    <div class="form-group">
        <label for="exampleInputPassword4">Update By</label>
        <input type="text" name="updateBy" class="form-control" id="exampleInputPassword4" placeholder="update by">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
    <button type="reset" class="btn btn-danger">Reset</button>
</form>
</body>
</html>