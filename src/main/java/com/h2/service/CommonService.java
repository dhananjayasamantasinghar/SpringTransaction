package com.h2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.h2.exception.AppGenericException;
import com.h2.model.Employee;
import com.h2.model.User;
import com.h2.repo.EmpRepo;
import com.h2.repo.UserRepo;

@Service
@Transactional(rollbackFor = AppGenericException.class)
public class CommonService {

	@Autowired
	private EmpRepo empRepo;

	@Autowired
	private UserRepo userRepo;

	public String save() {

		User user = new User();
		user.setName("Dhananjaya");
		user.setAddress("Rkl");

		Employee employee = new Employee();
		employee.setName(user.getName());
		employee.setAddress(user.getAddress());

		save(user, employee);

		return "Saved Successfully!!";

	}

	public String saveWithError() {

		User user = new User();
		user.setName("Dhananjaya");
		user.setAddress("Rkl");

		Employee employee = new Employee();
		employee.setName(user.getName());
		employee.setAddress(user.getAddress());

		saveWithError(user, employee);

		return "Saved Successfully!!";

	}

	public void save(User user, Employee employee) {
		userRepo.save(user);
		empRepo.save(employee);
	}

	public void saveWithError(User user, Employee employee) {
		userRepo.save(user);
		throwEx();
		empRepo.save(employee);
	}

	private void throwEx() {
		System.out.println("Exception Occured");
		throw new AppGenericException("XXXXX");
	}

}
