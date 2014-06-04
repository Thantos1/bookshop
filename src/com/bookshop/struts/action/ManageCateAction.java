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
import com.bookshop.domain.Category;
import com.bookshop.service.CategoryService;
import com.bookshop.struts.form.CateForm;
import com.bookshop.struts.message.Messages;

@Controller("/manageCateAction")
public class ManageCateAction extends DispatchAction {

	@Autowired
	private CategoryService cateService;

	public ActionForward addCate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Admin admin = (Admin) request.getSession().getAttribute("admin");

		if (admin == null || admin.getAdminType() != 1)
			return mapping.findForward("sorry");
		ActionMessages messages = new ActionMessages();
		CateForm cateForm = (CateForm) form;
		Category cate = new Category();
		cate.setCateName(new String(cateForm.getCateName().getBytes("iso8859-1"),"gb2312"));
		cate.setCateDesc(new String(cateForm.getCateDesc().getBytes("iso8859-1"),"gb2312"));
		boolean status = cateService.addCate(cate);
		if(status)
			messages.add("addCateStatus",new ActionMessage(Messages.MANAGE_CATE_ADD_SUCC));
		else
			messages.add("addCateStatus",new ActionMessage(Messages.MANAGE_CATE_ADD_FAILED));
		this.saveErrors(request, messages);
		ActionForward forward = new ActionForward();
		forward.setPath("/manageCateAction.do?method=browseCates");
		return forward;
	}

	public ActionForward deleteCate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if (admin == null || admin.getAdminType() != 1)
			return mapping.findForward("sorry");
		
		ActionMessages messages = new ActionMessages();
		int cateId = Integer.parseInt(request.getParameter("cateId"));
		boolean status = cateService.deleteCate(cateId);
		if(status)
			messages.add("deleteCateStatus",new ActionMessage(Messages.MANAGE_CATE_DEL_SUCC));
		else
			messages.add("deleteCateStatus",new ActionMessage(Messages.MANAGE_CATE_DEL_FAILED));
		
		this.saveErrors(request, messages);
		ActionForward forward = new ActionForward();
		forward.setPath("/manageCateAction.do?method=browseCates");
		return forward;
	}

	public ActionForward updateCate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Admin admin = (Admin) request.getSession().getAttribute("admin");

		if (admin == null || admin.getAdminType() != 1)
			return mapping.findForward("sorry");
		ActionMessages messages = new ActionMessages();
		int cateId = Integer.parseInt(request.getParameter("cateId"));
		CateForm cateForm = (CateForm) form;
		Category cate = new Category();
		cate.setId(cateId);
		cate.setCateName(new String(cateForm.getCateName().getBytes("iso8859-1"),"gb2312"));
		cate.setCateDesc(new String(cateForm.getCateDesc().getBytes("iso8859-1"),"gb2312"));
		boolean status = cateService.updateCate(cate);
		if(status)
			messages.add("updateCateStatus",new ActionMessage(Messages.MANAGE_CATE_MODIFY_SUCC));
		else
			messages.add("updateCateStatus",new ActionMessage(Messages.MANAGE_CATE_MODIFY_FAILED));
		this.saveErrors(request, messages);
		ActionForward forward = new ActionForward();
		forward.setPath("/manageCateAction.do?method=browseCates");
		return forward;
	}

	public ActionForward loadCate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Admin admin = (Admin) request.getSession().getAttribute("admin");

		if (admin == null || admin.getAdminType() != 1)
			return mapping.findForward("sorry");
		int cateId = Integer.parseInt(request.getParameter("cateId"));
		Category cate = cateService.loadCate(cateId);
		if (cate != null)
			request.setAttribute("cate", cate);
		return mapping.findForward("modifyCate");
	}

	public ActionForward browseCates(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Admin admin = (Admin) request.getSession().getAttribute("admin");

		if (admin == null || admin.getAdminType() != 1)
			return mapping.findForward("sorry");
		List<Category> cates = cateService.browseCates();
		if (cates != null && cates.size()>0)
			request.setAttribute("cateList", cates);
		return mapping.findForward("adminCate");
	}

}
