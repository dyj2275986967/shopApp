<%@page import="java.util.UUID"%>
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
<title>注册页面</title>
<!-- Bootstrap core CSS -->
<link href="${path }/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<link href="${path }/resources/css/taobao.css" rel="stylesheet" />
<script src="${path }/resources/jquery/jquery.min.js"></script>
<script type="text/javascript">

$(function() {

	//失去焦点事件校验账号是否匹配正确的格式
	$("#loginName").blur(function (){
		
		var standard = /^([a-zA-Z0-9]|[-]){5,12}$/;
		if (!this.value) {
			$("#loginNameTip").html( "<font color ='red'>*账号不能为空</color>");
		
		} else if (!standard.test(this.value)) {
			$("#loginNameTip").html( "<font color ='red'>*账号格式不正确</color>");
		}else{
		//异步判断是否有相同账号
		$.ajax({
			   //指定请求地址的url
			   url:"${path}/user/ajaxRegister",
		      //指定
			   type:"post",
			   data:"loginName="+$("#loginName").val(),
			  //预期服务器返回的数据类型
			   dateType:"text",
			   //服务器响应成功时候的回调函数
			   success:function(result){
				   if(result=="1"){
					$("#loginNameTip").html( "<font color ='red'>*该账号已被注册</color>");
				   }
		       
		    	},error:function(xhr, textStatus, err){//服务器响应失败时候的回调函数
	  	    			alert(xhr);
		    			alert(textStatus);
		    			alert(err)
		    			
				   
			   }
			  	   
		   })
		  	 //异步判断是否有相同账号	
		}
		
		
	
		
  	
	//	聚焦去掉提示值
	}).focus(function (){
      
		$("#loginNameTip").html("");
		
		
	})
	

	
	
	//失去焦点事件校验密码是否匹配正确的格式
	$("#password").blur(function(){
		var standard = /^([a-zA-Z0-9]|[-]){6,12}$/;

		if (!this.value) {

		$("#passwordTip").html( "<font color ='red'>*密码不能为空</color>");
  
		} else if (!standard.test(this.value)) {

		$("#passwordTip").html("<font color ='red'>*密码格式不正确</color>");
		}
		
		
  //聚集去掉提示值
    }).focus(function(){
			
		$("#passwordTip").html("");
		
	})

	

	
	//失去焦点事件校验确认密码是否匹配正确的格式
$("#okPassword").blur(function(){

	var passwordVal = $("#password").val();

	if (!this.value) {
		$("#okPasswordTip").html("<font color='red'>*请输入确认密码</font>");

	} else if (passwordVal != this.value) {
		$("#okPasswordTip").html( "<font color='red'>*两次密码不一致,请重新输入</font>");		
	}
	
	  //聚集去掉提示值
}).focus(function (){
    	 
		$("#okPasswordTip").html("");			 
     })
	
     
     //失去焦点事件校验手机账号是否匹配正确的格式
	$("#phone").blur(function (){
		var phoneStandard = /^1[3456789]\d{9}$/;
		if (!this.value) {

			$("#PhoneTip").html("<font color='red'>*手机号码不能为空</font>");
	 	} else if (!phoneStandard.test(this.value)) {
	    	$("#PhoneTip").html ("<font color='red'>*手机号码格式不正确,请重新输入</font>");
		
		}	
		
	}).focus(function(){
		$("#PhoneTip").html ("");		
	})
	
	
	  //失去焦点事件校验邮箱账号是否匹配正确的格式
	$("#email").blur(function (){
       var emailStandard = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
		if (!this.value) {
			
			$("#emailTip").html( "<font color='red'>*邮箱地址不能为空</font>");
    
		} else if (!emailStandard.test(this.value)) {
       
			$("#emailTip").html("<font color='red'>*邮箱地址格式不正确,请重新输入</font>"); 
			  }
	}).focus(function (){
				$("#emailTip").html ("");	
	})
  
	
	
	//验证码
   $("#yzm").blur(function(){
         if (!this.value) {
	   $("#yzmTip").html("<font color='red'>*验证码不能为空！</font>");
      //   $("imgyzm").src("yzm?date=" + Math.random());	   
		}else{
	    
		//异步判断是否有相同账号
			$.ajax({
				   //指定请求地址的url
				   url:"${path}/user/ajaxYzm",
			      //指定
				   type:"post",
				   data:"yzm="+$("#yzm").val(),
				  //预期服务器返回的数据类型
				   dateType:"text",
				   //服务器响应成功时候的回调函数
				   success:function(result){
					   if(result=="1"){
					
					   }else{
						   
							$("#yzmTip").html( "<font color ='red'>*验证码输入错误</color>");   
						   
					   }
			       
			    	},error:function(xhr, textStatus, err){//服务器响应失败时候的回调函数
		  	    			alert(xhr);
			    			alert(textStatus);
			    			alert(err)
			    				   
				   }
				  	   
			   })
			  	 //异步判断是否有相同账号		
		}

	 
 }).focus(function (){
		$("#yzmTip").html ("");	
	})
	
	
//异步用户注册按钮
$("#btn_submit").click(function(){
	
  	//对表单数据进行序列化
  	var dataSer =$("#registerForm").serialize();


	//异步判断是否有相同账号
	$.ajax({
	
		   //指定请求地址的url
		   url:"${path}/user/register",
	      //指定
		   type:"post",
		   data:dataSer,
		  //预期服务器返回的数据类型
		   dateType:"text",
		   //服务器响应成功时候的回调函数
		   success:function(result){
	
			   if(result=="账号"){
					  //清空输入框信息
					   $("#loginName").val("");
					/*    $("#loginNameTip").html( "<font color ='red'>*该账号已被注册,请您重新输入</color>"); */
	          } else if(result=="密码"){
	        
	        	  //清空输入框信息
	        	  
	        	  	   $("#okPassword").val("");
	        	  		$("#okPasswordTip").html( "<font color='red'>*请您再次核对密码</font>");		
			/* 	   $("#password").focus(function (){
					     	this.value="";	
		             }) */
				   
			   }else if(result=="手机"){
				   //聚焦清空输入框信息
				   	  	   $("#phone").val("");
				 /*   $("#phone").focus(function (){
					     	this.value="";	
		             })
				    */
			   }else if(result=="邮箱"){
				   //聚焦清空输入框信息
				     	   $("#email").val("");
			/* 	   $("#email").focus(function (){
					     	this.value="";	
		             }) */
			   }else if(result=="yzm"){
				   $("#yzm").val("");
				   //聚焦清空输入框信息
			/* 	   $("#yzm").focus(function (){
					     	this.value="";	
		             })
				    */
			   }else if(result=="名字"){
				   //聚焦
				  
			/* 	   $("#name").focus(function (){
			      }) */
				 $("#nameTip").html("<font color ='red'>*请输入您的名字</color>");
			      
			   }else if(result=="地址"){
				   //聚焦
			/* 	   $("#address").focus(function (){
			      }) */
				 $("#addressTip").html("<font color ='red'>*请输入正确地址</color>");
			      
			   }else {
				   
				   //跳转至首页
	            	  window.location = "${path}/user/login";
			   }
	       
	    	},error:function(xhr, textStatus, err){//服务器响应失败时候的回调函数
  	    			alert(xhr);
	    			alert(textStatus);
	    			alert(err)
	    				   
		   }
		  	   
	   })
//异步用户注册按钮	

//聚焦去提示值名字，地址
$("#address").focus(function(){
		 $("#addressTip").html("");
})
$("#name").focus(function(){
		 $("#nameTip").html("");
})

//聚焦去提示值名字，地址
	
})	
})
//重置按钮
	function resetFn(obj){
		obj.type=reset;
		
}
	function cutImg(obj) {
    //$("#imgyzm").src()
		obj.src = "yzm?date=" + Math.random();
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
					<li ><a href="${path }/article/index">首页</a></li>
					<li><a href="${path }/shopCar/shopcar.do">购物车</a></li>
					<li><a href="${path}/order/aorder.do">我的订单</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
							<li><a href="#"> <span style='color: red;'></span></a></li>
				    <c:choose>
                    <c:when test="${not empty userVo }">
       					<li class=""><a><font color="red">[${ userVo.name}]欢迎您</font></a></li>
					<li class=""><a href="${path}/user/logout">退出</a></li>
					</c:when>
					<c:otherwise>
					<li class=""><a href="${path }/user/login">登录</a></li>
			
					</c:otherwise>
					</c:choose>
					<li class="active"><a href="${path }/user/showRegister">免费注册</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<!--  横幅下方的主体开始 -->
	<center>
	<span id="msgTip"><font color="red" ></font></span>
    </center>
	<div class="container">
		<div class="row info-content">

			<form id="registerForm" class="form-horizontal" method="post"
				action="${path }/user/register" style="margin-top: 20px;" onsubmit="return submitFn()">
				<div class="form-group">
					<label class="col-sm-2 control-label">登录名称</label>
					<div class="col-sm-3">
						<input type="text" value="" id="loginName" name="loginName"
							class="form-control" placeholder="请输入您的登陆名称"
					    >
					</div>
					<div>
						<span id="loginNameTip"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">真实姓名</label>
					<div class="col-sm-3">
						<input type="text" id="name" value="" name="name"
							class="form-control" placeholder="请输入您的真实姓名">
					</div>
						<div>
						<span id="nameTip"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">登录密码</label>
					<div class="col-sm-3">
						<input type="password" id="password" name="password"
							class="form-control" placeholder="请输入您的密码"
						>
					</div>
					<div>
						<span id="passwordTip"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">确认密码</label>
					<div class="col-sm-3">
						<input type="password" id="okPassword" name="okpassword"
							class="form-control" placeholder="请输入您的确认密码"
						>
					</div>
					<div>
						<span id="okPasswordTip"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">性别</label>
					<div class="col-sm-3">
						<label>男:</label><input name="sex" value="1" type="radio"
							checked="checked" id="sex"> <label>女:</label><input
							name="sex" value="2" type="radio" id="sex">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">联系电话</label>
					<div class="col-sm-3">
						<input type="text" id="phone" value="" name="phone"
							class="form-control" placeholder="请输入您的电话"
						>
					</div>
					<div>
						<span id="PhoneTip"></span>
					</div>
				
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">地址</label>
					<div class="col-sm-8">
						<textarea id="address" name="address" rows="4" cols="30"
							class="form-control" placeholder="请输入您的地址信息"></textarea>
					</div>
					<div>
						<span id="addressTip"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">电子邮箱:</label>
					<div class="col-sm-3">
						<input type="text" id="email" value="" name="email"
							class="form-control" placeholder="请输入您的邮箱"
						>
					</div>
					<div>
						<span id="emailTip"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">验证码:</label>
					<div class="col-sm-2">
						<input type="text" id="yzm" value="" name="yzm"
							class="form-control" placeholder="请输入验证码" >
					</div>

					<div>
						<img src="${path }/user/yzm" style="width: 70px; height: 25px"
							onclick="cutImg(this)" id="imgyzm" />
					</div>
				        <div>	<span id="yzmTip"></span></div>
					</div>
	           
				<div class="col-sm-3">
					<span style="color: red;"></span>
				</div>
		</div>
		<%
			//获取当前注册时间
			java.util.Date date = new java.util.Date();
			request.setAttribute("date", date);
		%>
		<div>
			<!--  用户注册时的时间-->
			<input type="hidden" name="createDate" id="date"
				value="<fmt:formatDate value="${requestScope.date }"  pattern="yyyy-MM-dd  HH:mm:ss"/>" />
			<!--用户注册的角色:买家    买家为1 -->
			<input type="hidden" name="role" value="1"> <input
				type="hidden" name="active" value="${requestScope.uuid }" />
		</div>
	</div>

	<div class="form-group">
		<label class="col-sm-2 control-label"></label>
		<div class="col-sm-3">
			<button type="button" id="btn_submit" class="btn btn-success"
			>
				<span class="glyphicon glyphicon-user">注册 
			</button>
			<button type="reset" class="btn btn-info"   onclick="resetFn(this)">
				<span class="glyphicon glyphicon-edit">重置 
			</button>
		</div>
	</div>
	</form>
	</div>
	<footer>
		<p>&copy; 版权所有，欢迎借鉴</p>
	</footer>

	<script>
		
	</script>
	</div>
	<!--  横幅下方的主体结束 -->
</body>
</html>