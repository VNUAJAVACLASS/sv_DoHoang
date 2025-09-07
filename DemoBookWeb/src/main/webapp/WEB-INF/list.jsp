<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách đầu sách</title>
</head>
<body>
	<h2>Danh sách đầu sách</h2>
	<a href="books?action=create">Thêm sách</a>
	<br>
	<br>
	<table border="1" cellpadding="5">
		<tr>
			<th>ID</th>
			<th>Tiêu đề</th>
			<th>Tác giả</th>
			<th>Giá</th>
			<th>Hình ảnh</th>
			<th>Hành động</th>
		</tr>
		<c:forEach var="books" items="${booksList}">
			<tr>
				<td>${books.bookId}</td>
				<td><a href="books?action=detail&id=${books.bookId}">${books.title}</a>
				</td>
				<td><a href="books?action=detail&id=${books.bookId}">${books.author}</a>
				</td>
				<td><a href="books?action=detail&id=${books.bookId}">${books.price}</a>
				</td>
				<td><a href="books?action=detail&id=${books.bookId}">${books.imagePath}</a>
				</td>
				<td>
					<!-- Khi click sẽ gửi yêu cầu về servlet trả lại trang form.jsp -->
					<a href="books?action=edit&id=${books.bookId}">Sửa</a> | <a
					href="books?action=delete&id=${books.bookId}"
					onclick="return confirm('Xoá tin này?');">Xoá</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>