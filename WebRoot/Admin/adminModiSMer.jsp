<%@ page contentType="text/html; charset=gb2312" import="com.bookshop.domain.Merchandise"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%
int cateId = ((Merchandise)(request.getAttribute("mer"))).getCategory().getId();
 %>
<html>
<head>
<title><bean:message key="admin.pageTitle"/></title>
<link href="../CSS/stylesheet.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../JS/jsonrpc.js"></script>
<style type="text/css">
	<!--
	body {
		background-color: lightgrey;
	}
	-->
</style>
</head>
<body>

<html:form action="/manageMerAction.do?method=updateMer" enctype="multipart/form-data">
  <table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr height="40">
        <td colspan="2" class="itemTitle" align="center">
        	<bean:message key="smer.modify.text"/>
        </td>
      </tr>
      <tr height="24">
        <td width="160" align="right"><bean:message key="mer.category"/>：</td>
        <td>
        <html:select property="category" styleClass="textBox">
        <logic:present name="cateList">
		  	<logic:iterate id="cate" name="cateList" type="com.bookshop.domain.Category">
				<option value="${cate.id}">${cate.cateName}</option>					  		
		  	</logic:iterate>
		  </logic:present>	
        </html:select>
        </td>
     </tr>
      <tr height="24">
        <td valign="top"  align="right"><bean:message key="mer.name"/>：</td>
        <td><html:text property="merName" value="${mer.merName}" size="41" styleClass="textBox"/></td>
     </tr>
      <tr height="24">
        <td width="160" align="right"><bean:message key="mer.model"/>：</td>
        <td><html:text property="merModel" value="${mer.merModel}" size="41" styleClass="textBox"/></td>
     </tr>
      <tr height="24">
        <td valign="top"  align="right"><bean:message key="mer.picture"/>：</td>
        <td><html:file property="picture"  styleClass="textBox"/></td>
     </tr>
      <tr height="24">
        <td width="160" align="right"><bean:message key="mer.price"/>：</td>
        <td><html:text property="price" value="${mer.price}" size="41" styleClass="textBox"/></td>
     </tr>
      <tr height="24">
        <td valign="top"  align="right"><bean:message key="mer.special"/>：</td>
        <td>
			<html:radio property="special" value="0"/>无&nbsp;&nbsp;
            <html:radio property="special" value="1"/>有
		</td>
     </tr>
      <tr height="24">
        <td width="160" align="right"><bean:message key="mer.sprice"/>：</td>
        <td><html:text property="sprice" value="${mer.sprice}" size="41" styleClass="textBox"/></td>
     </tr>
      <tr height="24">
        <td valign="top"  align="right"><bean:message key="mer.desc"/>：</td>
        <td><html:textarea property="merDesc" value="${mer.merDesc}" cols="40" rows="10" styleClass="textBox"/></td>
     </tr>
      <tr height="24">
        <td width="160" align="right"><bean:message key="mer.manufacturer"/>：</td>
        <td><html:text property="manufacturer" value="${mer.manufacturer}" size="41" styleClass="textBox"/></td>
     </tr>
      <tr height="24">
        <td valign="top"  align="right"><bean:message key="mer.leaveFactoryDate"/>：</td>
        <td><html:text property="leaveFactoryDate" value="${mer.leaveFactoryDate}" size="41" styleClass="textBox"/></td>
     </tr>	 	 	 	 
      <tr height="24">
        <td colspan="2" align="center">
			<html:reset><bean:message key="reset.text"/></html:reset>
			<html:submit><bean:message key="submit.text"/></html:submit>
		</td>
     </tr>
      <tr>
        <td height="24" align="center" colspan="2" class="redText">
			<html:errors property="modiSMerStatus"/>
		</td>
      </tr>
    </table>
	<input type="hidden" name="merId" value="${mer.id }">	
</html:form>
<script language="javascript">
	
	for(var j=0;j<document.all.category.options.length;j++){
		if (document.all.category.options[j].value == <%=cateId %>){
			document.all.category.options[j].selected = "true";
		}
	}
	
		//置“有无特价”为“有”
	document.all.special[1].checked = "true";

	//日期选择
	function popupWin(temp){
		var xx=event.clientX;
		var yy=event.clientY;
		var value = showModalDialog("../JS/selectDate.htm",window,"dialogWidth:350px;dialogHeight:180px;center:0;dialogLeft:"+xx+";dialogTop:"+yy+";status:0;");
		temp.value=value;
	}
</script>	
</body>
</html>