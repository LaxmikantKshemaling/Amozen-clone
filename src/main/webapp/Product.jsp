<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="com.Amozen.model.Products"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Amozen Clone - Products</title>
<link
	href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css"
	rel="stylesheet">
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f5f5f5;
}

.product-card {
	border: 1px solid #e5e7eb;
	transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.product-card:hover {
	box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.15);
	transform: scale(1.05);
}

.header-footer-bg {
	background-color: #131921;
}

.nav-text {
	color: #f9f9f9;
	font-size: 14px;
}

.footer-links a:hover {
	color: #febd69;
}

/* Login modal styles */
.login-modal {
	position: fixed;
	top: 0;
	right: 0;
	height: 65%;
	width: 100%;
	max-width: 350px;
	background-color: #fff;
	box-shadow: -2px 0 10px rgba(0, 0, 0, 0.2);
	transform: translateX(100%);
	transition: transform 0.3s ease-in-out;
	z-index: 9999;
}

.login-modal.active {
	transform: translateX(0);
}

.login-modal-header {
	background-color: #131921;
	color: white;
	padding: 16px;
	font-size: 18px;
}

.login-modal-body {
	padding: 50px;
}

.close-modal {
	cursor: pointer;
	position: absolute;
	top: 16px;
	right: 16px;
	color: white;
	font-size: 18px;
}

/* Overlay */
.overlay {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.6);
	visibility: hidden;
	opacity: 0;
	transition: visibility 0s, opacity 0.3s ease-in-out;
	z-index: 9998;
}

.overlay.active {
	visibility: visible;
	opacity: 1;
}
</style>
</head>
<body>
	<!-- Header -->
	<header class="header-footer-bg">
		<nav class="flex items-center justify-between p-4 max-w-7xl mx-auto">
			<div class="flex items-center space-x-4">
				<div class="nav-logo">
					<img
						src="https://media.geeksforgeeks.org/wp-content/uploads/20240326183545/amazon.png"
						alt="Amazon Logo" class="w-28">
				</div>
				<div class="location">
					<p class="text-sm text-gray-400">Deliver to</p>
					<p class="text-white">India</p>
				</div>
			</div>

			<!-- Search form -->
			<form method="GET" action="products">
				<input type="text" name="searchQuery" placeholder="Search Amozen"
					class="search-box px-2 py-1 border rounded" />
				<button type="submit"
					class="search-icon px-4 py-1 bg-yellow-500 text-white rounded">Search</button>
			</form>

			<div class="flex items-center space-x-6">
				<div class="account-option text-white cursor-pointer" id="signInBtn">
					<p>Sign In</p>
				</div>
				<div class="cart-option flex items-center text-white">
					<i class="fa-solid fa-cart-shopping mr-1"></i> Cart
				</div>
			</div>
		</nav>
	</header>

	<!-- Products Section -->
	<div class="bg-gray-100 py-16">
		<div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
			<h2 class="text-3xl font-bold mb-10 text-gray-900">Available
				Products</h2>
			<div
				class="grid grid-cols-1 gap-x-6 gap-y-10 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4">
				<!-- Dynamically Generated Products -->
				<%
				List<Products> allProducts = (List<Products>) request.getAttribute("allProducts");
				if (allProducts != null) {
					for (Products product : allProducts) {
				%>
				<a href="category?productId=<%=product.getProductId()%>"
					class="group block border rounded-lg overflow-hidden product-card bg-white shadow hover:shadow-lg transform transition-all duration-300">
					<div
						class="aspect-w-1 aspect-h-1 w-full bg-gray-200 group-hover:opacity-75">
						<img src="<%=request.getContextPath() + product.getImagePath()%>"
							alt="<%=product.getName()%>"
							class="w-full h-full object-cover object-center">
					</div>
					<div class="p-4">
						<h3 class="text-sm font-semibold text-gray-900"><%=product.getName()%></h3>
						<p class="mt-2 text-lg font-bold text-yellow-600">
							$<%=product.getPrice()%></p>
					</div>
				</a>
				<%
				}
				} else {
				%>
				<p class="text-center text-gray-500">No products available.</p>
				<%
				}
				%>
			</div>
		</div>
	</div>

	<!-- Footer -->
	<footer class="header-footer-bg py-8">
		<a href="#" class="block text-center text-white pb-4">Back to top</a>
		<div
			class="max-w-7xl mx-auto grid grid-cols-2 sm:grid-cols-4 gap-8 text-white">
			<ul>
				<p class="font-bold">Get to Know Us</p>
				<a href="#">Careers</a>
				<a href="#">Blog</a>
				<a href="#">About Amozen</a>
			</ul>
			<ul>
				<p class="font-bold">Make Money with Us</p>
				<a href="#">Sell products on Amozen</a>
			</ul>
		</div>
		<p class="text-center text-gray-400 mt-4">&copy; 2024 Amozen, Inc.</p>
	</footer>

<script>
    document.getElementById('signInBtn').addEventListener('click', function() {
        // Redirect to the Login.jsp page
        window.location.href = '<%= request.getContextPath() %>/Login.jsp';
    });
</script>


</body>
</html>
