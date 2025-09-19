<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Đăng nhập</h2>
<c:if test="${not empty error}">
	<h4 style="red">${error}</h4>

</c:if>

<form action="Login" method="Post">
Tên Đăng nhập<br>

<input type="text" name="username" value="${rememberedUser}" required="required">

<br>mật khẩu<br>
<input type="password" name="password" required="required">
<label><input text="checkbox" name="remember"> Ghi nhơ đăng nhập <br><br></label>
<input text="submit" value="Đăng nhập">
</form>
</body>
</html>