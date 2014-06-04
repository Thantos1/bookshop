package com.bookshop.dao;

import java.util.List;

import com.bookshop.domain.Leaveword;

public interface LeavewordDAO {

	public abstract void addLeaveword(Leaveword word);

	public abstract void deleteLeaveword(int wordId);

	public abstract void updateLeaveword(Leaveword word);

	public abstract Leaveword searchLeaveword(int wordId);

	public abstract List<Leaveword> searchLeavewords();

	public abstract List<Leaveword> searchLeavewords(int pageNo, int pageSize);
	
	public abstract int countLeaveword();

}