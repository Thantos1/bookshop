<%@ page contentType="text/html; charset=gb2312" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html>
<head>
<title><bean:message key="admin.pageTitle"/></title>
<link href="../CSS/stylesheet.css" rel="stylesheet" type="text/css">
<style type="text/css">
	<!--
	body {
		background-color: lightgrey;
	}
	-->
</style>
</head>
<body>

<html:form action="/manageAdminAction.do?method=updateAdmin">
  <table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr height="40">
        <td colspan="2" class="itemTitle" align="center">
        	<bean:message key="admin.modify"/>
        </td>
      </tr>
      <tr height="30">
        <td width="160" align="right"><bean:message key="admin.realname"/>£º</td>
        <td><html:text property="name" value="${admin.name}" size="41" styleClass="textBox"/></td>
     </tr>
      <tr height="30">
        <td valign="top"  align="right"><bean:message key="admin.loginName"/>£º</td>
        <td><html:text property="username" value="${admin.username }"size="41" styleClass="textBox"/></td>
     </tr>
      <tr height="30">
        <td valign="top"  align="right"><bean:message key="admin.loginPwd"/>£º</td>
        <td><html:text property="password" value="${admin.password}" size="41" styleClass="textBox"/></td>
     </tr>
      <tr height="30">
        <td valign="top"  align="right"><bean:message key="admin.type"/>£º</td>
        <td>
        	<html:select property="adminType" styleClass="textBox">
	        	<html:option value="1"><bean:message key="admin.type1"/></html:option>
	        	<html:option value="2"><bean:message key="admin.type2"/></html:option> 
	        	<html:option value="3"><bean:message key="admin.type3"/></html:option> 
	        	<html:option value="4"><bean:message key="admin.type4"/></html:option> 	        		        		        	        	
        	</html:select>
        </td>
     </tr>          
      <tr height="30">
        <td colspan="2" align="center">
			<html:reset><bean:message key="reset.text"/></html:reset>
			<html:submit><bean:message key="submit.text"/></html:submit>
		</td>
     </tr>
      <tr>
        <td height="30" align="center" colspan="2" class="redText">
			<html:errors property="modiAdminStatus"/>
		</td>
      </tr>
    </table>
	<input type="hidden" name="adminId" value="${admin.id }">	
</html:form>
<script type="text/javascript">
for(var j=0;j<document.all.category.options.length;j++){
		if (document.all.category.options[j].value == "${admin.adminType}"){
			document.all.category.options[j].selected = "true";
		}
	}
	
</script>
</body>
</html>