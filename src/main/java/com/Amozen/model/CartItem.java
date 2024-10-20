package com.Amozen.model;

public class CartItem {
    private int itemId; // Item identifier
    private int productId; // Product identifier
    private String itemName; // Name of the item
    private int quantity; // Quantity of the item
    private double price; // Price of the item
    private String imagePath; // Path to the item's image

    // Parameterized constructor
    public CartItem(int itemId, int productId, String itemName, int quantity, double price, String imagePath) {
        this.itemId = itemId;
        this.productId = productId; // Initialize productId
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.imagePath = imagePath; // Initialize imagePath
    }

    // Getters and Setters
    public int getItemId() {
        return itemId;
    }

    public int getProductId() {
        return productId; // Getter for productId
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getImagePath() {
        return imagePath; // Getter for imagePath
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath; // Setter for imagePath
    }

    // Overriding toString for better display
    @Override
    public String toString() {
        return "CartItem [itemId=" + itemId + ", productId=" + productId + ", itemName=" + itemName + 
               ", quantity=" + quantity + ", price=" + price + ", imagePath=" + imagePath + "]";
    }
}
