package com.bookshop.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.bookshop.dao.CartDAO;
import com.bookshop.domain.Cart;
import com.bookshop.domain.CartMerchandise;
import com.bookshop.domain.Member;
import com.bookshop.domain.Merchandise;

@Transactional
@Repository
public class CartDAOImpl implements CartDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addCart(Cart cart) {
		
		Session session = sessionFactory.getCurrentSession();
		session.save(cart);
	}

	
	
	
	public void updateCart(Cart cart) {
		
		Session session = sessionFactory.getCurrentSession();
		session.update(cart);
	}

	
	public Cart searchCart(int cartId) {
		Session session = sessionFactory.getCurrentSession();
		Cart cart = (Cart) session.get(Cart.class, cartId);
		if(cart==null)
			return null;
		
		if(!Hibernate.isInitialized(cart.getMember()))
			Hibernate.initialize(cart.getMember());
		
		for(Merchandise mer : cart.getMerchandises()){
			if(!Hibernate.isInitialized(mer))
				Hibernate.initialize(mer);
		}	
		return cart;
	}
	
	public Cart searchCartByMember(int memberId) {
		Session session = sessionFactory.getCurrentSession();
		Member member = (Member) session.get(Member.class, memberId);
		String hql = "from Cart as c where c.member=:member and c.cartStatus = 1";
		Query query = session.createQuery(hql);
		query.setEntity("member", member);
		query.setMaxResults(1);
		Cart cart = (Cart)query.uniqueResult();
		if(cart==null)
			return null;
		
		if(!Hibernate.isInitialized(cart.getMember()))
			Hibernate.initialize(cart.getMember());
		
		for(Merchandise mer : cart.getMerchandises()){
			if(!Hibernate.isInitialized(mer))
				Hibernate.initialize(mer);
		}
		
		return cart;
	}

}
