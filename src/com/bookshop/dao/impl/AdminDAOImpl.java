package com.bookshop.dao.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookshop.dao.AdminDAO;
import com.bookshop.domain.Admin;

@Transactional
@Repository
public class AdminDAOImpl implements AdminDAO{
	@Autowired
	private SessionFactory sessionFactory;

	public void addAdmin(Admin admin) {
		
		Session session = sessionFactory.getCurrentSession();
		session.save(admin);
	}
	
	public void deleteAdmin(int adminId) {
		
		Admin admin = this.searchAdmin(adminId);
		Session session = sessionFactory.getCurrentSession();
		session.delete(admin);
	
	
	}
	
	public void updateAdmin(Admin admin) {
		
		Session session = sessionFactory.getCurrentSession();
		session.update(admin);

	}


	public Admin searchAdmin(int adminId) {
		Session session = sessionFactory.getCurrentSession();
		Admin admin = (Admin) session.get(Admin.class, adminId);
		return admin;

	}

	
	public List<Admin> searchAdmins() {

		Session session = sessionFactory.getCurrentSession();
		String hql = "from Admin";
		Query query = session.createQuery(hql);
		List<Admin> admins = query.list();
		return admins;
	}



	public Admin searchAdmin(String username, String password) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Admin as a where a.username=:username and a.password=:password";
		Query query = session.createQuery(hql);
		query.setString("username", username);
		query.setString("password", password);
		Admin admin = (Admin) query.uniqueResult();
		return admin;
	}
	

}
