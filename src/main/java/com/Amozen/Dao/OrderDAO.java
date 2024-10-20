package com.Amozen.Dao;

import java.util.List;
import com.Amozen.model.Order;

public interface OrderDAO {
    int addOrder(Order order);
    Order getOrder(int orderId);
    void updateOrder(Order order);
    void deleteOrder(int orderId);
    List<Order> getAllOrders();
}
