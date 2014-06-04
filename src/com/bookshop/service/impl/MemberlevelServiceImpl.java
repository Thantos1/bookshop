package com.bookshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookshop.dao.MemberlevelDAO;
import com.bookshop.domain.Memberlevel;
import com.bookshop.service.MemberlevelService;
@Service
public class MemberlevelServiceImpl implements MemberlevelService{
	@Autowired
	private MemberlevelDAO memberlevelDao;
	
	public Memberlevel loadMemberlevel(int memberlevelId) {
		return memberlevelDao.searchMemberlevel(memberlevelId);
	}

	public List<Memberlevel> browseMemberlevels() {
		return memberlevelDao.searchMemeberlevels();
	}
}
