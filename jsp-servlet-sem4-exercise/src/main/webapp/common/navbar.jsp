
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Jsp-Servlet_Sem4</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="index">Home</a></li>
            <li><a href="products">Product</a></li>
            <li><a href="#">Page 2</a></li>
            <li><a href="#">Page 3</a></li>
            <li><a href="/">Home</a></li>
            <% if (request.getSession().getAttribute("email") != null) { %>
            <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
            <% } else { %>
            <li><a href="${pageContext.request.contextPath}/login">Login</a></li>
            <% } %>
        </ul>
    </div>
</nav>