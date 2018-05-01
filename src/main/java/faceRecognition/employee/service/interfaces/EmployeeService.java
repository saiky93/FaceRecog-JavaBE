package faceRecognition.employee.service.interfaces;

import java.io.IOException;
import java.util.List;

import faceRecognition.employee.domain.Employee;
import faceRecognition.user.domain.CompanyRegister;

public interface EmployeeService {
	List<CompanyRegister> findByCompanyIdAsCompanyWrapper(String companyId);

	Employee getById(int id);

	void saveEmployee(Employee employee);

	int saveEmployeeAndReturnId(Employee employee) throws IOException;

	void deleteEmployee(int employeeId);

	String getPictureFromBase64(int employeeId, String base64Image) throws IOException;

	List<Employee> getByFirstOrLastName(String firstOrLastName);
}
