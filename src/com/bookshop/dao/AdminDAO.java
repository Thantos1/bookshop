package com.bookshop.dao;

import java.util.List;

import com.bookshop.domain.Admin;

public interface AdminDAO {

	public abstract void addAdmin(Admin admin);
	
	public abstract void deleteAdmin(int adminId);
	
	public abstract void updateAdmin(Admin admin);

	public abstract Admin searchAdmin(int adminId);

	public abstract List<Admin> searchAdmins();

	public abstract Admin searchAdmin(String username, String password);

}