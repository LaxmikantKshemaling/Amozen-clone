<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Login Page</title>
    <!-- Link to Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Link to Font Awesome for social icons -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
    <style>
        .vh-100 {
            min-height: 100vh;
        }
        .card {
            border-radius: 1rem;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
        }
        .img-fluid {
            object-fit: cover;
            height: 100%;
            width: 100%;
            border-radius: 1rem 0 0 1rem;
        }
        body {
            background-color: #333; /* Light black background */
        }
        .form-control {
            border-radius: 0.5rem;
        }
        .btn-dark {
            background-color: #ff6219;
            border: none;
        }
        .btn-dark:hover {
            background-color: #ff4500;
        }
        .link-primary {
            color: #ff6219;
        }
        .link-primary:hover {
            color: #ff4500;
        }
        /* Centering the card */
        .login-card {
            max-width: 600px;
            margin: 0 auto;
            padding: 30px;
        }
        /* Set text color to white */
        .form-label, .lead, p, h3 {
            color: white;
        }
    </style>
</head>
<body>
<section class="vh-100 d-flex align-items-center justify-content-center">
    <div class="container">
        <div class="row justify-content-center align-items-center h-100">
            <div class="col-md-9 col-lg-6 col-xl-5">
                <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp" class="img-fluid" alt="Sample image">
            </div>
            <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1 login-card">
                <form action="Sign" method="POST"> <!-- Updated form method and action -->
                    <div class="d-flex flex-row align-items-center justify-content-center justify-content-lg-start">
                        <p class="lead fw-normal mb-0 me-3">Sign in with</p>
                    </div>

                    <!-- User Name input -->
                    <div class="form-outline mb-4">
                        <input type="text" name="name" id="name" class="form-control form-control-lg" placeholder="User Name" />
                        <label class="form-label" for="name">User Name</label>
                    </div>

                    <!-- Password input -->
                    <div class="form-outline mb-3">
                        <input type="password" name="password" id="password" class="form-control form-control-lg" placeholder="Enter password" />
                        <label class="form-label" for="password">Password</label>
                    </div>

                    <div class="text-center text-lg-start mt-4 pt-2">
                        <button type="submit" class="btn btn-dark btn-lg" style="padding-left: 2.5rem; padding-right: 2.5rem;">Login</button>
                        <p class="small fw-bold mt-2 pt-1 mb-0">Don't have an account? <a href="SignUp.jsp" class="link-primary">Register</a></p>
                    </div>

                    <%-- Display error message if exists --%>
                    <c:if test="${not empty errorMessage}">
                        <div class="alert alert-danger mt-3">${errorMessage}</div>
                    </c:if>
                </form>
            </div>
        </div>
    </div>

<!-- Link to Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
