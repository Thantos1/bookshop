package com.bookshop.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Merchandise {
	private int id;
	private Category category;
	private String merName;
	private double price;
	private double sprice;
	private String merModel;
	private String picture;
	private String merDesc;
	private String manufacturer;
	private Date leaveFactoryDate;
	private int special;
	private Set<Comment> comments=new HashSet<Comment>();
	
	public Set<Comment> getComments() {
		return comments;
	}
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getMerName() {
		return merName;
	}
	public void setMerName(String merName) {
		this.merName = merName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getSprice() {
		return sprice;
	}
	public void setSprice(double sprice) {
		this.sprice = sprice;
	}
	public String getMerModel() {
		return merModel;
	}
	public void setMerModel(String merModel) {
		this.merModel = merModel;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getMerDesc() {
		return merDesc;
	}
	public void setMerDesc(String merDesc) {
		this.merDesc = merDesc;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public Date getLeaveFactoryDate() {
		return leaveFactoryDate;
	}
	public void setLeaveFactoryDate(Date leaveFactoryDate) {
		this.leaveFactoryDate = leaveFactoryDate;
	}
	public int getSpecial() {
		return special;
	}
	public void setSpecial(int special) {
		this.special = special;
	}

	
}
