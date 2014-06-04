package com.bookshop.dao;

import java.util.List;

import com.bookshop.domain.Memberlevel;

public interface MemberlevelDAO {
	public abstract Memberlevel searchMemberlevel(int memberlevelId);

	public abstract List<Memberlevel> searchMemeberlevels();
}