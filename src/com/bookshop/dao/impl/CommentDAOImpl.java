package com.bookshop.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookshop.dao.CommentDAO;
import com.bookshop.domain.Comment;
import com.bookshop.domain.Leaveword;

@Transactional
@Repository
public class CommentDAOImpl implements CommentDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addComment(Comment comment) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(comment);
		
	}

	@Override
	public void updateCart(Comment comment) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(comment);
	}



	@Override
	public Comment searchCart(int commentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> searchCommentByMerchandiase(int merId, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Comment where merchandise= :merId";
		Query query = session.createQuery(hql);
		query.setInteger("merId", merId);
		query.setFirstResult((pageNo - 1) * pageSize);
		query.setMaxResults(pageSize);
		
		List<Comment> comment = query.list();
		if (comment == null || comment.size() == 0)
			return null;
		for (Comment comm : comment) {
			if (!Hibernate.isInitialized(comm.getMember())) {
				Hibernate.initialize(comm.getMember());
				if (!Hibernate.isInitialized(comm.getMember().getMemberlevel()))
					Hibernate.initialize(comm.getMember().getMemberlevel());
			}

		}
		return comment;
	}

	@Override
	public int countCommentsByMerId(int merId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String hql="select count(*) from Comment as c where c.merchandise = :merId";
		Query query = session.createQuery(hql);
		query.setInteger("merId", merId);
		query.setMaxResults(1);
		int count = ((Long) query.uniqueResult()).intValue();
		return count;
	}

}
