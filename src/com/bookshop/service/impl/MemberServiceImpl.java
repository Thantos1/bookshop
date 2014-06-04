package com.bookshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookshop.dao.MemberDAO;
import com.bookshop.domain.Member;
import com.bookshop.domain.Memberlevel;
import com.bookshop.service.MemberService;
import com.bookshop.service.MemberlevelService;
@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberDAO memberDao;

	public boolean register(Member member) {
		boolean status = memberDao.checkUsername(member.getUsername());
		if(status){
			try{
				memberDao.addMember(member);
			   status = true;
			}catch(Exception e){
				status = false;
				e.printStackTrace();
			}
			return status;
		}
		return false;
	}

	public Member login(String username, String password) {
		Member member = memberDao.searchMember(username, password);
		return member;
	}


	public Member loadMember(int memberId) {
		return memberDao.searchMember(memberId);
	}

	public List<Member> browseMembers() {
		return memberDao.searchMembers();

	}

	public boolean deleteMember(int memberId) {
		
		boolean status = false;
		try{
			memberDao.deleteMember(memberId);
		status = true;
		}catch(Exception e){
			status = false;
			e.printStackTrace();
		}
		return status;
	}

	public boolean updateMember(Member member) {
		
		boolean status = false;
		try{
			memberDao.updateMember(member);
		status = true;
		}catch(Exception e){
			status = false;
			e.printStackTrace();
		}
		return status;
	}
}
