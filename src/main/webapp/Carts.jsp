<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.Amozen.model.Cart"%>
<%@ page import="com.Amozen.model.CartItem"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Shopping Cart</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <style>
        /* Additional styles for the cart modal */
        .cart-modal {
            border: none;
            border-radius: 0.5rem;
            background-color: white;
            max-height: 98vh;
            overflow-y: auto;
        }

        /* Header Styles */
        header {
            top: 0;
            color: #2d3748;
            padding: 0.5rem 0; /* Reduced padding for a smaller header height */
        }

        /* Push main content down */
        .main-content {
            padding-top: 5rem; /* Space for header */
            padding-bottom: 4rem; /* Space for footer */
            background-color: white; /* White background */
        }

        /* Style for the cart item */
        .cart-item {
            padding: 1.5rem 0;
            font-size: 1.125rem;
            width: 80%; /* Increase width of the cart item */
            margin: 0 auto; /* Center the cart item */
        }

        .cart-item img {
            border-radius: 0.375rem; /* Rounded corners for images */
            width: 100%; /* Ensure the image takes full width */
        }

        /* Additional styles... */
    </style>
</head>
<body class="bg-gray-100 font-sans leading-normal tracking-normal">
    <!-- Header -->
    <header class="bg-white">
        <div class="max-w-7xl mx-auto px-4">
            <div class="flex items-center">
                <h1 class="text-2xl font-bold text-gray-900">Amozen Shopping Cart</h1>
            </div>
        </div>
    </header>

    <!-- Main Content -->
    <div class="main-content">
        <!-- Shopping Cart Modal -->
        <div class="relative z-10 mt-6" aria-labelledby="slide-over-title" role="dialog" aria-modal="true">
            <div class="fixed inset-0 overlay" aria-hidden="true"></div>

            <div class="fixed inset-0 overflow-hidden">
                <div class="absolute inset-0 overflow-hidden">
                    <div class="pointer-events-none fixed inset-y-0 right-0 flex max-w-full pl-10">
                        <div class="pointer-events-auto w-screen max-w-md cart-modal">
                            <!-- Cart Modal Design -->
                            <div class="flex h-full flex-col bg-white shadow-xl rounded-lg">
                                <div class="flex-1 overflow-y-auto px-4 py-6 sm:px-6">
                                    <div class="flex items-start justify-between">
                                        <h2 class="text-lg font-medium text-gray-900" id="slide-over-title">Shopping Cart</h2>
                                        <div class="ml-3 flex h-7 items-center">
                                            <button type="button" class="relative -m-2 p-2 text-gray-400 hover:text-gray-500 close-button">
                                                <span class="sr-only">Close panel</span>
                                                <svg class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true">
                                                    <path stroke-linecap="round" stroke-linejoin="round" d="M6 18 18 6M6 6l12 12" />
                                                </svg>
                                            </button>
                                        </div>
                                    </div>

                                    <div class="mt-8">
                                        <div class="flow-root">
                                            <ul role="list" class="-my-6">
                                                <%-- JSP Logic to Display Cart Items --%>
                                                <%
                                                Cart cart = (Cart) session.getAttribute("cart");
                                                if (cart != null && !cart.getItems().isEmpty()) {
                                                    double totalAmount = cart.calculateTotal();
                                                    double taxRate = 0.08;
                                                    double taxAmount = totalAmount * taxRate;
                                                    double grandTotal = totalAmount + taxAmount;

                                                    for (CartItem item : cart.getItems().values()) {
                                                %>
                                                <li class="cart-item flex">
                                                    <div class="h-28 w-28 flex-shrink-0 overflow-hidden rounded-md">
                                                        <img src="<%=request.getContextPath() + item.getImagePath()%>" alt="Image of <%=item.getItemName()%>" class="h-full w-full object-cover object-center">
                                                    </div>

                                                    <div class="ml-4 flex flex-1 flex-col">
                                                        <div>
                                                            <div class="flex justify-between text-base font-medium text-gray-900">
                                                                <h3><%=item.getItemName()%></h3>
                                                                <p class="ml-4">$<%=item.getPrice() * item.getQuantity()%></p>
                                                            </div>
                                                            <p class="mt-1 text-sm text-gray-500">Qty: <%=item.getQuantity()%></p>
                                                        </div>
                                                        <div class="flex flex-1 items-end justify-between text-sm">
                                                            <form action="cart" method="post" class="mr-2">
                                                                <input type="hidden" name="itemId" value="<%=item.getItemId()%>"> 
                                                                <input type="number" name="quantity" value="<%=item.getQuantity()%>" min="1" class="w-12 text-center border">
                                                                <button type="submit" name="action" value="update" class="ml-2 text-indigo-600 hover:text-indigo-500">Update</button>
                                                            </form>
                                                            <form action="cart" method="post">
                                                                <input type="hidden" name="itemId" value="<%=item.getItemId()%>">
                                                                <button type="submit" name="action" value="remove" class="text-red-600 hover:text-red-500">Remove</button>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </li>
                                                <%
                                                    }
                                                %>
                                            </ul>
                                            <div class="px-4 py-6 sm:px-6">
                                                <div class="flex justify-between text-base font-medium text-gray-900">
                                                    <p>Subtotal</p>
                                                    <p>$<%=String.format("%.2f", totalAmount)%></p>
                                                </div>
                                                <div class="mt-6 flex justify-between">
                                                    <a href="Checkout.jsp" class="flex items-center justify-center rounded-md bg-indigo-600 px-6 py-3 text-base font-medium text-white shadow-sm hover:bg-indigo-700">Checkout</a>
                                                    <a href="category?productId=<%=session.getAttribute("productId")%>" class="flex items-center justify-center rounded-md bg-white px-6 py-3 text-base font-medium text-black font-bold shadow-sm hover:bg-gray-200">Add More</a>
                                                </div>
                                            </div>
                                        </div>
                                        <% } else { %>
                                        <h2 class="text-center font-semibold text-xl mt-10">Your Cart is Empty!</h2>
                                        <div class="mt-6 flex justify-center">
                                        </div>
                                        <% } %>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
