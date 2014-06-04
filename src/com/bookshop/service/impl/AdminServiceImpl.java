package com.bookshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookshop.dao.AdminDAO;
import com.bookshop.domain.Admin;
import com.bookshop.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminDAO adminDao;

	public boolean addAdmin(Admin admin) {
		boolean status = false;
		try{
		adminDao.addAdmin(admin);
		status = true;
		}catch(Exception e){
			status = false;
			e.printStackTrace();
		}
		return status;
		
	}

	public boolean deleteAdmin(Admin admin) {
	
		boolean status = false;
		try{
			adminDao.deleteAdmin(admin.getId());
		status = true;
		}catch(Exception e){
			status = false;
			e.printStackTrace();
		}
		return status;
	}

	public boolean updateAdmin(Admin admin) {	
		boolean status = false;
		try{
			adminDao.updateAdmin(admin);
		status = true;
		}catch(Exception e){
			status = false;
			e.printStackTrace();
		}
		return status;
	}

	public Admin loadAdmin(int adminId) {
		return adminDao.searchAdmin(adminId);

	}

	public List<Admin> browseAdmins() {
		return adminDao.searchAdmins();

	}

	public Admin login(String username, String password) {
		return adminDao.searchAdmin(username, password);
	}

}
