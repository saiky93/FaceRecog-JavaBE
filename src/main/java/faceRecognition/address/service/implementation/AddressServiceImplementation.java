package faceRecognition.address.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import faceRecognition.address.domain.City;
import faceRecognition.address.domain.Country;
import faceRecognition.address.domain.State;
import faceRecognition.address.repository.CityRepository;
import faceRecognition.address.repository.CountryRepository;
import faceRecognition.address.repository.StateRepository;
import faceRecognition.address.service.interfaces.AddressService;

@Service
public class AddressServiceImplementation implements AddressService {

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private CityRepository cityRepository;

	@Override
	public List<Country> getAllCountries() {
		// TODO Auto-generated method stub
		return (List<Country>) countryRepository.findAll();
	}

	@Override
	public List<State> getAllStates() {
		// TODO Auto-generated method stub
		return (List<State>) stateRepository.findAll();
	}

	@Override
	public List<City> getAllCities() {
		// TODO Auto-generated method stub
		return (List<City>) cityRepository.findAll();

	}

}
