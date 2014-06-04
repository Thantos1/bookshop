package com.bookshop.struts.action;

import java.util.Date;

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

import com.bookshop.domain.Member;
import com.bookshop.service.MemberService;
import com.bookshop.struts.form.LoginForm;
import com.bookshop.struts.message.Messages;

@Controller("/memberLoginAction")
public class MemberLoginAction extends DispatchAction {
	@Autowired
	private MemberService memberService;
	
	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LoginForm loginForm = (LoginForm) form;
		Member member = memberService.login(loginForm.getUsername(),loginForm.getPassword());
		if(member==null){
			ActionMessages messages = new ActionMessages();
			messages.add("memberLoginStatus", new ActionMessage(Messages.MEMBER_LOGIN_FAILED));
			this.saveErrors(request, messages);
		}
		else{
			member.setLastLoginDate(new Date());
			member.setLoginTimes(member.getLoginTimes()+1);
			memberService.updateMember(member);
			request.getSession().setAttribute("member", member);
		}
		ActionForward forward = new ActionForward();
		forward.setPath("/merAction.do?method=browseIndexMer");
		return forward;
	}
	
	public ActionForward logout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession().removeAttribute("member");
		ActionForward forward = new ActionForward();
		forward.setPath("/merAction.do?method=browseIndexMer");
		return forward;
	}

}
