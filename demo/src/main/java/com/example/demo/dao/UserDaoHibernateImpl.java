package com.example.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.UserEntity;

@Repository
public class UserDaoHibernateImpl implements UserDao {

	private EntityManager entityManager;
	
	@Autowired
	public UserDaoHibernateImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<UserEntity> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<UserEntity> theQuery = 
				currentSession.createQuery("from UserEntity", UserEntity.class);
		List<UserEntity> users = theQuery.getResultList();
		
		return users;
	}

	@Override
	public UserEntity findById(int theId) {
		Session currentSession = entityManager.unwrap(Session.class);
		UserEntity theUserEntity =
				currentSession.get(UserEntity.class, theId);
		
		return theUserEntity;
	}

	@Override
	public void save(UserEntity theUserEntity) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(theUserEntity);
		
	}

	@Override
	public void deleteById(int theId) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query theQuery = 
				currentSession.createQuery
				("delete from UserEntity where id=:userId");
		theQuery.setParameter("userId", theId);
		theQuery.executeUpdate();
		
	}

}
