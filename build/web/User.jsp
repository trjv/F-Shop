<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User Page</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>

            .sidebar {
                padding: 15px;
                background-color: #f8f9fa;
                border-right: 1px solid #e7e7e7;
                height: 100%;
            }
            .sidebar a {
                display: block;
                color: #333;
                padding: 10px 0;
                text-decoration: none;
            }
            .sidebar a:hover {
                background-color: #ddd;
                color: #000;
            }
        </style>
    </head>
    <body class="body">
        <header>
            <jsp:include page="navbar.jsp"></jsp:include>
            </header>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-3 sidebar">
                        <a href="User?username=${sessionScope.user}&password=${sessionScope.pass}">Profile</a>
                    <a href="Purchase.jsp">Your Purchase</a>
                    <a href="Home.jsp">Chua nghi ra</a>
                </div>
                <div class="col-md-9">
                    <!-- Nội dung của các trang sẽ được hiển thị tại đây -->
                    <h2 class="text-center my-4">User Page</h2>
                </div>
                <div class="col-md-9">
                    <!-- Bảng hiển thị thông tin người dùng -->

                </div>
            </div>
        </div>
        <hr>
        <jsp:include page="footer.jsp"></jsp:include>
        <!-- Bootstrap JS và các phụ thuộc -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
