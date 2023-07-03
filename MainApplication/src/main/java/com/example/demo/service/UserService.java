package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.User;

public interface UserService {
	
	public User addUser(User user);

	public User getUserById(int userId);
	
	public List<User> getAllUsers();
	
	public void deleteUserById(int userId);

	public List<User> findByOrderByUserName();

}
