package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.exception.UserNotAddedSuccessfully;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/addUser")
	public ResponseEntity<User> addUser(@RequestBody User user){
		ResponseEntity<?> resp=null;
		try {
			if(user!=null ||  user.getUserName()!=null ||user.getUserEmail()!=null) {
			User user1=userService.addUser(user);
			  resp=new ResponseEntity<User>(user1,HttpStatus.OK);
			}else {
				throw new UserNotAddedSuccessfully("User Not Added Successfully");
			}
			}catch(UserNotAddedSuccessfully usr){
			 resp=new ResponseEntity<> (usr.getMessage(),HttpStatus.NOT_FOUND);
			}
		return (ResponseEntity<User>) resp;
	}
	
	@PostMapping("/updateUserById/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable int userId,@RequestBody User user){
		ResponseEntity<User>  resp=null;
		user.setUserId(userId);
		resp=new ResponseEntity<>(userService.addUser(user),HttpStatus.OK);
		return resp;
	}
	
	@GetMapping("/getUserById/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable int userId){
		ResponseEntity<User>resp=null;
		try {
			User user1=userService.getUserById(userId);
			if(user1!=null) {
				resp=new ResponseEntity<User>(user1,HttpStatus.OK);
			}else {
				throw new UserNotFoundException("User Not Found");
			}
			
		}catch(UserNotFoundException usr) {
			resp=new ResponseEntity(usr.getMessage(),HttpStatus.NOT_FOUND);
		}
		return (ResponseEntity<User>) resp;
	}
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<User>>  getAllUsers(){
		ResponseEntity<List<User>> resp=null;
		try {
			resp=new ResponseEntity<List<User>>(userService.getAllUsers(),HttpStatus.OK);
		}catch(UserNotFoundException usr) {
			resp=new ResponseEntity(usr.getMessage(),HttpStatus.NOT_FOUND);
		}
		return (ResponseEntity<List<User>>) resp;
	}
	
	@DeleteMapping("/deleteUserById/{userId}")
	public String deleteUserById(@PathVariable int userId) {
		userService.deleteUserById(userId);
		return  "User Deleted Successfully";
	}
	
	@GetMapping("/sortByUserName")
	public List<User> findByOrderByUserName(){
		return userService.findByOrderByUserName();
	}
	}
