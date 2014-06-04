package com.bookshop.service;

import java.util.List;

import com.bookshop.domain.Merchandise;

public interface MerchandiseService {

	public abstract boolean addMerchandise(Merchandise mer);

	public abstract boolean deleteMerchandise(int merId);

	public abstract boolean updateMerchandise(Merchandise mer);

	public abstract Merchandise loadMerchandise(int merId);
	
	public abstract List<Merchandise> browseMerchandises(boolean special);
	
	public abstract int countMerchandise(String key,int cateId);
	
	public abstract List<Merchandise> searchMersByKeyAndCate(int pageNo,
			int pageSize, String key, int cateId);
	
	public abstract int countMerchandise(int cateId);
	
	public abstract List<Merchandise> searchMersByCate(int pageNo,
			int pageSize, int cateId);

	public abstract int countMerchandise(boolean special);
	
	public abstract List<Merchandise> searchMerchandise(int pageNo,
			int pageSize, boolean special);
}