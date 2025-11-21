package model;

public class OrderDetail {

    private int obId;
    private int orderId;
    private int bookId;
    private int quantity;

    public OrderDetail() {}

    public int getObId() {
        return obId;
    }
    public void setObId(int obId) {
        this.obId = obId;
    }

    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getBookId() {
        return bookId;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
