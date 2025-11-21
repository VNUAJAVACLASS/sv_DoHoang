package controller;

import vnpay.VNPayConfig;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/vnpay_return")
public class VNPayReturnServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Map<String, String> fields = new HashMap<>();

        for (Enumeration<String> params = req.getParameterNames(); params.hasMoreElements();) {
            String key = params.nextElement();
            String value = req.getParameter(key);

            if (key.startsWith("vnp_")) fields.put(key, value);
        }

        String vnp_SecureHash = fields.remove("vnp_SecureHash");

        // tạo hash kiểm tra
        List<String> sortedKeys = new ArrayList<>(fields.keySet());
        Collections.sort(sortedKeys);

        StringBuilder sb = new StringBuilder();

        for (String key : sortedKeys) {
            if (sb.length() > 0) sb.append("&");
            sb.append(key).append("=").append(fields.get(key));
        }

        String myHash = VNPayServlet.hmacSHA512(VNPayConfig.vnp_HashSecret, sb.toString());

        if (myHash.equals(vnp_SecureHash)) {

            String responseCode = fields.get("vnp_ResponseCode");

            if ("00".equals(responseCode)) {

                req.setAttribute("msg", "Thanh toán thành công! Đơn hàng sẽ được xử lý.");
            } else {
                req.setAttribute("msg", "Thanh toán thất bại! Mã lỗi: " + responseCode);
            }

        } else {
            req.setAttribute("msg", "Sai checksum! Không hợp lệ.");
        }

        req.getRequestDispatcher("payment_result.jsp").forward(req, resp);
    }
}
