package com.bookshop.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookshop.dao.LeavewordDAO;
import com.bookshop.domain.Admin;
import com.bookshop.domain.Leaveword;
import com.bookshop.domain.Member;
import com.bookshop.service.LeavewordService;

@Service
public class LeavewordServiceImpl implements LeavewordService {
	@Autowired
	private LeavewordDAO leavewordDao;

	public boolean leaveWords(Member member, String title, String content) {
		Leaveword word = new Leaveword();
		word.setContent(content);
		word.setMember(member);
		word.setTitle(title);
		word.setLeaveDate(new Date());
		
		boolean status = false;
		try{
			leavewordDao.addLeaveword(word);
		status = true;
		}catch(Exception e){
			status = false;
			e.printStackTrace();
		}
		return status;
	}

	public boolean deleteLeaveWord(int leavewordId) {
		
		boolean status = false;
		try{
			leavewordDao.deleteLeaveword(leavewordId);
		status = true;
		}catch(Exception e){
			status = false;
			e.printStackTrace();
		}
		return status;
	}

	public boolean answerWord(int leavewordId, Admin admin, String content) {
		Leaveword word = leavewordDao.searchLeaveword(leavewordId);
		word.setAdmin(admin);
		word.setAnswerContent(content);
		word.setAnswerDate(new Date());
		
		boolean status = false;
		try{
			 leavewordDao.updateLeaveword(word);
		status = true;
		}catch(Exception e){
			status = false;
			e.printStackTrace();
		}
		return status;
	}

	public List<Leaveword> browseLeavewords(int pageNo, int pageSize) {
		return leavewordDao.searchLeavewords(pageNo, pageSize);
	}

	public Leaveword loadLeaveword(int leavewordId) {
		return leavewordDao.searchLeaveword(leavewordId);
	}

	public List<Leaveword> browseLeavewords() {
		return leavewordDao.searchLeavewords();
	}

	public int countLeaveword() {
		return leavewordDao.countLeaveword();
	}

}
