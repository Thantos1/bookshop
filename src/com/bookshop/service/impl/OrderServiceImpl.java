package com.bookshop.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookshop.dao.CartDAO;
import com.bookshop.dao.OrderDAO;
import com.bookshop.domain.Cart;
import com.bookshop.domain.Member;
import com.bookshop.domain.Order;
import com.bookshop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private CartDAO cartDao;
	@Autowired
	private OrderDAO orderDao;

	public Order submitOrder(Member member) {
		Order order = new Order();
		order.setMember(member);
		Cart cart = cartDao.searchCartByMember(member.getId());
		order.setCart(cart);
		order.setOrderDate(new Date());
		order.setOrderNo(String.valueOf(System.currentTimeMillis()));
		order.setOrderStatus(1);

		boolean status = false;
		try {
			orderDao.addOrder(order);
			status = true;
		} catch (Exception e) {
			status = false;
			e.printStackTrace();
		}
		if (status)
			return order;
		else
			return null;
	}

	public List<Order> browseOrders(Member member) {
		return orderDao.searchOrdersByMember(member);
	}

	public Order loadOrder(int orderId) {
		return orderDao.searchOrder(orderId);
	}

	public boolean deleteOrder(int orderId) {

		boolean status = false;
		try {
			orderDao.deleteOrder(orderId);
			status = true;
		} catch (Exception e) {
			status = false;
			e.printStackTrace();
		}
		return status;
	}

	public void acceptOrder(int orderId) {
		orderDao.changeOrderStatus(orderId, 2);
	}

	public void endOrder(int orderId) {
		orderDao.changeOrderStatus(orderId, 3);
	}

	public List<Order> browseOrders() {
		return orderDao.searchOrders();
	}

}
