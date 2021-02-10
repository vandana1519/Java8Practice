package com.springboot.employeemanagement.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.springboot.employeemanagement.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{
	
	List<Employee> findByJoiningDateContains(LocalDateTime date);
	
	@Query(value = "SELECT * FROM employee e WHERE date(e.joining_date) = :joiningDate order by e.joining_date desc", nativeQuery=true)
	List<Employee> getByJoiningDate(@Param("joiningDate") String joiningDate);
	
	@Query(value = "SELECT * FROM employee e WHERE e.joining_date = :joiningDate order by e.joining_date", nativeQuery=true)
	List<Employee> getByJoiningDateTime(@Param("joiningDate") String joiningDate);

}
