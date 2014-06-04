package com.bookshop.struts.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bookshop.domain.Admin;
import com.bookshop.domain.Member;
import com.bookshop.domain.Merchandise;
import com.bookshop.service.MemberService;
import com.bookshop.struts.message.Messages;

@Controller("/manageMemberAction")
public class ManageMemberAction extends DispatchAction {
	@Autowired
	private MemberService memberService;
	
	public ActionForward browseMembers(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if(admin == null || admin.getAdminType() != 3)
			return mapping.findForward("sorry");
		
		List<Member> members = memberService.browseMembers();
		if(members!=null && members.size()>0)
			request.setAttribute("memberList", members);
		return mapping.findForward("adminMember");
	}
	
	
	public ActionForward browseMember(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if(admin == null || admin.getAdminType() != 3)
			return mapping.findForward("sorry");
		
		int memberId = Integer.parseInt(request.getParameter("memberId"));
		Member member = memberService.loadMember(memberId);
		if(member!=null)
			request.setAttribute("member", member);
		return mapping.findForward("memberInfo");
		
	}
	
	
	public ActionForward deleteMember(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if(admin == null || admin.getAdminType() != 3)
			return mapping.findForward("sorry");
		int memberId = Integer.parseInt(request.getParameter("memberId"));
		boolean status = false;
		try{
			status = memberService.deleteMember(memberId);
		}catch(Exception e){
			System.out.println("----------------------delete member error----------------------------------");
		}
		ActionMessages messages = new ActionMessages();
		if(status)
			messages.add("deleteMemberStatus",new ActionMessage(Messages.MANAGE_MEMBER_DEL_SUCC));
		else
			messages.add("deleteMemberStatus",new ActionMessage(Messages.MANAGE_MEMBER_DEL_FAILED));
		this.saveErrors(request, messages);
		ActionForward forward = new ActionForward();
		forward.setPath("/manageMemberAction.do?method=browseMembers");
		return forward;
		
	}

}
