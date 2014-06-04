package com.bookshop.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookshop.dao.CategoryDAO;
import com.bookshop.domain.Category;

@Transactional
@Repository
public class CategoryDAOImpl implements CategoryDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void addCate(Category cate) {
		Session session = sessionFactory.getCurrentSession();
		session.save(cate);
	}

	public void deleteCate(int cateId) {
		Category cate = this.searchCate(cateId);
		Session session = sessionFactory.getCurrentSession();
		session.delete(cate);
	}

	public void updateCate(Category cate) {

		Session session = sessionFactory.getCurrentSession();
		session.update(cate);
	}

	public Category searchCate(int cateId) {
		Session session = sessionFactory.getCurrentSession();
		Category cate = (Category) session.get(Category.class, cateId);
		return cate;
	}

	public List<Category> searchCates() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Category";
		Query query = session.createQuery(hql);
		List<Category> cates = query.list();
		return cates;
	}

}
