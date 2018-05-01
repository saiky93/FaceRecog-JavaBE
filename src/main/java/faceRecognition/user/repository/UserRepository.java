package faceRecognition.user.repository;

import org.springframework.data.repository.CrudRepository;

import faceRecognition.user.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	User findByEmail(String email);

	User findByEmployee(int employeeId);

}
