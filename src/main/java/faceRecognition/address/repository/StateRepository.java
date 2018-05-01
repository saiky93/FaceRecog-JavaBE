package faceRecognition.address.repository;

import org.springframework.data.repository.CrudRepository;

import faceRecognition.address.domain.State;

public interface StateRepository extends CrudRepository<State, Integer>{

}
