package faceRecognition.company.repository;

import org.springframework.data.repository.CrudRepository;

import faceRecognition.company.domain.Company;

public interface CompanyRepository extends CrudRepository<Company, Integer>{

}
