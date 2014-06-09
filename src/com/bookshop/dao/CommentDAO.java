package com.bookshop.dao;

import java.util.List;


import com.bookshop.domain.Cart;
import com.bookshop.domain.Comment;


public interface CommentDAO {

	public abstract void addComment(Comment comment);
	
	public abstract void updateCart(Comment comment);

	public Comment searchCart(int commentId);		
	
	public abstract List<Comment> searchCommentByMerchandiase(int merId,int pageNo, int pageSize);
	
	public abstract int countCommentsByMerId(int merId);
}