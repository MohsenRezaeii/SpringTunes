package com.mohsen.springtunes.dao;

import com.mohsen.springtunes.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDAO {

	private EntityManager entityManager;

	public UserDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public User findByUserName(String theUserName) {

		TypedQuery<User> typedQuery = entityManager.createQuery("from User where userName=:uName", User.class);
		typedQuery.setParameter("uName", theUserName);

		User user = null;
		try {
			user = typedQuery.getSingleResult();
		} catch (Exception e) {
			user = null;
		}

		return user;
	}

}
