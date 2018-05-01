package faceRecognition.company.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import faceRecognition.company.domain.Company;
import faceRecognition.company.service.interfaces.CompanyService;
import faceRecognition.user.domain.ApiResponse;
import faceRecognition.user.domain.ResponseCode;
import faceRecognition.user.domain.User;
import faceRecognition.user.service.interfaces.UserService;

@RestController
@RequestMapping(value = "/company")
public class CompanyController {
	protected Logger logger = Logger.getLogger(CompanyController.class.getName());
	@Autowired
	private CompanyService companyService;

	@Autowired
	private UserService userService;

	/**
	 * Fetch an company with the specified company id.
	 * 
	 * @param companyId
	 *            A string.
	 * @return The company if found.
	 */

	@RequestMapping(value = "/{companyId}", method = RequestMethod.GET)
	public ApiResponse getCompanyById(@PathVariable int companyId) {

		logger.info("company-controller getCompanyById() invoked: " + companyId);
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

	
	
}
