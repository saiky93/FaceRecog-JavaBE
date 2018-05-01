package faceRecognition.company.service.interfaces;

import faceRecognition.company.domain.Company;

public interface CompanyService {
	
	int saveCompany(Company company);

	Company getById(int id);
}
