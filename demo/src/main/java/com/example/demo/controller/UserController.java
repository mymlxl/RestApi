package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import com.example.demo.vo.PagedResponse;

@RestController
@RequestMapping("/api")
public class UserController {
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService theUserService) {
		userService = theUserService;
	}
	
	@GetMapping("/users")
	public List<UserEntity> findAll() {
		return userService.findAll();
	}

	@GetMapping("/users/{userId}")
	public UserEntity getUser(@PathVariable int userId) {
		
		UserEntity theUserEntity = userService.findById(userId);
		
		if (theUserEntity == null) {
			throw new RuntimeException("user id not found - "+userId);
		}
		
		return theUserEntity;
	}
	
	@PostMapping("/users")
	public UserEntity addUser(@RequestBody UserEntity theUserEntity) {
		theUserEntity.setId(0);
		userService.save(theUserEntity);
		return theUserEntity;
	}
	
	@PutMapping("/users/{id}")
	public UserEntity updateUser(@PathVariable("id") int id, @RequestBody UserEntity theUserEntity) {
		UserEntity currentUserEntity = userService.findById(id);
		if (currentUserEntity == null) {
			throw new RuntimeException("user id not found - "+id);
		}
		userService.save(theUserEntity);
		return theUserEntity;
	}
	
	@DeleteMapping("/users/{userId}")
	public String deleteUser(@PathVariable int userId) {
		UserEntity tempUserEntity = userService.findById(userId);
		if (tempUserEntity == null) {
			throw new RuntimeException("user id not found - "+userId);
		}
		
		userService.deleteById(userId);
		return "Deleted user id - "+userId;
	}
	
//	@GetMapping("/users")
//	public ResponseEntity<PagedResponse<UserEntity>> getUserPagenation(@RequestParam(required = false, defaultValue = "0") Integer pageNo,
//														   @RequestParam(required = false, defaultValue = "5") Integer rows) {
//
//		PagedResponse<UserEntity> users = userService.findPaginated(pageNo, rows);
//		if (users.isEmpty()) {
//			throw new RuntimeException("user id not found");
//		}
//		return new ResponseEntity<PagedResponse<UserEntity>>(users, HttpStatus.OK);
//
//	}
}

