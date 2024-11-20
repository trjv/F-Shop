
<!-- templates/login.html -->
<!doctype html>
<html>
    <head>
        <title>Sign up</title>
        <link rel="stylesheet" href="https://matcha.mizu.sh/matcha.css">
        <style>
            body{
                background-image: url('img/camera-doko-head01.gif');
            }
            .login-container {
                background-color: #555;
                padding: 20px;
                border-radius: 10px;
                width: 300px;
            }

            .form-control {
                background-color: #777;
                color: #fff;
                border-color: #555;
            }

            .form-control:focus {
                background-color: #888;
                color: #fff;
                border-color: #555;
                box-shadow: none;
            }

            .btn-primary {
                background-color: #007bff;
                color: #fff;
                border: none;
                padding: 10px 20px;
                border-radius: 5px;
                cursor: pointer;
                width: 100%;
            }

            .btn-primary:hover {
                background-color: #0056b3;
            }
        </style>

    </head>
    <body class="body">
        <h2>Sign up</h2>
        <form action="Login" method="post">
            <input type="hidden" name="action" value="signup">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" class="form-control" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="phoneNumber">Phone Number</label>
                <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" required>
            </div>
            <div class="form-group">
                <label for="gender">Gender</label>
                <select class="form-control" id="gender" name="gender" required>
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                    <option value="Other">Other</option>
                </select>
            </div>
            <div class="form-group">
                <label for="securityAnswer">What is your favorite pet?</label>
                <input type="text" class="form-control" id="securityAnswer" name="securityAnswer" required>
            </div>
            <div class="form-group">
                <label for="dateOfBirth">Date of Birth</label>
                <input type="date" class="form-control" id="dateOfBirth" name="dateOfBirth" required>
            </div>
            <button type="submit" class="btn btn-primary">Signup</button>

        </form>

        <%
          String error = (String) request.getAttribute("error");
        if (error != null) {
                out.println("<p style='color: red;'>" + error + "</p>");
            }
      
        %>
    </body>
</html>
