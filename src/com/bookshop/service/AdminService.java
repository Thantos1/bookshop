package com.bookshop.service;

import java.util.List;

import com.bookshop.domain.Admin;

public interface AdminService {

	public abstract boolean addAdmin(Admin admin);

	public abstract boolean deleteAdmin(Admin admin);
	
	public abstract boolean updateAdmin(Admin admin);

	public abstract Admin loadAdmin(int adminId);

	public abstract List<Admin> browseAdmins();

	public abstract Admin login(String username, String password);

}