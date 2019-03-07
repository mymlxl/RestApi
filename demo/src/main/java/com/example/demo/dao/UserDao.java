package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.UserEntity;

public interface UserDao {
	
	public List<UserEntity> findAll();
	
	public UserEntity findById(int theId);
	
	public void save(UserEntity theUserEntity);
	
	public void deleteById(int theId);
	

}
