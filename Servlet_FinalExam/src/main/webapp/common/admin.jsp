<%--
@author: Dinh Quang Anh
Date   : 6/16/2023
Project: jsp-servlet-sem4-exercise
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<html>
<head>
    <title>Admin</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.2.3/css/bootstrap.min.css" />

    <sitemesh:write propertiy="head" />
</head>
<body>
    <%@include file="navbar.jsp"%>
<div class="page-content">
    <sitemesh:write propertiy="body"/>
</div>
</body>
</html>
