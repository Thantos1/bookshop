package com.bookshop.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookshop.dao.MerchandiseDAO;
import com.bookshop.domain.Category;
import com.bookshop.domain.Merchandise;

@Transactional
@Repository
public class MerchandiseDAOImpl implements MerchandiseDAO{
	@Autowired
	private SessionFactory sessionFactory;

	public void addMerchandise(Merchandise mer) {
		
			Session session = sessionFactory.getCurrentSession();
			session.save(mer);
	}


	public void deleteMerchandise(int merId) {
		
		Session session = sessionFactory.getCurrentSession();
		Merchandise mer = (Merchandise) session.get(Merchandise.class, merId);
		session.delete(mer);
		
	}

	public void updateMerchandise(Merchandise mer) {
		
			Session session = sessionFactory.getCurrentSession();
			session.update(mer);
			
	}

	public Merchandise searchMerchandise(int merId) {
		Session session = sessionFactory.getCurrentSession();
		Merchandise mer = (Merchandise) session.get(Merchandise.class, merId);
		if (mer!=null&&!Hibernate.isInitialized(mer.getCategory()))
			Hibernate.initialize(mer.getCategory());
		return mer;
	}

	public List<Merchandise> searchMerchandises(boolean special) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Merchandise as m where m.special=:special";
		Query query = session.createQuery(hql);
		if(special)
			query.setInteger("special", 1);
		else
			query.setInteger("special", 0);
		List<Merchandise> mers = query.list();
		if(mers==null || mers.size()==0)
			return null;
		for(Merchandise mer: mers){
			if(!Hibernate.isInitialized(mer.getCategory()))
				Hibernate.initialize(mer.getCategory());
		}
			
		return mers;

	}

	public int countMerchandise(String key, int cateId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = null;
		String hql = null;
		if (cateId == 0) {
			if (key != null) {
				hql = "select count(*) from Merchandise as m where m.merName like '%"+key+"%'";
				query = session.createQuery(hql);
				
			} else {
				hql = "select count(*) from Merchandise";
				query = session.createQuery(hql);
			}

		} else {
			if (key != null) {
				hql = "select count(*) from Merchandise as m where m.category=:cate and m.merName like '%"+key+"%'";
				query = session.createQuery(hql);
				Category category = (Category) session.get(Category.class,cateId);
				query.setEntity("cate", category);
				
			}

			else {
				hql = "select count(*) from Merchandise as m where m.category=:cate";
				query = session.createQuery(hql);
				Category category = (Category) session.get(Category.class,
						cateId);
				query.setEntity("cate", category);
			}

		}
		
		query.setMaxResults(1);
		int count = ((Long)query.uniqueResult()).intValue();
		return count;
	}
	
	public List<Merchandise> searchMersByKeyAndCate(int pageNo, int pageSize,
			String key, int cateId) {

		Session session = sessionFactory.getCurrentSession();
		Query query = null;
		String hql = null;
		if(key!=null)
			key = key.trim();
		if (cateId == 0) {
			if (key != null) {
				hql = "from Merchandise as m where m.merName like '%"+key+"%'";
				query = session.createQuery(hql);
			} else {
				hql = "from Merchandise";
				query = session.createQuery(hql);
			}

		} else {
			if (key != null) {
				hql = "from Merchandise as m where m.category=:cate and m.merName like '%"+key+"%'";
				query = session.createQuery(hql);
				Category category = (Category) session.get(Category.class,cateId);
				query.setEntity("cate", category);
			}

			else {
				hql = "from Merchandise as m where m.category=:cate";
				query = session.createQuery(hql);
				Category category = (Category) session.get(Category.class,
						cateId);
				query.setEntity("cate", category);
			}

		}

		query.setFirstResult((pageNo - 1) * pageSize);
		query.setMaxResults(pageSize);
		List<Merchandise> mers = query.list();
		if(mers==null || mers.size()==0)
			return null;
		
		for(Merchandise mer:mers){
			if(!Hibernate.isInitialized(mer.getCategory()))
				Hibernate.initialize(mer.getCategory());
		}
		return mers;
	}


	public int countMerchandise(boolean special) {
		Session session = sessionFactory.getCurrentSession();
		String hql = null;
		if (special)
			hql = "select count(*) from Merchandise as m where m.special=1";
		else
			hql = "select count(*) from Merchandise as m where m.special=0";
		Query query = session.createQuery(hql);
		query.setMaxResults(1);
		int count = ((Long)query.uniqueResult()).intValue();
		return count;
	}

	public List<Merchandise> searchMerchandise(int pageNo, int pageSize,
			boolean special) {

		Session session = sessionFactory.getCurrentSession();
		String hql = null;
		if (special)
			hql = "from Merchandise as m where m.special=1";
		else
			hql = "from Merchandise as m where m.special=0";

		Query query = session.createQuery(hql);
		query.setFirstResult((pageNo-1)*pageSize);
		query.setMaxResults(pageSize);
		
		List<Merchandise> mers = query.list();
		if(mers==null || mers.size()==0)
			return null;
		for(Merchandise mer:mers){
			if(!Hibernate.isInitialized(mer.getCategory()))
				Hibernate.initialize(mer.getCategory());
		}
		return mers;
	}

	
	public int countMerchandise(int cateId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select count(*) from Merchandise as m where m.category = :cate";
		Query query = session.createQuery(hql);
		Category cate = (Category) session.get(Category.class, cateId);
		query.setEntity("cate", cate);
		query.setMaxResults(1);
		int count = ((Long)query.uniqueResult()).intValue();
		return count;
	}
	public List<Merchandise> searchMerchandiseByCate(int pageNo, int pageSize,int cateId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Merchandise as m where m.category = :cate";
		Query query = session.createQuery(hql);
		Category cate = (Category) session.get(Category.class, cateId);
		query.setEntity("cate", cate);
		query.setFirstResult((pageNo - 1) * pageSize);
		query.setMaxResults(pageSize);
		List<Merchandise> mers = query.list();
		if(mers==null || mers.size()==0)
			return null;
		for(Merchandise mer:mers){
			if(!Hibernate.isInitialized(mer.getCategory()))
				Hibernate.initialize(mer.getCategory());
		}
		return mers;
	}

}
