package com.bookshop.dao;

import java.util.List;

import com.bookshop.domain.Merchandise;

public interface MerchandiseDAO {

	public abstract void addMerchandise(Merchandise mer);

	public abstract void deleteMerchandise(int merId);

	public abstract void updateMerchandise(Merchandise mer);

	public abstract Merchandise searchMerchandise(int merId);

	public abstract List<Merchandise> searchMerchandises(boolean special);
	
	public abstract int countMerchandise(String key,int cateId);

	public abstract List<Merchandise> searchMersByKeyAndCate(int pageNo,
			int pageSize, String key, int cateId);
	
	public abstract int countMerchandise(boolean special);
	
	public abstract List<Merchandise> searchMerchandise(int pageNo,
			int pageSize, boolean special);
	
	public abstract int countMerchandise(int cateId);

	public abstract List<Merchandise> searchMerchandiseByCate(int pageNo,
			int pageSize, int cateId);

}