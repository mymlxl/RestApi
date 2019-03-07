package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.UserEntity;
import com.example.demo.vo.PagedResponse;

public interface UserService {
	
	public List<UserEntity> findAll();

	public void save(UserEntity theUserEntity);

	public UserEntity findById(int theId);

	public void deleteById(int theId);

}
