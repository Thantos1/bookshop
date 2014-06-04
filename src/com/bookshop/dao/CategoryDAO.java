package com.bookshop.dao;

import java.util.List;

import com.bookshop.domain.Category;

public interface CategoryDAO {

	public abstract void addCate(Category cate);

	public abstract void deleteCate(int cateId);

	public abstract void updateCate(Category cate);
	
	public abstract Category searchCate(int cateId);

	public abstract List<Category> searchCates();
}