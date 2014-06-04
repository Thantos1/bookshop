package com.bookshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookshop.dao.CategoryDAO;
import com.bookshop.domain.Category;
import com.bookshop.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDAO cateDao;

	public boolean addCate(Category cate) {
	
		
		boolean status = false;
		try{
			cateDao.addCate(cate);
		status = true;
		}catch(Exception e){
			status = false;
			e.printStackTrace();
		}
		return status;
	}

	public boolean deleteCate(int cateId) {
		
		boolean status = false;
		try{
			cateDao.deleteCate(cateId);
		status = true;
		}catch(Exception e){
			status = false;
			e.printStackTrace();
		}
		return status;

	}
	
	public boolean updateCate(Category cate) {
		
		boolean status = false;
		try{
			cateDao.updateCate(cate);
		status = true;
		}catch(Exception e){
			status = false;
			e.printStackTrace();
		}
		return status;
	}


	public Category loadCate(int cateId) {
		return cateDao.searchCate(cateId);
	}
	
	public List<Category> browseCates() {
		return cateDao.searchCates();
	}
}
