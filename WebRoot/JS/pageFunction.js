	
	
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