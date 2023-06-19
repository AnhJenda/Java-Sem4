
<%--<nav class="navbar navbar-default">--%>
<%--    <div class="container-fluid">--%>
<%--        <div class="navbar-header">--%>
<%--            <a class="navbar-brand" href="#">Jsp-Servlet_Sem4</a>--%>
<%--        </div>--%>
<%--        <ul class="nav navbar-nav">--%>
<%--            <li class="active"><a href="${pageContext.request.contextPath}/">Home</a></li>--%>
<%--            <li><a href="products">Product</a></li>--%>
<%--            <li><a href="manufacturer">Manufacturer</a></li>--%>
<%--            <li><a href="warehouse">Warehouse</a></li>--%>
<%--            <li><a href="good-receipt">Good Receipt</a></li>--%>
<%--            <li><a href="good-issue">Good Issue</a></li>--%>
<%--            <% if (request.getSession().getAttribute("email") != null) { %>--%>
<%--            <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>--%>
<%--            <% } else { %>--%>
<%--            <li><a href="${pageContext.request.contextPath}/login">Login</a></li>--%>
<%--            <% } %>--%>
<%--        </ul>--%>
<%--    </div>--%>
<%--</nav>--%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Navbar</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="products">Product</a></li>
                <li class="nav-item"><a class="nav-link" href="manufacturer">Manufacturer</a></li>
                <li class="nav-item"><a class="nav-link" href="warehouse">Warehouse</a></li>
                <li class="nav-item"><a class="nav-link" href="good-receipt">Good Receipt</a></li>
                <li class="nav-item"><a class="nav-link" href="good-issue">Good Issue</a></li>
                <% if (request.getSession().getAttribute("email") != null) { %>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a></li>
                <% } else { %>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a></li>
                <% } %>
            </ul>
        </div>
    </div>
</nav>