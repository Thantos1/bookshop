package com.bookshop.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookshop.dao.LeavewordDAO;
import com.bookshop.domain.Leaveword;

@Transactional
@Repository
public class LeavewordDAOImpl implements LeavewordDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void addLeaveword(Leaveword word) {

		Session session = sessionFactory.getCurrentSession();
		session.save(word);
	}

	public void deleteLeaveword(int wordId) {

		Leaveword word = this.searchLeaveword(wordId);
		Session session = sessionFactory.getCurrentSession();
		session.delete(word);
	}

	public void updateLeaveword(Leaveword word) {

		Session session = sessionFactory.getCurrentSession();
		session.update(word);
	}

	public Leaveword searchLeaveword(int wordId) {
		Session session = sessionFactory.getCurrentSession();
		Leaveword word = (Leaveword) session.get(Leaveword.class, wordId);
		if (word != null && !Hibernate.isInitialized(word.getMember())) {
			Hibernate.initialize(word.getMember());
			if (!Hibernate.isInitialized(word.getMember().getMemberlevel()))
				Hibernate.initialize(word.getMember().getMemberlevel());
		}

		if (word != null && !Hibernate.isInitialized(word.getAdmin()))
			Hibernate.initialize(word.getAdmin());
		return word;
	}

	public List<Leaveword> searchLeavewords() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Leaveword";
		Query query = session.createQuery(hql);
		List<Leaveword> words = query.list();
		if (words == null || words.size() == 0)
			return null;
		for (Leaveword word : words) {
			if (!Hibernate.isInitialized(word.getMember())) {
				Hibernate.initialize(word.getMember());
				if (!Hibernate.isInitialized(word.getMember().getMemberlevel()))
					Hibernate.initialize(word.getMember().getMemberlevel());
			}
			if (!Hibernate.isInitialized(word.getAdmin()))
				Hibernate.initialize(word.getAdmin());
		}
		return words;
	}

	public List<Leaveword> searchLeavewords(int pageNo, int pageSize) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Leaveword";
		Query query = session.createQuery(hql);
		query.setFirstResult((pageNo - 1) * pageSize);
		query.setMaxResults(pageSize);
		List<Leaveword> leavewords = query.list();
		if (leavewords == null || leavewords.size() == 0)
			return null;
		for (Leaveword word : leavewords) {
			if (!Hibernate.isInitialized(word.getMember())) {
				Hibernate.initialize(word.getMember());
				if (!Hibernate.isInitialized(word.getMember().getMemberlevel()))
					Hibernate.initialize(word.getMember().getMemberlevel());
			}
			if (!Hibernate.isInitialized(word.getAdmin()))
				Hibernate.initialize(word.getAdmin());
		}
		return leavewords;
	}

	public int countLeaveword() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select count(*) from Leaveword";
		Query query = session.createQuery(hql);
		query.setMaxResults(1);
		int count = ((Long) query.uniqueResult()).intValue();
		return count;
	}
}
