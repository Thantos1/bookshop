package com.bookshop.dao;

import java.util.List;

import com.bookshop.domain.Member;

public interface MemberDAO {

	public abstract void addMember(Member member);

	public abstract void deleteMember(int memberId);

	public abstract void updateMember(Member member);

	public abstract Member searchMember(int memberId);

	public abstract List<Member> searchMembers();

	public abstract boolean checkUsername(String username);

	public abstract Member searchMember(String username, String password);

}