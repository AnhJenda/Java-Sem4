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
</head>
<body>
<h1>Create Product</h1>
<form method="post" action="product/create">
    <div class="form-group">
        <label for="exampleInputEmail1">Product name</label>
        <input type="text" name="name" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter name">
    </div>
    <div class="form-group">
        <label for="exampleInputPassword1">Price</label>
        <input type="date" name="price" class="form-control" id="exampleInputPassword1" placeholder="Enter price">
    </div>

    <button type="submit" class="btn btn-primary">Submit</button>
    <button type="reset" class="btn btn-danger">Reset</button>
</form>
</body>
</html>