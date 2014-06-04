package com.bookshop.dao;

import java.util.List;

import com.bookshop.domain.Cart;
import com.bookshop.domain.CartMerchandise;
import com.bookshop.domain.Merchandise;

public interface CartMerDAO {

	public abstract void deleteCartMer(int cartMerId);

	public abstract void deleteCartMersByCartId(int cartId);

	public abstract void updateCartMer(CartMerchandise cartMer);
	
	public abstract CartMerchandise searchCartMer(int cartMerId);

	public abstract CartMerchandise searchCartMer(int cartId, int merId);

	public abstract List<CartMerchandise> searchCartMers(int cartId);
}