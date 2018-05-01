package faceRecognition.user.repository;

import org.springframework.data.repository.CrudRepository;

import faceRecognition.user.domain.SecurityQuestion;

public interface SecurityQuestionRepository extends CrudRepository<SecurityQuestion, Integer> {

}
