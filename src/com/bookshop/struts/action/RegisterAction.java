package com.bookshop.struts.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
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
import com.bookshop.domain.Memberlevel;
import com.bookshop.service.CategoryService;
import com.bookshop.service.MemberService;
import com.bookshop.service.MemberlevelService;
import com.bookshop.struts.form.LoginForm;
import com.bookshop.struts.form.MemberForm;
import com.bookshop.struts.message.Messages;

@Controller("/registerAction")
public class RegisterAction extends DispatchAction {
	@Autowired
	private MemberService memberService;
	@Autowired
	private CategoryService cateService;
	@Autowired
	private MemberlevelService memberlevelService;
	
	public ActionForward forwardRegister(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Category> cates = cateService.browseCates();
		if(cates!=null)
			request.setAttribute("cateList", cates);
		
		List<Memberlevel> memberlevels = memberlevelService.browseMemberlevels();
		if(memberlevels!=null)
			request.setAttribute("memberlevelList", memberlevels);
		return mapping.findForward("registerMember");
	}
	
	public ActionForward register(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberForm memberForm = (MemberForm) form;
		Member member = new Member();
		
		member.setAddress(new String(memberForm.getAddress().getBytes("iso8859-1"),"gb2312"));
		member.setEmail(memberForm.getEmail());
		Memberlevel memberlevel = memberlevelService.loadMemberlevel(memberForm.getMemberlevel());
		member.setMemberlevel(memberlevel);
		member.setName(new String(memberForm.getName().getBytes("iso8859-1"),"gb2312"));
		member.setPassword(memberForm.getPassword());
		member.setPhone(memberForm.getPhone());
		member.setUsername(memberForm.getUsername());
		member.setZipCode(memberForm.getZipCode());
		member.setRegisterDate(new Date());
		boolean status = memberService.register(member);
		if(status){
			ActionForward forward = new ActionForward();
			forward.setPath("/merAction.do?method=browseIndexMer");
			return forward;
		}
		
		ActionMessages messages = new ActionMessages();
		messages.add("memberReisterStatus", new ActionMessage(Messages.MEMBER_REG_FAILED));
		this.saveErrors(request, messages);
		ActionForward forward = new ActionForward();
		forward.setPath("/registerAction.do?method=forwardRegister");
		return forward;
	}
}
