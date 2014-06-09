package com.bookshop.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookshop.dao.CartDAO;
import com.bookshop.dao.CartMerDAO;
import com.bookshop.dao.MerchandiseDAO;
import com.bookshop.domain.Cart;
import com.bookshop.domain.CartMerchandise;
import com.bookshop.domain.Member;
import com.bookshop.domain.Merchandise;
import com.bookshop.service.CartService;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private CartDAO cartDao;
	@Autowired
	private CartMerDAO cartMerDao;
	@Autowired
	private MerchandiseDAO merDao;

	public void buyMerchandise(Member member, Merchandise mer, int num) {
		Cart cart = cartDao.searchCartByMember(member.getId());
		//System.out.println("----------1-------");
		CartMerchandise cartMer = null;
		int favourable = member.getMemberlevel().getFavourable();
		if (cart == null) {// 购物车为空：第一次购买
			cart = new Cart();
			cart.setCartStatus(1);
			cart.setMember(member);
			cart.getMerchandises().add(mer);
			if (mer.getSpecial() == 1)
				cart.setMoney(num * mer.getSprice());
			else {
				cart.setMoney(num * mer.getPrice() * favourable / 100);
			}
			//System.out.println(cart.getMoney()+"------------money");
			cartDao.addCart(cart);
			//System.out.println("----------2-------");
		} else {// 之前将物品加入过购物车，但是未提交订单
			cartMer = cartMerDao.searchCartMer(cart.getId(), mer.getId());

			if (cartMer != null) {// 购物车中已经有过该种商品
				cartMer.setNumber(cartMer.getNumber() + num);
				if (mer.getSpecial() == 1) {
					cartMer.setPrice(mer.getSprice());
					cartMer.setMoney(cartMer.getNumber() * cartMer.getPrice());
				}

				else {
					cartMer.setPrice(mer.getPrice() * favourable / 100);
					cartMer.setMoney(cartMer.getNumber() * cartMer.getPrice());
				}
				cartMer.setCommentStatus(0);
				cartMerDao.updateCartMer(cartMer);
			} else {
				cart.getMerchandises().add(mer);
			}

			if (mer.getSpecial() == 1)
				cart.setMoney(cart.getMoney() + num * mer.getSprice());
			else
				cart.setMoney(cart.getMoney() + num * mer.getPrice()
						* favourable / 100);
			cartDao.updateCart(cart);
			//System.out.println("-----------------");
		}
		if (cartMer == null) {

			cartMer = cartMerDao.searchCartMer(cart.getId(), mer.getId());
			cartMer.setNumber(num);
			if (mer.getSpecial() == 1) {
				cartMer.setPrice(mer.getSprice());
				cartMer.setMoney(num * cartMer.getPrice());
			} else {
				cartMer.setPrice(mer.getPrice() * favourable / 100);
				cartMer.setMoney(num * cartMer.getPrice());
			}
			cartMer.setCommentStatus(0);
			cartMerDao.updateCartMer(cartMer);
		}
	}

	public List<CartMerchandise> browseCart(Member member) {
		Cart cart = this.loadCart(member);
		if (cart == null) {
			cart = new Cart();
			cart.setCartStatus(1);
			cart.setMember(member);
			cart.setMoney(0);
			cartDao.addCart(cart);
		}
		return this.browseCart(cart.getId());
	}

	public List<CartMerchandise> browseCart(int cartId) {

		return cartMerDao.searchCartMers(cartId);
	}

	public void modifyMerCount(int cartMerId, int count) {
		CartMerchandise cartMer = cartMerDao.searchCartMer(cartMerId);
		double money = cartMer.getMoney();
		if (count == 0) {
			this.deleteMerFromCart(cartMerId);
			return;
		}
		cartMer.setNumber(count);
		cartMer.setMoney(cartMer.getNumber() * cartMer.getPrice());
		cartMerDao.updateCartMer(cartMer);
		Cart cart = cartDao.searchCart(cartMer.getCart());
		cart.setMoney(cart.getMoney() - money + cartMer.getMoney());
		cartDao.updateCart(cart);
	}

	public void clearCart(Member member) {
		Cart cart = this.loadCart(member);
		cartMerDao.deleteCartMersByCartId(cart.getId());
		cart.setMoney(0);
		cart.getMerchandises().clear();
		cartDao.updateCart(cart);
	}

	public Cart loadCart(Member member) {

		Cart cart = cartDao.searchCartByMember(member.getId());
		return cart;
	}

	public void deleteMerFromCart(int cartMerId) {
		CartMerchandise cartMer = cartMerDao.searchCartMer(cartMerId);
		cartMerDao.deleteCartMer(cartMerId);
		Cart cart = cartDao.searchCart(cartMer.getCart());
		cart.setMoney(cart.getMoney() - cartMer.getMoney());
		for (Merchandise mer : cart.getMerchandises()) {
			if (mer.getId() == cartMer.getMerchandise())
				cart.getMerchandises().remove(mer);
		}
		cartDao.updateCart(cart);
	}

	public void updateCartStatus(Member member, int status) {
		Cart cart = this.loadCart(member);
		cart.setCartStatus(status);
		cartDao.updateCart(cart);
	}

	@Override
	public CartMerchandise loadCartMerchandiseById(int cartMerId) {
		// TODO Auto-generated method stub
		CartMerchandise cartMer = cartMerDao.searchCartMer(cartMerId);
		return cartMer;
	}

	
	@Override
	public Merchandise searchMerchandiseByCartMerId(int cartMerId) {
		// TODO Auto-generated method stub
		CartMerchandise cartMer = cartMerDao.searchCartMer(cartMerId);
		int merId= cartMer.getMerchandise();
		Merchandise mer= merDao.searchMerchandise(merId);
		return mer;
	}

	@Override
	public boolean updateCommnetStatus(int cartMerId) {
		// TODO Auto-generated method stub
		boolean status = false;
		try{
			CartMerchandise cartMer = cartMerDao.searchCartMer(cartMerId);
			cartMer.setCommentStatus(1);
			cartMerDao.updateCartMer(cartMer);
			status = true;
		}catch(Exception e){
			status = false;
			e.printStackTrace();
		}
		return status;
		
	}
}
