package faceRecognition.address.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import faceRecognition.address.domain.City;
import faceRecognition.address.domain.Country;
import faceRecognition.address.domain.State;
import faceRecognition.address.service.interfaces.AddressService;
import faceRecognition.user.domain.ApiResponse;
import faceRecognition.user.domain.ResponseCode;
import faceRecognition.user.domain.SecurityQuestion;
import faceRecognition.user.service.interfaces.UserService;

@RestController
@RequestMapping(value = "/address")
public class AddressRestController {

	protected Logger logger = Logger.getLogger(AddressRestController.class.getName());

	@Autowired
	private AddressService addressService;
	@Autowired
	private UserService userService;

	/**
	 * Fetch countries. So <code>http://.../country/</code> will find all the
	 * countries
	 * 
	 * @return A non-null, non-empty set of countries.
	 */

	@RequestMapping(value = "/country", method = RequestMethod.GET)
	public ApiResponse getAllCountries() {
		logger.info("Address-rest-controller getAllCountries() invoked: " + addressService.getClass().getName());

		ApiResponse response = new ApiResponse();
		List<Country> countries = addressService.getAllCountries();

		if (null == countries || 0 == countries.size()) {
			response.setMessage("There are no Countries exist");
			response.setResponseCode(ResponseCode.NO_DATA_FOUND);
		} else {
			response.setResponseCode(ResponseCode.OK);
			response.setResult(countries);

		}
		return response;
	}

	/**
	 * Fetch users. So <code>http://.../securityQuestion/</code> will find all the
	 * security questions
	 * 
	 * @return A non-null, non-empty set of security questions.
	 */

	@RequestMapping(value = "/questions", method = RequestMethod.GET)
	public ApiResponse getAllSecurityQuestions() {
		logger.info("User-rest-controller getAllSecurityQuestions() invoked: " + userService.getClass().getName());

		ApiResponse response = new ApiResponse();
		List<SecurityQuestion> securityQuestions = userService.getAllSecurityQuestions();

		if (null == securityQuestions || 0 == securityQuestions.size()) {
			response.setMessage("There are no security questions exist");
			response.setResponseCode(ResponseCode.NO_DATA_FOUND);
		} else {
			response.setResponseCode(ResponseCode.OK);
			response.setResult(securityQuestions);

		}
		return response;
	}


	/**
	 * Fetch states. So <code>http://.../state/</code> will find all the countries
	 * 
	 * @return A non-null, non-empty set of states.
	 */

	@RequestMapping(value = "/state", method = RequestMethod.GET)
	public ApiResponse getAllStates() {
		logger.info("Address-rest-controller getAllStates() invoked: " + addressService.getClass().getName());

		ApiResponse response = new ApiResponse();
		List<State> states = addressService.getAllStates();

		if (null == states || 0 == states.size()) {
			response.setMessage("There are no states exist");
			response.setResponseCode(ResponseCode.NO_DATA_FOUND);
		} else {
			response.setResponseCode(ResponseCode.OK);
			response.setResult(states);

		}
		return response;
	}

	/**
	 * Fetch cities. So <code>http://.../city/</code> will find all the cities
	 * 
	 * @return A non-null, non-empty set of cities.
	 */

	@RequestMapping(value = "/city", method = RequestMethod.GET)
	public ApiResponse getAllCities() {
		logger.info("Address-rest-controller getAllCities() invoked: " + addressService.getClass().getName());

		ApiResponse response = new ApiResponse();
		List<City> cities = addressService.getAllCities();

		if (null == cities || 0 == cities.size()) {
			response.setMessage("There are no cities exist");
			response.setResponseCode(ResponseCode.NO_DATA_FOUND);
		} else {
			response.setResponseCode(ResponseCode.OK);
			response.setResult(cities);

		}
		return response;
	}
}
