package faceRecognition.employee.service.implementation;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.apache.commons.codec.binary.Base64;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import faceRecognition.company.domain.Company;
import faceRecognition.employee.domain.Employee;
import faceRecognition.employee.repository.EmployeeRepository;
import faceRecognition.employee.service.interfaces.EmployeeService;
import faceRecognition.user.domain.CompanyRegister;
import faceRecognition.user.domain.User;
import faceRecognition.user.service.interfaces.UserService;
import org.apache.commons.lang.StringUtils;

@Service
public class EmployeeServiceImplementation implements EmployeeService {

	private static String DIR_URL = "C:\\Users\\hibrahim.MACROSOFTINC\\eclipse-workspace\\FaceRecognitionV2\\src\\main\\resources\\static\\images\\";
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private UserService userService;

	@Override
	public List<CompanyRegister> findByCompanyIdAsCompanyWrapper(String companyId) {
		// TODO Auto-generated method stub
		List<Employee> employees = employeeRepository.findByCompanyId(companyId);
		List<CompanyRegister> companyuseraccounts = new ArrayList<CompanyRegister>();
		employees.forEach(employee -> {
			CompanyRegister companywrapper = new CompanyRegister();
			companywrapper.setEmployee(employee);
			User user = userService.findByEmployeeId(employee.getId());
			companywrapper.setUser(user);
			companyuseraccounts.add(companywrapper);
		});
		return companyuseraccounts;
	}

	@Override
	public void saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		employeeRepository.save(employee);
	}

	@Override
	public int saveEmployeeAndReturnId(Employee employee) throws IOException {
		// TODO Auto-generated method stub
		String base64 = employee.getPicture();
		if (base64.equals(null) || base64.equals("")) {
		} else {
			if (base64.contains("data:image/png;base64,")) {
				base64 = base64.replaceAll("data:image/png;base64,", "");
			} else {
				base64 = base64.replaceAll("data:image/jpeg;base64,", "");

			}
			System.out.println("IMAGE URI IS:" + base64);
			byte[] base64Val = convertToImg(base64);

			employee.setPicture("");
			employeeRepository.save(employee);
			int employeeId = employee.getId();

			String imageName = employeeId + ".png";

			writeByteToImageFile(base64Val, DIR_URL + imageName);
			// System.out.println("Picture is " + employee.getPicture());
			employee.setPicture(imageName);
		}
		employeeRepository.save(employee);
		return employee.getId();

	}

	public static byte[] convertToImg(String base64) throws IOException {
		return Base64.decodeBase64(base64);
	}

	public static void writeByteToImageFile(byte[] imgBytes, String imgFileName) throws IOException {
		System.out.println("Filename is:" + imgFileName);
		Files.deleteIfExists(Paths.get(imgFileName));
		File imgFile = new File(imgFileName);
		BufferedImage img = ImageIO.read(new ByteArrayInputStream(imgBytes));
		ImageIO.write(img, "png", imgFile);
	}

	@Override
	public Employee getById(int id) {
		// TODO Auto-generated method stub
		return employeeRepository.findOne(id);
	}

	@Override
	public void deleteEmployee(int id) {
		employeeRepository.delete(id);
	}

	@Override
	public String getPictureFromBase64(int employeeId, String base64Image) throws IOException {
		// TODO Auto-generated method stub

		String imageName = employeeId + ".png";
		if (null != base64Image && !base64Image.isEmpty()) {

			String base64 = base64Image;
			if (base64.contains("data:image/png;base64,")) {
				base64 = base64.replaceAll("data:image/png;base64,", "");
			} else {
				base64 = base64.replaceAll("data:image/jpeg;base64,", "");

			}
			byte[] base64Val = convertToImg(base64);

			writeByteToImageFile(base64Val, DIR_URL + imageName);

		}

		return imageName;
	}

	@Override
	public List<Employee> getByFirstOrLastName(String firstOrLastName) {
		// TODO Auto-generated method stub
		List<Employee> employees = new ArrayList<Employee>();

		List<Employee> allEmployees = (List<Employee>) employeeRepository.findAll();

		allEmployees.forEach(employee -> {

			int similarityDistanceBetweenStringAndFirstName = StringUtils.getLevenshteinDistance(firstOrLastName,
					employee.getFirstName());

			int similarityDistanceBetweenStringAndLastName = StringUtils.getLevenshteinDistance(firstOrLastName,
					employee.getLastName());
			//System.out.println(" Similarity distance for " + firstOrLastName + " and first Name :"
				//	+ employee.getFirstName() + " and last name : " + employee.getLastName()
					//+ similarityDistanceBetweenStringAndFirstName + " " + similarityDistanceBetweenStringAndLastName);
			if (similarityDistanceBetweenStringAndFirstName < 3 || similarityDistanceBetweenStringAndLastName < 3) {
				employees.add(employee);
			}
		});

		return employees;
	}

}