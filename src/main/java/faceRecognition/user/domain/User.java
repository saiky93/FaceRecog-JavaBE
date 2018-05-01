package faceRecognition.user.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	@Id
	@GeneratedValue
	private int id;
	private String email;
	private String password;
	private int companyId;
	@Column(name = "employee_id")
	private int employee;
	private UserType userType;

	@Column(columnDefinition = "tinyint(1) default 0")
	private boolean changedPassword;

	private String securityQuestion1;

	public String getSecurityQuestion1() {
		return securityQuestion1;
	}

	public void setSecurityQuestion1(String securityQuestion1) {
		this.securityQuestion1 = securityQuestion1;
	}

	public String getSecurityAnswer1() {
		return securityAnswer1;
	}
	
	public void setSecurityAnswer1(String securityAnswer1) {
		this.securityAnswer1 = securityAnswer1;
	}

	public String getSecurityQuestion2() {
		return securityQuestion2;
	}

	public void setSecurityQuestion2(String securityQuestion2) {
		this.securityQuestion2 = securityQuestion2;
	}

	public String getSecurityAnswer2() {
		return securityAnswer2;
	}

	public void setSecurityAnswer2(String securityAnswer2) {
		this.securityAnswer2 = securityAnswer2;
	}

	public String getSecurityQuestion3() {
		return securityQuestion3;
	}

	public void setSecurityQuestion3(String securityQuestion3) {
		this.securityQuestion3 = securityQuestion3;
	}

	public String getSecurityAnswer3() {
		return securityAnswer3;
	}

	public void setSecurityAnswer3(String securityAnswer3) {
		this.securityAnswer3 = securityAnswer3;
	}

	private String securityAnswer1;

	private String securityQuestion2;
	private String securityAnswer2;

	private String securityQuestion3;
	private String securityAnswer3;

	public UserType getUserType() {
		return userType;
	}

	public boolean isChangedPassword() {
		return changedPassword;
	}

	public void setChangedPassword(boolean changedPassword) {
		this.changedPassword = changedPassword;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	@Transient
	private String confirmPassword;

	public String getPassword() {
		return password;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getEmployee() {
		return employee;
	}

	public void setEmployee(int employee) {
		this.employee = employee;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// @Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	// @Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	// @Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	// @Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	// @Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", companyId=" + companyId
				+ ", employee=" + employee + ", userType=" + userType + ", changedPassword=" + changedPassword
				+ ", securityQuestion1=" + securityQuestion1 + ", securityAnswer1=" + securityAnswer1
				+ ", securityQuestion2=" + securityQuestion2 + ", securityAnswer2=" + securityAnswer2
				+ ", securityQuestion3=" + securityQuestion3 + ", securityAnswer3=" + securityAnswer3
				+ ", confirmPassword=" + confirmPassword + "]";
	}

}
