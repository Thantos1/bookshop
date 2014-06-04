package com.bookshop.service;

import java.util.List;

import com.bookshop.domain.Member;

public interface MemberService {

	public abstract boolean register(Member member);

	public abstract Member login(String username, String password);

	public abstract Member loadMember(int memberId);

	public abstract List<Member> browseMembers();

	public abstract boolean deleteMember(int memberId);

	public abstract boolean updateMember(Member member);

}