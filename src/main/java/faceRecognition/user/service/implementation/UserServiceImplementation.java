package faceRecognition.user.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import faceRecognition.company.service.interfaces.CompanyService;
import faceRecognition.user.domain.CompanyRegister;
import faceRecognition.user.domain.SecurityQuestion;
import faceRecognition.user.domain.User;
import faceRecognition.user.repository.SecurityQuestionRepository;
import faceRecognition.user.repository.UserRepository;
import faceRecognition.user.service.interfaces.UserService;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private SecurityQuestionRepository securityQuestionRepository;

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return (List<User>) userRepository.findAll();
	}

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		userRepository.save(user);
		return user;
	}

	@Override
	public List<SecurityQuestion> getAllSecurityQuestions() {
		// TODO Auto-generated method stub
		return (List<SecurityQuestion>) securityQuestionRepository.findAll();
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User findByEmployeeId(int employeeId) {
		// TODO Auto-generated method stub
		return userRepository.findByEmployee(employeeId);
	}

	@Override
	public String getPassword(CompanyRegister companyWrapper) {
		// TODO Auto-generated method stub
		int companyId = Integer.parseInt(companyWrapper.getEmployee().getCompanyId());
		String companyName = companyService.getById(companyId).getName();
		String employeeLastName = companyWrapper.getEmployee().getLastName();
		return companyName + "" + employeeLastName;
	}

	@Override
	public User getById(int id) {
		// TODO Auto-generated method stub
		return userRepository.findOne(id);
	}

	@Override
	public void deleteUser(int id) {
		User user = new User();
		user = userRepository.findByEmployee(id);
		userRepository.delete(user.getId());
	}

}
