<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="com.Amozen.model.Order" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Confirmation</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            margin-top: 50px;
        }
        .order-summary {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        .alert-success-custom {
            background-color: #d4edda;
            color: #155724;
            border-color: #c3e6cb;
            padding: 10px;
            border-radius: 5px;
        }
    </style>
</head>
<body>

<div class="container">
    <h1 class="text-center">Order Confirmation</h1>
    <div class="order-summary">
        <h4>Thank you for your order!</h4>
        <p>Your order has been placed successfully. Below are the details:</p>

        <!-- Display order details if available -->
        <%
            // Assuming orderList is set in the request scope
            List<Order> orderList = (List<Order>) request.getAttribute("orderList");
            if (orderList != null && !orderList.isEmpty()) {
        %>
            <ul class="list-group">
                <%
                    for (Order order : orderList) {
                %>
                    <li class="list-group-item">
                      <p><strong>Item Name:</strong> <%=order.getOrderId() %></p>
                      <p><strong>Item Name:</strong> <%=order.getUserId() %></p>
                      <p><strong>Item Name:</strong> <%=order.getProductId() %></p>
                    
      
                        <p><strong>Total Amount:</strong> <%= order.getTotalAmount() %> </p>
                       <p> <strong>Payment Method:</strong> <%= order.getModeOfPayment() %> </p>
                       <p> <strong>Delivery Address:</strong> <%= order.getAddress() %> </p>
                       <p> <strong>Phone No:</strong> <%= order.getPhoneNo() %></p>
                    </li>
                <%
                    }
                %>
            </ul>

            <!-- Success message -->
            <div id="successAlert" class="alert-success-custom" style="display: block;">
                Payment Completed! Your order will arrive soon.
            </div>
        <%
            } else {
        %>
            <!-- If no orders were found -->
            <div class="alert alert-warning" role="alert">
                No order found!
            </div>
        <%
            }
        %>

        <a href="home.jsp" class="btn btn-primary mt-3">Continue Shopping</a>
    </div>
</div>

<script>
    // Automatically hide the success alert after a few seconds
    setTimeout(function() {
        var alert = document.getElementById("successAlert");
        if (alert) {
            alert.style.display = 'none'; // Hide the alert
        }
    }, 5000); // Adjust the time in milliseconds (5000 = 5 seconds)
</script>

</body>
</html>
