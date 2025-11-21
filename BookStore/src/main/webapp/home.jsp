<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Book Store</title>

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" />

    <style>
        body { background: #f3f3f3; font-family: Arial; }

        .top-header { background: white; padding: 15px; border-bottom: 3px solid #ddd; }

        .menu { background: #333; padding: 10px; }
        .menu a { color: white; margin-right: 15px; text-decoration: none; }

        .sidebar { background: white; padding: 15px; border-radius: 6px; }

        .book-card { background: white; padding: 10px; border-radius: 6px; transition: .2s; }
        .book-card:hover { box-shadow: 0 4px 15px rgba(0,0,0,0.15); }

        .book-card img { width: 100%; height: 200px; object-fit: cover; border-radius: 5px; }

        .badge-cart { position: absolute; top: -8px; right: -8px; }

        .section-title { font-size: 20px; font-weight: bold; margin: 25px 0 10px 0; }

        /* Nút 3 gạch */
        .menu-btn {
            font-size: 28px;
            background: none;
            border: none;
            cursor: pointer;
        }

        /* MOBILE */
        @media (max-width: 768px) {

            .top-header {
                padding: 10px 15px !important;
                justify-content: space-between !important;
                gap: 12px !important;
            }

            .d-md-flex.gap-3 { display: none !important; }

            /* Sidebar mobile */
            .sidebar {
                position: fixed;
                left: -260px;
                top: 0;
                width: 240px;
                height: 100%;
                padding: 15px;
                z-index: 99999;
                background: white;
                transition: left .3s ease;
                box-shadow: 2px 0 10px rgba(0,0,0,0.2);
            }
            .sidebar.active {
                left: 0;
            }
        }
    </style>
</head>

<script>
    function toggleMenu() {
        document.querySelector(".sidebar").classList.toggle("active");
    }
</script>

<body>

<!-- HEADER -->
<header class="top-header d-flex justify-content-between align-items-center">

    <!-- Nút 3 gạch -->
    <button class="menu-btn d-md-none" onclick="toggleMenu()">☰</button>

    <!-- Logo -->
    <div class="d-flex align-items-center">
        <img src="${pageContext.request.contextPath}/image/logo.png" width="45" class="me-2">
        <h3 class="m-0 d-none d-md-block">BookStore</h3>
    </div>

    <!-- Ô tìm kiếm -->
    <form action="${pageContext.request.contextPath}/search"
          method="get"
          class="d-flex search-bar flex-grow-1 mx-3">
        <input type="text" name="keyword" class="form-control" placeholder="Tìm kiếm sách...">
        <button class="btn btn-dark ms-2">Tìm</button>
    </form>

    <!-- USER + CART -->
    <div class="d-none d-md-flex align-items-center gap-3">

        <c:choose>
            <c:when test="${empty sessionScope.user}">
                <a href="${pageContext.request.contextPath}/login" class="btn btn-primary btn-sm">Đăng nhập</a>
                <a href="${pageContext.request.contextPath}/register" class="btn btn-success btn-sm">Đăng ký</a>
            </c:when>
            <c:otherwise>
                <span>Xin chào <b>${sessionScope.user.fullname}</b></span>
                <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger btn-sm">Đăng xuất</a>
            </c:otherwise>
        </c:choose>

        <div class="position-relative">
            <a href="${pageContext.request.contextPath}/cart" class="btn btn-outline-primary">🛒</a>
            <span class="badge bg-danger badge-cart">
                <c:choose>
                    <c:when test="${not empty sessionScope.cart}">
                        ${fn:length(sessionScope.cart)}
                    </c:when>
                    <c:otherwise>0</c:otherwise>
                </c:choose>
            </span>
        </div>

    </div>
</header>

<!-- MENU -->
<div class="menu">
    <a href="${pageContext.request.contextPath}/home">Trang chủ</a>
    <a href="#">Sách nổi bật</a>
    <a href="#">Giảm giá</a>
    <a href="#">Liên hệ</a>
</div>


<div class="container mt-4">
    <div class="row">

        <!-- SIDEBAR -->
        <div class="col-md-3">
            <div class="sidebar">

                <h5 class="fw-bold mb-3">📚 Danh mục sách</h5>
                <ul class="list-unstyled">
                    <c:forEach var="c" items="${categories}">
                        <li class="mb-2">
                            <a href="${pageContext.request.contextPath}/search?keyword=${c.title}">
                                📘 ${c.title}
                            </a>
                        </li>
                    </c:forEach>
                </ul>

                <hr>

                <!-- USER ở sidebar -->
                <c:choose>
                    <c:when test="${empty sessionScope.user}">
                        <a href="${pageContext.request.contextPath}/login"
                           class="btn btn-primary w-100 mb-2">🔐 Đăng nhập</a>
                        <a href="${pageContext.request.contextPath}/register"
                           class="btn btn-success w-100 mb-3">📝 Đăng ký</a>
                    </c:when>
                    <c:otherwise>
                        <p class="fw-bold">Xin chào, ${sessionScope.user.fullname}</p>
                        <a href="${pageContext.request.contextPath}/logout"
                           class="btn btn-danger w-100 mb-3">🚪 Đăng xuất</a>
                    </c:otherwise>
                </c:choose>

                <!-- Cart -->
                <a href="${pageContext.request.contextPath}/cart" class="btn btn-outline-primary w-100">
                    🛒 Giỏ hàng
                    <span class="badge bg-danger ms-1">
                        <c:choose>
                            <c:when test="${not empty sessionScope.cart}">
                                ${fn:length(sessionScope.cart)}
                            </c:when>
                            <c:otherwise>0</c:otherwise>
                        </c:choose>
                    </span>
                </a>

            </div>
        </div>

        <!-- MAIN CONTENT -->
        <div class="col-md-9">

            <div class="section-title">📚 Sách nổi bật</div>

            <div class="row">
                <c:forEach var="b" items="${books}">
                    <div class="col-md-4 mb-4">
                        <div class="book-card">
                            <img src="${pageContext.request.contextPath}/image/${b.imagePath}">
                            <div class="fw-bold mt-2">${b.title}</div>
                            <div class="text-danger fw-bold">${b.price}đ</div>

                            <div class="d-grid gap-2 mt-2">
                                <a href="${pageContext.request.contextPath}/detail?id=${b.bookId}"
                                   class="btn btn-outline-primary btn-sm">Xem chi tiết</a>
                                <a href="${pageContext.request.contextPath}/cart?action=add&id=${b.bookId}"
                                   class="btn btn-success btn-sm">🛒 Thêm vào giỏ</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <!-- PAGINATION -->
            <nav>
                <ul class="pagination justify-content-center">
                    <c:forEach var="i" begin="1" end="${totalPage}">
                        <li class="page-item ${i == currentPage ? 'active' : ''}">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/home?page=${i}">
                                ${i}
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </nav>

            <div class="section-title">🆕 Sách mới nhất</div>

            <div class="row">
                <c:forEach var="n" items="${newBooks}">
                    <div class="col-md-3 mb-4">
                        <div class="book-card">
                            <img src="${pageContext.request.contextPath}/image/${n.imagePath}">
                            <div class="fw-bold mt-2">${n.title}</div>
                            <div class="text-danger fw-bold">${n.price}đ</div>

                            <a href="${pageContext.request.contextPath}/cart?action=add&id=${n.bookId}"
                               class="btn btn-success btn-sm mt-2"> 🛒 Thêm </a>

                        </div>
                    </div>
                </c:forEach>
            </div>

        </div>
    </div>
</div>

</body>
</html>
