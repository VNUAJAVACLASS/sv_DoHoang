<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Login</title>

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

    <style>
        body {
            background: #f8f9fa;
        }
        .login-box {
            max-width: 420px;
            margin: 80px auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 3px 10px rgba(0,0,0,0.15);
        }
    </style>
</head>
<body>

<div class="login-box">
    <h3 class="text-center mb-3">🔐 Admin Login</h3>

    <form action="admin-login" method="post">

        <div class="mb-3">
            <label class="form-label">Email đăng nhập</label>
            <input type="text" name="username" class="form-control" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Mật khẩu</label>
            <input type="password" name="password" class="form-control" required>
        </div>

        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <button class="btn btn-primary w-100">Đăng nhập</button>

    </form>
</div>

</body>
</html>
