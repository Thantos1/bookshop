<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%> 

<html> 
	<head>
	<title><bean:message key="admin.pageTitle"/></title>
	<link rel="stylesheet" type="text/css" href="../CSS/stylesheet.css">
	<script type="text/javascript" src="../JS/jsonrpc.js"></script>				
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
	<table border="0" align="center" cellpadding="0" cellspacing="0" style="background-color:lightgrey; border:0px;">
	  <tr>
		<td height="40" class="itemTitle" align="center"><bean:message key="member.info"/></td>
	  </tr>
	  <tr>
	    <td height="30" align="center">
		<table width="94%" border="0" cellpadding="0" cellspacing="0">
          <logic:notPresent name="member">
            <tr align="center">
              <td colspan="2" height="30" class="redText"><bean:message key="member.notExist"/></td>
            </tr>
          </logic:notPresent>
          <logic:present name="member">
            <tr><td height="10" colspan="2"></td></tr>
            <tr>
              <td width="160" height="30" align="right"><bean:message key="member.level"/>
                ：</td>
              <td>&nbsp;${member.memberlevel.levelName }</td>
            </tr>
            <tr>
              <td width="160" height="30" align="right"><bean:message key="member.login.name"/>
                ：</td>
              <td>&nbsp;${member.username}</td>
            </tr>
            <tr>
              <td width="160" height="30" align="right"><bean:message key="member.realname"/>
                ：</td>
              <td>&nbsp;${member.name}</td>
            </tr>
            <tr>
              <td width="160" height="30" align="right"><bean:message key="member.phone"/>
                ：</td>
              <td>&nbsp;${member.phone}</td>
            </tr>
            <tr>
              <td width="160" height="30" align="right"><bean:message key="member.address"/>
                ：</td>
              <td>&nbsp;${member.address}</td>
            </tr>
            <tr>
              <td width="160" height="30" align="right"><bean:message key="member.zip"/>
                ：</td>
              <td>&nbsp;${member.zipCode}</td>
            </tr>
            <tr>
              <td width="160" height="30" align="right"><bean:message key="member.email"/>
                ：</td>
              <td>&nbsp;${member.email}</td>
            </tr>
            <tr>
              <td width="160" height="30" align="right"><bean:message key="member.regdate"/>
                ：</td>
              <td>&nbsp;${member.registerDate}</td>
            </tr>
            <tr>
              <td width="160" height="30" align="right"><bean:message key="member.logintimes"/>
                ：</td>
              <td>&nbsp;${member.loginTimes}</td>
            </tr>
            <tr>
              <td width="160" height="30" align="right"><bean:message key="member.lastdate"/>
                ：</td>
              <td>&nbsp;${member.lastLoginDate}</td>
            </tr>
			
            <tr><td height="10" colspan="2"></td></tr>
          </logic:present>
        </table>
		</td>
      </tr>
  </table>
  <center>
	<html:link page="/manageMemberAction.do?method=browseMembers">
		<span><bean:message key="return.text"/></span>
	</html:link>
  </center>
</body>
</html>