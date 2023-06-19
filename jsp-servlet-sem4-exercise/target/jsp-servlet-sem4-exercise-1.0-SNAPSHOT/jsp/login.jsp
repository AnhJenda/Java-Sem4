<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/16/2023
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/admin.jsp"%>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div style="display: flex; justify-content: center; align-items: center; width: 400px; margin: 0 auto; border: 1px solid lightblue; border-radius: 12px; padding: 0">
    <form action="${pageContext.request.contextPath}/login" method="post">
        <div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">Email address</label>
            <input type="email" class="form-control" id="exampleInputEmail1" name="email" aria-describedby="emailHelp">
        </div>
        <div class="mb-3">
            <label for="exampleInputPassword1" class="form-label">Password</label>
            <input type="password" name="password" class="form-control" id="exampleInputPassword1">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>

</body>
</html>
