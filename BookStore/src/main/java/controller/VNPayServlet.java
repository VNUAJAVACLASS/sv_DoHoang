package controller;

import vnpay.VNPayConfig;
import dao.BookDAO;
import model.Book;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/vnpay")
public class VNPayServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String amountStr = req.getParameter("amount");

        // FIX LỖI: amount rỗng → dùng total từ session
        if (amountStr == null || amountStr.trim().isEmpty()) {
            Object totalObj = req.getSession().getAttribute("total");
            if (totalObj != null) {
                amountStr = totalObj.toString();
            } else {
                throw new RuntimeException("Không tìm thấy amount để thanh toán!");
            }
        }

        int amount = Integer.parseInt(amountStr); // luôn hợp lệ
        amount = amount * 100;

        String orderInfo = "Thanh toan don hang";
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "other";

        String vnp_TxnRef = String.valueOf(System.currentTimeMillis());
        String vnp_IpAddr = req.getRemoteAddr();

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(calendar.getTime());

        Map<String, String> params = new HashMap<>();
        params.put("vnp_Version", vnp_Version);
        params.put("vnp_Command", vnp_Command);
        params.put("vnp_TmnCode", VNPayConfig.vnp_TmnCode);
        params.put("vnp_Amount", String.valueOf(amount));
        params.put("vnp_CurrCode", "VND");
        params.put("vnp_TxnRef", vnp_TxnRef);
        params.put("vnp_OrderInfo", orderInfo);
        params.put("vnp_OrderType", orderType);
        params.put("vnp_Locale", "vn");
        params.put("vnp_ReturnUrl", VNPayConfig.vnp_ReturnUrl);
        params.put("vnp_IpAddr", vnp_IpAddr);
        params.put("vnp_CreateDate", vnp_CreateDate);

        List<String> sortedKeys = new ArrayList<>(params.keySet());
        Collections.sort(sortedKeys);

        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();

        for (String key : sortedKeys) {
            String value = params.get(key);

            if (hashData.length() > 0) hashData.append("&");

            hashData.append(key).append("=")
                    .append(URLEncoder.encode(value, "UTF-8"));

            query.append(URLEncoder.encode(key, "UTF-8")).append("=")
                 .append(URLEncoder.encode(value, "UTF-8")).append("&");
        }

        String secureHash = hmacSHA512(VNPayConfig.vnp_HashSecret, hashData.toString());
        query.append("vnp_SecureHash=").append(secureHash);

        String paymentUrl = VNPayConfig.vnp_PayUrl + "?" + query.toString();

        resp.sendRedirect(paymentUrl);
    }

    public static String hmacSHA512(String key, String data) {
        try {
            javax.crypto.Mac hmac512 = javax.crypto.Mac.getInstance("HmacSHA512");
            javax.crypto.spec.SecretKeySpec secret_key =
                    new javax.crypto.spec.SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA512");
            hmac512.init(secret_key);

            byte[] bytes = hmac512.doFinal(data.getBytes("UTF-8"));
            StringBuilder hash = new StringBuilder();

            for (byte aByte : bytes) {
                hash.append(String.format("%02x", aByte));
            }
            return hash.toString();

        } catch (Exception e) {
            return "";
        }
    }
}
