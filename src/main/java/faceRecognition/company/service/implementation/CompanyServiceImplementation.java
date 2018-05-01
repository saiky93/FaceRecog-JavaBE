package faceRecognition.company.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import faceRecognition.company.domain.Company;
import faceRecognition.company.repository.CompanyRepository;
import faceRecognition.company.service.interfaces.CompanyService;

@Service
public class CompanyServiceImplementation implements CompanyService {
	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public int saveCompany(Company company) {
		// TODO Auto-generated method stub

		companyRepository.save(company);
		return company.getId();
	}

	@Override
	public Company getById(int id) {
		// TODO Auto-generated method stub
		return companyRepository.findOne(id);
	}
}
