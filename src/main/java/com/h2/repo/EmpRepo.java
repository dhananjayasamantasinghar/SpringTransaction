package com.h2.repo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.h2.model.Employee;

@Repository
public class EmpRepo {

	@Autowired
	private SessionFactory sessionFactory;

	public Employee save(Employee employee) {
		Session session = sessionFactory.getCurrentSession();
		session.save(employee);
		System.out.println("Saved in Employee Table");
		return employee;
	}
}
