<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="${pageContext.request.contextPath}" var="path"></c:set>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>购物车</title>
<!-- Bootstrap core CSS -->
<link href="${path}/resources/bootstrap/css/bootstrap.css" rel="stylesheet" />

<link href="${path}/resources/css/taobao.css" rel="stylesheet" />
<script src="${path}/resources/jquery/jquery-1.11.0.min.js"></script>
<script src="${path}/resources/jquery/jquery-migrate-1.2.1.min.js"></script>
<script src="${path}/resources/bootstrap/js/bootstrap.js"></script>
<script src="${path}/resources/js/taobao.js"></script>
<script type="text/javascript">
function cutBuyNum(storage,articleId,buynum){
	  
	   if (parseInt(buynum)>1) {
		   buynum=parseInt(buynum)-1;	
	}
   	  window.location = "${path}/shopCar/addShopCar?number="+buynum+"&articleId="+articleId;

}




function addBuyNum(storage,articleId,buynum){

	  
	 if (parseInt(buynum)<parseInt(storage)) {

		 buynum=parseInt(buynum)+1;
	}
	
	//numObj.value=  parsetInt(numObj.value)>parsetInt(storage.value)?storage.value:(parseInt(numObj.value)+parseInt(1));
	   
	   
	   
	   
	   
	   window.location = "${path}/shopCar/addShopCar?number="+buynum+"&articleId="+articleId;
}


function numberBlurFn(obj,storage,articleId,buynum){
	

	if(isNaN(obj.value)||obj.value<1 ){
		obj.value=1;
		//如果用户选择的商品的数量 大于商品库存量  商品数量就等于1
	}else if(obj.value>storage){
		obj.value=1;
	}else{
		obj.value=Math.ceil(obj.value);
		
	}
	   		 window.location = "${path}/shopCar/addShopCar?number="+obj.value+"&articleId="+articleId;

}






//全选
function checkBoxAll(){
	var checkAllObj=document.getElementById("checkAll");
	var boxs=document.getElementsByName("box");
	var orderMsg=document.getElementById("orderMsg");
	 var checkPrice=	document.getElementById("checkPrice");
	 //获取提交订单的金额
	 var amount=	document.getElementById("amount");
	 for (var i =0; i < boxs.length; i++) {
	    if ( boxs[i].checked) {
	    	 boxs[i].checked=checkAllObj.checked;
		}else{
		
			 boxs[i].checked=checkAllObj.checked;
			 orderMsg.value+= boxs[i].value+"_";
		}
		
	 }
	 if (!checkAllObj.checked) {
		 orderMsg.value="";
		 checkPrice.innerHTML="0"*1;
		 amount.value=0;
	}else{
		str=orderMsg.value.split("_");
		//置空 checkPrice.innerHTML 防止把已选中的checkBox加上去
		checkPrice.innerHTML="0"*1;
		amount.value=0;
		for (var i = 1; i < str.length; i+=3) {
			  parseFloat(str[i]).toFixed(2)*1;	
			  checkPrice.innerHTML=parseFloat(checkPrice.innerHTML*1+parseFloat(str[i]).toFixed(2)*1).toFixed(2);
			  amount.value=parseFloat(amount.value*1+parseFloat(str[i]).toFixed(2)*1).toFixed(2);
		}
		
	}
	
	 
	 
	}


//当子check全部选中时      全选的checkbox 勾上
function boxClickFn(obj){

	var checkAllObj=document.getElementById("checkAll");
	var boxs=document.getElementsByName("box");
//操作提交订单按钮
   var checkPrice=	document.getElementById("checkPrice");
	var orderMsg=document.getElementById("orderMsg");
	//获取订单金额
	 var amount=	document.getElementById("amount");
  var num=0;
  var str=""

for (var i = 0; i < boxs.length; i++) {
		if (boxs[i].checked) {
			
			
			num++;         	
		}
	
	}

	 checkAllObj.checked=num==boxs.length;
	 
  
if (obj.checked) {

	str=obj.value.split("_");
	for (var i = 1; i < str.length; i+=3) {
		  parseFloat(str[i]).toFixed(2)*1;	
		  checkPrice.innerHTML=parseFloat(checkPrice.innerHTML*1+parseFloat(str[i]).toFixed(2)*1).toFixed(2);
		  amount.value=parseFloat(amount.value*1+parseFloat(str[i]).toFixed(2)*1).toFixed(2);
	}
	orderMsg.value+=obj.value+"_";
}else{
	
	orderMsg.value=orderMsg.value.replace(obj.value+"_","");
	str=obj.value.split("_");
	for (var i = 1; i < str.length; i+=3) {
		  parseFloat(str[i]).toFixed(2)*1;	
		  checkPrice.innerHTML=parseFloat(checkPrice.innerHTML*1-parseFloat(str[i]).toFixed(2)*1).toFixed(2);
		  amount.value=parseFloat(amount.value*1-parseFloat(str[i]).toFixed(2)*1).toFixed(2)
	}

}
	
	
}


function submitFn(){



	    
	var boxs=document.getElementsByName("box");
      var submitNum=0;
	for(var i=0;i<boxs.length;i++){
		if(boxs[i].checked){
			submitNum++;
		}
	}
	if(submitNum>0){
		document.getElementById("form").submit();
	}else{
		alert("亲，请至少选一个提交哦");
	
	}    

	
	
	}


	

</script>

</head>
<body>
	<!-- 横幅导航条开始 -->
	<nav class="navbar navbar-fixed-top navbar-inverse" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">显示导航条</span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${path }/article/index">shopApp</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="${path }/article/index">首页</a></li>
					<li class="active"><a href="${path }/shopCar/shopcar.do">购物车</a></li>
					<li><a href="${path}/order/aorder.do">我的订单</a></li>
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"> <span style='color: red;'></span></a></li>
					<c:choose>
						<c:when test="${not empty userVo }">
							<li class=""><a><font color="red">[${ userVo.name}]欢迎您</font></a></li>
							<li class=""><a href="${path}/user/loginOut">退出</a></li>
						</c:when>
						<c:otherwise>
							<li class=""><a href="${path }/user/login">登录</a></li>

						</c:otherwise>
					</c:choose>
					<li><a href="${path }/user/showRegister">免费注册</a></li>



				</ul>
			</div>
		</div>
	</nav>
	<!-- /.navbar -->
	<!--  横幅下方的主体开始 -->
	<div class="container">
		<div class="row row-offcanvas row-offcanvas-right">

			<!-- 内容主体开始 -->
			<div class="col-xs-12 col-sm-12">
				<div>当前位置：我的购物车</div>
				<div class="table-responsive">

					<table class="table table-hover table-bordered table-striped">

						<thead>
							<tr>
								<th><input type="checkbox" id="checkAll"
									onclick="checkBoxAll()"></th>
								<th style="width: 15%">图片</th>
								<th style="width: 50%">名称</th>
								<th style="width: 10%">价格</th>
								<th style="width: 10%">数量</th>
								<th style="width: 8%">小计</th>
								<th style="width: 7%">操作</th>
							</tr>
						</thead>
						<c:forEach items="${shopVo}" var="shopCar" varStatus="step">
						
					
	                      	<tbody>
	                
								<tr>

									<td style="vertical-align: middle;"><input type="checkbox"
								
										value="${shopCar.article.id}_${shopCar.article.price *shopCar.article.discount*shopCar.buynum}_${shopCar.buynum}" name="box" id="${shopCar.article.id}"   onclick="boxClickFn(this)" 
									/> <span id="total_1"
										style="display: none;">4899.00</span></td>
										   <td>      <a href="${path }/article/detial?id=${shopCar.article.id}"  >
									<img alt="商品图片"
										src="${path }/image/article/${shopCar.article.image }">
										 </a></td>
										 
					     	<td>  <a href="${path }/article/detial?id=${shopCar.article.id}"  >	${shopCar.article.title }</a></td>
									<td><span class="price">${shopCar.article.price }</span></td>
									<td><span class="glyphicon glyphicon-minus"
										style="cursor: pointer;"
										onclick="cutBuyNum(${shopCar.article.storage},${shopCar.article.id},${shopCar.buynum })"></span>
										<input type="hidden" value="${shopCar.article.id}"> <input
										type="hidden" value="${shopCar.article.storage}" id="storage">
										<input id="'${shopCar.article.id}" value="${shopCar.buynum }"
										style="width: 40px; text-align: center;"
										onblur="numberBlurFn(this,${shopCar.article.storage},${shopCar.article.id},${shopCar.buynum })"
										name="number_1" /> <span class="glyphicon glyphicon-plus"
										style="cursor: pointer;"
										onclick="addBuyNum(${shopCar.article.storage},${shopCar.article.id},${shopCar.buynum })"></span>
									</td>


									<td><font color="red"><fmt:formatNumber
												value="${shopCar.article.price *shopCar.article.discount*shopCar.buynum}"
												pattern="0.00"></fmt:formatNumber></font></td>
									<td><a
										href="${path }/shopCar/deleteShopCar?articleId=${shopCar.article.id}"
										onclick="return confirm('确定要把\n${shopCar.article.title }  \n从购物车中删除吗？')">删除</a>
										<a>收藏</a></td>

								</tr>
    
							</tbody>

						</c:forEach>
					</table>

				</div>
			</div>
			<!-- 内容主体结束 -->
		</div>
		<!--/row-->
	<div align="right">
	         <form action="${path }/order/alipay"  method="get"  id="form">
			总金额:<span id="totalMoney" style="color: red;"><fmt:formatNumber value="${allPrice }"  pattern="0.00" ></fmt:formatNumber></span>&nbsp;&nbsp;&nbsp;&nbsp;
		     <input type="hidden"  id="orderMsg" name="orderMsg"/>
		     <input type="hidden" id="amount" name="amount">
			<button id="commitOrder" class="btn btn-danger" type="button"
			
				onclick="submitFn()" data-toggle="modal" >
				
				提交订单 <span class="badge" id="checkPrice"></span>
			</button>
             </form>
		</div>
		<hr>
		
		<footer>
			<p>&copy; 版权所有，欢迎借鉴</p>
		</footer>
	</div>
	<!--/.container-->
	<!--  横幅下方的主体结束 -->
</body>
</html>