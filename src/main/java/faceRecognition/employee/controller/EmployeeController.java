package faceRecognition.employee.controller;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import faceRecognition.company.controller.CompanyController;
import faceRecognition.employee.domain.Employee;
import faceRecognition.employee.service.interfaces.EmployeeService;
import faceRecognition.user.domain.ApiResponse;
import faceRecognition.user.domain.ResponseCode;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

	protected Logger logger = Logger.getLogger(CompanyController.class.getName());

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public ApiResponse uploadEmployeeImage(@RequestParam("file") MultipartFile file) {
		ApiResponse response = new ApiResponse();
		response.setMessage("Successfully uploaded");
		logger.info("EmployeeController uploadEmployeeImage() invoked: ");

		return response;
	}

	@RequestMapping(value = "/takePictures/{employeeId}", method = RequestMethod.PUT)
	public ApiResponse getEmployeeByIdSetTakenImages(@PathVariable int employeeId) {

		logger.info("company-controller getCompanyById() invoked: " + employeeId);
		Employee currentEmployee = employeeService.getById(employeeId);
		ApiResponse response = new ApiResponse();
		if (currentEmployee == null) {
			response.setMessage("No employee exist for this id");
			response.setResponseCode(ResponseCode.NO_DATA_FOUND);
			logger.info("Company-controller getCompanyById() Company is null ");

		} else {
			currentEmployee.setTakenPicture(true);
			employeeService.saveEmployee(currentEmployee);
			response.setResult(currentEmployee);
			response.setResponseCode(ResponseCode.OK);
			logger.info("Company-controller getCompanyById() found company : " + currentEmployee);

		}
		return response;
	}

	@RequestMapping(value = "/{employeeId}", method = RequestMethod.GET)
	public ApiResponse getEmployeeById(@PathVariable int employeeId) {

		logger.info("company-controller getCompanyById() invoked: " + employeeId);
		Employee currentEmployee = employeeService.getById(employeeId);
		ApiResponse response = new ApiResponse();
		if (currentEmployee == null) {
			response.setMessage("No employee exist for this id");
			response.setResponseCode(ResponseCode.NO_DATA_FOUND);
			logger.info("Company-controller getCompanyById() Company is null ");

		} else {
			response.setResult(currentEmployee);
			response.setResponseCode(ResponseCode.OK);
			logger.info("Company-controller getCompanyById() found company : " + currentEmployee);

		}
		return response;
	}

	@RequestMapping(value = "/byFirstOrLastName/{firstOrLastName}", method = RequestMethod.GET)
	public ApiResponse getEmployeesByFirstOrLastName(@PathVariable String firstOrLastName) {
		ApiResponse response = new ApiResponse();

		List<Employee> employees = employeeService.getByFirstOrLastName(firstOrLastName);

		//System.out.println("employees" + employees.get(0));
		response.setResult(employees);
		return response;
	}

}
