package vnpay;

public class VNPayConfig {

    public static String vnp_TmnCode = "hoang"; // ma minh
    public static String vnp_HashSecret = "YOUR_HASH_SECRET"; 
    public static String vnp_PayUrl = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
    public static String vnp_ReturnUrl = "http://localhost:8081/YourProjectName/vnpay_return";
}
