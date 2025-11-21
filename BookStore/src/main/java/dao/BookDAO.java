package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Book;

public class BookDAO {

    // =============================
    // MAP KẾT QUẢ DB → OBJECT BOOK
    // =============================
    private Book mapToBook(ResultSet rs) throws SQLException {
        Book b = new Book();
        b.setBookId(rs.getInt("book_id"));
        b.setTitle(rs.getString("title"));
        b.setAuthor(rs.getString("author"));
        b.setPrice(rs.getInt("price"));
        b.setQuantity(rs.getInt("quantity_in_stock"));
        b.setDetail(rs.getString("detail"));
        b.setImagePath(rs.getString("image_path"));
        b.setCreateDate(rs.getTimestamp("create_date")); 
        return b;
    }

    // =============================
    // 1. Lấy X sách mới nhất
    // =============================
    public List<Book> getNewBooks(int limit) {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM tblbook ORDER BY create_date DESC LIMIT ?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, limit);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapToBook(rs));
            }

        } catch (Exception e) { e.printStackTrace(); }

        return list;
    }

    // =============================
    // 2. Lấy tất cả sách
    // =============================
    public List<Book> getAllBooks() {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM tblbook ORDER BY book_id DESC";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapToBook(rs));
            }

        } catch (Exception e) { e.printStackTrace(); }

        return list;
    }

    // =============================
    // 3. Lấy sách theo trang
    // =============================
    public List<Book> getBooksByPage(int offset, int limit) {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM tblbook ORDER BY book_id DESC LIMIT ?, ?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, offset);
            ps.setInt(2, limit);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapToBook(rs));
            }

        } catch (Exception e) { e.printStackTrace(); }

        return list;
    }

    // =============================
    // 4. Đếm tổng số sách
    // =============================
    public int countBooks() {
        String sql = "SELECT COUNT(*) FROM tblbook";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) return rs.getInt(1);

        } catch (Exception e) { e.printStackTrace(); }

        return 0;
    }

    // =============================
    // 5. Tìm kiếm theo tên
    // =============================
    public List<Book> searchByTitle(String keyword) {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM tblbook WHERE title LIKE ? ORDER BY book_id DESC";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapToBook(rs));
            }

        } catch (Exception e) { e.printStackTrace(); }

        return list;
    }

    // =============================
    // 6. Lấy 1 sách theo ID (quan trọng cho giỏ hàng)
    // =============================
    public Book getBookById(int id) {
        String sql = "SELECT * FROM tblbook WHERE book_id = ?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return mapToBook(rs);

        } catch (Exception e) { e.printStackTrace(); }

        return null;
    }

    // =============================
    // 7. Trừ số lượng khi đặt hàng (dùng cho OrderServlet)
    // =============================
    public boolean updateQuantity(int bookId, int qty) {
        String sql = "UPDATE tblbook SET quantity_in_stock = quantity_in_stock - ? WHERE book_id = ?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, qty);
            ps.setInt(2, bookId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) { e.printStackTrace(); }

        return false;
    }
}
