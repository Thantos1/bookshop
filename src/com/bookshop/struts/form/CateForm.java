package com.bookshop.struts.form;

import org.apache.struts.action.ActionForm;

public class CateForm extends ActionForm {
	private String cateName;
	private String cateDesc;

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getCateDesc() {
		return cateDesc;
	}

	public void setCateDesc(String cateDesc) {
		this.cateDesc = cateDesc;
	}

}
