<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
<meta charset="UTF-8">
<title>Chi tiết sách</title>
</head>
<body>
	<h2>${book.title}</h2>
	<p>${book.author}</p>
	<p>${book.price}</p>
	<p>${book.imagePath}</p>
	<br>
	<a href="${pageContext.request.contextPath}/books">Quay lại danh sách</a>
</body>
</html>