package com.bookshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookshop.dao.CartMerDAO;
import com.bookshop.dao.CommentDAO;
import com.bookshop.domain.CartMerchandise;
import com.bookshop.domain.Comment;
import com.bookshop.service.CommentService;
@Service 
public class CommentServiceImpl implements CommentService{
	@Autowired
	private CommentDAO commentDao;
	
	@Override
	public boolean addComment(Comment comment) {
		// TODO Auto-generated method stub
		boolean status = false;
		try{
			commentDao.addComment(comment);
			
			status = true;
		}catch(Exception e){
			status = false;
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public List<Comment> searchCommentsByMerId(int merId, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		List<Comment> result=commentDao.searchCommentByMerchandiase(merId, pageNo, pageSize);
		return result;
	}

	@Override
	public int countCommentsByMerId(int merId) {
		// TODO Auto-generated method stub
		int count = commentDao.countCommentsByMerId(merId);
		return count;
	}
	
}
