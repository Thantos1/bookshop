package com.bookshop.struts.action;

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
import com.bookshop.service.AdminService;
import com.bookshop.struts.form.LoginForm;
import com.bookshop.struts.message.Messages;

@Controller("/adminLoginAction")
public class AdminLoginAction extends DispatchAction {
	@Autowired
	private AdminService adminService;

	public ActionForward login(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginForm loginForm = (LoginForm)form;
		Admin admin = adminService.login(loginForm.getUsername(),loginForm.getPassword());
		if (admin != null) {
			request.getSession().setAttribute("admin", admin);
			return mapping.findForward("adminIndex");
		}
	
		ActionMessages messages = new ActionMessages();
		messages.add("adminLoginStatus",new ActionMessage(Messages.ADMIN_LOGIN_FAILED));
		this.saveErrors(request, messages);
		return mapping.findForward("adminLogin");
	}

	public ActionForward logout(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession().removeAttribute("admin");
		return mapping.findForward("adminLogin");
	}


}
