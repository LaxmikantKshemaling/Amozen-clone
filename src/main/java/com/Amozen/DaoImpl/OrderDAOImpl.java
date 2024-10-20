package com.Amozen.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Amozen.Dao.OrderDAO;
import com.Amozen.model.Order;
import com.Amozen.util.DBConnectionUtil;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public int addOrder(Order order) {
        int generatedId = 0; // Initialize orderId
        String sql = "INSERT INTO orders (userId, productId, modeOfPayment, address, phoneNo, orderDate, status, totalAmount) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            statement.setInt(1, order.getUserId());
            statement.setInt(2, order.getProductId());
            statement.setString(3, order.getModeOfPayment());
            statement.setString(4, order.getAddress());
            statement.setString(5, order.getPhoneNo());
            statement.setTimestamp(6, new java.sql.Timestamp(order.getOrderDate().getTime())); // Correctly set the timestamp
            statement.setString(7, order.getStatus());
            statement.setDouble(8, order.getTotalAmount());

            // Execute insert and get generated keys
            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                    	generatedId = generatedKeys.getInt(1); // Get the generated order ID
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions here, possibly log them
        }
        return generatedId; // Return the generated order ID
    }

    @Override
    public Order getOrder(int orderId) {
        Order order = null;
        String sql = "SELECT * FROM orders WHERE orderId = ?"; // Ensure the column name is correct
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                order = new Order();
                order.setOrderId(resultSet.getInt("orderId"));
                order.setUserId(resultSet.getInt("userId"));
                order.setProductId(resultSet.getInt("productId"));
                order.setTotalAmount(resultSet.getDouble("totalAmount"));
                order.setModeOfPayment(resultSet.getString("modeOfPayment"));
                order.setOrderDate(resultSet.getTimestamp("orderDate")); // Ensure correct column name
                order.setAddress(resultSet.getString("address"));
                order.setPhoneNo(resultSet.getString("phoneNo"));
                order.setStatus(resultSet.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle error
        }
        return order;
    }

    @Override
    public void updateOrder(Order order) {
        String sql = "UPDATE orders SET userId = ?, productId = ?, totalAmount = ?, modeOfPayment = ?, orderDate = ?, address = ?, phoneNo = ?, status = ? WHERE orderId = ?";
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, order.getUserId());
            statement.setInt(2, order.getProductId());
            statement.setDouble(3, order.getTotalAmount());
            statement.setString(4, order.getModeOfPayment());
            statement.setTimestamp(5, new java.sql.Timestamp(order.getOrderDate().getTime())); // Use Timestamp
            statement.setString(6, order.getAddress());
            statement.setString(7, order.getPhoneNo());
            statement.setString(8, order.getStatus());
            statement.setInt(9, order.getOrderId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle error
        }
    }

    @Override
    public void deleteOrder(int orderId) {
        String sql = "DELETE FROM orders WHERE orderId = ?";
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle error
        }
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Order order = new Order();
                order.setOrderId(resultSet.getInt("orderId"));
                order.setUserId(resultSet.getInt("userId"));
                order.setProductId(resultSet.getInt("productId"));
                order.setTotalAmount(resultSet.getDouble("totalAmount"));
                order.setModeOfPayment(resultSet.getString("modeOfPayment"));
                order.setOrderDate(resultSet.getTimestamp("orderDate")); // Use Timestamp
                order.setAddress(resultSet.getString("address"));
                order.setPhoneNo(resultSet.getString("phoneNo"));
                order.setStatus(resultSet.getString("status"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle error
        }
        return orders;
    }
}
