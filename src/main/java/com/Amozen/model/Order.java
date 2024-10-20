package com.Amozen.model;

import java.util.Date;

public class Order {
    private int orderId;
    private int userId;
    private int productId;
    private double totalAmount;
    private String modeOfPayment; // Changed from enum to String
    private Date orderDate;
    private String address; // Added address
    private String phoneNo; // Added phone number
    private String status; // Added status
    
    public Order() {
        // Default constructor
    }
    
    public Order(int orderId, int userId, int productId, double totalAmount, String modeOfPayment, Date orderDate,
                 String address, String phoneNo, String status) {
        this.orderId = orderId;
        this.userId = userId;
        this.productId = productId;
        this.totalAmount = totalAmount;
        this.modeOfPayment = modeOfPayment;
        this.orderDate = orderDate;
        this.address = address;
        this.phoneNo = phoneNo;
        this.status = status;
    }

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getModeOfPayment() {
        return modeOfPayment;
    }

    public void setModeOfPayment(String modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getStatus() {
        return status; // Getter for status
    }

    public void setStatus(String status) { // Setter for status
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order [orderId=" + orderId + ", userId=" + userId + ", productId=" + productId +
               ", totalAmount=" + totalAmount + ", modeOfPayment=" + modeOfPayment + 
               ", orderDate=" + orderDate + ", address=" + address + 
               ", phoneNo=" + phoneNo + ", status=" + status + "]";
    }
}
