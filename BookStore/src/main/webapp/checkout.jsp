<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Thanh toán</title>

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

    <style>
        body { background: #f4f4f4; }
        .checkout-box {
            background: white;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 3px 10px rgba(0,0,0,0.1);
        }
        .order-summary img {
            width: 60px;
            height: 80px;
            object-fit: cover;
            border-radius: 5px;
        }
    </style>
</head>

<body>

<div class="container mt-4">

    <h3 class="mb-4">🧾 Thanh toán đơn hàng</h3>

    <div class="row">

        <!-- FORM THÔNG TIN -->
        <div class="col-md-7">
            <div class="checkout-box">

                <h5 class="mb-3">Thông tin người nhận</h5>

                <form action="${pageContext.request.contextPath}/vnpay" method="get">

                    <div class="mb-3">
                        <label class="form-label">Họ và tên</label>
                        <input type="text" name="fullname" class="form-control" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Số điện thoại</label>
                        <input type="text" name="phone" class="form-control" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Email</label>
                        <input type="email" name="email" class="form-control" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Địa chỉ giao hàng</label>
                        <textarea name="address" class="form-control" rows="3" required></textarea>
                    </div>

                    <!-- 🟩 TOTAL KHÔNG BAO GIỜ ĐỂ RỖNG -->
                    <input type="hidden" name="amount" value="${sessionScope.total}">

                    <button class="btn btn-danger btn-lg mt-2 w-100">Thanh toán VNPAY</button>
                </form>

            </div>
        </div>

        <!-- TÓM TẮT ĐƠN HÀNG -->
        <div class="col-md-5">
            <div class="checkout-box order-summary">

                <h5 class="mb-3">Đơn hàng của bạn</h5>

                <c:forEach var="b" items="${items}">
                    <div class="d-flex align-items-center mb-3">
                        <img src="image/${b.imagePath}">
                        <div class="ms-3">
                            <div class="fw-bold">${b.title}</div>
                            <div>Số lượng: ${b.quantity}</div>
                            <div class="text-danger fw-bold">${b.price * b.quantity} đ</div>
                        </div>
                    </div>
                </c:forEach>

                <hr>

                <div class="d-flex justify-content-between">
                    <h5>Tổng tiền:</h5>
                    <h5 class="text-danger">${total} đ</h5>
                </div>

            </div>
        </div>

    </div>

</div>

</body>
</html>
