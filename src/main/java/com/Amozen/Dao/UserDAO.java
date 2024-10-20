package com.Amozen.Dao;

import java.util.List;

import com.Amozen.model.User;


public interface UserDAO {
	
	void addUser(User user);

	User getUser(int userId);

	void updateUser(User user);

	void deleteUser(int userId);

	List<User> getAllUsers();
	
	void addUserSignup(User user);
	
	User getUserByUsernameAndPassword(String name, String password);

}
