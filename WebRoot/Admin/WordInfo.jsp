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
          <logic:notPresent name="leaveword">
            <tr align="center">
              <td colspan="2" height="30" class="redText"><bean:message key="word.notExist"/></td>
            </tr>
          </logic:notPresent>
          <logic:present name="leaveword">
            <tr><td height="10" colspan="2"></td></tr>
            <tr>
              <td width="160" height="30" align="right"><bean:message key="word.title"/>
                ：</td>
              <td>&nbsp;${leaveword.title}</td>
            </tr>
            <tr>
              <td width="160" height="30" align="right"><bean:message key="word.user"/>
                ：</td>
              <td>&nbsp;${leaveword.member.name}</td>
            </tr>
            <tr>
              <td width="160" height="30" align="right"><bean:message key="word.date"/>
                ：</td>
              <td>&nbsp;${leaveword.leaveDate}</td>
            </tr>
            <tr>
              <td width="160" height="30" align="right"><bean:message key="word.content"/>
                ：</td>
              <td>&nbsp;${leaveword.content}</td>
            </tr>
            <tr>
              <td width="160" height="30" align="right"><bean:message key="answer.text"/>
                ：</td>
              <td>&nbsp;
				<form name="form1" action="manageLeavewordAction.do?method=answerLeaveword" method="post" style="margin:0px;">		              
				  	<textarea class="textBox" id="answerContent" name="answerContent" rows="6" cols="60">${leaveword.answerContent}</textarea>
					<input type="hidden" name="leavewordId" value="${leaveword.id}"/>
				</form>							  	
			  </td>
            </tr>
            <tr>
              <td height="40" colspan="2" align="center"><input type="button" value='<bean:message key="word.answer"/>' onClick="checkForm()"/></td>
            </tr>
            <tr><td height="10" colspan="2"></td></tr>
          </logic:present>
        </table>
		</td>
      </tr>
  </table>
  <center>
	<html:link page="/manageLeavewordAction.do?method=browseLeavewords">
		<span><bean:message key="return.text"/></span>
	</html:link>
  </center>
 <script language="javascript">
 	//表单验证
	function checkForm(){
		if (document.all.answerContent.value==''){
			alert("回复内容不能为空！");
			return false;
		}else{
				document.form1.submit();
		}
	}
 </script>
</body>
</html>