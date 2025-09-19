<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Sử dụng cú pháp nhúng code java -->
<%
// Trang form.jsp dùng chung cho chức năng tạo mới và sửa tin tức
// Ghi vào request scope một dấu hiệu (isEdit) để phân biệt
// Nếu đang ở chế độ sửa tin tức, trong request scope sẽ có đối tượng News
// chứa thông tin để hiển thị lên form
// request là biến ngầm định có sẵn trong trang
request.setAttribute("isEdit", request.getAttribute("books") != null);
%>
<html>
<head>
<meta charset="UTF-8">
<!-- Xử lý động tiêu đề -->
<title>${isEdit ? 'Sửa tin tức' : 'Tạo tin tức'}</title>
</head>
<body>
	<h2>${isEdit ? 'Sửa tin tức' : 'Tạo tin tức'}</h2>
	<form action="books" method="post">
		<!-- Nếu đang ở chế độ Edit, sẽ có id của tin tức cần sửa -->
		<c:if test="${isEdit}">
			<!-- Dùng thẻ ẩn hidden lưu id của tin tức
để sử dụng đến khi cập nhật CSDL về sau
-->
			<input type="hidden" name="id" value="${books.id}">
		</c:if>
		Tiêu đề: <br>
		<!-- Trường giá trị value sẽ rỗng nếu ứng với tình huống tạo mới tin tức -->
		<input type="text" name="title" value="${books.title}" required><br>
		<br> Tác giả <br>
		<textarea name="author" rows="5" cols="40" required>${books.author}</textarea>
		<br>
		<br> giá cả <br>
		<textarea name="author" rows="5" cols="40" required>${books.price}</textarea>
		<br>
		<br> <input type="submit"
			value="${isEdit ? 'Cập nhật' : 'Tạo mới'}">
	</form>
	<br>
	<a href="adminHome">Quay lại danh sách</a>
</body>
</html>