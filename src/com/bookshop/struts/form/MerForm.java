package com.bookshop.struts.form;

import java.util.Date;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.bookshop.domain.Category;

public class MerForm extends ActionForm {

	private String manufacturer;
	private double sprice;
	private int special;
	private String leaveFactoryDate;
	private double price;
	private FormFile picture;
	private int category;
	private String merName;
	private String merModel;
	private String merDesc;
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public double getSprice() {
		return sprice;
	}
	public void setSprice(double sprice) {
		this.sprice = sprice;
	}
	public int getSpecial() {
		return special;
	}
	public void setSpecial(int special) {
		this.special = special;
	}
	public String getLeaveFactoryDate() {
		return leaveFactoryDate;
	}
	public void setLeaveFactoryDate(String leaveFactoryDate) {
		this.leaveFactoryDate = leaveFactoryDate;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public FormFile getPicture() {
		return picture;
	}
	public void setPicture(FormFile picture) {
		this.picture = picture;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getMerName() {
		return merName;
	}
	public void setMerName(String merName) {
		this.merName = merName;
	}
	public String getMerModel() {
		return merModel;
	}
	public void setMerModel(String merModel) {
		this.merModel = merModel;
	}
	public String getMerDesc() {
		return merDesc;
	}
	public void setMerDesc(String merDesc) {
		this.merDesc = merDesc;
	}
	
	
	
}
