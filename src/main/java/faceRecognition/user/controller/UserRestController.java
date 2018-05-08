package faceRecognition.user.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import faceRecognition.company.domain.Company;
import faceRecognition.company.service.interfaces.CompanyService;
import faceRecognition.employee.domain.Employee;
import faceRecognition.employee.service.interfaces.EmployeeService;
import faceRecognition.user.domain.ApiResponse;
import faceRecognition.user.domain.CompanyRegister;
import faceRecognition.user.domain.ResponseCode;
import faceRecognition.user.domain.User;
import faceRecognition.user.domain.UserType;
import faceRecognition.user.service.interfaces.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping(value = "/user")
public class UserRestController {

	protected Logger logger = Logger.getLogger(UserRestController.class.getName());

	/**
	 * Autowire instances of the User service and the company service to be used in
	 * the controller
	 */
	@Autowired
	private UserService userService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private CompanyService companyService;

	/**
	 * Fetch users. So <code>http://.../user/</code> will find all the users
	 * 
	 * @return A non-null, non-empty set of users.
	 * 
	 *         Note: this is a secure api so to access this api you will have to
	 *         send the token in the header as a value for the authorization key and
	 *         you have the add the prefix Bearer before the token for example
	 *         "Bearer xxxxxuixxuyuxuxiix"
	 */

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ApiResponse getAllUsers() {
		logger.info("User-rest-controller getAllUsers() invoked: " + userService.getClass().getName());

		ApiResponse response = new ApiResponse();

		List<User> users = userService.getAllUsers();

		if (null == users || 0 == users.size()) {
			response.setMessage("There are no users exist");
			response.setResponseCode(ResponseCode.NO_DATA_FOUND);
		} else {
			response.setResponseCode(ResponseCode.OK);
			response.setResult(users);

		}
		return response;
	}

	/**
	 * Fetch a user with the specified id.
	 * 
	 * @param userId
	 *            A string.
	 * @return The user if found.
	 */

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public ApiResponse getUserById(@PathVariable int userId) {

		logger.info("user-rest-controller getUserById() invoked: " + userId);
		User user = userService.getById(userId);
		ApiResponse response = new ApiResponse();
		if (null == user) {
			response.setMessage("No user exist for this id");
			response.setResponseCode(ResponseCode.NO_DATA_FOUND);
			logger.info("user-rest-controller getUserById() user is null ");

		} else {
			response.setResult(user);
			response.setResponseCode(ResponseCode.OK);
			logger.info("user-rest-controller getUserById() found user : " + user);

		}
		return response;
	}

	/**
	 * Update an address record through the rest api
	 * 
	 * @param addressId
	 *            int, Address object
	 * 
	 * @return ApiResponse
	 * 
	 */

	@RequestMapping(value = "/company", method = RequestMethod.PUT)
	public ApiResponse updateCompany(@RequestBody Company company) {

		logger.info("Company-controller updateCompany() invoked: " + company + " and used the class "
				+ companyService.getClass().getName() + " to update the Company");

		int companyId = 0;

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = null;
		if (null != auth) {
			userName = auth.getName();
		}
		User user = null;
		if (null != userName) {

			user = userService.findByEmail(userName);

		}

		if (null != user)
			companyId = user.getCompanyId();

		ApiResponse response = new ApiResponse();
		if (companyId == 0) {
			logger.info("Unable to update. No company account logged in.");
			response.setMessage("Unable to update. No company account logged in");
			response.setResponseCode(ResponseCode.NO_DATA_FOUND);
			return response;
		}
		Company currentCompany = companyService.getById(companyId);
		if (null == currentCompany) {
			logger.info("Unable to update. The Company with the id " + companyId + "doesn't exist.");
			response.setMessage("Company doesn't exist");
			response.setResponseCode(ResponseCode.NO_DATA_FOUND);
		} else {
			currentCompany.setAddressLine1(company.getAddressLine1());
			currentCompany.setAddressLine2(company.getAddressLine2());
			currentCompany.setCity(company.getCity());
			currentCompany.setCountry(company.getCountry());
			currentCompany.setDescription(company.getDescription());
			currentCompany.seteIN(company.geteIN());
			currentCompany.setName(company.getName());
			currentCompany.setPhone(company.getPhone());
			currentCompany.setState(company.getState());
			currentCompany.setWebsite(company.getWebsite());
			currentCompany.setZipCode(company.getZipCode());

			companyService.saveCompany(currentCompany);
			response.setMessage("updated successfully");
			response.setResponseCode(ResponseCode.OK);

		}
		return response;

	}

	@RequestMapping(value = "/employeeImage/{employeeId}", method = RequestMethod.PUT)
	public ApiResponse updateEmployeeImage(@PathVariable int employeeId, @RequestBody String image) throws IOException {
		logger.info("User-rest-controller updateEmployeeImage() invoked: and used the class "
				+ employeeService.getClass().getName() + " to upload the employee image");

		ApiResponse response = new ApiResponse();

		if (image == null || null == employeeService.getById(employeeId)) {
			response.setMessage("No image uploaded");
			response.setResponseCode(ResponseCode.NO_DATA_FOUND);

		} else {
			Employee employee = employeeService.getById(employeeId);
			employee.setPicture(employeeService.getPictureFromBase64(employeeId, image));
			employeeService.saveEmployee(employee);
			response.setResponseCode(ResponseCode.OK);
			response.setResult("Success");
		}
		return response;
	}

	@RequestMapping(value = "/employee/{employeeId}", method = RequestMethod.PUT)
	public ApiResponse updateEmployee(@PathVariable int employeeId, @RequestBody Employee employee) throws IOException {
		logger.info("User-rest-controller updateEmployee() invoked: " + employee + " and used the class "
				+ employeeService.getClass().getName() + " to update the employee");

		ApiResponse response = new ApiResponse();

		Employee currentEmployee = employeeService.getById(employeeId);
		if (null == currentEmployee) {
			logger.info("Unable to update. The employee with the id " + employeeId + "doesn't exist.");
			response.setMessage("Employee doesn't exist");
			response.setResponseCode(ResponseCode.NO_DATA_FOUND);
		} else {
			if (employee.getPicture().equals(currentEmployee.getPicture()) && employee.getPicture().equals(null)) {
				System.out.println("Image is :" + employee.getPicture());
				System.out.println("Image is :" + currentEmployee.getPicture());
				currentEmployee.setPicture(employeeService.getPictureFromBase64(employeeId, employee.getPicture()));
			}
			currentEmployee.setFirstName(employee.getFirstName());
			currentEmployee.setLastName(employee.getLastName());
			currentEmployee.setPhone(employee.getPhone());
			currentEmployee.setPosition(employee.getPosition());
			currentEmployee.setAddressLine1(employee.getAddressLine1());
			currentEmployee.setAddressLine2(employee.getAddressLine2());
			currentEmployee.setCity(employee.getCity());
			currentEmployee.setState(employee.getState());
			currentEmployee.setCountry(employee.getCountry());
			currentEmployee.setZipCode(employee.getZipCode());

			employeeService.saveEmployee(currentEmployee);
			response.setMessage("updated successfully");
			response.setResponseCode(ResponseCode.OK);

		}
		return response;
	}

	@RequestMapping(value = "/employeeUserInfo/{userId}", method = RequestMethod.PUT)
	public ApiResponse updateEmployeeUserInfo(@PathVariable int userId, @RequestBody User user) {
		logger.info("User-rest-controller updateEmployeeUserInfo() invoked: " + user + " and used the class "
				+ userService.getClass().getName() + " to update the employee user info ");

		ApiResponse response = new ApiResponse();

		User currentUser = userService.getById(userId);
		if (null == currentUser) {
			logger.info("Unable to update. The user with the id " + userId + "doesn't exist.");
			response.setMessage("User doesn't exist");
			response.setResponseCode(ResponseCode.NO_DATA_FOUND);
		} else {
			currentUser.setSecurityQuestion1(user.getSecurityQuestion1());
			currentUser.setSecurityAnswer1(user.getSecurityAnswer1());
			currentUser.setSecurityQuestion2(user.getSecurityQuestion2());
			currentUser.setSecurityAnswer2(user.getSecurityAnswer2());
			currentUser.setSecurityQuestion3(user.getSecurityQuestion3());
			currentUser.setSecurityAnswer3(user.getSecurityAnswer3());

			if (currentUser.getPassword() != user.getPassword()) {
				currentUser.setPassword(user.getPassword());
				currentUser.setChangedPassword(true);
			}
			userService.saveUser(currentUser);
			response.setMessage("updated successfully");
			response.setResponseCode(ResponseCode.OK);

		}
		return response;
	}

	@RequestMapping(value = "/loggedCompany", method = RequestMethod.GET)
	public ApiResponse getLoggedCompany() {
		logger.info("company-controller getLoggedCompany() invoked: ");

		int companyId = 1;

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = null;
		if (null != auth) {
			userName = auth.getName();
		}
		User user = null;
		if (null != userName) {

			user = userService.findByEmail(userName);

		}

		if (null != user)
			companyId = user.getCompanyId();

		Company company = companyService.getById(companyId);
		ApiResponse response = new ApiResponse();
		if (company == null) {
			response.setMessage("No Company exist for this id");
			response.setResponseCode(ResponseCode.NO_DATA_FOUND);
			logger.info("Company-controller getCompanyById() Company is null ");

		} else {
			response.setResult(company);
			response.setResponseCode(ResponseCode.OK);
			logger.info("Company-controller getCompanyById() found company : " + company);

		}
		return response;
	}

	@RequestMapping(value = "/loggedEmployee", method = RequestMethod.GET)
	public ApiResponse getLoggedEmployee() {
		logger.info("company-controller getLoggedEmployee() invoked: ");

		int employeeId = 1;

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = null;
		if (null != auth) {
			userName = auth.getName();
		}
		User user = null;
		if (null != userName) {

			user = userService.findByEmail(userName);

		}

		if (null != user)
			employeeId = user.getEmployee();

		Employee employee = employeeService.getById(employeeId);
		ApiResponse response = new ApiResponse();
		if (employee == null) {
			response.setMessage("No Employee exist for this id");
			response.setResponseCode(ResponseCode.NO_DATA_FOUND);
			logger.info("User-controller getLoggedEmployee() Employee is null ");

		} else {
			response.setResult(employee);
			response.setResponseCode(ResponseCode.OK);
			logger.info("User-controller getLoggedEmployee() found employee : " + employee);

		}
		return response;
	}

	@RequestMapping(value = "/company/employee", method = RequestMethod.GET)
	public ApiResponse getAllEmployees() {
		logger.info("User-rest-controller getAllEmployees() invoked: " + employeeService.getClass().getName());

		String companyId = null;

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = null;
		if (null != auth) {
			userName = auth.getName();
		}
		User user = null;
		if (null != userName) {

			user = userService.findByEmail(userName);

		}

		if (null != user)
			companyId = Integer.toString(user.getCompanyId());

		ApiResponse response = new ApiResponse();
		List<CompanyRegister> employees = employeeService.findByCompanyIdAsCompanyWrapper(companyId);

		if (null == employees || 0 == employees.size()) {
			response.setMessage("There are no employees exist");
			response.setResponseCode(ResponseCode.NO_DATA_FOUND);
		} else {
			response.setResponseCode(ResponseCode.OK);
			response.setResult(employees);

		}
		return response;
	}

	/**
	 * @param username
	 * @param password
	 * @param response
	 * @return JSON contains token and user after success authentication.
	 * @throws IOException
	 */

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> login(@RequestBody User user, HttpServletResponse response)
			throws IOException {
		String token = null;
		User currentUser = userService.findByEmail(user.getEmail());
		Map<String, Object> tokenMap = new HashMap<String, Object>();
		if (currentUser != null && user.getPassword().equals(currentUser.getPassword())) {
			token = Jwts.builder().setSubject(currentUser.getEmail()).claim("roles", currentUser.getUserType())
					.setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secretkey").compact();
			tokenMap.put("token", token);
			tokenMap.put("user", currentUser);
			return new ResponseEntity<Map<String, Object>>(tokenMap, HttpStatus.OK);
		} else {
			tokenMap.put("token", null);
			return new ResponseEntity<Map<String, Object>>(tokenMap, HttpStatus.UNAUTHORIZED);
		}

	}

	/**
	 * Add new address record through the rest api
	 * 
	 * @param Address
	 *            object
	 * 
	 * @return ApiResponse
	 * 
	 */

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ApiResponse registerCompany(@RequestBody CompanyRegister companyWrapper) {

		logger.info("user-rest-controller registerCompany() invoked: " + companyWrapper + "and used the class "
				+ companyService.getClass().getName() + " to register the company");

		ApiResponse response = new ApiResponse();
		User user = userService.findByEmail(companyWrapper.getUser().getEmail());

		if (null != user) {
			response.setMessage("The email exist");
			response.setResponseCode(ResponseCode.EMAIL_ALREADY_EXIST);
			logger.info("Unable to create. User already exist");

		} else {

			int companyId = companyService.saveCompany(companyWrapper.getCompany());
			companyWrapper.getUser().setCompanyId(companyId);
			companyWrapper.getUser().setUserType(UserType.COMPANY);
			userService.saveUser(companyWrapper.getUser());

			response.setMessage("Registered");
			response.setResponseCode(ResponseCode.OK);
			response.setResult(companyWrapper);
		}
		return response;

	}

	@RequestMapping(value = "/employee", method = RequestMethod.POST)
	public ApiResponse addEmployee(@RequestBody CompanyRegister employeeWrapper) throws IOException {

		logger.info("user-rest-controller addEmployee() invoked: " + employeeWrapper + "and used the class "
				+ employeeService.getClass().getName() + " to register the employee");

		ApiResponse response = new ApiResponse();
		User user = null;
		if (null != employeeWrapper.getUser()) {
			user = userService.findByEmail(employeeWrapper.getUser().getEmail());

		}

		if (null != user) {
			response.setMessage("The email exist");
			response.setResponseCode(ResponseCode.EMAIL_ALREADY_EXIST);
			logger.info("Unable to create. User already exist");

		} else {

			int employeeId = employeeService.saveEmployeeAndReturnId(employeeWrapper.getEmployee());
			String password = userService.getPassword(employeeWrapper);
			employeeWrapper.getUser().setPassword(password);
			employeeWrapper.getUser().setEmployee(employeeId);
			employeeWrapper.getUser().setUserType(UserType.EMPLOYEE);
			userService.saveUser(employeeWrapper.getUser());

			response.setMessage("Registered");
			response.setResponseCode(ResponseCode.OK);
			response.setResult(employeeWrapper);
		}
		return response;

	}

	// @PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("/logged")
	public ApiResponse getLoggedUser() {
		ApiResponse response = new ApiResponse();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = null;
		if (null != auth) {
			userName = auth.getName();
		}
		User user = null;
		if (null != userName) {

			user = userService.findByEmail(userName);

		}

		if (null == auth || null == userName || null == user) {
			response.setMessage("No user registered");
			response.setResponseCode(ResponseCode.NO_DATA_FOUND);
		} else {
			response.setResult(user);
			response.setResponseCode(ResponseCode.OK);
		}
		return response;
	}

	@RequestMapping(value = "/loggedCompanyId", method = RequestMethod.GET)
	public ApiResponse getLoggedCompanyId() {

		ApiResponse response = new ApiResponse();
		response.setResult("1");
		return response;
	}

	@RequestMapping(value = "/email", method = RequestMethod.POST)
	public ApiResponse findByEmail(@RequestBody User user) {
		logger.info("User-rest-controller findByEmail() invoked: " + userService.getClass().getName());

		ApiResponse response = new ApiResponse();
		User currentUser = userService.findByEmail(user.getEmail());
		if (null == currentUser) {
			response.setMessage("There are no user exist");
			response.setResponseCode(ResponseCode.NO_DATA_FOUND);

		} else {
			response.setResponseCode(ResponseCode.OK);
			response.setResult(currentUser);

		}
		return response;

	}

	@RequestMapping(value = "/employee/{employeeId}", method = RequestMethod.DELETE)
	public ApiResponse deleteEmployee(@PathVariable int employeeId) {
		logger.info("address-rest-controller deleteAddress() invoked: " + employeeId);

		ApiResponse response = new ApiResponse();

		User user = userService.findByEmployeeId(employeeId);
		if (null == user) {
			logger.info("Unable to delete. User with id " + employeeId + " is not found.");
			response.setResponseCode(ResponseCode.NO_DATA_FOUND);
			response.setMessage("Unable to delete. User with id " + employeeId + " not found.");

		} else {
			userService.deleteUser(employeeId);
			employeeService.deleteEmployee(employeeId);
			response.setResponseCode(ResponseCode.OK);
			response.setMessage("Deleted successfully");

			logger.info("deleted.");

		}
		return response;
	}
	
	@RequestMapping(value = "/employee/{employeeId}", method = RequestMethod.GET)
	public ApiResponse getEmployeeUserEmailSpeech(@PathVariable int employeeId){
		ApiResponse response = new ApiResponse();
		
		List<User> user = userService.getAllUsers();
		response.setResult(user);
		return response;
	}
	
}
