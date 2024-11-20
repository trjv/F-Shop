<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="img/logo2.png" type="img/x-icon">

        <title>sản phẩm</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    </head>
    <body class="body">
        <header>
            <jsp:include page="navbar.jsp"></jsp:include>
            </header>
           

            <h2 class="text-center my-4">LIST PRODUCT</h2>
            <div class="container">
                <div class="row">
                <c:forEach var="product" items="${product}">
                    <div class="col-md-4 mb-4">
                        <div class="card">
                            <a href="Product?productID=${product.id}">
                                <img src="img/${product.image}" class="card-img-top" alt="${product.name}">
                            </a>
                            <div class="card-body">
                                <h5 class="card-title">${product.name}</h5>
                                <h6 class="card-subtitle mb-2 text-muted">ID: ${product.id}</h6>
                                <p class="card-text">${product.description}</p>
                                <p class="card-text"><strong>Price: </strong>${product.price}</p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </body>
    <hr>
    <jsp:include page="footer.jsp"></jsp:include>
</html>
