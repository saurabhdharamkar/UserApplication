package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.exception.UserNotAddedSuccessfully;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public User addUser(User user) {
		User user1=  userRepo.save(user);
		if(user1!=null) {
			return user1;
	}else {
		throw new UserNotAddedSuccessfully("User Not Added Successfully");
	}
		
	}

	@Override
	public User getUserById(int userId)  {
		Optional<User> user1=userRepo.findById(userId);
		if(user1.isPresent()) {
			return user1.get();
		}else {
			throw new UserNotFoundException("User Not Found");
		}
		
	}

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public void deleteUserById(int userId) {
		userRepo.deleteById(userId);
		
	}

	@Override
	public List<User> findByOrderByUserName() {
		return userRepo.findByOrderByUserName();
	}


}

