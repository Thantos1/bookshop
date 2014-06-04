package com.bookshop.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookshop.dao.OrderDAO;
import com.bookshop.domain.Member;
import com.bookshop.domain.Order;

@Transactional
@Repository
public class OrderDAOImpl implements OrderDAO{
	@Autowired
	private SessionFactory sessionFactory;

	public void addOrder(Order order) {
		
		Session session = sessionFactory.getCurrentSession();
		session.save(order);
		
	}
	
	public void deleteOrder(int orderId) {
		
		Order order = this.searchOrder(orderId);
		Session session = sessionFactory.getCurrentSession();
		session.delete(order);
	}
	
	public Order searchOrder(int orderId) {
		Session session = sessionFactory.getCurrentSession();
		Order order = (Order) session.get(Order.class, orderId);
		if (order!=null&&!Hibernate.isInitialized(order.getMember())){
			Hibernate.initialize(order.getMember());
			if(!Hibernate.isInitialized(order.getMember().getMemberlevel()))
				Hibernate.initialize(order.getMember().getMemberlevel());		
		}
		if(order!=null && !Hibernate.isInitialized(order.getCart()))
			Hibernate.initialize(order.getCart());
		return order;
	}
	
	public List<Order> searchOrders() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Order";
		Query query = session.createQuery(hql);
		List<Order> orders = query.list();
		if(orders==null || orders.size()==0)
			return null;
		for(Order order : orders){
			if(!Hibernate.isInitialized(order.getMember())){
				Hibernate.initialize(order.getMember());
			if(!Hibernate.isInitialized(order.getMember().getMemberlevel()))
				Hibernate.initialize(order.getMember().getMemberlevel());	
			}
			if(!Hibernate.isInitialized(order.getCart()))
				Hibernate.initialize(order.getCart());		
		}
		return orders;	
	}

	public List<Order> searchOrdersByMember(Member member) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Order as o where o.member=:member";
		Query query = session.createQuery(hql);
		query.setEntity("member", member);
		List<Order> orders = query.list();
		if(orders==null || orders.size()==0)
			return null;
		for(Order order : orders){
			if(!Hibernate.isInitialized(order.getMember())){
				Hibernate.initialize(order.getMember());
			if(!Hibernate.isInitialized(order.getMember().getMemberlevel()))
				Hibernate.initialize(order.getMember().getMemberlevel());
			}
			if(!Hibernate.isInitialized(order.getCart()))
				Hibernate.initialize(order.getCart());
		}
		return orders;
	}


	public void changeOrderStatus(int orderId,int status){
		Order order = this.searchOrder(orderId);
		Session session = sessionFactory.getCurrentSession();
		order.setOrderStatus(status);
		session.update(order);	
	}

}
