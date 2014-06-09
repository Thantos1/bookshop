package com.bookshop.service;

import java.util.List;

import com.bookshop.domain.Cart;
import com.bookshop.domain.CartMerchandise;
import com.bookshop.domain.Member;
import com.bookshop.domain.Merchandise;

public interface CartService {

	public abstract void buyMerchandise(Member member, Merchandise mer, int num);

	public abstract List<CartMerchandise> browseCart(Member member);
	
	public abstract List<CartMerchandise> browseCart(int cartId);
	
	public abstract void modifyMerCount(int cartMerId,int count);

	public abstract void clearCart(Member member);

	public abstract Cart loadCart(Member member);
	
	//CartMerchandise没有自己的service，用的cart的service来提供业务逻辑，应给分离
	public abstract CartMerchandise loadCartMerchandiseById(int cartMerId);
	
	public abstract Merchandise searchMerchandiseByCartMerId(int cartMerId);

	public abstract void deleteMerFromCart(int cartMerId);
	
	public abstract void updateCartStatus(Member member,int status);
	
	public abstract boolean updateCommnetStatus(int cartMerId);

}