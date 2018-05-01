package faceRecognition.address.repository;

import org.springframework.data.repository.CrudRepository;

import faceRecognition.address.domain.Country;

public interface CountryRepository extends CrudRepository<Country, Integer>{

}
