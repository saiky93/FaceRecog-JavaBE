package faceRecognition.address.service.interfaces;

import java.util.List;

import faceRecognition.address.domain.City;
import faceRecognition.address.domain.Country;
import faceRecognition.address.domain.State;

public interface AddressService {
	List<Country> getAllCountries();

	List<State> getAllStates();

	List<City> getAllCities();
}
