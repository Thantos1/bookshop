package com.bookshop.dao;

import java.util.List;

import com.bookshop.domain.Member;
import com.bookshop.domain.Order;

public interface OrderDAO {

	public abstract void addOrder(Order order);
	
	public abstract void deleteOrder(int orderId);
	
	public abstract Order searchOrder(int orderId);
	
	public abstract List<Order> searchOrders();

	public abstract List<Order> searchOrdersByMember(Member member);

	public abstract void changeOrderStatus(int orderId, int status);



}