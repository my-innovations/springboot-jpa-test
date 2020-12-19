package com.contactapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contactapp.entity.Employee;
import com.contactapp.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Optional<Employee> getEmpById(Long id) {
		return employeeRepository.findById(id);
	}

}
