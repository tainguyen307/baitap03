<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Forget Password</title>
    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: #f8f9fa;
        }
        .form-reset {
            max-width: 400px;
            margin: 80px auto;
            padding: 30px;
            border-radius: 15px;
            background: white;
            box-shadow: 0 0 15px rgba(0,0,0,0.1);
        }
        .form-reset h3 {
            text-align: center;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

<div class="form-reset">
    <h3>Reset Password</h3>
    <form action="ForgetPassword" method="post">
        <!-- Email -->
        <div class="mb-3">
            <label for="username" class="form-label">Username</label>
            <input type="text" name="username" class="form-control" id="username" required>
        </div>

        <!-- New Password -->
        <div class="mb-3">
            <label for="newPassword" class="form-label">New Password</label>
            <input type="password" name="newPassword" class="form-control" id="newPassword" required>
        </div>

        <!-- Submit -->
        <div class="d-grid">
            <button type="submit" class="btn btn-primary">Reset Password</button>
        </div>
    </form>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
