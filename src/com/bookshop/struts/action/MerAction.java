package com.bookshop.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bookshop.domain.Category;
import com.bookshop.domain.Merchandise;
import com.bookshop.service.CategoryService;
import com.bookshop.service.MerchandiseService;

@Controller("/merAction")
public class MerAction extends DispatchAction {
	@Autowired
	private MerchandiseService merService;
	@Autowired
	private CategoryService cateService;
	
	
	public ActionForward browseIndexMer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int pageNo = 1, pageSize = 3;
		List<Merchandise> smers = merService.searchMerchandise(pageNo,pageSize,true);
		List<Merchandise> mers = merService.searchMerchandise(pageNo,pageSize,false);
		List<Category> cates = cateService.browseCates();
		if (smers != null)
			request.setAttribute("smerList", smers);
		if (mers != null)
			request.setAttribute("merList", mers);
		if (cates != null)
			request.setAttribute("cateList", cates);
		
		return mapping.findForward("default");
	}
	
	
	public ActionForward browseMerchandise(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int merId = Integer.parseInt(request.getParameter("merId"));
		Merchandise mer = merService.loadMerchandise(merId);
		if(mer!=null)
			request.setAttribute("mer", mer);
		
		List<Category> cates = cateService.browseCates();
		if(cates!=null && cates.size()>0)
			request.setAttribute("cateList", cates);
		return mapping.findForward("showMer");
		
	}
	
	
	
	public ActionForward searchMerByKeyAndCate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String key = request.getParameter("key");
		//key= new String(key.getBytes("ISO-8859-1"),"UTF-8");
		System.out.println("key:"+key);
		int cateId = Integer.parseInt(request.getParameter("cateId"));
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		int pageSize = 15;
		List<Merchandise> mers = merService.searchMersByKeyAndCate(pageNo,pageSize, key, cateId);
		if (mers != null)
			request.setAttribute("merList", mers);
		
		List<Category> cates = cateService.browseCates();
		if(cates!=null)
			request.setAttribute("cateList", cates);
		
		int totalCount = merService.countMerchandise(key, cateId);
		request.setAttribute("totalCount", totalCount);
		int totalPages = totalCount / pageSize;
		if(totalCount%pageSize!=0)
			totalPages = totalPages+1;
		request.setAttribute("totalPages", totalPages);
		
		return mapping.findForward("searchMer");  
	}

	public ActionForward searchMerByCate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int cateId = Integer.parseInt(request.getParameter("cateId"));
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		int pageSize = 10;
		List<Category> cates = cateService.browseCates();
		if(cates!=null)
			request.setAttribute("cateList", cates);
		List<Merchandise> mers = merService.searchMersByCate(pageNo, pageSize,cateId);
		if (mers != null)
			request.setAttribute("merList", mers);
		
		int totalCount = merService.countMerchandise(cateId);
		request.setAttribute("totalCount", totalCount);
		int totalPages = totalCount / pageSize;
		if(totalCount%pageSize!=0)
			totalPages = totalPages+1;
		request.setAttribute("totalPages", totalPages);
		return mapping.findForward("searchMer");
	}
	
	
	public ActionForward browseMers(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int isSpecial = Integer.parseInt(request.getParameter("special"));
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		int pageSize = 10;
		List<Category> cates = cateService.browseCates();
		if(cates!=null)
			request.setAttribute("cateList", cates);
		if(isSpecial==1){
			int totalCount = merService.countMerchandise(true);
			request.setAttribute("totalCount", totalCount);
			int totalPages = totalCount / pageSize;
			if((totalCount%pageSize)!=0)
				totalPages = totalPages+1;
			request.setAttribute("totalPages", totalPages);
			List<Merchandise> smers = merService.searchMerchandise(pageNo, pageSize, true);
			if(smers!=null)
				request.setAttribute("smerList", smers);
		}
		else{
			int totalCount = merService.countMerchandise(false);
			request.setAttribute("totalCount", totalCount);
			int totalPages = totalCount / pageSize;
			if((totalCount%pageSize)!=0)
				totalPages = totalPages+1;
			request.setAttribute("totalPages", totalPages);
			List<Merchandise> mers = merService.searchMerchandise(pageNo, pageSize, false);
			if(mers!=null)
				request.setAttribute("merList", mers);
		}
		
		if(isSpecial==1)
			return mapping.findForward("browseSMer");
		else
			return mapping.findForward("browseMer");
			
	}

}
