package com.bookshop.dao;

import java.util.List;

import com.bookshop.domain.Cart;
import com.bookshop.domain.CartMerchandise;
import com.bookshop.domain.Member;
import com.bookshop.domain.Merchandise;

public interface CartDAO {

	public abstract void addCart(Cart cart);
	
	public abstract void updateCart(Cart cart);
	
	public abstract Cart searchCart(int cartId);

	public abstract Cart searchCartByMember(int memberId);
}