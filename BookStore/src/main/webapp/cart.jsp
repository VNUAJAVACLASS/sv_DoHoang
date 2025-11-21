<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Giỏ hàng</title>

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

    <style>
        .cart-item img {
            width: 80px;
            height: 100px;
            object-fit: cover;
            border-radius: 5px;
        }
        .qty-box {
            width: 60px;
            text-align: center;
        }
        .total-box {
            font-size: 22px;
            font-weight: bold;
            color: #d9534f;
        }
    </style>
</head>

<body class="bg-light">

<div class="container mt-4">

    <h3 class="mb-4">🛒 Giỏ hàng của bạn</h3>

    <!-- Nếu giỏ hàng trống -->
    <c:if test="${empty items}">
        <div class="alert alert-info">Giỏ hàng hiện đang trống.</div>
        <a href="home" class="btn btn-primary">Quay lại mua sách</a>
    </c:if>

    <!-- Danh sách sản phẩm -->
    <c:if test="${not empty items}">
        <table class="table table-bordered bg-white">
            <thead>
                <tr class="text-center">
                    <th>Ảnh</th>
                    <th>Tên sách</th>
                    <th>Giá</th>
                    <th width="120">Số lượng</th>
                    <th>Thành tiền</th>
                    <th>Xóa</th>
                </tr>
            </thead>

            <tbody>
                <c:forEach var="b" items="${items}">
                    <tr class="text-center cart-item">

                        <td><img src="image/${b.imagePath}"></td>

                        <td class="text-start">${b.title}</td>

                        <td>${b.price} đ</td>

                        <td>
                            <form action="cart" method="get" class="d-flex justify-content-center">
                                <input type="hidden" name="action" value="update">
                                <input type="hidden" name="id" value="${b.bookId}">
                                <input type="number" name="qty"
                                       value="${b.quantity}" min="1"
                                       class="form-control qty-box">
                                <button class="btn btn-sm btn-primary ms-2">OK</button>
                            </form>
                        </td>

                        <td class="text-danger fw-bold">
                            ${b.price * b.quantity} đ
                        </td>

                        <td>
                            <a class="btn btn-danger btn-sm"
                               href="cart?action=remove&id=${b.bookId}">
                                Xóa
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- Tổng tiền -->
        <div class="text-end mb-3 total-box">
            Tổng cộng: ${total} đ
        </div>

        <div class="d-flex justify-content-between">
            <a href="home" class="btn btn-secondary">← Tiếp tục mua hàng</a>
            <a href="${pageContext.request.contextPath}/checkout" 
   class="btn btn-success btn-lg">Thanh toán →</a>
            
        </div>
    </c:if>

</div>

</body>
</html>
