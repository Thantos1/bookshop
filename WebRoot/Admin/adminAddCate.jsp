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
<html:form action="/manageCateAction.do?method=addCate">
  <table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr height="40">
        <td colspan="2" class="itemTitle" align="center">
        	<bean:message key="category.table.add"/>
        </td>
      </tr>
      <tr height="30">
        <td width="160" align="right"><bean:message key="category.table.label1"/>£º</td>
        <td><html:text property="cateName" size="41" styleClass="textBox" value=""/></td>
     </tr>
      <tr height="30">
        <td valign="top"  align="right"><bean:message key="category.table.label2"/>£º</td>
        <td><html:textarea property="cateDesc" cols="40" rows="10" styleClass="textBox" value=""/></td>
     </tr>
      <tr height="30">
        <td colspan="2" align="center">
			<html:reset><bean:message key="reset.text"/></html:reset>
			<html:submit><bean:message key="submit.text"/></html:submit>
		</td>
     </tr>
      <tr>
        <td height="30" align="center" colspan="2" class="redText">
			<html:errors property="addCateStatus"/>
		</td>
      </tr>
    </table>
</html:form>
</body>
</html>