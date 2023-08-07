<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/9/2023
  Time: 7:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/admin.jsp"%>
<html>
<head>
    <title>Details</title>
</head>
<body>
<h1>Employee details</h1>
<p>Name: ${classroom.name}</p>
<p>Create by: ${classroom.code}</p>
<p>Create by: ${classroom.currentSemester}</p>
<p>Create by: ${classroom.size}</p>
<p>Create by: ${classroom.startTime}</p>
<p>Create by: ${classroom.endTime}</p>
</body>
</html>
