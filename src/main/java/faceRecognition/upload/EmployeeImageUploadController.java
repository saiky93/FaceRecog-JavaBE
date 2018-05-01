package faceRecognition.upload;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import faceRecognition.company.domain.Company;
import faceRecognition.company.service.interfaces.CompanyService;
import faceRecognition.employee.domain.Employee;
import faceRecognition.employee.service.interfaces.EmployeeService;
import faceRecognition.user.domain.ApiResponse;
import faceRecognition.user.domain.ResponseCode;
import faceRecognition.user.domain.User;
import faceRecognition.user.domain.UserType;
import faceRecognition.user.service.interfaces.UserService;

@RestController
@RequestMapping(value = "/upload")
public class EmployeeImageUploadController {
	protected Logger logger = Logger.getLogger(EmployeeImageUploadController.class.getName());

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private UserService userService;

	@Autowired
	private CompanyService companyService;

	private static String DIR_URL = "C:\\\\Users\\\\hibrahim.MACROSOFTINC\\\\eclipse-workspace\\\\FaceRecognitionV2\\\\src\\\\main\\\\resources\\static\\";

	@RequestMapping(value = "/csv", method = RequestMethod.POST)
	public ApiResponse csvUpload(MultipartHttpServletRequest request) throws IOException {
		Iterator<String> itr = request.getFileNames();
		MultipartFile file = request.getFile(itr.next());
		String fileName = file.getOriginalFilename();
		String fname = fileName;
		File dir = new File(EmployeeImageUploadController.DIR_URL + "csv\\");
		if (dir.isDirectory()) {
			File serverFile = new File(dir, fileName);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(file.getBytes());
			stream.close();
		}
		ApiResponse response = new ApiResponse();
		response.setMessage("Successfully uploaded ");
		response.setResult(fname);
		return response;
	}

	@RequestMapping(value = "/image", method = RequestMethod.POST)
	public ApiResponse imageUpload(HttpServletRequest request) throws IOException {
		System.out.println("Upload here " + request.getInputStream());

		String fileUrl = EmployeeImageUploadController.DIR_URL + "images\\" + request.getInputStream().hashCode()
				+ ".jpg";
		Files.copy(request.getInputStream(), Paths.get(fileUrl), StandardCopyOption.REPLACE_EXISTING);

		ApiResponse response = new ApiResponse();
		response.setMessage("Successfully uploaded ");
		response.setResult(request.getInputStream().hashCode() + ".jpg");

		return response;
	}

	@RequestMapping(value = "/image/{employeeId}", method = RequestMethod.POST)
	public ApiResponse imageUploadForEmployee(@PathVariable int employeeId, HttpServletRequest request)
			throws IOException {
		String picture = request.getInputStream().hashCode() + ".jpg";
		String fileUrl = EmployeeImageUploadController.DIR_URL + "images\\" + picture;
		Files.copy(request.getInputStream(), Paths.get(fileUrl), StandardCopyOption.REPLACE_EXISTING);
		ApiResponse response = new ApiResponse();

		Employee employee = employeeService.getById(employeeId);
		if (null == employee) {
			response.setMessage("This employee doesn't exist ");
			response.setResult(ResponseCode.NO_DATA_FOUND);

		} else {

			employee.setPicture(picture);
			employeeService.saveEmployee(employee);
			response.setMessage("Successfully uploaded ");
			response.setResult(picture);

		}

		return response;
	}

	@RequestMapping(value = "/csv/createaccount/{companyId}/{filename}", method = RequestMethod.GET)

	public ApiResponse csvParse(@PathVariable String companyId, @PathVariable String filename) throws IOException {
		logger.info("file name is " + filename);
		Company cn = new Company();
		cn = companyService.getById(Integer.valueOf(companyId));
		String cname = cn.getName();
		int check = 0;
		String SAMPLE_CSV_FILE_PATH = EmployeeImageUploadController.DIR_URL + "csv\\" + filename + ".csv";

		try (Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));) {
			CsvToBean csvToBean = new CsvToBeanBuilder(reader).withType(CSVUser.class).withIgnoreLeadingWhiteSpace(true)
					.build();

			List<CSVUser> csvUsers = csvToBean.parse();

			for (CSVUser csvUser : csvUsers) {

				Employee em = new Employee();
				// Check if the user exist
				String email = csvUser.getEmail();
				User ifExistUser = userService.findByEmail(email);
				if (null == ifExistUser) {
					check = 1;
					User us = new User();
					em.setFirstName(csvUser.getFname());
					em.setLastName(csvUser.getLname());
					em.setPhone(csvUser.getPhoneNo());
					em.setCompanyId(companyId);
					em.setPicture("");
					us.setEmployee(employeeService.saveEmployeeAndReturnId(em));
					us.setEmail(csvUser.getEmail());
					us.setPassword(cname + csvUser.getLname());
					us.setUserType(UserType.EMPLOYEE);
					userService.saveUser(us);
				}

			}
		}
		ApiResponse response = new ApiResponse();

		if (check == 1) {
			response.setMessage("Accounts Successfully created ");
		} else {
			response.setMessage("Please match the csv file's format and make sure there are no duplicate entries");
		}
		return response;
	}

}
