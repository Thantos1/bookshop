<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

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
	<c:set var="label1"><bean:message key="order.no"/></c:set>
	<c:set var="label2"><bean:message key="sel.money"/></c:set>
	<c:set var="label3"><bean:message key="order.date"/></c:set>
	<c:set var="label4"><bean:message key="order.status"/></c:set>
	<c:set var="label5"><bean:message key="order.edit"/></c:set>	  	  
	<table border="0" align="center" cellpadding="0" cellspacing="0" style="background-color:lightgrey; border:0px;">
	  <tr>
		<td height="40" class="itemTitle" align="center"><bean:message key="order.manage"/></td>
	  </tr>
	  <tr>
	    <td height="30" align="center">		
			<display:table name="orderList" id="row" pagesize="15" export="true" class="displaytag" requestURI="/manageOrderAction.do?method=browseOrders" >
				<display:column property="orderNo" title="${label1}" sortable="true" headerClass="sortable" style="text-align:center;"/>
				<display:column property="cart.money" title="${label2}" style="text-align:center;"/>				
				<display:column property="orderDate" title="${label3}" style="text-align:center;"/>
				<display:column title="${label4}" media="html" style="text-align:center;">				
					<logic:equal name="row" property="orderStatus" value="1">
						<bean:message key="order.status1"/>
					</logic:equal>
					<logic:equal name="row" property="orderStatus" value="2">
						<bean:message key="order.status2"/>
					</logic:equal>
					<logic:equal name="row" property="orderStatus" value="3">
						<bean:message key="order.status3"/>
					</logic:equal>
				</display:column>													
				<display:column title="${label5}" media="html" style="text-align:center;">
					<html:link page="/manageOrderAction.do?method=browseOrder" 
							   paramId="orderId" 
							   paramName="row" 
							   paramProperty="id">
						<bean:message key="order.view"/>
					</html:link>
					<logic:equal name="row" property="orderStatus" value="1">
						<html:link page="/manageOrderAction.do?method=acceptOrder" 
								   paramId="orderId" 
								   paramName="row" 
								   paramProperty="id">
							<bean:message key="order.to.status2"/>
						</html:link>
					</logic:equal>										
					<logic:equal name="row" property="orderStatus" value="2">
						<html:link page="/manageOrderAction.do?method=endOrder" 
								   paramId="orderId" 
								   paramName="row" 
								   paramProperty="id">
							<bean:message key="order.to.status3"/>
						</html:link>
					</logic:equal>	
					<html:link page="/manageOrderAction.do?method=deleteOrder" 
							   paramId="orderId" 
							   paramName="row" 
							   paramProperty="id">
						<bean:message key="order.del"/>
					</html:link>
				</display:column>
				<display:setProperty name="export.csv.filename" value="orders.csv"/>
				<display:setProperty name="export.excel.filename" value="orders.xls"/>
			</display:table>		
		</td>
      </tr>
       <tr>
	    <td height="30" align="center" class="redText"><html:errors property="deleteOrderStatus"/></td>
      </tr>
  </table>
  </body>
</html>