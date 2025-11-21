package model;

import java.util.Date;

public class Order {

    private int orderId;
    private String orderNo;
    private String customerUsername;
    private Date orderDate;
    private Date orderApproveDate;
    private String paymentMode;
    private int orderStatus;
    private int totalCost;
    private String paymentImg;
    private int paymentStatus;
    private Date statusDate;
    private String deliveryAddress;

    public Order() {}

    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }
    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getOrderApproveDate() {
        return orderApproveDate;
    }
    public void setOrderApproveDate(Date orderApproveDate) {
        this.orderApproveDate = orderApproveDate;
    }

    public String getPaymentMode() {
        return paymentMode;
    }
    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public int getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getTotalCost() {
        return totalCost;
    }
    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public String getPaymentImg() {
        return paymentImg;
    }
    public void setPaymentImg(String paymentImg) {
        this.paymentImg = paymentImg;
    }

    public int getPaymentStatus() {
        return paymentStatus;
    }
    public void setPaymentStatus(int paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Date getStatusDate() {
        return statusDate;
    }
    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
