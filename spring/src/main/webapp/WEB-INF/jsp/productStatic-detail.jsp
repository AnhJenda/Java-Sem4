
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../common/admin.jsp" %>
<html>
<head>
    <title>Product List</title>
</head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<body>
<div class="container">

    <div class="container">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <h2>Product Detail</h2>
                <hr>
                <!-- Product Information -->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h1 class="panel-title">${product.productName}</h1>
                    </div>
                    <div class="panel-body">
                        <p><strong>Price:</strong> ${product.producerName}</p>
                    </div>
                </div>

                <!-- Back to Product List -->
                <a href="/products" class="btn btn-default">Back to Product List</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
