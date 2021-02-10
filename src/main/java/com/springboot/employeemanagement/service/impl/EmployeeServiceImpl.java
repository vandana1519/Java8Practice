package com.springboot.employeemanagement.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.employeemanagement.model.Employee;
import com.springboot.employeemanagement.repository.EmployeeRepository;
import com.springboot.employeemanagement.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void addEmployee(Employee employee) {
		employee.setJoiningDate(LocalDateTime.now());
		employeeRepository.save(employee);
	}

	public List<Employee> getEmployeeByJoiningDate(String joiningDate) {
		return employeeRepository.getByJoiningDate(joiningDate).parallelStream().limit(5).collect(Collectors.toList());
	}

	public List<Employee> getEmployeeByJoiningDateTime(String joiningDate) {
		return employeeRepository.getByJoiningDateTime(joiningDate).parallelStream().collect(Collectors.toList());
	}

	public List<Employee> getDNACandidate() {
		return employeeRepository.findAll().stream().filter(employee -> employee.getExperience() > 7)
				.collect(Collectors.toCollection(() -> new ArrayList<Employee>()));
	}

	public Map<Boolean, List<Employee>> getEmployeeByCategory() {
		 return	 employeeRepository.findAll().stream().collect(Collectors.partitioningBy(Employee::getIsManager));
	}

}
