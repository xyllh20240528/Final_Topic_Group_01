package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository empRepo;
	
	public Employee saveEmp(Employee emp) {
		return empRepo.save(emp);
	}
	
	public Employee findEmpById(Integer id) {
		Optional<Employee> optional = empRepo.findById(id);
		
		if(optional.isPresent()) {
			Employee result = optional.get();
			return result;
		}
		
		return null;
	}
	
	public void deleteEmpById(Integer id) {
		empRepo.deleteById(id);
	}
	
	public List<Employee> findAllEmp(){
		return empRepo.findAll();
	}

}
