package com.bookshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookshop.dao.MerchandiseDAO;
import com.bookshop.domain.Merchandise;
import com.bookshop.service.MerchandiseService;
@Service
public class MerchandiseServiceImpl implements MerchandiseService{
	@Autowired
	private MerchandiseDAO merDao;

	
	public boolean addMerchandise(Merchandise mer) {
		
		boolean status = false;
		try{
			 merDao.addMerchandise(mer);
		status = true;
		}catch(Exception e){
			status = false;
			e.printStackTrace();
		}
		return status;
	}

	public boolean deleteMerchandise(int merId) {

		boolean status = false;
		try{
			merDao.deleteMerchandise(merId);
		status = true;
		}catch(Exception e){
			status = false;
			e.printStackTrace();
		}
		return status;
	}

	
	public boolean updateMerchandise(Merchandise mer) {
		
		boolean status = false;
		try{
			merDao.updateMerchandise(mer);
		status = true;
		}catch(Exception e){
			status = false;
			e.printStackTrace();
		}
		return status;
	}


	public Merchandise loadMerchandise(int merId) {
		return merDao.searchMerchandise(merId);
		
	}

	
	public List<Merchandise> browseMerchandises(boolean special) {
		return merDao.searchMerchandises(special);
		
	}

    public int countMerchandise(String key, int cateId) {   
		return merDao.countMerchandise(key, cateId);
	}

	public List<Merchandise> searchMersByKeyAndCate(int pageNo,int pageSize,String key, int cateId) {
		return merDao.searchMersByKeyAndCate(pageNo, pageSize, key, cateId);
	}
	

	public int countMerchandise(int cateId) {
		return merDao.countMerchandise(cateId);
	}
	
	public List<Merchandise> searchMersByCate(int pageNo,int pageSize,int cateId){
		return merDao.searchMerchandiseByCate(pageNo, pageSize, cateId);
	}
	
    public int countMerchandise(boolean special) {	
		return merDao.countMerchandise(special);
	}

	public List<Merchandise> searchMerchandise(int pageNo,int pageSize,boolean special){
		return merDao.searchMerchandise(pageNo, pageSize, special);	
	}
}
