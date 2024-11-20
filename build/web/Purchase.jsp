<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Cart</title>
        <link rel="icon" href="img/logo2.png" type="img/x-icon">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <!-- Custom CSS -->
        <style>
            .cart-table img {
                max-width: 100px;
                height: auto;
            }
            .cart-table th, .cart-table td {
                vertical-align: middle;
            }
            .checkout-btn {
                margin-top: 20px;
            }
        </style>
    </head>
    <body>
        <header>
            <jsp:include page="navbar.jsp"></jsp:include>
            </header>
            <div class="container mt-5">
                <h1>Your Cart</h1>
            <c:if test="${not empty cart}">
                <table class="table table-bordered table-striped cart-table">
                    <thead class="thead-dark">
                        <tr>
                            <th>ID Product</th>
                            <th>Ảnh sản phẩm</th>
                            <th>Tên sản phẩm</th>
                            <th>Số lượng</th>
                            <th>Giá</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${requestScope.cart}">
                            <tr>
                                <td>${item.productId}</td>
                                <td><img src="img/${item.productImage}" alt="${item.productName}" class="img-thumbnail"></td>
                                <td>${item.productName}</td>
                                <td>${item.quantity}</td>
                                <td>${item.price}</td>
                                <td>
                                    <a href="buyProduct?action=remove&productId=${item.productId}" class="btn btn-danger btn-sm">Remove</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <div class="d-flex justify-content-between align-items-center">
                    <h3>Total: $<c:out value="${requestScope.total}"/></h3>
                    <form action="Checkout" method="post" class="checkout-btn">
                        <input type="hidden" name="username" value="${sessionScope.user}">
                        <button type="submit" class="btn btn-primary btn-lg">Checkout</button>
                    </form>
                </div>
            </c:if>
            <c:if test="${empty cart}">
                <p class="alert alert-warning">Thank You!</p>
            </c:if>
        </div>
        <jsp:include page="footer.jsp"></jsp:include>

    </body>
</html>
