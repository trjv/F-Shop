<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="img/logo2.png" type="img/x-icon">
    <title>Trang chủ</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>

    </style>
</head>
<body class="body">
    <header>
        <jsp:include page="navbar.jsp"></jsp:include>
        </header>
        <!--<h2 class="text-center my-4">NEW PRODUCT</h2>-->
        <!-- Carousel outside container to make it full width -->
        <div id="productCarousel" class="carousel slide" data-ride="carousel" data-interval="3000"> <!-- 3000ms = 3s -->
            <div class="carousel-inner">
            <c:forEach var="product" items="${requestScope.newlist}" varStatus="status">
                <div class="carousel-item ${status.first ? 'active' : ''}">
                    <img src="img/${product.image}" class="d-block w-100" alt="${product.name}">
                    <div class="carousel-caption d-none d-md-block">
                        <h5>${product.name}</h5>
                        <p>${product.description}</p>
                    </div>
                </div>
            </c:forEach>
        </div>
        <a class="carousel-control-prev" href="#productCarousel" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#productCarousel" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
    <div class="container">

        <h2 class="text-center my-4">PRODUCT</h2>
        <c:if test="${sessionScope.role == 'admin'}">
            <a href="ProductForm.jsp" class="btn btn-primary mb-4">Add New Product</a>
        </c:if>
        <div class="row">
            <c:choose>
                <c:when test="${not empty list}">
                    <c:forEach var="product" items="${requestScope.list}">
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
                                    <p class="card-text"><strong>Stock: </strong>${product.stock}</p>
                                    <c:if test="${sessionScope.role == 'admin'}">
                                        <a href="Controller?action=update&id=${product.id}" class="btn btn-primary">Update</a>
                                        <a href="Controller?action=remove&id=${product.id}" class="btn btn-danger" onclick="return confirm('Are you sure?')">Remove</a>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p>No products found.</p>
                </c:otherwise>
            </c:choose>
        </div>
        <a href="Product?type=ALL" class="btn btn-primary">Go to Shop</a>

    </div>

    <hr>
    <jsp:include page="footer.jsp"></jsp:include>
</body>
