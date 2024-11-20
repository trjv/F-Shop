<!-- templates/signup.html -->
<!doctype html>
<html>
    <head>
        <title>Forgot password</title>
        <link rel="stylesheet" href="https://matcha.mizu.sh/matcha.css">

    </head>
    <body>
        <h2>Reset password</h2>
        <form action="Forgotpassword" method="post">
            <div class="form-group">
                <label for="forgotUsername">Username:</label>
                <input type="text" class="form-control" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="securityAnswer">What is your favorite pet?</label>
                <input type="text" class="form-control" id="securityAnswer" name="securityAnswer" required>
            </div>
            <div class="form-group">
                <label for="newPassword">New Password:</label>
                <input type="text" class="form-control" id="newPassword" name="newPassword" required>
            </div>
            <button type="submit" class="btn btn-link">RESET</button>
        </form>
        <%
       String error = (String) request.getAttribute("error");
     if (error != null) {
             out.println("<p style='color: red;'>" + error + "</p>");
         }
      
        %>
    </body>
</html>