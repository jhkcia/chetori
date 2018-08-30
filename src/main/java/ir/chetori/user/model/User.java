package ir.chetori.user.model;

import ir.chetori.core.EntityField;
import ir.chetori.core.entity.BaseEntity;

public class User extends BaseEntity {
	@EntityField
	protected String id;
	@EntityField
	private String firstName;
	@EntityField
	private String lastName;
	@EntityField
	private String phoneNumber;
	@EntityField
	private String password;
	@EntityField
	private String token;

	@EntityField
	private String emailAddress;

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Role getRole() {
		return Role.ADMIN;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

}
