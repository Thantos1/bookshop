package com.bookshop.domain;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Cart {
	private int id;
	private Member member;
	private double money;
	private int cartStatus;
	private Set<Merchandise> merchandises = new HashSet<Merchandise>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public int getCartStatus() {
		return cartStatus;
	}
	public void setCartStatus(int cartStatus) {
		this.cartStatus = cartStatus;
	}
	public Set<Merchandise> getMerchandises() {
		return merchandises;
	}
	public void setMerchandises(Set<Merchandise> merchandises) {
		this.merchandises = merchandises;
	}

}
