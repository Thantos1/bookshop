package com.bookshop.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookshop.dao.MemberDAO;
import com.bookshop.domain.Member;

@Transactional
@Repository
public class MemberDAOImpl implements MemberDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void addMember(Member member) {
		
			Session session = sessionFactory.getCurrentSession();
			session.save(member);
	}

	public void deleteMember(int memberId) {
		
			Session session = sessionFactory.getCurrentSession();
			Member member = (Member) session.get(Member.class, memberId);
			session.delete(member);
	}

	public void updateMember(Member member) {
		
			Session session = sessionFactory.getCurrentSession();
			session.update(member);
	}

	public Member searchMember(int memberId) {
		Session session = sessionFactory.getCurrentSession();
		Member member = (Member) session.get(Member.class, memberId);
		if(member!=null && !Hibernate.isInitialized(member.getMemberlevel()))
			Hibernate.initialize(member.getMemberlevel());
		return member;
	}

	public List<Member> searchMembers() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Member";
		Query query = session.createQuery(hql);
		List<Member> members = query.list();
		if(members==null || members.size()==0)
			return null;
		for(Member member : members){
			if(!Hibernate.isInitialized(member.getMemberlevel()))
				Hibernate.initialize(member.getMemberlevel());
		}
		return members;
	}

	public boolean checkUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Member as m where m.username = :name";
		Query query = session.createQuery(hql);
		query.setString("name", username);
		query.setMaxResults(1);
		Member member = (Member) query.uniqueResult();
		if (member == null)
			return true;
		return false;
	}

	public Member searchMember(String username, String password) {

		Session session = sessionFactory.getCurrentSession();
		String hql = "from Member as m where m.username=:username and m.password=:password";
		Query query = session.createQuery(hql);
		query.setString("username", username);
		query.setString("password", password);
		Member member = (Member) query.uniqueResult();
		if(member!=null&&!Hibernate.isInitialized(member.getMemberlevel()))
			Hibernate.initialize(member.getMemberlevel());
		return member;
	}
}
