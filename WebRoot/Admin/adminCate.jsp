<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html> 
	<head>
		<title><bean:message key="admin.pageTitle"/></title>
		<link rel="stylesheet" type="text/css" href="../CSS/stylesheet.css">
		<link rel="stylesheet" type="text/css" href="../CSS/displaytag.css" />		
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<style type="text/css">
		<!--
		body {
			background-color: lightgrey;
		}
		-->
	</style>
	</head>  
  <body>
	<c:set var="label1"><bean:message key="category.table.label1"/></c:set>
	<c:set var="label2"><bean:message key="category.table.label2"/></c:set>
	<c:set var="label3"><bean:message key="category.table.label3"/></c:set>	  
	<table border="0" align="center" cellpadding="0" cellspacing="0" style="background-color:lightgrey; border:0px;">
	  <tr>
		<td height="40" class="itemTitle" align="center"><bean:message key="category.table.title"/></td>
	  </tr>
	  <tr>
		<td height="30" class="blueText" align="center">
			<a href="<%=request.getContextPath() %>/Admin/adminAddCate.jsp">
					 <span class="blueText"><bean:message key="category.table.add"/></span>			
			</a>
		</td>
	  </tr>
	  <tr>
	    <td height="30" align="center">		
			<display:table name="cateList" id="row" pagesize="15" export="true" class="displaytag" requestURI="/manageCateAction.do?method=browseCates" >
				<display:column property="cateName" title="${label1}" sortable="true" headerClass="sortable" style="text-align:center;"/>
				<display:column property="cateDesc" title="${label2}" sortable="true" headerClass="sortable" style="text-align:center;"/>
				<display:column title="${label3}" media="html" style="text-align:center;">
					<html:link page="/manageCateAction.do?method=loadCate" 
							   paramId="cateId" 
							   paramName="row" 
							   paramProperty="id">
						<bean:message key="category.table.modify"/>
					</html:link>
					<html:link page="/manageCateAction.do?method=deleteCate" 
							   paramId="cateId" 
							   paramName="row" 
							   paramProperty="id">
						<bean:message key="category.table.delete"/>
					</html:link>
				</display:column>
				<display:setProperty name="export.csv.filename" value="category.csv"/>
				<display:setProperty name="export.excel.filename" value="category.xls"/>
			</display:table>		
		</td>
      </tr>
      <tr>
	    <td height="30" align="center" class="redText"><html:errors property="addCateStatus"/><html:errors property="deleteCateStatus"/><html:errors property="updateCateStatus"/></td>
      </tr>
  </table>
  </body>
</html>