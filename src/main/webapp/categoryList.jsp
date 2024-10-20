<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.Amozen.model.Category"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Amazon Clone</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
        }

        header {
            background-color: #131921;
            color: white;
        }

        footer {
            background-color: #232f3e;
            color: white;
            padding: 20px;
            font-size: 12px;
        }

        .category-item {
            transition: transform 0.2s;
        }

        .category-item:hover {
            transform: scale(1.05);
        }

        /* Styling for the cart section */
        .cart-option {
            position: relative;
        }

        .cart-item-count {
            position: absolute;
            top: -5px;
            right: -5px;
            background-color: red;
            border-radius: 50%;
            padding: 2px 6px;
            font-size: 12px;
            color: white;
        }

        .category-item .btn-cart {
            transition: background-color 0.3s ease;
        }

        .category-item .btn-cart:hover {
            background-color: #ff9900;
        }
    </style>
</head>

<body>
    <header>
        <nav class="flex justify-between items-center p-4">
            <div class="flex items-center">
                <div class="nav-logo">
                    <img src="https://media.geeksforgeeks.org/wp-content/uploads/20240326183545/amazon.png" alt="Amazon Logo" width="100">
                </div>
                <div class="location ml-4">
                    <p class="text-xs text-gray-400">Deliver to</p>
                    <p class="text-sm">India</p>
                </div>
            </div>

            <div class="flex items-center">
                <div class="account-option mr-4">
                    <p class="text-sm">Sign In</p>
                </div>
                <div class="cart-option flex items-center text-white">
                    <i class="fas fa-cart-shopping mr-1"></i>
                    Cart
                    <span class="cart-item-count">3</span> <!-- Dynamic count for cart items -->
                </div>
            </div>
        </nav>
    </header>

    <div class="category-container mx-auto max-w-7xl px-4 py-8 grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
        <%
        List<Category> categoryList = (List<Category>) request.getAttribute("categoryList");

        if (categoryList != null && !categoryList.isEmpty()) {
            for (Category category : categoryList) {
        %>
        <div class="category-item bg-white border rounded-lg p-4 shadow-lg hover:shadow-xl transition duration-300 ease-in-out">
            <img src="<%=request.getContextPath() + category.getImagePath()%>" alt="Image of <%=category.getName()%>" class="w-full h-48 object-cover rounded-md">
            <div class="category-item-details mt-4">
                <h3 class="text-lg font-bold"><%=category.getName()%></h3>
                <p class="text-sm text-gray-500"><%=category.getDescription()%></p>
            </div>
            <form action="cart" method="post" class="mt-4 flex items-center justify-between">
                <input type="number" name="quantity" value="1" min="1" class="w-16 border rounded px-2">
                <input type="hidden" name="itemId" value="<%=category.getCategoryId()%>">
                <input type="hidden" name="action" value="add">
                <button type="submit" class="btn-cart bg-yellow-400 text-white px-4 py-2 rounded hover:bg-yellow-500">Add to Cart</button>
            </form>
        </div>
        <%
            }
        } else {
        %>
        <p class="text-center col-span-3">No categories available.</p>
        <%
        }
        %>
    </div>

    <footer class="text-center">
        <a href="#" class="back-option">Back to top</a>

        <div class="footer-links grid grid-cols-2 sm:grid-cols-4 gap-4 p-4">
            <ul>
                <p>Get to Know Us</p>
                <li><a href="#" class="text-white">Careers</a></li>
                <li><a href="#" class="text-white">Blog</a></li>
                <li><a href="#" class="text-white">About Amazon</a></li>
                <li><a href="#" class="text-white">Investor Relations</a></li>
            </ul>
            <ul>
                <p>Make Money with Us</p>
                <li><a href="#" class="text-white">Sell products on Amazon</a></li>
                <li><a href="#" class="text-white">Become an Affiliate</a></li>
            </ul>
            <ul>
                <p>Amazon Payment Products</p>
                <li><a href="#" class="text-white">Amazon Business Card</a></li>
                <li><a href="#" class="text-white">Reload Your Balance</a></li>
            </ul>
            <ul>
                <p>Let Us Help You</p>
                <li><a href="#" class="text-white">Your Account</a></li>
                <li><a href="#" class="text-white">Returns & Replacements</a></li>
            </ul>
        </div>
    </footer>
</body>

</html>
