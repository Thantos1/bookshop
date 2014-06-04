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

import com.bookshop.domain.Category;
import com.bookshop.domain.Member;
import com.bookshop.service.CategoryService;
import com.bookshop.service.MemberService;
import com.bookshop.struts.form.MemberForm;
import com.bookshop.struts.message.Messages;

@Controller("/updateMemberAction")
public class UpdateMemberAction extends DispatchAction {
	@Autowired
	private MemberService memberService;
	@Autowired
	private CategoryService cateService;

	public ActionForward browseMember(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Member member = (Member) request.getSession().getAttribute("member");
		if (member == null)
			return mapping.findForward("memSorry");
		List<Category> cates = cateService.browseCates();
		if(cates!=null && cates.size()>0)
			request.setAttribute("cateList", cates);
		return mapping.findForward("modifyMember");
	}

	public ActionForward updateMember(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Member member = (Member) request.getSession().getAttribute("member");
		if (member == null)
			return mapping.findForward("memSorry");
		List<Category> cates = cateService.browseCates();
		if(cates!=null)
			request.setAttribute("cateList", cates);
		MemberForm memberForm = (MemberForm) form;
		Member mem = memberService.loadMember(member.getId());
		mem.setName(new String(memberForm.getName().getBytes("iso8859-1"),"gb2312"));
		mem.setUsername(memberForm.getUsername());
		mem.setPassword(memberForm.getPassword());
		mem.setPhone(memberForm.getPhone());
		mem.setAddress(new String(memberForm.getAddress().getBytes("iso8859-1"),"gb2312"));
		mem.setZipCode(memberForm.getZipCode());
		mem.setEmail(memberForm.getEmail());
		boolean status = memberService.updateMember(mem);
		ActionMessages messages = new ActionMessages();
		if(status){
			member.setName(new String(memberForm.getName().getBytes("iso8859-1"),"gb2312"));
			member.setUsername(memberForm.getUsername());
			member.setPassword(memberForm.getPassword());
			member.setPhone(memberForm.getPhone());
			member.setAddress(new String(memberForm.getAddress().getBytes("iso8859-1"),"gb2312"));
			member.setZipCode(memberForm.getZipCode());
			member.setEmail(memberForm.getEmail());
			messages.add("updateMemberStatus", new ActionMessage(Messages.MEMBER_MODIFY_SUCC));
		}
		else
			messages.add("updateMemberStatus", new ActionMessage(Messages.MEMBER_MODIFY_FAILED));
		this.saveErrors(request, messages);
		ActionForward forward = new ActionForward();
		forward.setPath("/updateMemberAction.do?method=browseMember");
		return forward;
	}
}
