    <%--
      Created by IntelliJ IDEA.
      User: chu tien dat
      Date: 6/7/2023
      Time: 7:48 PM
      To change this template use File | Settings | File Templates.
    --%>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ include file="../../common/admin.jsp" %>
    <html>
    <head>
        <title>Product List</title>
    </head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <body>
    <div class="container">
        <button><a href="details.jsp">Create new book</a></button>
        <table>
            <tr>
                <th>Title</th>
                <th>Price</th>
                <th>Action</th>
            </tr>
            <c:forEach var="item" items="${products}">
                <tr>
                    <td>${item.name}</td>
                    <td>${item.price}</td>
                    <td>
                        <ul>
                            <li><a class="details" href="/product?id=${item.id}">Details</a></li>
                            <li><a class="edit" href="/product/form?id=${item.id}">Edit</a></li>
                            <li><a class="delete" href="#">Delete</a></li>
                        </ul>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    </body>
    </html>
