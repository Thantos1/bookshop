<%@ page contentType="text/html; charset=gb2312" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

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
		<A href="javascript:QuickSearch()"><IMG src="images/icon_search.gif" align="absMiddle" border="0"></A>    </TD>
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
    <td><table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="60"><img src="images/Car_icon_04.gif" /></td>
      </tr>
      <tr>
        <td><table cellspacing="0" cellpadding="0" border="0">
            <tr valign="center">
              <td><img hspace="5" src="images/Car_07.gif" /></td>
              <td class="C_Carbg_Current"><bean:message key="cart.step1"/></td>
              <td><img height="39" src="images/Car_15.gif" width="1" /></td>
              <td align="middle"><img hspace="5" src="images/Car_09.gif" /></td>
              <td class="C_Carbg_Default"><bean:message key="cart.step2"/></td>
              <td><img height="39" src="images/Car_15.gif" width="1" /></td>
              <td align="middle"><img hspace="5" src="images/Car_11.gif" /></td>
              <td class="C_Carbg_Default"><bean:message key="cart.step3"/></td>
              <td><img height="39" src="images/Car_15.gif" width="1" /></td>
            </tr>
        </table></td>
      </tr>
      <tr>
        <td align="center">
			<table cellspacing="1" cellpadding="0" width="94%" border="0" bgcolor="#F7F3F7">
              <tr height="26">
                <td class="blackTitle" align="center"><bean:message key="mer.name"/></td>
                <td class="blackTitle" align="center"><bean:message key="mer.price"/></td>
                <td class="blackTitle" align="center"><bean:message key="sel.price"/></td>
                <td class="blackTitle" align="center"><bean:message key="sel.number"/></td>
                <td class="blackTitle" align="center"><bean:message key="sel.money"/></td>
                <td class="blackTitle" align="center"><bean:message key="cart.delete"/></td>
              </tr>
			<logic:notPresent name="result">
              <tr align="center" bgcolor="#FFFFFF">
                <td colspan="6" height="26" class="redText"><bean:message key="cart.empty"/></td>
              </tr>				
			</logic:notPresent>
			<logic:present name="result">
				<logic:iterate id="row" name="result" type="java.util.Map">
				  <tr class="text" align="center" bgcolor="#FFFFFF">
					<td>
						&nbsp;<a href="merAction.do?method=browseMerchandise&merId=${row.merId}" target="_blank"> 
						  <span class="blueText">${row.merName}</span>
						</a>					</td>
					<td>￥${row.price}</td>
					<td>￥<span id="price${row.selId}">${row.memprice}</span></td>
					<td><input type="text" class="textBox" onChange="modiNum(${row.selId},this.value)" value="${row.number}" size="4"/></td>
					<td>￥<span id="money${row.selId}">${row.money}</span></td>
					<td><input onClick="delCart(${row.selId})" type="image" src="images/delete_01.gif" border="0"/></td>
				  </tr>
				</logic:iterate>
              <tr>
                <td colspan="6" class="Order_Total"><img hspace="5" src="images/me03.gif" align="absmiddle" /> 
				<bean:message key="cart.totalmoney"/><span id="totalMoney">${totalMoney}</span><bean:message key="cart.memo"/>				</td>
              </tr>				
			</logic:present>
			<tr>
	      <td height="30" align="center" class="redText"><html:errors property="checkOrderStatus"/></td>
         </tr>						
        </table></td>
      </tr>
	  <tr height="20"><td colspan="5"></td></tr>	  
      <tr align="right">
        <td>
			<input type="image" src="images/Car_icon_01.gif" style="BORDER: 0px;WIDTH: 126px; HEIGHT: 39px;" onClick="clearCart()">
			<img style="CURSOR: hand" onClick="continueBuy()" src="images/Car_icon_02.gif" />
			<img src="images/Car_icon_03.gif" onClick="next()" border="0" style="CURSOR: hand"/>
		</td>
      </tr>
      <tr align="right">
        <td height="20">&nbsp;</td>
      </tr>
    </table>	</td>
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
	
	
	//搜索商品
	function QuickSearch(){
		var url = "merAction.do?method=searchMerByKeyAndCate&cateId="+document.all.category.value+"&pageNo=1"
		var key = document.all.qKey.value;
		if (key !=null && key!="商品关键字" && key.length>0)url = url+"&key="+key;
		window.location = url;
	}
	
	//修改选购数量
	function modiNum(selid,newNum){
	    var url = "manageCartAction.do?method=modifyMerCount&cartMerId="+selid+"&merCount="+newNum;
		window.location = url;
			
		
	}
	
	//删除选购记录
	function delCart(selid){
		var url = "manageCartAction.do?method=deleteMerFromCart&cartMerId="+selid;
		window.location = url;
	}
	
	//清空购物车
	function clearCart(){
		var url = "manageCartAction.do?method=clearCart";
		window.location = url;
		}
	
		//继续购物
	function continueBuy(){
		var url = "merAction.do?method=searchMerByKeyAndCate&cateId=0&pageNo=1";
		window.location = url;
	}

	//进入下一步
	function next(){
		var url = "manageCartAction.do?method=checkOrder";
		window.location = url;
	}
	
			
</script>
</body>
</html>