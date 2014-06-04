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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bookshop.domain.Admin;
import com.bookshop.domain.CartMerchandise;
import com.bookshop.domain.Member;
import com.bookshop.domain.Merchandise;
import com.bookshop.domain.Order;
import com.bookshop.service.CartService;
import com.bookshop.service.MerchandiseService;
import com.bookshop.service.OrderService;
import com.bookshop.struts.message.Messages;

@Controller("/manageOrderAction")
public class ManageOrderAction extends DispatchAction {
	@Autowired
	private OrderService orderService;
	@Autowired
	private CartService cartService;
	
	@Autowired
	private MerchandiseService merService;

	public ActionForward browseOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if(admin == null || admin.getAdminType() != 2)
			return mapping.findForward("sorry");
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
						row.put("memberPrice", (mer.getPrice())*(order.getMember().getMemberlevel().getFavourable())/100);
					row.put("merNumber", cartMer.getNumber());
					row.put("money",((Double)row.get("memberPrice")).doubleValue()*(cartMer.getNumber()));
					result.add(row);
					
					
				}
				request.setAttribute("result",result);
			}		
		}
	
		return mapping.findForward("orderInfo");

	}

	public ActionForward acceptOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if(admin == null || admin.getAdminType() != 2)
			return mapping.findForward("sorry");

		int orderId = Integer.parseInt(request.getParameter("orderId"));
		orderService.acceptOrder(orderId);
		ActionForward forward = new ActionForward();
		forward.setPath("/manageOrderAction.do?method=browseOrders");
		return forward;
	}

	public ActionForward endOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if(admin == null || admin.getAdminType() != 2)
			return mapping.findForward("sorry");
		
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		orderService.endOrder(orderId);
		ActionForward forward = new ActionForward();
		forward.setPath("/manageOrderAction.do?method=browseOrders");
		return forward;
	}

	public ActionForward deleteOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if(admin == null || admin.getAdminType() != 2)
			return mapping.findForward("sorry");
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		boolean status = orderService.deleteOrder(orderId);
		ActionMessages messages = new ActionMessages();
		if(status)
			messages.add("deleteOrderStatus",new ActionMessage(Messages.MANAGE_ORDER_DEL_SUCC));
		else
			messages.add("deleteOrderStatus",new ActionMessage(Messages.MANAGE_ORDER_DEL_FAILED));
		this.saveErrors(request, messages);
		ActionForward forward = new ActionForward();
		forward.setPath("/manageOrderAction.do?method=browseOrders");
		return forward;
	}

	public ActionForward browseOrders(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if(admin == null || admin.getAdminType() != 2)
			return mapping.findForward("sorry");
		List<Order> orders = orderService.browseOrders();
		if (orders != null && orders.size()>0)
			request.setAttribute("orderList", orders);
		return mapping.findForward("adminOrder");
	}
}
