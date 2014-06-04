package com.bookshop.service;

import java.util.List;

import com.bookshop.domain.Member;
import com.bookshop.domain.Order;

public interface OrderService {

	public abstract Order submitOrder(Member member);

	public abstract List<Order> browseOrders(Member member);

	public abstract Order loadOrder(int orderId);

	public abstract boolean deleteOrder(int orderId);

	public abstract void acceptOrder(int orderId);

	public abstract void endOrder(int orderId);

	public abstract List<Order> browseOrders();

}