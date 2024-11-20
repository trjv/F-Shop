<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product List</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"> 
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .carousel {
            width: 100vw; /* Full viewport width */
            margin: 0 auto; /* Center align the carousel */
        }
        .carousel-inner {
            display: flex;
        }
        .carousel-item {
            transition: transform 0.5s ease;
            height: 500px; /* Adjust this height as needed */
            position: relative;
        }
        .carousel-item img {
            margin-top: 20px;
            height: 100%;
            width: 10%;
            object-fit: contain;
            border-radius: 15px;
        }
        .carousel-caption {
            position: absolute;
            bottom: 10px; /* Adjust this value as needed */
            left: 0;
            right: 0;
            background-color: rgba(0, 0, 0, 0.5);
            border-radius: 10px;
            padding: 10px;
            margin: 0 15px; /* Add margin to keep it away from the edges */
            text-align: center;
        }
        .card img {
            max-height: 600px; /* Giới hạn chiều cao của hình ảnh */
            object-fit: cover;
        }
        .card-footer {
            background-color: #f8f9fa; /* Màu nền cho footer */
            text-align: center;
            padding: 10px;
            border-top: 1px solid #e9ecef;
        }
        conntainer {
            display: flex;
            justify-content: center;
        }

        .footer-container {
            background-color: #fff; /* Màu xám nhạt */
            padding: 40px 0;
        }
        #detail_product{
        }
        #img_product{
            display: flex;
            margin-top: 10px;
            margin-bottom:  10px;
        }

        .navbar-light .navbar-nav .nav-link {
            color: white; /* Make navbar links white to stand out against dark background */
        }
        .navbar-light .navbar-toggler-icon {
            filter: invert(100%); /* Make toggler icon white */
        }
        .body{
            background-image: url('img/blackwhite.jpg');
        }
        hr {
            border-top: 1px solid #e7e7e7;
            margin-bottom: 30px;
        }
        #navbarNav{
            font-weight: bold;
            font-size: 75%;
        }
        .container {
            margin-top: 20px;
        }
        .product-image {
            text-align: center;
            margin-bottom: 20px;
        }
        .product-details {
            margin-bottom: 20px;
        }
        .product-price {
            font-size: 1.5rem;
            font-weight: bold;
            color: #007bff; /* Màu sắc của giá sản phẩm */
        }
        .product-description {
            margin-top: 20px;
        }
        .back-link {
            margin-top: 20px;
        }
        #leftimg img {
            border: 4px solid #ccc; /* Border dày 4px, màu xám nhạt */
            border-radius: 25px; /* Bo góc 12px */
            overflow: hidden; /* Đảm bảo không bị tràn nội dung nếu ảnh lớn hơn border */
            width: 100%; /* Đảm bảo hình ảnh lấp đầy chiều rộng của khung chứa */
            max-width: 80%; /* Giới hạn chiều rộng tối đa là 100% của khung chứa */
            height: auto; /* Cho phép chiều cao tự động điều chỉnh tương ứng */
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2); /* Bóng đổ màu xám nhạt, độ mờ 10px */
        }
        #rightinfo{
            margin-top: 125px;
        }
        .price-section{
            color: #e42903;
            display: inline;
            font-size: 25px;
            font-weight: 700;
            line-height: 40px;
            text-align: left;
        }

        .navbar-light .navbar-nav .nav-link {
            color: white; /* Make navbar links white to stand out against dark background */
        }
        .navbar-light .navbar-toggler-icon {
            filter: invert(100%); /* Make toggler icon white */
        }
        .body{
            background-image: url('img/blackwhite.jpg');
        }
        hr {
            border-top: 1px solid #e7e7e7;
            margin-bottom: 30px;
        }
        #navbarNav{
            font-weight: bold;
        }
        .card {
            /* Các thuộc tính CSS của card tùy chỉnh */
            border: 2px solid #ccc;
            border-radius: 8px;
            padding: 15px;
            background-color: #f8f9fa;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            display: flex;
            flex-direction: column;
            height: 100%; /* Make the card take up the full height */
        }
        .card-body {
            flex-grow: 1; /* Allow the card body to grow and take up available space */
        }
        header {
            background-image: url('img/WhiteModern.png');

            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            color: white; /* Optional: To make text white if the background is dark */
            padding: 20px 0; /* Optional: To add some padding */
        }

    </style> 
</head>
<!--bat dau thanh navbar-->
<nav class="navbar navbar-expand-lg navbar-light bg-transparent">
    <!--logo-->
    <a class="navbar-brand" href="Controller">
        <img src="./img/logo2.png" alt="alt" />
    </a>
    <!-- Thêm thanh tìm kiếm ở giữa navbar -->
    <form id="navsearch" class="form-inline mx-auto" action="Search" method="get">
        <input class="form-control mr-sm-2 " type="search" placeholder="Search" aria-label="Search" name="txt" required>
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <!--navbar list-->
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownProduct" role="button"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Product
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownProduct">
                    <a class="dropdown-item" href="Product?type=All">ALL</a>
                    <a class="dropdown-item" href="Product?type=sony">Sony</a>
                    <a class="dropdown-item" href="Product?type=canon">Canon</a>
                    <a class="dropdown-item" href="Product?type=fujifilm">Fujifilm</a>
                    <a class="dropdown-item" href="Product?type=nikon">Nikon</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="Accessories.jsp">Accessories</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="about.jsp">About Us</a>
            </li>
          
        </ul>

        <ul class="navbar-nav ml-auto">
            <c:if test="${sessionScope.user != null}">
                <li class="nav-item">
                    <a class="nav-link shopping-cart-icon" href="buyProduct?user=${sessionScope.user}">
                        <i class="fa-solid fa-gifts fa-2x"></i>
                    </a>
                </li>
            </c:if>
                
            <c:choose>
                <c:when test="${not empty sessionScope.user}">
                    <!--nếu muốn thêm tên hoặc ava thì tạo một vòng for ở đây để get về-->
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownUser" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <!-- <img src="img/" alt="Avatar">-->
                            ${sessionScope.user}
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownUser">
                            <a class="dropdown-item" href="User.jsp">Profile</a>
                            <a class="dropdown-item" href="Login?action=signout">Sign out</a>
                        </div>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="nav-item">
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#loginModal">
                            Login
                        </button>
                    </li>

                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>
<!--ket thuc thanh navbar-->

<style>
    #navsearch{

    }
    .navbar-brand img {
        width: 150px; /* Bạn có thể điều chỉnh chiều cao theo nhu cầu */
        height: 150px;
        border-radius: 10px;
    }
    .form-inline {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 50%;
    }
    .navbar-nav {
        display: flex;
        justify-content: center;
        align-items: center;
    }
    .navbar-nav .nav-item {
        display: flex;
    }
    .shopping-cart-icon {
        margin-right: 15px; /* Điều chỉnh giá trị này theo ý muốn */
    }
    .dropdown-menu {
        max-width: 250px; /* Đặt chiều rộng tối đa cho menu thả xuống */
        right: 0;
        left: auto; /* Căn chỉnh menu thả xuống về phía bên phải */
    }
</style>