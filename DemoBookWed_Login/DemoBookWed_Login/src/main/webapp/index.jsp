<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

    <table border="1.5" cellpadding="5">
        <tr>
            <th>ID</th>
            <th>Tên sách</th>
            <th>Xem chi tiết</th>
        </tr>
        
        <c:forEach var="book" items="${bookList}">
            <tr>
                <td>${book.id}</td>
                <td>${book.title}</td>
                <td><a href="clientHome?action=detail&id=${book.id}"> Xem chi tiết</a></td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
