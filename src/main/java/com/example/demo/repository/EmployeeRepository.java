package com.example.demo.repository;

<<<<<<< HEAD
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	List<Employee> findByCompanyId(Long companyId);
	Page<Employee> findByProjects_Id(Long projectId, Pageable pageable);
	
=======
import com.example.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	List<Employee> findByCompanyId(Long companyId);
>>>>>>> e6be921d50ff3e445bc43a1919d947bdf03c51d4
}