<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
 <c:set  value="${pageContext.request.contextPath}"   var="path"></c:set>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>商品详情</title>
<link href="${path }/resources/bootstrap/css/bootstrap.css" rel="stylesheet" />
<link href="${path }/resources/css/taobao.css" rel="stylesheet" />
<script src="${path }/resources/jquery/jquery.min.js"></script>
<script src="${path }/resources/bootstrap/js/bootstrap.js"></script>
<script src="${path }/resources/js/taobao.js"></script>








<script type="text/javascript">

// 当页面加载完 才能拿到属性 元素值
$(function (){
	//加一件商品
	$("#addNumber").click(function (){

		$("#number").prop("value",parseInt($("#number").val())+1);
		})

		
//减一件商品		
		$("#cutNumber").click(function (){
   //如果用户点击减号时显示的值  小于等于一件 就不动值，否则就减一
		       $("#number").val($("#number").val()<=1?$("#number").val():parseInt($("#number").val())-1);

		})
		
   //失去焦点事件，校验用户输入的商品件数值是否符合规范	
   $("#number").blur(function (){
	   
	 	if (isNaN($("#number").val())||$("#number").val()<1) {
			$("#number").val(1);

		}else{
			$("#number").val(Math.ceil($("#number").val()));

		}
 	$("#button").submit()

})
		
})





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
					<li ><a href="${path }/article/index">首页</a></li>
					<li ><a href="${path }/shopCar/shopcar.do">购物车</a></li>
					<li><a href="${path}/order/aorder.do">我的订单</a></li>
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"><span style='color: red;'></span></a></li>
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
			<!-- /.nav-collapse -->
		</div>
	</nav>
	<!--  横幅下方的主体开始 -->
	<div class="container">

		<div class="row row-offcanvas row-offcanvas-right">

			<!-- 内容主体开始 -->
			<div class="col-xs-12 col-sm-12">
				<div class="col-xs-12 col-sm-6">
					<img alt="商品的图片" src="${path }/image/article/${articleVo.image}">
				</div>
				<div class="col-xs-12 col-sm-6">
					<p>${articleVo.title }</p>
					<p>${articleVo.supplier }</p>
					<p>${articleVo.locality }</p>
					<p>
							<span class="price">${articleVo.price }</span>
						<span >折后价:</span>
       						<font  color="red"><span> <fmt:formatNumber value="${articleVo.price*articleVo.discount }"  pattern="0.00"></fmt:formatNumber>  </span></font>

					</p>
					<p>
						库存量：<span id="storage">${articleVo.storage }</span>
					</p>
					<p>
					<form method="get" action="${path }/shopCar/shopcar.do">
						<input type="hidden" name="id" value="1" /> <span
							style="font-weight: bold; font-size: 18px; cursor: pointer; margin-left: 10px; margin-right: 10px;" id="cutNumber">-</span>
						<input id="number" name="number" value="1"   style="width: 50px;" />
						<span
							style="font-weight: bold; font-size: 18px; cursor: pointer; margin-left: 10px; margin-right: 10px;" id="addNumber">+</span>
						<button  id="button">
						    <input type="hidden" name="articleId"  value="${articleVo.id }"/>
							<span class="glyphicon glyphicon-shopping-cart"
								style="color: red;"></span>加入购物车
						</button>
					</form>
					</p>
				</div>
				<div class="col-xs-12">
					<fieldset>
						<legend>介绍</legend>
                        ${articleVo.description }
					</fieldset>
				</div>
			</div>
			<!--/.col-xs-12.col-sm-9-->
			<!-- 内容主体结束 -->
		</div>
		<!--/row-->
		<hr>
		<footer>
			<p>&copy; 版权所有，欢迎借鉴</p>
		</footer>

	</div>
	<!--/.container-->
	<!--  横幅下方的主体结束 -->

</body>
</html>