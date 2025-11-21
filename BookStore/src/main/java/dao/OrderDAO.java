package dao;

import model.Order;
import java.sql.*;
import java.util.*;

public class OrderDAO {

    // Tạo mã đơn hàng ngẫu nhiên
    public String generateOrderNo() {
        return "OD" + System.currentTimeMillis();
    }

    // THÊM ĐƠN HÀNG
    public int insertOrder(Order o) {
        String sql = "INSERT INTO tblorder(order_no, customer_username, order_date, "
                + "payment_mode, order_status, total_cost, delivery_address) "
                + "VALUES(?,?,?,?,?,?,?)";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, o.getOrderNo());
            ps.setString(2, o.getCustomerUsername());
            ps.setTimestamp(3, new Timestamp(o.getOrderDate().getTime()));
            ps.setString(4, o.getPaymentMode());
            ps.setInt(5, o.getOrderStatus());
            ps.setInt(6, o.getTotalCost());
            ps.setString(7, o.getDeliveryAddress());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);

        } catch (Exception ex) { ex.printStackTrace(); }

        return -1;
    }

    // LẤY DANH SÁCH ĐƠN THEO USER
    public List<Order> getOrdersByUser(String username) {

        List<Order> list = new ArrayList<>();

        String sql = "SELECT * FROM tblorder WHERE customer_username=? ORDER BY order_id DESC";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(map(rs));
            }

        } catch (Exception ex) { ex.printStackTrace(); }

        return list;
    }

    // LẤY CHI TIẾT 1 ĐƠN
    public Order getOrderById(int id) {
        String sql = "SELECT * FROM tblorder WHERE order_id=?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return map(rs);

        } catch (Exception ex) { ex.printStackTrace(); }

        return null;
    }

    // MAP RESULTSET TO ORDER
    private Order map(ResultSet rs) throws Exception {
        Order o = new Order();

        o.setOrderId(rs.getInt("order_id"));
        o.setOrderNo(rs.getString("order_no"));
        o.setCustomerUsername(rs.getString("customer_username"));
        o.setOrderDate(rs.getTimestamp("order_date"));
        o.setOrderApproveDate(rs.getTimestamp("order_approve_date"));
        o.setPaymentMode(rs.getString("payment_mode"));
        o.setOrderStatus(rs.getInt("order_status"));
        o.setTotalCost(rs.getInt("total_cost"));
        o.setPaymentImg(rs.getString("payment_img"));
        o.setPaymentStatus(rs.getInt("payment_status"));
        o.setStatusDate(rs.getTimestamp("status_date"));
        o.setDeliveryAddress(rs.getString("delivery_address"));

        return o;
    }
}
