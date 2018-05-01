package faceRecognition.address.repository;

import org.springframework.data.repository.CrudRepository;

import faceRecognition.address.domain.City;

public interface CityRepository extends CrudRepository<City, Integer> {

}
