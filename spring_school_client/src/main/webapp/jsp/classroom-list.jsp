<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/7/2023
  Time: 8:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/admin.jsp" %>
<html>
<head>
    <title>Product List</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>
<div class="container">
    <button><a href="classroom/create">Create new classroom</a></button>
    <br>
    <form action="classrooms" method="get">
        <input type="text" name="name" placeholder="Search by name"/>
        <button type="submit">Search</button>
    </form>
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Code</th>
            <th scope="col">Current Semester</th>
            <th scope="col">Size</th>
            <th scope="col">Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${classrooms}">
            <tr>
                <td>${item.name}</td>

                <td>${item.code}</td>
                <td>${item.currentSemester}</td>
                <td>${item.size}</td>
                <td>
                    <ul style="list-style-type: none; display: flex; align-items: center;padding: 0px;">
                        <li class="details"><a href="classroom?id=${item.id}">Details</a></li>
                        <li class="edit"><a href="classroom/update?id=${item.id}">Update</a></li>
                        <li class="delete"><a onclick="confirmDelete(${item.id})" href="#">Delete</a></li>
                    </ul>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <script>
        function confirmDelete(id) {
            var result = confirm("Confirm Delete?");
            if (result) {
                window.location.href = "classroom/delete?id=" + id;
            }
        }
    </script>

    <div>
        <c:if test="${pages.totalPages >= 1}">
            <ul class="pagination">
                <li>
                    <c:if test="${pages.pageNumber > 10}">
                        <a href="?pageNumber=0&pageSize=${pages.pageSize}">&laquo;</a>
                    </c:if>
                </li>

                <c:set var="endPage" value="${(pages.pageNumber + 5) < pages.totalPages ? (pages.pageNumber + 5) : pages.totalPages}" />

                <c:if test="${endPage - 5 > 0}">
                    <c:set var="startPage" value="${endPage - 5}" />
                </c:if>
                <c:if test="${endPage - 5 <= 0}">
                    <c:set var="startPage" value="1" />
                </c:if>

                <c:forEach var="pageNum" begin="${startPage}" end="${endPage}" varStatus="loop">
                    <li>
                        <c:choose>
                            <c:when test="${pages.pageNumber + 1 == pageNum}">
                                <a href="?pageNumber=${pageNum - 1}&pageSize=${pages.pageSize}" style="color: white; background-color: darkblue;" class="active">${pageNum}</a>
                            </c:when>
                            <c:otherwise>
                                <a href="?pageNumber=${pageNum - 1}&pageSize=${pages.pageSize}">${pageNum}</a>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </c:forEach>

                <li>
                    <c:if test="${pages.pageNumber + 6 < pages.totalPages}">
                        <a href="?pageNumber=${pages.totalPages - 1}&pageSize=${pages.pageSize}">&raquo;</a>
                    </c:if>
                </li>
            </ul>
        </c:if>
    </div>

</div>
</body>
</html>
