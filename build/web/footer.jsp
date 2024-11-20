<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.Cookie" %>
<%@ page import="jakarta.servlet.http.HttpServletRequest" %>
<%
    String username = "";
    String password = "";
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user")) {
                username = cookie.getValue();
            } else if (cookie.getName().equals("pass")) {
                password = cookie.getValue();
            }
        }
    }
%>
<!-- Modal -->
<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="loginModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="loginModalLabel">Login</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <!-- Form đăng nhập -->
                <form action="Login" method="post">
                    <div class="form-group">
                        <label for="username">Username</label>
                        <input type="text" class="form-control" id="username" name="username" value="<%= username %>" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" id="password" name="password" value="<%= password %>" required>
                    </div>
                    <div class="form-group form-check">
                        <input type="checkbox" class="form-check-input" id="rememberMe" name="rememberMe" checked>
                        <label class="form-check-label" for="rememberMe">Remember me</label>
                    </div>
                    <button type="submit" class="btn btn-primary">Login</button>
                </form>

                <%
                    String error = (String) session.getAttribute("error");
                    if (error != null) {
                        out.println("<p style='color: red;'>" + error + "</p>");
                    }
                %>
                <hr>
                <div class="text-center">
                    <p>Forgot your password? <a href="Forget.jsp">Reset it here</a></p>
                </div>
                <div class="text-center mt-3">
                    <p>Don't have an account? <a href="Login.jsp">Sign Up</a></p>

                </div>
            </div>
        </div>
    </div>
</div>
<div class="footer-container">
    <footer>
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <h5>Contact Us</h5>
                    <p>5, Thai Ha Street</p>
                    <p>Hanoi City, VietNam, 12345</p>
                    <p>Phone: 0398764212</p>
                    <p>Email: quanththe187097@fpt.edu.vn</p>
                </div>
                <div class="col-md-8">
                    <h5>Our Location</h5>
                    <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d59587.978404680645!2d105.79576382656118!3d21.02273463976122!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3135ab9bd9861ca1%3A0xe7887f7b72ca17a9!2zSMOgIE7hu5lpLCBWaeG7h3QgTmFt!5e0!3m2!1svi!2s!4v1718395598607!5m2!1svi!2s"
                            width="100%" height="200" style="border:0;" allowfullscreen="" loading="lazy"></iframe>
                </div>
            </div>
        </div>
    </footer>
</div>
<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<% 
    String showLoginModal = request.getParameter("showLoginModal");
    if ("true".equals(showLoginModal)) {
%>
<script>
    $(document).ready(function () {
        $('#loginModal').modal('show');
    });
</script>
<% 
    } 
%>
