<%--
  Created by IntelliJ IDEA.
  User: chu tien dat
  Date: 6/9/2023
  Time: 8:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ include file="/common/taglist.jsp" %>--%>
<%--<html>--%>
<%--<head>--%>

<%--    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">--%>

<%--    <!-- jQuery library -->--%>
<%--    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>--%>

<%--    <!-- Latest compiled JavaScript -->--%>
<%--    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>--%>
<%--    <sitemesh:write property="head"/>--%>
<%--</head>--%>
<%--<body>--%>
<%--<%@ include file="/common/navbar.jsp" %>--%>
<%--<div class="page-content">--%>
<%--    <sitemesh:write property="body"/>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/taglist.jsp"%>
<html>
<head>
    <title>Admin</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <sitemesh:write propertiy="head" />
</head>
<body>
<%@include file="navbar.jsp"%>
<div class="page-content">
    <sitemesh:write propertiy="body"/>
</div>
</body>
</html>
