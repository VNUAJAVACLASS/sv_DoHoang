package dao;

import model.OrderDetail;
import java.sql.*;
import java.util.*;

public class OrderDetailDAO {

    // THÊM 1 CHI TIẾT ĐƠN HÀNG
    public void insertOrderDetail(OrderDetail d) {

        String sql = "INSERT INTO tblorder_detail(order_id, book_id, quantity) VALUES(?,?,?)";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, d.getOrderId());
            ps.setInt(2, d.getBookId());
            ps.setInt(3, d.getQuantity());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // LẤY DANH SÁCH CHI TIẾT THEO ORDER_ID
    public List<OrderDetail> getDetailsByOrderId(int orderId) {

        List<OrderDetail> list = new ArrayList<>();

        String sql = "SELECT * FROM tblorder_detail WHERE order_id = ?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(map(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    // MAP DỮ LIỆU
    private OrderDetail map(ResultSet rs) throws Exception {

        OrderDetail d = new OrderDetail();

        d.setObId(rs.getInt("ob_id"));
        d.setOrderId(rs.getInt("order_id"));
        d.setBookId(rs.getInt("book_id"));
        d.setQuantity(rs.getInt("quantity"));

        return d;
    }
}
