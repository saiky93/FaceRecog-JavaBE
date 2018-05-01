package faceRecognition.user.service.interfaces;

import java.util.List;

import faceRecognition.user.domain.CompanyRegister;
import faceRecognition.user.domain.SecurityQuestion;
import faceRecognition.user.domain.User;

public interface UserService {

	User getById(int id);

	List<User> getAllUsers();

	User saveUser(User user);

	List<SecurityQuestion> getAllSecurityQuestions();

	User findByEmail(String email);

	User findByEmployeeId(int employeeId);

	String getPassword(CompanyRegister companyWrapper);

	void deleteUser(int id);
}
