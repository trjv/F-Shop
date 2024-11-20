<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="img/logo2.png" type="img/x-icon">
        <title>Product Detail</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <!-- Custom CSS -->
        <style>
            body {
                font-family: Arial, sans-serif;
            }
            .product-details {
                margin-top: 20px;
            }
            .price-section p {
                font-size: 1.2rem;
                margin-bottom: 10px;
                color: #0056b3
            }
            .price-section .btn-primary {
                background-color: #007bff;
                border: none;
                padding: 10px 20px;
                font-size: 1rem;
                border-radius: 5px;
            }
            .price-section .btn-primary:hover {
                background-color: #0056b3;
            }
            #img_product img {
                max-width: 100%;
                border-radius: 30px;
                border: 2px solid #ddd; /* Thêm viền */
                box-shadow: 0 5px 8px rgba(0, 0, 0, 0.2); /* Thêm bóng đổ */
            }
            .product-details h1 {
                font-size: 2.5rem;
                font-weight: bold;
                margin-bottom: 20px;
            }
            .description{
                font-size: 30px;
            }
            .price-section form{
            }
        </style>
    </head>
    <body>
        <header>
            <!--bat dau thanh navbar-->
            <jsp:include page="navbar.jsp"></jsp:include>
                <!--ket huc thanh navbar-->
            </header>
            <div class="container mt-5">
                <div class="row">
                    <div class="col-md-12 mt-4">
                        <h1>${detail.name}</h1>
                </div>
                <div id="img_product" class="col-md-5">
                    <div class="text-center">
                        <img src="img/${detail.image}" class="img-fluid" alt="${detail.name}">
                    </div>
                </div>
                <div id="detail_product" class="col-md-7">
                    <div class="product-details">
                        <div class="price-section">
                            <p style="color: #650000"><strong>Price:</strong> $${detail.price}</p>
                            <p style="color: #007bff"><strong>Stock:</strong> ${detail.stock}</p>
                            <form action="buyProduct" method="post">
                                <label for="quantity"><strong>Quantity:</strong></label>
                                <input type="number" id="quantity" name="quantity" class="form-control" min="1" value="1" style="width: 100px; display: inline-block; margin-right: 10px;">
                                <input type="hidden" name="productId" value="${detail.id}">
                                <input type="hidden" name="productName" value="${detail.name}">
                                <input type="hidden" name="productImage" value="${detail.image}">
                                <input type="hidden" name="price" value="${detail.price}">
                                <input type="hidden" name="username" value="${sessionScope.user}">

                                <button type="submit" class="btn btn-primary">Buy Now</button>
                            </form>
                            <p>${outofstock}</p>

                        </div>
                    </div>
                </div>
                <div class="col-md-12 mt-4">
                    <div class="product-details">
                        <p class="description"><strong>Description:</strong> ${detail.description}</p>
                    </div>
                </div>
            </div>
        </div>
        <!--                    <h2>MORE PRODUCT</h2>
                <div class="row">
        <c:choose>
            <c:when test="${not empty list}">
                <c:forEach var="product" items="${requestScope.sim}">
                    <div class="col-md-4 mb-4">
                        <div class="card">
                            <a href="Product?productID=${product.id}">                      
                                <img src="img/${product.image}" class="card-img-top" alt="${product.name}">
                            </a>
                            <div class="card-body">
                                <h5 class="card-title">${product.name}</h5>
                                <h6 class="card-subtitle mb-2 text-muted">ID: ${product.id}</h6>
                                <p class="card-text"><strong>Price: </strong>${product.price}</p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <p>No products found.</p>
            </c:otherwise>
        </c:choose>
    </div>-->
        <hr>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
