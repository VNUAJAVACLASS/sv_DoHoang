<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Đăng Nhập</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <style>
        body {
            background: linear-gradient(135deg, #4facfe, #00f2fe);
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: Arial;
        }

        .form-box {
            width: 380px;
            background: #ffffff;
            padding: 30px;
            border-radius: 20px;
            box-shadow: 0 10px 25px rgba(0,0,0,0.2);
            animation: fadeIn 0.4s ease-in-out;
        }

        .title {
            font-size: 26px;
            font-weight: bold;
            text-align: center;
            margin-bottom: 25px;
        }

        button {
            border-radius: 10px !important;
            font-size: 17px;
            padding: 10px;
        }

        .input-group-text {
            background: #e3f2fd;
        }

        .link {
            margin-top: 10px;
            text-align: center;
        }

        @keyframes fadeIn {
            from {opacity: 0; transform: translateY(20px);}
            to   {opacity: 1; transform: translateY(0);}
        }
    </style>
</head>

<body>

<div class="form-box">

    <div class="title">Đăng Nhập</div>

    <!-- Hiện lỗi -->
    <c:if test="${not empty error}">
        <div class="alert alert-danger py-2 text-center">
            ${error}
        </div>
    </c:if>

    <form action="${pageContext.request.contextPath}/login" method="post">

        <!-- Email -->
        <div class="mb-3">
            <label class="form-label">Email</label>
            <div class="input-group">
                <span class="input-group-text">
                    📧
                </span>
                <input type="email" name="email" class="form-control" required placeholder="Nhập email của bạn...">
            </div>
        </div>

        <!-- Password -->
        <div class="mb-3">
            <label class="form-label">Mật khẩu</label>
            <div class="input-group">
                <span class="input-group-text">
                    🔒
                </span>
                <input type="password" name="password" class="form-control" required placeholder="Nhập mật khẩu...">
            </div>
        </div>

        <!-- Login button -->
        <button class="btn btn-primary w-100 mt-2">Đăng Nhập</button>

        <!-- Register link -->
        <div class="link">
            <a href="${pageContext.request.contextPath}/register">Chưa có tài khoản? Đăng ký ngay</a>
        </div>
    </form>

</div>

</body>
</html>
