<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/style.css">
    <style>
        /* Custom styles for profile section */
        .profile-section {
            margin-top: 20px;
            border: 1px solid #ccc;
            padding: 20px;
            background-color: #f8f9fa;
            border-radius: 5px;
        }
        .profile-section h5 {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<header>
    <!-- Navbar include -->
    <jsp:include page="navbar.jsp"></jsp:include>
</header>

<div class="container mt-5">
    <div class="row">
        <div class="col-md-12">
            <h5>User Profile</h5>
        </div>
    </div>
    <div class="row profile-section">
        <div class="col-md-12">
            <!-- Hiển thị thông tin người dùng từ session trong bảng -->
            <table class="table table-striped">
                <tbody>
                    <tr>
                        <th scope="row">ID</th>
                        <td>${sessionScope.profile.getUserID()}</td>
                    </tr>
                    <tr>
                        <th scope="row">Username</th>
                        <td>${sessionScope.profile.getUserName()}</td>
                    </tr>
                    <tr>
                        <th scope="row">Email</th>
                        <td>${sessionScope.profile.getEmail()}</td>
                    </tr>
                    <tr>
                        <th scope="row">Phone Number</th>
                        <td>${sessionScope.profile.getPhoneNumber()}</td>
                    </tr>
                    <tr>
                        <th scope="row">Gender</th>
                        <td>${sessionScope.profile.getGender()}</td>
                    </tr>
                    <tr>
                        <th scope="row">Date of Birth</th>
                        <td>${sessionScope.profile.getDateOfBirth()}</td>
                    </tr>
                    <!-- Các thông tin khác của người dùng -->
                </tbody>
            </table>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
