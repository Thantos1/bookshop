package com.bookshop.struts.form;

import org.apache.struts.action.ActionForm;


public class CommentForm extends ActionForm {
	private String commentLevel="好评";
	private String commentContent;
	
	
	public String getCommentLevel() {
		return commentLevel;
	}

	public void setCommentLevel(String commentLevel) {
		this.commentLevel = commentLevel;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}


	

}
