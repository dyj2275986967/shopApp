<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath}" var="path"></c:set>
<!DOCTYPE html>
<html lang="zh_CN">
<script src="${path }/resources/jquery/jquery.min.js"></script>
<script type="text/javascript">
   $(function (){
	//如果uuid有值则说明激活成功
	if($("#uuid").val()){
		
		$("#activeTip").html("<font color ='red'>激活成功</color>");
			}	
	
  //校验账号是否匹配正确的格式
	$("#loginName").blur(function (){
	
		var standard = /^([a-zA-Z0-9]|[-]){5,12}$/;
	      if(!this.value){
			$("#loginNameTip").html("<font color ='red'>*用户名不能为空</color>");
			   
		   }else	if (!standard.test(this.value)) {
				$("#loginNameTip").html("<font color ='red'>*用户名格式不正确</color>");
	   }	  
	   //聚焦事件，聚焦时将提示清掉   
          }).focus(function (){
	      	$("#loginNameTip").html("");
	    })
   
  
   $("#passWord").blur(function (){

	   var standard = /^([a-zA-Z0-9]|[-]){5,12}$/;
		if (!this.value) {
			$("#passwordTip").html( "<font color ='red'>*密码不能为空</color>");
		}else if (!standard.test(this.value)) {
	
			$("#passwordTip").html("<font color ='red'>*密码格式不正确</color>");

		}	
		   //聚焦事件，聚焦时将提示清掉       
   }).focus(function (){
	    $("#passwordTip").html("");   
   })
  
  
   //jQuery AJAX
   $("#loginBtn").click(function (){

	  //获取账号信息
	  var loginNameVal=$("#loginName").val();
	  var passWordVal=$("#passWord").val(); 
	  var yzmVal=$("#yzm").val();
	  var uuidVal=$("#uuid").val();
	  var checkedVal="0";
	
	  if($("#checked").is(':checked')){
		  
		  checkedVal=$("#checked").val();
		  
	  }
	  
	 

	   $.ajax({
		   //指定请求地址的url
		   url:"${path}/user/loginIn",
	      //指定
		   type:"post",
		   data:"loginName="+loginNameVal+"&password="+passWordVal+"&yzm="+yzmVal+"&active="+uuidVal+"&remember="+checkedVal,
		  //预期服务器返回的数据类型
		   dateType:"text",
		   //服务器响应成功时候的回调函数
		   success:function(result){
			   
	            if(result== "1"){
                 //聚焦清空登陆名的值
	            	 $("#loginName").focus(function() {
	            		 this.val("");     		 
	            	 })
	            	
	            	 $("#loginNameTip").html( "<font color='red'>账号不存在！</font>");
	              }else if(result == "2"){
	            	   //聚焦清空登陆名的值
		            	 $("#passWord").focus(function() {
		            		 this.val("");     		 
		            	 })
	            	 $("#passwordTip").html( "<font color='red'> 密码输入错误</font>");
	              }else if(result =="3"){
	            	
	            	  $("#activeTip").html( "<font color ='red'>账号未激活，请先激活</font>");
	            	  
	              }else if(result=="4"){
	            	  
	            	  $("#yzmTip").html ( "<font color ='red'>验证码错误</font>");	
	            	  
	              }else{
	            	  //跳转至首页
	            	  window.location = "${path}/article/index";
	              }
	    			
	    		},error:function(xhr, textStatus, err){//服务器响应失败时候的回调函数
  	    			alert(xhr);
	    			alert(textStatus);
	    			alert(err)
	    			
			   
		   }
		  	   
	   })
	   

  })   
 
  //注册跳转按钮

$("#registerBtn").click(function (){

	  window.location = "${path}/user/showRegister";
	
	
})
  
  
  
   })









//验证码
function cutImg(obj){
	
  obj.src = "yzm?date="+Math.random();
	
}







</script>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>登录页面</title>
<!-- Bootstrap core CSS -->
<link href="${path }/resources/bootstrap/css/bootstrap.css" rel="stylesheet" />
<link href="${path }/resources/css/taobao.css" rel="stylesheet" />
<script src="${path }/resources/bootstrap/js/bootstrap.js"></script>
<script src="${path }/resources/jquery/jquery.js"></script>
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
					<li><a href="${path }/shopCar/shopcar.do">购物车</a></li>
					<li><a href="${path}/order/aorder.do">我的订单</a></li>
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"><span style='color: red;'></span></a></li>
					<li class="active"><a href="${path }/user/login">登录</a></li>
					<li><a href="${path }/user/showRegister">免费注册</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- /.navbar -->
	<!--  横幅下方的主体开始 -->
	<div class="container">
		<!-- 入门-->
		<!-- 登录界面 -->
		<div class="page-header">
			<div>
				<span><font color="red">${msg }</font></span>
			</div>
			<div>
				<span id="activeTip"></span>
			</div>
			<h1>用户登录</h1>
		</div>
		<form class="form-horizontal" method="post" >
			<div class="form-group">
				<div class="col-sm-4">
					<input class="form-control" value="" placeholder="用户名/邮箱"
						type="text" id="loginName" name="user.loginName" />
				</div>
				<div>
					<span id="loginNameTip"></span>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-4">
					<input class="form-control" placeholder="密码" id="passWord"
						type="password" name="user.password" />
				</div>
				<input id="uuid" type="hidden" name="user.active"
					value="${requestScope.active }" />
				<div>
					<span id="passwordTip"></span>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-2">
					<input class="form-control" value="" placeholder="请输入验证码"
						type="text" id="yzm" name="user.yzm" onblur="yzmBlurFn(this)" />
				</div>
				<div>
					<img src="${path}/user/yzm" style="width: 70px; height: 25px"
						onclick="cutImg(this)" />
				</div>
				<div>
					<span id="yzmTip"></span>
				</div>
				
			</div>
           <div class="form-group">
				<div class="col-sm-2">
					<input type="checkbox" name="remember"  value="1"  id="checked"/> 是否记住一周
				</div>
		
			</div>
              


			<div class="form-group">
				<div class="col-sm-4">
					<span style="color: red;"></span>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-4">
					<div class="btn-group btn-group-justified" role="group"
						aria-label="...">
						<div class="btn-group" role="group">
							<button type="button" id="loginBtn" class="btn btn-success">
								<span class="glyphicon glyphicon-log-in"></span>&nbsp;登录
							</button>
						</div>
						<div class="btn-group" role="group">
							<button type="button" class="btn btn-danger"  id="registerBtn">
								<span class="glyphicon glyphicon-edit"></span>注册
							</button>
						</div>
					</div>
				</div>
			</div>
		</form>
		<hr>

		<!-- 彈出框-->
		<div id="myModal" class="modal bs-example-modal-sm fade">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">登录提示</h4>
					</div>
					<div class="modal-body">
						<p id="tip"></p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button id="sure" type="button" class="btn btn-primary"
							data-dismiss="modal">确定</button>
					</div>
				</div>
			</div>
		</div>

		<footer>
			<p>&copy; 版权所有，欢迎借鉴</p>
		</footer>

	</div>
	<!--/.container-->
	<!--  横幅下方的主体结束 -->


</body>
</html>