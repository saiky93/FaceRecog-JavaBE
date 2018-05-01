package faceRecognition.employee.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import faceRecognition.employee.domain.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
	List<Employee> findByCompanyId(String companyId);
}
