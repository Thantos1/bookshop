package com.bookshop.struts.action;

import java.util.Date;
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
import com.bookshop.domain.Leaveword;
import com.bookshop.domain.Member;
import com.bookshop.service.CategoryService;
import com.bookshop.service.LeavewordService;
import com.bookshop.struts.form.LeavewordForm;
import com.bookshop.struts.message.Messages;

@Controller("/leavewordAction")
public class LeavewordAction extends DispatchAction {
	@Autowired
	private LeavewordService wordService;
	@Autowired
	private CategoryService cateService;

	public ActionForward browseLeavewords(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		List<Category> cates = cateService.browseCates();
		if(cates!=null && cates.size()>0)
			request.setAttribute("cateList", cates);
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		int pageSize = 3;
		List<Leaveword> words = wordService.browseLeavewords(pageNo, pageSize);
		if (words != null && words.size()>0)
			request.setAttribute("leavewordList", words);

		int totalCount = wordService.countLeaveword();
		request.setAttribute("totalCount", new Integer(totalCount));
		int totalPages = totalCount/pageSize;
		if((totalCount%pageSize)!=0)
			totalPages=totalPages+1;
		request.setAttribute("totalPages", new Integer(totalPages));
		return mapping.findForward("leavewords");
	}

	public ActionForward leaveWords(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Member member = (Member) request.getSession().getAttribute("member");
		if (member == null)
			return mapping.findForward("memSorry");
		LeavewordForm wordForm = (LeavewordForm) form;
		String title = new String(wordForm.getTitle().getBytes("iso8859-1"),"gb2312");
		String content = new String(wordForm.getContent().getBytes("iso8859-1"),"gb2312");
		boolean status = wordService.leaveWords(member, title, content);
		ActionMessages messages = new ActionMessages();
		if(status)
			messages.add("addLeavewordStatus",new ActionMessage(Messages.WORD_ADD_SUCC));
		else
			messages.add("addLeavewordStatus",new ActionMessage(Messages.WORD_ADD_FAILED));
		this.saveErrors(request, messages);
		ActionForward forward = new ActionForward();
		forward.setPath("/leavewordAction.do?method=browseLeavewords");
		return forward;
	}

}
