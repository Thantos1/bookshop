package com.bookshop.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookshop.dao.MemberlevelDAO;
import com.bookshop.domain.Memberlevel;

@Transactional
@Repository
public class MemberlevelDAOImpl implements MemberlevelDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public Memberlevel searchMemberlevel(int memberlevelId) {
		Session session = sessionFactory.getCurrentSession();
		Memberlevel level = (Memberlevel) session.get(Memberlevel.class, memberlevelId);
		return level;
	}

	public List<Memberlevel> searchMemeberlevels() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Memberlevel";
		Query query = session.createQuery(hql);
		List<Memberlevel> levels = query.list();
		return levels;
	}

}
