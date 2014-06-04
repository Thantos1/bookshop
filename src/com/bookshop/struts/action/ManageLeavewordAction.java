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
import com.bookshop.domain.Leaveword;
import com.bookshop.domain.Member;
import com.bookshop.service.LeavewordService;
import com.bookshop.struts.message.Messages;

@Controller("/manageLeavewordAction")
public class ManageLeavewordAction extends DispatchAction {
	@Autowired
	private LeavewordService leavewordService;

	public ActionForward browseLeavewords(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if (admin == null || admin.getAdminType() != 3)
			return mapping.findForward("sorry");

		List<Leaveword> leavewords = leavewordService.browseLeavewords();
		if (leavewords != null && leavewords.size() > 0)
			request.setAttribute("leavewordList", leavewords);
		return mapping.findForward("adminLeaveword");
	}

	public ActionForward deleteLeaveword(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if (admin == null || admin.getAdminType() != 3)
			return mapping.findForward("sorry");
		ActionMessages messages = new ActionMessages();
		int leavewordId = Integer.parseInt(request.getParameter("leavewordId"));
		boolean status = leavewordService.deleteLeaveWord(leavewordId);
		if(status)
			messages.add("deleteLeavewordStatus",new ActionMessage(Messages.MANAGE_WORD_DEL_SUCC));
		else
			messages.add("deleteLeavewordStatus",new ActionMessage(Messages.MANAGE_WORD_DEL_FAILED));
		this.saveErrors(request, messages);
		ActionForward forward = new ActionForward();
		forward.setPath("/manageLeavewordAction.do?method=browseLeavewords");
		return forward;
	}

	public ActionForward browseLeaveword(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if (admin == null || admin.getAdminType() != 3)
			return mapping.findForward("sorry");
		int leavewordId = Integer.parseInt(request.getParameter("leavewordId"));
		Leaveword leaveword = leavewordService.loadLeaveword(leavewordId);
		if (leaveword != null)
			request.setAttribute("leaveword", leaveword);

		return mapping.findForward("leavewordInfo");
	}

	public ActionForward answerLeaveword(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if (admin == null || admin.getAdminType() != 3)
			return mapping.findForward("sorry");

		ActionMessages messages = new ActionMessages();
		String answerContent = new String(request.getParameter("answerContent").getBytes("iso8859-1"),"gb2312");
		int leavewordId = Integer.parseInt(request.getParameter("leavewordId"));
		boolean status = leavewordService.answerWord(leavewordId, admin, answerContent);
		if(status)
			messages.add("answerWordStatus",new ActionMessage(Messages.MANAGE_WORD_ANSWER_SUCC));
		else
			messages.add("answerWordStatus",new ActionMessage(Messages.MANAGE_WORD_ANSWER_FAILED));
		this.saveErrors(request, messages);
		ActionForward forward = new ActionForward();
		forward.setPath("/manageLeavewordAction.do?method=browseLeavewords");
		return forward;

	}

}
