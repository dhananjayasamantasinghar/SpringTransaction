package com.h2.repo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.h2.model.User;

@Repository
public class UserRepo {

	@Autowired
	private SessionFactory sessionFactory;

	public User save(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(user);
		System.out.println("Saved in User Table");
		return user;
	}

}
