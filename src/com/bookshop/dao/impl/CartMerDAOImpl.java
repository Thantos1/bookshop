package com.bookshop.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookshop.dao.CartMerDAO;
import com.bookshop.domain.CartMerchandise;

@Transactional
@Repository
public class CartMerDAOImpl implements CartMerDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void deleteCartMer(int cartMerId) {
		Session session = sessionFactory.getCurrentSession();
		CartMerchandise cartMer = this.searchCartMer(cartMerId);
		session.delete(cartMer);
		
	}
	
	public void deleteCartMersByCartId(int cartId) {
		Session session = sessionFactory.getCurrentSession();
		List<CartMerchandise> cartMers = this.searchCartMers(cartId);
		for(CartMerchandise cartMer : cartMers)
			session.delete(cartMer);
	}
	
	
	public void updateCartMer(CartMerchandise cartMer) {
		Session session = sessionFactory.getCurrentSession();
		session.update(cartMer);
	}
	
	public CartMerchandise searchCartMer(int cartMerId) {
			Session session = sessionFactory.getCurrentSession();
			CartMerchandise cartMer = (CartMerchandise) session.get(CartMerchandise.class, cartMerId);
			return cartMer;
	}
	
    public CartMerchandise searchCartMer(int cartId, int merId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from CartMerchandise as cm where cm.cart = :cartId and cm.merchandise=:merId";
		Query query = session.createQuery(hql);
		query.setInteger("cartId", cartId);
		query.setInteger("merId", merId);
		query.setMaxResults(1);
		CartMerchandise cartMer = (CartMerchandise) query.uniqueResult();
		return cartMer;
	}
    
    
    public List<CartMerchandise> searchCartMers(int cartId) {
    	Session session = sessionFactory.getCurrentSession();
    	String hql = "from CartMerchandise as cm where cm.cart=:cartId";
    	Query query = session.createQuery(hql);
    	query.setInteger("cartId", cartId);
    	List<CartMerchandise> cartMers = query.list();
    	return cartMers;
	}

}
