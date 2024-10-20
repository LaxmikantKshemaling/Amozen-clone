package com.Amozen.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Amozen.Dao.UserDAO;
import com.Amozen.model.User;
import com.Amozen.util.DBConnectionUtil;

public class UserDAOImpl implements UserDAO {

    // Add a new user to the database
    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO users (name, email, password, phoneNo, role) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnectionUtil.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getPhoneNo());
            stmt.setString(5, user.getRole());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get a user by userId from the database
    @Override
    public User getUser(int userId) {
        String sql = "SELECT * FROM users WHERE userId = ?";
        User user = null;
        try (Connection conn = DBConnectionUtil.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("userId"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setPhoneNo(rs.getString("phoneNo"));
                user.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // Update user information in the database
    @Override
    public void updateUser(User user) {
        String sql = "UPDATE users SET name = ?, email = ?, password = ?, phoneNo = ?, role = ? WHERE userId = ?";
        try (Connection conn = DBConnectionUtil.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getPhoneNo());
            stmt.setString(5, user.getRole());
            stmt.setInt(6, user.getUserId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a user by userId from the database
    @Override
    public void deleteUser(int userId) {
        String sql = "DELETE FROM users WHERE userId = ?";
        try (Connection conn = DBConnectionUtil.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve all users from the database
    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        List<User> userList = new ArrayList<>();
        try (Connection conn = DBConnectionUtil.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("userId"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setPhoneNo(rs.getString("phoneNo"));
                user.setRole(rs.getString("role"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    // Add user signup
    @Override
    public void addUserSignup(User user) {
        String sql = "INSERT INTO users(name, email, password, phoneNo) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnectionUtil.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPhoneNo());
            
            String encryptedPassword = encrypt1(user.getPassword());
            stmt.setString(4, encryptedPassword);
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String encrypt1(String s) {
        StringBuilder t = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            c = (char) (c - 1); // Simple encryption logic
            t.append(c);
        }
        return t.toString();
    }

    public static String decrypt(String s) {
        StringBuilder t = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            c = (char) (c + 1); // Adjust decryption logic
            t.append(c);
        }
        return t.toString();
    }

    public User getUserByUsernameAndPassword(String name, String password) {
        User user = null;
        String sql = "SELECT * FROM users WHERE name = ? AND password = ?"; // Adjust table/column names as necessary
        
        try (Connection conn = DBConnectionUtil.getConnection(); // Implement this method to establish connection
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, password); // Consider using hashed passwords for security
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("id")); // Adjust based on your User class
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                // Set other user fields as necessary
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
        return user;
    }


    private User extractUserFromResultSet(ResultSet res) throws SQLException {
        User user = new User();
        user.setUserId(res.getInt("userId"));
        user.setName(res.getString("name"));
        user.setEmail(res.getString("email"));
        user.setPassword(res.getString("password"));
        user.setPhoneNo(res.getString("phoneNo"));
        user.setRole(res.getString("role"));
        return user;
    }

    // Use unique encryption method to avoid redundancy
    private String encrypt(String s) {
        StringBuilder t = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            c = (char) (c - 1);
            t.append(c);
        }
        return t.toString();
    }
}
