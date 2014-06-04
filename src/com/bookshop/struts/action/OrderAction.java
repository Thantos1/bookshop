package com.bookshop.struts.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bookshop.domain.Admin;
import com.bookshop.domain.CartMerchandise;
import com.bookshop.domain.Category;
import com.bookshop.domain.Member;
import com.bookshop.domain.Merchandise;
import com.bookshop.domain.Order;
import com.bookshop.service.CartService;
import com.bookshop.service.CategoryService;
import com.bookshop.service.MerchandiseService;
import com.bookshop.service.OrderService;

@Controller("/orderAction")
public class OrderAction extends DispatchAction {
	@Autowired
	private OrderService orderService;
	@Autowired
	private CartService cartService;
	@Autowired
	private CategoryService cateService;
	@Autowired
	private MerchandiseService merService;
	
	
	public ActionForward browseOrders(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Member member = (Member) request.getSession().getAttribute("member");
		if(member==null)
			return mapping.findForward("memSorry");
		List<Category> cates = cateService.browseCates();
		if(cates!=null && cates.size()>0)
			request.setAttribute("cateList", cates);
		List<Order> orders = orderService.browseOrders(member);
		if (orders != null && orders.size()>0)
			request.setAttribute("orderList", orders);
		return mapping.findForward("adminOrder");
	}

	
	public ActionForward browseOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Member member = (Member) request.getSession().getAttribute("member");
		if(member==null)
			return mapping.findForward("memSorry");
		List<Category> cates = cateService.browseCates();
		if(cates!=null && cates.size()>0)
			request.setAttribute("cateList", cates);
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		Order order = orderService.loadOrder(orderId);
		if (order != null) {
			request.setAttribute("order", order);
			List<CartMerchandise> cartMers = cartService.browseCart(order.getCart().getId());
			if (cartMers != null && cartMers.size()>0){
				List<Map> result = new ArrayList<Map>();
				Map row = null;
				for(int i=0;i<cartMers.size();i++){
					row = new HashMap();
					CartMerchandise cartMer = cartMers.get(i);
					int merId = cartMer.getMerchandise();
					Merchandise mer = merService.loadMerchandise(merId);
					row.put("merName", mer.getMerName());
					row.put("price",mer.getPrice());
					if(mer.getSpecial()==1)
						row.put("memberPrice", mer.getSprice());
					else
						row.put("memberPrice", (mer.getPrice())*(member.getMemberlevel().getFavourable())/100);
					row.put("merNumber", cartMer.getNumber());
					row.put("money",((Double)row.get("memberPrice")).doubleValue()*(cartMer.getNumber()));
					row.put("merId",mer.getId());
					result.add(row);
					
					
				}
				request.setAttribute("result",result);
			}		
		}
		return mapping.findForward("orderInfo");

	}
	
	
	public ActionForward deleteOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Member member = (Member) request.getSession().getAttribute("member");
		if(member==null)
			return mapping.findForward("memSorry");
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		orderService.deleteOrder(orderId);
		ActionForward forward = new ActionForward();
		forward.setPath("/orderAction.do?method=browseOrders");
		return forward;
	}
}
