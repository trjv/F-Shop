<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Về Chúng Tôi</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        /* Bổ sung các style tùy chỉnh nếu cần */
    </style>
</head>

<body class="body">
    <header>
        <jsp:include page="navbar.jsp"></jsp:include>
    </header>

    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h2 class="text-center my-4">Về Công Ty Chúng Tôi</h2>
                <p>Chào mừng bạn đến với trang Giới thiệu về công ty của chúng tôi! Chúng tôi tận tâm cung cấp các sản phẩm và dịch vụ chất lượng cao đến khách hàng.</p>
                <p>Được thành lập vào năm 2024, công ty của chúng tôi đã phát triển từ một công ty khởi nghiệp nhỏ thành một đối tác đáng tin cậy trong ngành công nghiệp. Sứ mệnh của chúng tôi là...</p>
                <p>Chúng tôi tin rằng...</p>
                <p>Đội ngũ chuyên nghiệp và nhiệt huyết của chúng tôi luôn cố gắng hết mình để...</p>
                <p>Tại Fshop, chúng tôi chuyên về máy ảnh. Cam kết của chúng tôi về chất lượng và sự hài lòng của khách hàng là điểm khác biệt của chúng tôi so với các đối thủ.</p>
                <p>Hãy khám phá website của chúng tôi để tìm hiểu thêm về các sản phẩm/dịch vụ mà chúng tôi cung cấp, và đừng ngần ngại liên hệ với chúng tôi nếu có bất kỳ thắc mắc nào. Xin cảm ơn bạn đã ghé thăm!</p>
            </div>
        </div>
    </div>

    <hr>
    <jsp:include page="footer.jsp"></jsp:include>
</body>
