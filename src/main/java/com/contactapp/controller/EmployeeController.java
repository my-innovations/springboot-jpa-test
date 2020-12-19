package com.contactapp.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.contactapp.entity.Employee;
import com.contactapp.entity.ServiceResponse;
import com.contactapp.service.EmployeeService;

@RestController
@RequestMapping(value="/api/rest")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/emp")
	public Employee getMsg(@RequestParam Long id) {
		return employeeService.getEmpById(id).get();
	}
	
	@GetMapping("/queryService/{serviceName}")
	public ServiceResponse queryService(@PathVariable String serviceName, @RequestParam Long id) {
		Employee e = null;
		ServiceResponse serviceResponse = null;
		
		//Map<String, Long> uriVariables = new HashMap<>();
		//uriVariables.put("id", id);
		
		try {
			e = restTemplate.getForObject("http://localhost:8081/api/rest/"+ serviceName+"?id="+id, Employee.class);
			serviceResponse = new ServiceResponse();
			serviceResponse.setObj(e);
			serviceResponse.setReqId(UUID.randomUUID().toString());
			serviceResponse.setResStatus("success");
			
		} catch (RuntimeException e1) {
			e1.printStackTrace();
			serviceResponse = new ServiceResponse();
			serviceResponse.setResMsg(e1.getMessage());
			serviceResponse.setReqId(UUID.randomUUID().toString());
			serviceResponse.setResStatus("failed");
		}

		return serviceResponse;
	}

}
