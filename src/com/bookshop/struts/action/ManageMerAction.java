package com.bookshop.struts.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bookshop.domain.Admin;
import com.bookshop.domain.Category;
import com.bookshop.domain.Merchandise;
import com.bookshop.service.CategoryService;
import com.bookshop.service.MerchandiseService;
import com.bookshop.struts.form.MerForm;
import com.bookshop.struts.message.Messages;

@Controller("/manageMerAction")
public class ManageMerAction extends DispatchAction {
	@Autowired
	private MerchandiseService merService;
	@Autowired
	private CategoryService cateService;

	public ActionForward forwardAddMer(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if(admin==null || admin.getAdminType()!=1)
			return mapping.findForward("sorry");
		
		List<Category> cates = cateService.browseCates();
		if(cates!=null && cates.size()>0)
			request.setAttribute("cateList",cates);
		
		return mapping.findForward("addMer");
		
	}
	
	
	public ActionForward forwardAddSMer(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if(admin==null || admin.getAdminType()!=1)
			return mapping.findForward("sorry");
		
		List<Category> cates = cateService.browseCates();
		if(cates!=null && cates.size()>0)
			request.setAttribute("cateList",cates);
		
		return mapping.findForward("addSMer");
		
	}

	
	public ActionForward addMer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if(admin==null || admin.getAdminType()!=1)
			return mapping.findForward("sorry");
		ActionMessages messages= new ActionMessages();
		ActionForward forward = new ActionForward();
		MerForm merForm = (MerForm)form;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Merchandise mer = new Merchandise();
		mer.setCategory(cateService.loadCate(merForm.getCategory()));
		mer.setMerName(new String(merForm.getMerName().getBytes("iso8859-1"),"gb2312"));
		mer.setPrice(merForm.getPrice());
		mer.setSprice(merForm.getSprice());
		mer.setMerModel(merForm.getMerModel());
		mer.setMerDesc(new String(merForm.getMerDesc().getBytes("iso8859-1"),"gb2312"));
		mer.setManufacturer(new String(merForm.getManufacturer().getBytes("iso8859-1"),"gb2312"));
		mer.setLeaveFactoryDate(df.parse(merForm.getLeaveFactoryDate()));
		mer.setSpecial(merForm.getSpecial());
		FormFile pictureFile = merForm.getPicture();
		if(pictureFile!=null && pictureFile.getFileName().length()>=1)
			mer.setPicture("Picture/"+pictureFile.getFileName());
		boolean status = merService.addMerchandise(mer);
		if(status==false){
			if(mer.getSpecial()==0){
				messages.add("addMerStatus",new ActionMessage(Messages.MANAGE_MER_ADD_FAILED));
				forward.setPath("/manageMerAction.do?method=browseMers");
			}
			else{
				messages.add("addSMerStatus",new ActionMessage(Messages.MANAGE_SMER_ADD_FAILED));
				forward.setPath("/manageMerAction.do?method=browseSMers");
			}
			this.saveErrors(request, messages);
			return forward;
		}
		if(mer.getPicture()==null){
			if(mer.getSpecial()==0){
				messages.add("addMerStatus",new ActionMessage(Messages.MANAGE_MER_ADD_SUCC));
				forward.setPath("/manageMerAction.do?method=browseMers");
			}
			else{
				messages.add("addSMerStatus",new ActionMessage(Messages.MANAGE_SMER_ADD_SUCC));
				forward.setPath("/manageMerAction.do?method=browseSMers");
			}
			this.saveErrors(request, messages);
			return forward;	
		}

		try {
			InputStream input = pictureFile.getInputStream();
			String filePath = request.getRealPath("/Picture")+"/"+pictureFile.getFileName();
			File file = new File(filePath);
			if(file.exists())
				file.delete();
			file.createNewFile();
			OutputStream output = new FileOutputStream(file);
			int length = 0;
			byte[] data = new byte[1024];
			while((length = input.read(data))>0)
				output.write(data, 0, length);	
			output.close();
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		if(mer.getSpecial()==0){
			messages.add("addMerStatus",new ActionMessage(Messages.MANAGE_MER_ADD_SUCC));
			forward.setPath("/manageMerAction.do?method=browseMers");
		}
		else{
			messages.add("addSMerStatus",new ActionMessage(Messages.MANAGE_SMER_ADD_SUCC));
			forward.setPath("/manageMerAction.do?method=browseSMers");
		}
		this.saveErrors(request, messages);
		return forward;
	}

	public ActionForward deleteMer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if(admin==null || admin.getAdminType()!=1)
			return mapping.findForward("sorry");
		int merId = Integer.parseInt(request.getParameter("merId"));
		Merchandise mer = merService.loadMerchandise(merId);
		boolean status = merService.deleteMerchandise(merId);
		ActionMessages messages= new ActionMessages();
		ActionForward forward = new ActionForward();
		if(status){
			if(mer.getSpecial()==0){
				messages.add("deleteMerStatus",new ActionMessage(Messages.MANAGE_MER_DEL_SUCC));
				forward.setPath("/manageMerAction.do?method=browseMers");
			}
			else{
				messages.add("deleteSMerStatus",new ActionMessage(Messages.MANAGE_SMER_DEL_SUCC));
				forward.setPath("/manageMerAction.do?method=browseSMers");
			}
		}else{
			if(mer.getSpecial()==0){
				messages.add("deleteMerStatus",new ActionMessage(Messages.MANAGE_MER_DEL_FAILED));
				forward.setPath("/manageMerAction.do?method=browseMers");
			}else{
				messages.add("deleteSMerStatus",new ActionMessage(Messages.MANAGE_SMER_DEL_FAILED));
				forward.setPath("/manageMerAction.do?method=browseSMers");
			}
			
		}
		this.saveErrors(request, messages);
		return forward;
	}

	public ActionForward updateMer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
			{
		
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if(admin==null || admin.getAdminType()!=1)
			return mapping.findForward("sorry");
		MerForm merForm = (MerForm) form;
		ActionMessages messages = new ActionMessages();
		ActionForward forward = new ActionForward();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		int merId = Integer.parseInt(request.getParameter("merId"));
		Merchandise mer = new Merchandise();
		mer.setId(merId);
		mer.setCategory(cateService.loadCate(merForm.getCategory()));
		mer.setMerName(new String(merForm.getMerName().getBytes("iso8859-1"),"gb2312"));
		mer.setPrice(merForm.getPrice());
		mer.setSprice(merForm.getSprice());
		mer.setMerModel(merForm.getMerModel());
		mer.setMerDesc(new String(merForm.getMerDesc().getBytes("iso8859-1"),"gb2312"));
		mer.setManufacturer(new String(merForm.getManufacturer().getBytes("iso8859-1"),"gb2312"));
		mer.setLeaveFactoryDate(df.parse(merForm.getLeaveFactoryDate()));
		mer.setSpecial(merForm.getSpecial());
		FormFile pictureFile = merForm.getPicture();
		
		if(pictureFile!=null && pictureFile.getFileName().length()>=1)
			mer.setPicture("Picture/"+pictureFile.getFileName());
		
		boolean status = merService.updateMerchandise(mer);
		if(status == false){
			if(mer.getSpecial()==0){
				messages.add("updateMerStatus",new ActionMessage(Messages.MANAGE_MER_MODIFY_FAILED));
				forward.setPath("/manageMerAction.do?method=browseMers");
			}
			else{
				messages.add("updateSMerStatus",new ActionMessage(Messages.MANAGE_SMER_MODIFY_FAILED));
				forward.setPath("/manageMerAction.do?method=browseSMers");
			}
			this.saveErrors(request, messages);
			return forward;
			
		}
		if(mer.getPicture()==null){
			if(mer.getSpecial()==0){
				messages.add("updateMerStatus",new ActionMessage(Messages.MANAGE_MER_MODIFY_SUCC));
				forward.setPath("/manageMerAction.do?method=browseMers");
			}
			else{
				messages.add("updateSMerStatus",new ActionMessage(Messages.MANAGE_SMER_MODIFY_SUCC));
				forward.setPath("/manageMerAction.do?method=browseSMers");
			}
			this.saveErrors(request, messages);
			return forward;
		}
		try {
			InputStream input = pictureFile.getInputStream();
			String filePath = request.getRealPath("/Picture")+"/"+pictureFile.getFileName();
			File file = new File(filePath);
			if(file.exists())
				file.delete();
			file.createNewFile();
			OutputStream output = new FileOutputStream(file);
			int length = 0;
			byte[] data = new byte[1024];
			while((length = input.read(data))>0)
				output.write(data, 0, length);	
			output.close();
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		if(mer.getSpecial()==0){
			messages.add("updateMerStatus",new ActionMessage(Messages.MANAGE_MER_MODIFY_SUCC));
			forward.setPath("/manageMerAction.do?method=browseMers");
		}
		else{
			messages.add("updateSMerStatus",new ActionMessage(Messages.MANAGE_SMER_MODIFY_SUCC));
			forward.setPath("/manageMerAction.do?method=browseSMers");	
		}
		this.saveErrors(request, messages);
		return forward;
	}

	public ActionForward browseMer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if(admin==null || admin.getAdminType()!=1)
			return mapping.findForward("sorry");
		int merId = Integer.parseInt(request.getParameter("merId"));
		Merchandise mer = merService.loadMerchandise(merId);
		if (mer != null)
			request.setAttribute("mer", mer);
		if(mer.getSpecial()==0)
			return mapping.findForward("showMer");
		else
			return mapping.findForward("showSMer");

	}
	
	public ActionForward loadMer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if(admin==null || admin.getAdminType()!=1)
			return mapping.findForward("sorry");
		int merId = Integer.parseInt(request.getParameter("merId"));
		Merchandise mer = merService.loadMerchandise(merId);
		if (mer != null)
			request.setAttribute("mer", mer);
		
		List<Category> cates = cateService.browseCates();
		if(cates!=null && cates.size()>0)
			request.setAttribute("cateList",cates);
		if(mer.getSpecial()==0)
			return mapping.findForward("modifyMer");
		else
			return mapping.findForward("modifySMer");

	}

	public ActionForward browseMers(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if(admin==null || admin.getAdminType()!=1)
			return mapping.findForward("sorry");
		List<Merchandise> mers = merService.browseMerchandises(false);
		if (mers != null && mers.size()>0)
			request.setAttribute("merList", mers);
		return mapping.findForward("adminMer");
	}
	
	
	public ActionForward browseSMers(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if(admin==null || admin.getAdminType()!=1)
			return mapping.findForward("sorry");
		List<Merchandise> mers = merService.browseMerchandises(true);
		if (mers != null && mers.size()>0)
			request.setAttribute("merList", mers);
		return mapping.findForward("adminSMer");
	}

}
