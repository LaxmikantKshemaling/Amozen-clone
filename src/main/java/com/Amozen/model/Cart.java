package com.Amozen.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Integer, CartItem> items; // Map holding cart items with itemId as key

    // Constructor initializing an empty cart
    public Cart() {
        this.items = new HashMap<>();
    }

    // Method to add a new item to the cart or update the quantity if it exists
    public void addItem(CartItem newItem) 
    {
        int itemId = newItem.getItemId();
        if (items.containsKey(itemId)) 
        {
            CartItem existingItem = items.get(itemId);
            existingItem.setQuantity(existingItem.getQuantity() + newItem.getQuantity()); // Increment quantity
        } 
        else 
        {
            items.put(itemId, newItem); // Add new item
        }
    }

    // Method to update the quantity of an existing item
    public void updateItem(int itemId, int quantity) {
        if (items.containsKey(itemId)) {
            if (quantity <= 0) {
                items.remove(itemId); // Remove if quantity is 0 or less
            } else {
                items.get(itemId).setQuantity(quantity); // Update item quantity
            }
        }
    }

    // Method to remove an item from the cart
    public void removeItem(int itemId) {
        items.remove(itemId); // Remove item from cart
    }

    // Getter to retrieve all items in the cart
    public Map<Integer, CartItem> getItems() {
        return items; // Return the items in the cart
    }

    // Method to clear the cart
    public void clear() {
        items.clear(); // Remove all items
    }

    // Method to calculate total amount
    public double calculateTotal() {
        return items.values().stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
    }
}
