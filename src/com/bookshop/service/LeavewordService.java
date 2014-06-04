package com.bookshop.service;

import java.util.List;

import com.bookshop.domain.Admin;
import com.bookshop.domain.Leaveword;
import com.bookshop.domain.Member;

public interface LeavewordService {

	public abstract boolean leaveWords(Member member, String title,String content);
	
	public abstract boolean deleteLeaveWord(int leavewordId);

	public abstract boolean answerWord(int leavewordId, Admin admin, String content);

	public abstract List<Leaveword> browseLeavewords(int pageNo, int pageSize);

	public abstract Leaveword loadLeaveword(int leavewordId);

	public abstract List<Leaveword> browseLeavewords();

	public abstract int countLeaveword();

}