package com.bookshop.struts.form;

import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.bookshop.domain.Memberlevel;

public class MemberForm extends ActionForm {
	private int memberlevel;
	private String username;
	private String password;
	private String name;
	private String phone;
	private String address;
	private String zipCode;
	private String email;

	public int getMemberlevel() {
		return memberlevel;
	}

	public void setMemberlevel(int memberlevel) {
		this.memberlevel = memberlevel;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
