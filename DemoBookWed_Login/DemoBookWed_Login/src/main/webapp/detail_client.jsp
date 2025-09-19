<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
<meta charset="UTF-8">

<title>Chi tiết tin tức</title>
</head>
<body>
<!-- Truy cập đối tượng News được ghi vào request scope theo tên "news"
để hiển thị thông tin
-->
<h2>${books.title}</h2>
<p>${books.author}</p>
<br>
<!-- Với ứng dụng phức tạp nên gọi servlet theo dạng
${pageContext.request.contextPath}/<alias của servlet>
để đảm bảo đường dẫn luôn chính xác -->
<a href="${pageContext.request.contextPath}/clientHome">Quay lại danh sách</a>
</body>
</html>