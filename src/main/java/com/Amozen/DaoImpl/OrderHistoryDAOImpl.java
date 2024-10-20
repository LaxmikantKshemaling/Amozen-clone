package com.Amozen.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.Amozen.Dao.OrderHistoryDAO;
import com.Amozen.model.OrderHistory;
import com.Amozen.util.DBConnectionUtil;

public class OrderHistoryDAOImpl implements OrderHistoryDAO {

    @Override
    public void addOrderHistory(OrderHistory orderHistory) {
        String query = "INSERT INTO order_history (itemId, productId, itemName, price, totalAmount, status, modeOfPayment, orderDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, orderHistory.getItemId());
            ps.setInt(2, orderHistory.getProductId());
            ps.setString(3, orderHistory.getItemName());
            ps.setDouble(4, orderHistory.getPrice());
            ps.setDouble(5, orderHistory.getTotalAmount());
            ps.setString(6, orderHistory.getStatus());
            ps.setString(7, orderHistory.getModeOfPayment());
            ps.setDate(8, new java.sql.Date(orderHistory.getOrderDate().getTime()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OrderHistory getOrderHistory(int orderHistoryId) {
        OrderHistory orderHistory = null;
        String query = "SELECT * FROM order_history WHERE orderHistoryId = ?";
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, orderHistoryId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                orderHistory = new OrderHistory();
                orderHistory.setOrderHistoryId(rs.getInt("orderHistoryId"));
                orderHistory.setItemId(rs.getInt("itemId"));
                orderHistory.setProductId(rs.getInt("productId"));
                orderHistory.setItemName(rs.getString("itemName"));
                orderHistory.setPrice(rs.getDouble("price"));
                orderHistory.setTotalAmount(rs.getDouble("totalAmount"));
                orderHistory.setStatus(rs.getString("status"));
                orderHistory.setModeOfPayment(rs.getString("modeOfPayment"));
                orderHistory.setOrderDate(rs.getDate("orderDate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistory;
    }

    @Override
    public void updateOrderHistory(OrderHistory orderHistory) {
        String query = "UPDATE order_history SET itemId = ?, productId = ?, itemName = ?, price = ?, totalAmount = ?, status = ?, modeOfPayment = ?, orderDate = ? WHERE orderHistoryId = ?";
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, orderHistory.getItemId());
            ps.setInt(2, orderHistory.getProductId());
            ps.setString(3, orderHistory.getItemName());
            ps.setDouble(4, orderHistory.getPrice());
            ps.setDouble(5, orderHistory.getTotalAmount());
            ps.setString(6, orderHistory.getStatus());
            ps.setString(7, orderHistory.getModeOfPayment());
            ps.setDate(8, new java.sql.Date(orderHistory.getOrderDate().getTime()));
            ps.setInt(9, orderHistory.getOrderHistoryId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrderHistory(int orderHistoryId) {
        String query = "DELETE FROM order_history WHERE orderHistoryId = ?";
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, orderHistoryId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OrderHistory> getAllOrderHistories() {
        List<OrderHistory> orderHistoryList = new ArrayList<>();
        String query = "SELECT * FROM order_history";
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                OrderHistory orderHistory = new OrderHistory();
                orderHistory.setOrderHistoryId(rs.getInt("orderHistoryId"));
                orderHistory.setItemId(rs.getInt("itemId"));
                orderHistory.setProductId(rs.getInt("productId"));
                orderHistory.setItemName(rs.getString("itemName"));
                orderHistory.setPrice(rs.getDouble("price"));
                orderHistory.setTotalAmount(rs.getDouble("totalAmount"));
                orderHistory.setStatus(rs.getString("status"));
                orderHistory.setModeOfPayment(rs.getString("modeOfPayment"));
                orderHistory.setOrderDate(rs.getDate("orderDate"));
                orderHistoryList.add(orderHistory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistoryList;
    }
}
