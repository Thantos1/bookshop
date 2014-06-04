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
import com.bookshop.service.AdminService;
import com.bookshop.struts.form.AdminForm;
import com.bookshop.struts.message.Messages;

@Controller("/manageAdminAction")
public class ManageAdminAction extends DispatchAction{
	@Autowired
	private AdminService adminService;

	public ActionForward addAdmin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Admin admin1 = (Admin) request.getSession().getAttribute("admin");
		if(admin1==null || admin1.getAdminType()!=4)
			return mapping.findForward("sorry");
		ActionMessages messages = new ActionMessages();
		AdminForm adminForm = (AdminForm) form;
		Admin admin = new Admin();
		admin.setAdminType(adminForm.getAdminType());
		admin.setName(new String(adminForm.getName().getBytes("iso8859-1"),"gb2312"));
		admin.setUsername(adminForm.getUsername());
		admin.setPassword(adminForm.getPassword());
		boolean status = adminService.addAdmin(admin);
		if(status)
			messages.add("addAdminStatus",new ActionMessage(Messages.MANAGE_ADMIN_ADD_SUCC));
		else
			messages.add("addAdminStatus",new ActionMessage(Messages.MANAGE_ADMIN_ADD_FAILED));
		this.saveErrors(request, messages);		
		ActionForward forward = new ActionForward();
		forward.setPath("/manageAdminAction.do?method=browseAdmins");
		return forward;
	}
	
	public ActionForward deleteAdmin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if(admin==null || admin.getAdminType()!=4)
			return mapping.findForward("sorry");
		ActionMessages messages = new ActionMessages();
		int adminId = Integer.parseInt(request.getParameter("adminId"));
		Admin admin1 = adminService.loadAdmin(adminId);
		boolean status = adminService.deleteAdmin(admin1);
		if(status)
			messages.add("deleteAdminStatus",new ActionMessage(Messages.MANAGE_ADMIN_DEL_SUCC));
		else
			messages.add("deleteAdminStatus",new ActionMessage(Messages.MANAGE_ADMIN_DEL_FAILED));
		this.saveErrors(request, messages);
		ActionForward forward = new ActionForward();
		forward.setPath("/manageAdminAction.do?method=browseAdmins");
		return forward;
		
	}
	
	
	public ActionForward updateAdmin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Admin admin1 = (Admin) request.getSession().getAttribute("admin");
		if(admin1==null || admin1.getAdminType()!=4)
			return mapping.findForward("sorry");
		AdminForm adminForm = (AdminForm) form;
		ActionMessages messages = new ActionMessages();
		Admin admin = new Admin();
		admin.setAdminType(adminForm.getAdminType());
		admin.setName(new String(adminForm.getName().getBytes("iso8859-1"),"gb2312"));
		admin.setUsername(adminForm.getUsername());
		admin.setPassword(adminForm.getPassword());
		admin.setId(Integer.parseInt(request.getParameter("adminId")));
		boolean status = adminService.updateAdmin(admin);
		if(status)
			messages.add("updateAdminStatus",new ActionMessage(Messages.MANAGE_ADMIN_MODIFY_SUCC));
		else
			messages.add("updateAdminStatus",new ActionMessage(Messages.MANAGE_ADMIN_MODIFY_FAILED));
		this.saveErrors(request, messages);	
		ActionForward forward = new ActionForward();
		forward.setPath("/manageAdminAction.do?method=browseAdmins");
		return forward;
		
	}
	
	
	public ActionForward browseAdmins(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Admin admin = (Admin) request.getSession().getAttribute("admin");	
		if(admin==null || admin.getAdminType()!=4)
			return mapping.findForward("sorry");
		List<Admin> admins = adminService.browseAdmins();
		if(admins!=null && admins.size()>0)
			request.setAttribute("adminList", admins);
		return mapping.findForward("adminUser");
	}
	
	
	public ActionForward loadAdmin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Admin admin1 = (Admin) request.getSession().getAttribute("admin");
		if(admin1==null || admin1.getAdminType()!=4)
			return mapping.findForward("sorry");
		
		int adminId = Integer.parseInt(request.getParameter("adminId"));
		Admin admin = adminService.loadAdmin(adminId);
		if(admin!=null)
			request.setAttribute("admin", admin);
		return mapping.findForward("adminModify");
	}
	
}
