package com.bookshop.struts.action;

import java.text.DecimalFormat;
import java.util.ArrayList;
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
import com.bookshop.domain.Member;
import com.bookshop.domain.Merchandise;
import com.bookshop.domain.Order;
import com.bookshop.service.CartService;
import com.bookshop.service.CategoryService;
import com.bookshop.service.MemberService;
import com.bookshop.service.MerchandiseService;
import com.bookshop.service.OrderService;
import com.bookshop.struts.message.Messages;

@Controller("/manageCartAction")
public class ManageCartAction extends DispatchAction {
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
	
	public ActionForward browseCart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Member member = (Member) request.getSession().getAttribute("member");
		if(member==null)
			return mapping.findForward("memSorry");
		ActionForward forward = null;
		List<Category> cates = cateService.browseCates();
		if(cates!=null)
			request.setAttribute("cateList", cates);
		List<CartMerchandise> cartMers = cartService.browseCart(member);
		if(cartMers!=null && cartMers.size()>0){
			List result = new ArrayList();
			Map row = null;
			double money = 0;
			DecimalFormat df = new DecimalFormat(".##");
			for(int i=0;i<cartMers.size();i++){
				CartMerchandise cartMer = cartMers.get(i);
				Merchandise mer = merService.loadMerchandise(cartMer.getMerchandise());
				row = new HashMap();
				row.put("merId", mer.getId());
				row.put("selId", cartMer.getId());					
				row.put("merName", mer.getMerName().trim());
				row.put("memprice", Double.valueOf(df.format(cartMer.getPrice())));
				row.put("price",  Double.valueOf(df.format(mer.getPrice())));						
				row.put("number", cartMer.getNumber());
				row.put("money", cartMer.getMoney());
				money = money + cartMer.getMoney();
				result.add(row);
				
			}
			
			request.setAttribute("result", result);
			request.setAttribute("totalMoney", Double.valueOf(df.format(money)));	
		}
		
		return mapping.findForward("cart");
	}
	
	
	
	public ActionForward buyMerchandise(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		Member member = (Member) request.getSession().getAttribute("member");
		if(member==null)
			return mapping.findForward("memSorry");
		
		int merId = Integer.parseInt(request.getParameter("merId"));
		Merchandise mer = merService.loadMerchandise(merId);
		cartService.buyMerchandise(member,mer, 1);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/manageCartAction.do?method=browseCart");
		return forward;
		
		
	}
	
	public ActionForward deleteMerFromCart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Member member = (Member) request.getSession().getAttribute("member");
		if(member==null)
			return mapping.findForward("memSorry");
		
		int cartMerId = Integer.parseInt(request.getParameter("cartMerId"));
		cartService.deleteMerFromCart(cartMerId);
		ActionForward forward = new ActionForward();
		forward.setPath("/manageCartAction.do?method=browseCart");
		return forward;
	}
	
	
	
	public ActionForward clearCart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Member member = (Member) request.getSession().getAttribute("member");
		if(member==null)
			return mapping.findForward("memSorry");
		
		cartService.clearCart(member);
		ActionForward forward = new ActionForward();
		forward.setPath("/manageCartAction.do?method=browseCart");
		return forward;
	}
	
	
	public ActionForward modifyMerCount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Member member = (Member) request.getSession().getAttribute("member");
		if(member==null)
			return mapping.findForward("memSorry");
		int cartMerId = Integer.parseInt(request.getParameter("cartMerId"));
		int count = Integer.parseInt(request.getParameter("merCount"));
		cartService.modifyMerCount(cartMerId,count);
		ActionForward forward = new ActionForward();
		forward.setPath("/manageCartAction.do?method=browseCart");
		return forward;	
	}
	

	
	
	public ActionForward checkOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Member member = (Member) request.getSession().getAttribute("member");
		if(member==null)
			return mapping.findForward("memSorry");
		List<CartMerchandise> cartMers = cartService.browseCart(member);
		if(cartMers==null || cartMers.size()==0){
			ActionMessages messages = new ActionMessages();
			messages.add("checkOrderStatus",new ActionMessage(Messages.ORDER_CHECK_FAILED));
			this.saveErrors(request, messages);
			ActionForward forward = new ActionForward();
			forward.setPath("/manageCartAction.do?method=browseCart");
			return forward;
		}
		List<Category> cates = cateService.browseCates();
		if(cates!=null && cates.size()>0)
			request.setAttribute("cateList", cates);
		return mapping.findForward("checkOrder");
	}
	
	
	public ActionForward submitOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Member member = (Member) request.getSession().getAttribute("member");
		if(member==null)
			return mapping.findForward("memSorry");
		
		String name = new String(request.getParameter("name").getBytes("iso8859-1"),"gb2312");
		String phone = request.getParameter("phone");
		String zipCode = request.getParameter("zipCode");
		String address = new String(request.getParameter("address").getBytes("iso8859-1"),"gb2312");
		member.setName(name);
		member.setPhone(phone);
		member.setZipCode(zipCode);
		member.setAddress(address);
		memberService.updateMember(member);
		Cart cart = cartService.loadCart(member);
		Order order = orderService.submitOrder(member);
		cartService.updateCartStatus(member, 0);
		request.setAttribute("order", order);
		return mapping.findForward("submitOrder");
	}

}
