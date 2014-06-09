package com.bookshop.domain;

public class CartMerchandise {
	private int id;
	private int cart;
	private int merchandise;
	private int number;
	private double price;
	private double money;
	//是否评价标志位 ，1：已评价
	private int commentStatus;

	public int getCommentStatus() {
		return commentStatus;
	}

	public void setCommentStatus(int commentStatus) {
		this.commentStatus = commentStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCart() {
		return cart;
	}

	public void setCart(int cart) {
		this.cart = cart;
	}

	public int getMerchandise() {
		return merchandise;
	}

	public void setMerchandise(int merchandise) {
		this.merchandise = merchandise;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

}
