package com.bookshop.struts.action;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.bookshop.domain.Cart;
import com.bookshop.domain.CartMerchandise;
import com.bookshop.domain.Category;
import com.bookshop.domain.Comment;
import com.bookshop.domain.Member;
import com.bookshop.domain.Merchandise;
import com.bookshop.domain.Order;
import com.bookshop.service.CartService;
import com.bookshop.service.CategoryService;
import com.bookshop.service.CommentService;
import com.bookshop.service.MemberService;
import com.bookshop.service.MerchandiseService;
import com.bookshop.service.OrderService;
import com.bookshop.struts.form.CommentForm;
import com.bookshop.struts.message.Messages;

@Controller("/commentAction")
public class CommentAction extends DispatchAction {
	@Autowired
	private CommentService commentService;
	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private MerchandiseService merService;
	@Autowired
	private CategoryService cateService;

	public ActionForward addComment(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Member member = (Member) request.getSession().getAttribute("member");
		if (member == null)
			return mapping.findForward("memSorry");

		int cartMerId = Integer.parseInt(request.getParameter("cartMerId"));
		Merchandise mer = cartService.searchMerchandiseByCartMerId(cartMerId);
		request.setAttribute("mer", mer);
		request.getSession().setAttribute("cartMerId", cartMerId);
		return mapping.findForward("comment");
	}
	
	public ActionForward submitComment(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Member member = (Member) request.getSession().getAttribute("member");
		if (member == null)
			return mapping.findForward("memSorry");
		
		int merId=Integer.parseInt(request.getParameter("merId"));
		CommentForm commentForm = (CommentForm)form;
		Comment comment=new Comment();
		String content = new String(commentForm.getCommentContent().getBytes("iso8859-1"),"gb2312");
		content = content.replace("\n", "</br>");
		comment.setCommentContent(content);
		String level = new String(commentForm.getCommentLevel().getBytes("iso8859-1"),"gb2312");
		comment.setCommentLevel(level);
		System.out.println(level);
		comment.setCommentDate(new Date());
		comment.setMember(member);
		comment.setMerchandise(merService.loadMerchandise(merId));
		
		int cartMerId=(Integer) request.getSession().getAttribute("cartMerId");
		ActionForward forward=new ActionForward();
		ActionMessages messages= new ActionMessages();
		boolean status = commentService.addComment(comment);
		//cartMer中的commentStatus置1		
		boolean status1=cartService.updateCommnetStatus(cartMerId);
		if(status==false||status1==false){//评论失败跳转  undone
			messages.add("addCommentStatus",new ActionMessage(Messages.COMMENT_ADD_FAILED));
			//重新评论
			return mapping.findForward("comment");
//			//forward.setPath("/manageMerAction.do?method=browseMers");
//			
//			this.saveErrors(request, messages);
//			return forward;
		}		
		return mapping.findForward("default");		
	}
	
		

}
