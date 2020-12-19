package com.contactapp.service;

import java.util.Optional;

import com.contactapp.entity.Employee;

public interface EmployeeService {
	
	public Optional<Employee> getEmpById(Long id);

}
