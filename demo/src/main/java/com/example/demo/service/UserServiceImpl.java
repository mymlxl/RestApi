package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.UserEntity;

@Service
public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	
	@Autowired
	public UserServiceImpl(UserDao theUserDao) {
		userDao = theUserDao;
	}

	@Override
	@Transactional
	public List<UserEntity> findAll() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}

	@Override
	@Transactional
	public void save(UserEntity theUserEntity) {
		// TODO Auto-generated method stub
		userDao.save(theUserEntity);

	}

	@Override
	@Transactional
	public UserEntity findById(int theId) {
		// TODO Auto-generated method stub
		return userDao.findById(theId);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		// TODO Auto-generated method stub
		userDao.deleteById(theId);
	}



}
