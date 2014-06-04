package com.bookshop.service;

import java.util.List;

import com.bookshop.domain.Category;

public interface CategoryService {

	public abstract boolean addCate(Category cate);

	public abstract boolean deleteCate(int cateId);
	
	public abstract boolean updateCate(Category cate);
	
	public abstract Category loadCate(int cateId);

	public abstract List<Category> browseCates();


}