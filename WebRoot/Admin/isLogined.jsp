<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="com.bookshop.domain.Admin" %>
<%
	//如果没有登录就跳转到登录页面
	Admin admin = (Admin)session.getAttribute("admin");
	if (admin ==null) {
%>
	<jsp:forward page="/Admin/adminLogin.jsp"/>
<%}%>