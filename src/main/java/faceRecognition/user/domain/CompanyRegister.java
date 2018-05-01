package faceRecognition.user.domain;

import faceRecognition.company.domain.Company;
import faceRecognition.employee.domain.Employee;

public class CompanyRegister {
	private Company company;
	private Employee employee;
	private User user;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "CompanyRegister [company=" + company + ", employee=" + employee + ", user=" + user + "]";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
