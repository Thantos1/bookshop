package com.bookshop.service;

import java.util.List;

import com.bookshop.domain.Cart;
import com.bookshop.domain.CartMerchandise;
import com.bookshop.domain.Comment;
import com.bookshop.domain.Member;
import com.bookshop.domain.Merchandise;

public interface CommentService {

	public abstract boolean addComment(Comment comment);

	public abstract List<Comment> searchCommentsByMerId(int merId,int pageNo,int pageSize);
	
	public abstract int countCommentsByMerId( int merId);
}