<%@ page contentType="text/html; charset=gb2312" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<html>
<head>
<title><bean:message key="website.title"/></title>
<link href="CSS/stylesheet.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="JS/jsonrpc.js"></script>
<script type="text/javascript" src="JS/checkform.js"></script>
</head>
<body class="body">
<table width="780" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" style="border:1px; border-style:solid; border-color:#888888">
  <tr>
    <td width="20">&nbsp;</td>
    <TD height="50" align="right" valign="bottom">
		<IMG src="images/icon_login.gif" align=absMiddle> 
		<INPUT id="qKey" name="qKey" value="商品关键字" onClick="this.value=''"> 
		 <select id="category">
       	<option value="0">所有商品</option>
		  <logic:present name="cateList">
		  	<logic:iterate id="cate" name="cateList" type="com.bookshop.domain.Category">
				<option value="${cate.id}">${cate.cateName}</option>					  		
		  	</logic:iterate>
		  </logic:present>	       	
        </select>		
		<A href="javascript:QuickSearch()"><IMG src="images/icon_search.gif" align="absMiddle" border="0"></A>
    </TD>
    <td width="20">&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>
	<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
        <TR align="center">
          <TD valign="top" width="9"><IMG src="images/icon02.gif"></TD>
          <TD class="header_menu" align="middle">
		  	<A href="merAction.do?method=browseIndexMer"><span class="whiteTitle"><bean:message key="menu.item1"/></span></A>
		  </TD>
          <TD background="images/Bule_06.gif" width="2"></TD>
          <TD class="header_menu" align="middle">
		  	<A href="manageCartAction.do?method=browseCart"><span class="whiteTitle"><bean:message key="menu.item2"/></span></A>
		  </TD>
          <TD background="images/Bule_06.gif" width="2"></TD>
          <TD class="header_menu" align="middle">
		  	<A href="orderAction.do?method=browseOrders"><span class="whiteTitle"><bean:message key="menu.item3"/></span></A>
		  </TD>
          <TD background="images/Bule_06.gif" width="2"></TD>
          <TD class="header_menu" align="middle">
		  	<A href="leavewordAction.do?method=browseLeavewords&pageNo=1"><span class="whiteTitle"><bean:message key="menu.item4"/></span></A>
		  </TD>
          <TD background="images/Bule_06.gif" width="2"></TD>
          <TD class="header_menu" align="middle">
		  	<A href="updateMemberAction.do?method=browseMember"><span class="whiteTitle"><bean:message key="menu.item5"/></span></A>
		  </TD>
          <TD vAlign=top width=7><IMG src="images/icon07.gif"></TD>
        </TR>
    </TABLE></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>
	<form action="updateMemberAction.do?method=updateMember" style="margin:0px;" method="post" onSubmit="return CheckForm.Check(this,2)">             	
	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#FFFFFF">
      <tr>
        <td height="30" colspan="2">&nbsp;</td>
      </tr>
      <tr>
        <td height="40" colspan="2"><img src="images/EditUser_01.gif" /></td>
      </tr>
      <tr bgcolor="#F7F3F7">
        <td width="260" height="26" class="text" align="right"><bean:message key="member.level"/>
          ：</td>
        <td height="26" class="text">${member.memberlevel.levelName}</td>
      </tr>
      <tr bgcolor="#F7F3F7">
        <td width="260" height="26" class="text" align="right"><bean:message key="member.realname"/>
          ：</td>
        <td height="26"><input type="text" name="name" size="30" class="textBox" value="${member.name}" require="true" dataType="Require" msg="真实姓名不能为空!"/></td>
      </tr>
      <tr bgcolor="#F7F3F7">
        <td width="260" height="26" class="text" align="right"><bean:message key="member.login.name"/>
          ：</td>
        <td height="26"><input type="text" name="username" size="30" class="textBox" value="${member.username}" require="true" dataType="Require" msg="登录名不能为空!"/></td>
      </tr>
      <tr bgcolor="#F7F3F7">
        <td width="260" height="26" class="text" align="right"><bean:message key="member.login.pwd"/>
          ：</td>
        <td height="26"><input type="password" name="loginPwd" size="30" class="textBox"/></td>
      </tr>
      <tr bgcolor="#F7F3F7">
        <td width="260" height="26" class="text" align="right"><bean:message key="member.login.repwd"/>
          ：</td>
        <td height="26"><input name="password" type="password" class="textBox" id="reLoginPwd" onBlur="checkPwd()" size="30"/></td>
      </tr>
      <tr bgcolor="#F7F3F7">
        <td width="260" height="26" class="text" align="right"><bean:message key="member.phone"/>
          ：</td>
        <td height="26"><input type="text" name="phone" size="30" class="textBox" value="${member.phone}" require="true" dataType="Phone" msg="联系电话不正确!"/></td>
      </tr>
      <tr bgcolor="#F7F3F7">
        <td width="260" height="26" class="text" align="right"><bean:message key="member.address"/>
          ：</td>
        <td height="26"><input type="text" name="address" size="30" class="textBox" value="${member.address}" require="true" dataType="Require" msg="联系地址不能为空!"/></td>
      </tr>
      <tr bgcolor="#F7F3F7">
        <td width="260" height="26" class="text" align="right"><bean:message key="member.zip"/>
          ：</td>
        <td height="26"><input type="text" name="zipCode" size="30" class="textBox" value="${member.zipCode}" require="true" dataType="Zip" msg="邮政编码不正确!"/></td>
      </tr>
      <tr bgcolor="#F7F3F7">
        <td width="260" height="26" class="text" align="right"><bean:message key="member.email"/>
          ：</td>
        <td height="26"><input type="text" name="email" size="30" class="textBox" value="${member.email}" require="false" dataType="Email" msg="电子邮箱不正确!"/></td>
      </tr>
      <tr>
        <td height="40" colspan="2" align="center">
			<input class="C_Input" type="submit" value="保存"/>
	   </td>
      </tr>
      <tr>
	    <td height="30" align="center" class="redText"><html:errors property="updateMemberStatus"/></td>
      </tr>
    </table>
	</form> 
	</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td height="30">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td height="30" bgcolor="#4282CE" class="whiteText" align="center">
		<bean:message key="website.foot"/>	</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td height="20">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
<script language="javascript">
	
	
	//密码核对
	function checkPwd(){
		if (document.all.loginPwd.value != document.all.reLoginPwd.value){
			alert('对不起，两次输入的密码不一致，请重新输入！');
			document.all.loginPwd.value = "";
			document.all.reLoginPwd.value ="";
			document.all.loginPwd.focus();
		}
	}
	
	//搜索商品
	function QuickSearch(){
		var url = "merAction.do?method=searchMerByKeyAndCate&cateId="+document.all.category.value+"&pageNo=1"
		var key = document.all.qKey.value;
		if (key !=null && key!="商品关键字" && key.length>0)url = url+"&key="+key;
		window.location = url;
	}
</script>
</body>
</html>