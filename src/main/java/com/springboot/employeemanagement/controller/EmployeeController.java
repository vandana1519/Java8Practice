package com.springboot.employeemanagement.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.employeemanagement.model.Employee;
import com.springboot.employeemanagement.service.EmployeeService;
import com.springboot.employeemanagement.service.impl.EmployeeServiceImpl;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;

	@PostMapping()
	public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
		employeeService.addEmployee(employee);
		return new ResponseEntity<>("Employee details added successfully", HttpStatus.OK);
	}

	@GetMapping("/joiningDate")
	public List<Employee> getEmployeeByJoiningDate(@RequestParam String joiningDate) {
		return employeeServiceImpl.getEmployeeByJoiningDate(joiningDate);
	}

	@GetMapping("/joiningDateTime")
	public List<Employee> getEmployeeByJoiningDateTime(@RequestParam String joiningDate) {
		return employeeServiceImpl.getEmployeeByJoiningDateTime(joiningDate);
	}

	@GetMapping("/dnaCandidate")
	public List<Employee> getDNACandidate() {
		return employeeServiceImpl.getDNACandidate();
	}

	@GetMapping("/printNumbers")
	public List<Integer> printNumber() {
		return employeeService.printNumber();

	}

	@GetMapping("/category/isManager")
	public Map<Boolean, List<Employee>> getEmployeeByCategory() {
		return employeeServiceImpl.getEmployeeByCategory();
	}

	@GetMapping("/nextWeekBusinessDays")
	public Stream<LocalDate> getNextWeekBusinessDaysCount() {
		return EmployeeService.getNextWeekBusinessDays();
	}

}
