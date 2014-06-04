package com.bookshop.service;

import java.util.List;

import com.bookshop.domain.Memberlevel;

public interface MemberlevelService {

	public abstract Memberlevel loadMemberlevel(int memberlevelId);
	
	public abstract List<Memberlevel> browseMemberlevels();
}