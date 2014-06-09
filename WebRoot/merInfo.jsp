<%@ page contentType="text/html; charset=gb2312" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
int pageNo = Integer.parseInt(request.getParameter("pageNo"));
int totalCount = ((Integer)request.getAttribute("totalCount")).intValue();
int totalPages = ((Integer)request.getAttribute("totalPages")).intValue();
int prePageNo = pageNo-1;
int nextPageNo = pageNo + 1;
%>
<html>
<head>
<title><bean:message key="website.title"/></title>
<link href="CSS/stylesheet.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="JS/jsonrpc.js"></script>
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
		  	<A href="leavecommentAction.do?method=browseLeavecomments&pageNo=1"><span class="whiteTitle"><bean:message key="menu.item4"/></span></A>
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
		<table width="94%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#FFFFFF">
		  <tr>
			<td height="80" colspan="2"><IMG src="images/icon_goods.gif" width="150" height="29"></td>
		  </tr>
		  <tr>
			<td colspan="2"></td>
		  </tr>
		  <tr>
			<td height="40" colspan="2" align="left" class="text">
              	<logic:present name="mer" property="picture">
					<img src="${mer.picture}" border="0" align="left">
				</logic:present>
				&nbsp;<bean:message key="mer.category"/>：${mer.category.cateName}<br>
				&nbsp;<bean:message key="mer.name"/>：${mer.merName}<br>
				&nbsp;<bean:message key="mer.model"/>：${mer.merModel}<br>
				&nbsp;<bean:message key="mer.price"/>：￥${mer.price}<br>
				<logic:equal name="mer" property="special" value="1">
					&nbsp;<bean:message key="mer.sprice"/>：￥${mer.sprice}<br>
				</logic:equal>				
				&nbsp;<bean:message key="mer.manufacturer"/>：${mer.manufacturer}<br>
				&nbsp;<bean:message key="mer.leaveFactoryDate"/>：${mer.leaveFactoryDate}<br>
				&nbsp;<bean:message key="mer.desc"/>：${mer.merDesc}<br>
			</td>
		  </tr>
		  <tr>
			<td colspan="2" height="40" align="center">
				<a href="manageCartAction.do?method=buyMerchandise&merId=${mer.id}"><img src="images/icon_buy.gif" border=0></a>
			</td>
		  </tr>
		</table>
	</td>
    <td>&nbsp;</td>
  </tr>
  <!-- 商品评论 -->
    <tr>
    <td>&nbsp;</td>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td align="center"><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#FFFFFF">
          <tr>
            <td height="40"><img src="images/icon_liuyan.gif" /></td>
          </tr>
          <tr>
            <td height="26" align="center">
			<logic:notPresent name="commentList">
				<span class="redText"><bean:message key="comment.empty"/></span>
			</logic:notPresent>
			<logic:present name="commentList">
			  <logic:iterate id="comment" name="commentList" type="com.bookshop.domain.Comment">
				<fmt:formatDate value="${comment.commentDate}" var="commentDate" type="both" pattern="yyyy年MM月dd日"/>			
				<table width="90%" border="0" cellpadding="4" cellspacing="0" style="border:1px; border-color:black; border-style:solid;">
				  <tr bgcolor="#F7F3F7">
					<td class="text">${comment.commentLevel}</td>
					<td align="right" class="text">
						<span class="blackTitle">
						<bean:message key="comment.user"/></span>：${comment.member.name}&nbsp;
						<span class="blackTitle"><bean:message key="comment.commentDate"/></span>：${commentDate}</td>
				  </tr>
				  <tr>
					<td class="text" colspan="2">${comment.commentContent}</td>
				  </tr>
<%-- 商品回复				  <logic:present name="comment" property="answerContent">
				  <tr>
					<td colspan="2" bgcolor="#F7F3F7" class="blackTitle"><bean:message key="answer.text"/>：</td>
				  </tr>
				  <tr>
					<td class="text" colspan="2">${comment.answerContent}</td>
				  </tr>
				  </logic:present> --%>
				</table>
				<table cellpadding="0" cellspacing="0"><tr height="10"><td></td></tr></table>
			</logic:iterate>
		</logic:present>			</td>
          </tr>
          <tr bgcolor="#F7F3F7">
            <td height="26" bgcolor="#FFFFFF" align="center" class="text">
				<a href="merAction.do?method=browseMerchandise&merId=${mer.id}&pageNo=1" class="blueText"><span class="blueText">首页</span></a>&nbsp;
				<%if (pageNo>1){%>
					<a href="merAction.do?method=browseMerchandise&merId=${mer.id}&pageNo=<%=prePageNo%>" class="blueText"><span class="blueText">上一页</span></a>&nbsp;
				<%}else{%>
					<span class="grayText">上一页</span>&nbsp;
				<%}%>
				<%if (pageNo<totalPages){%>
					<a href="merAction.do?method=browseMerchandise&merId=${mer.id}&pageNo=<%=nextPageNo%>" class="blueText"><span class="blueText">下一页</span></a>&nbsp;
				<%}else{%>
					<span class="grayText">下一页</span>&nbsp;
				<%}%>
					<a href="merAction.do?method=browseMerchandise&merId=${mer.id}&pageNo=<%=totalPages%>" class="blueText"><span class="blueText">末页</span></a>　
				去第<input type="text" id="willGoPage" name="willGoPage" class="control" size="2" onKeyPress="return isNumber()">
				页<input type="button" class="button" id="go" value="GO" name="go" onClick="goPage()">   　
				第<span class="redText"><%=pageNo%></span>页/共<span class="redText"><%=totalPages%></span>页　
			总数<span class="redText"><%=totalCount%></span>			  
			</td>
          </tr>
        </table></td>
      </tr>
	  <tr height="20"><td colspan="5"></td></tr>      
      <tr align="right">
        <td height="20">&nbsp;</td>
      </tr>
    </table>
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
		//是否输入数字
	function isNumber(){
		return ((event.keyCode>47)&&(event.keyCode<58));
	}
	
	//响应“GO”按钮
	function goPage(goPageNo){
		var maxPageNo = <%=totalPages%>;
		var goPageNo = document.all.willGoPage.value;
		var url = "merAction.do?method=browseMerchandise&merId=${mer.id}&pageNo="+goPageNo;
		if (goPageNo<1 || goPageNo>maxPageNo || goPageNo==''){
			alert("对不起，您输入的页号无效，请您核对后重新输入！");
			return ;
		}else
		{
			window.location = url;
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