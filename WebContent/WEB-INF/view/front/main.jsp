<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@taglib prefix="z" uri="/fkjava/oa"%>
     <c:set  value="${pageContext.request.contextPath}"   var="path"></c:set>
   <!DOCTYPE html>
<html lang="zh_CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>商品首页</title>
<link href="${path}/resources/bootstrap/css/bootstrap.css" rel="stylesheet" />
<link href="${path}/resources/css/taobao.css" rel="stylesheet" />
<!-- 引入分页样式文件 -->
<link href="${path}/resources/css/pager.css" type="text/css" rel="stylesheet" />



</head>
<script src="${path}/resources/jquery/jquery.min.js"></script>
<script src="${path}/resources/bootstrap/js/bootstrap.js"></script>
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
					<li class="active"><a href="${path }/article/index">首页</a></li>
					<li><a href="${path }/shopCar/shopcar.do">购物车</a></li>
					<li><a href="${path}/order/aorder.do">我的订单</a></li>
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"> <span style='color: red;'></span></a></li>
				    <c:choose>
                    <c:when test="${not empty userVo }">
       					<li class=""><a><font color="red">[${userVo.name}]欢迎您</font></a></li>
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
		<!-- /.container -->
	</nav>
	<!-- 横幅导航条结束 -->
	<!--  横幅下方的主体开始 -->
	<div class="container">
		<div class="row row-offcanvas row-offcanvas-right">
			<!-- 侧边导航开始 -->
			<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar"
				role="navigation">
				<div class="list-group">
				
			<c:forEach  items="${requestScope.firstArticleType}" var="article">
     <c:choose>		
         <c:when test="${requestScope.typeCode eq article.code}"> 
                 
			<a  href="${path }/article/index?typeCode=${article.code}" class="list-group-item  active"  id="Abackground" >${article.name}</a>
		   </c:when>	
		   <c:otherwise>
		   
		   			<a  href="${path }/article/index?typeCode=${article.code}" class="list-group-item  "  id="Abackground" >${article.name}</a>
		   
		   </c:otherwise>
		   
		   
		   
		</c:choose>	
					</c:forEach>
				</div>
			</div>
			<!--  侧边导航结束 -->
			<!-- 内容主体开始 -->
			<div class="col-xs-12 col-sm-9">
				<p class="pull-right visible-xs">
					<button type="button" class="btn btn-primary btn-xs"
						data-toggle="offcanvas">显示导航条</button>
				</p>
				<div class="alert alert-info" role="alert">
            <c:forEach   var="article" items="${requestScope.twoArticleType }">
            <c:choose>
               <c:when test="${twoTypeCode eq article.code }">
            
				 <a href="${path}/article/index?typeCode=${article.code }" class="btn btn-default  active">${ article.name} </a>
				</c:when>
				<c:otherwise>
				 <a href="${path}/article/index?typeCode=${article.code }" class="btn btn-default  ">${ article.name} </a>
			
				</c:otherwise>
					</c:choose>
					</c:forEach>
					<div>
						<form action="${path }/article/index" method="get">
							<!-- 如果当前选择了商品的类型，仅在该类型下面进行搜索 -->
							<input type="hidden" name="typeCode" value="${requestScope.typeCode }" /> <input
								name="keyword" value="${keyword }" />
							<button type="submit">搜索</button>
						</form>
					</div>
				</div>

				<div class="row">
				<!--/.col-xs-6.col-lg-4-->
                    <c:forEach   var="articleAll" items="${requestScope.articleAllMsgByCode}" >
					<div class="col-xs-6 col-lg-4">
					
					
						<span class="thumbnail"> <a href="${path }/article/detial?id=${articleAll.id}">
						 	<img src=" ${path }/image/article/${articleAll.image }" alt="..."> 
								<p style="height: 20px; overflow: hidden;">${articleAll.title}</a>
							<p>
								<span class="price">${articleAll.price }</span>
									<span >折后价:</span>
       						<font  color="red"><span>     <fmt:formatNumber value="${articleAll.price*articleAll.discount }"  pattern="0.00"></fmt:formatNumber>  </span></font>
                              </p>
                       
						</span>
					</div>
					</c:forEach>
					<!--/.col-xs-6.col-lg-4-->

				</div>
				<!--/row-->


				<!-- p2是url前段部分pageNumber=之前 -->


				<!--  分页开始 -->

				<div class="row">
					<nav>
						<ul class="pagination">
						<!-- 	<div>
								<a href="javascript:goPage(1)">首页</a> <a
									href='javascript:goPage(1)'>上一页</a> <a
									href='javascript:goPage(2)'>下一页</a> <a
									href='javascript:goPage(7)'>尾页</a> &nbsp; <span>第&nbsp;
									1&nbsp;页/共&nbsp; 7页 ，共 52 条数据 </span>
							</div> -->
  <z:pager pageIndex="${pageIndex}" pageSize="${pageModel.pageSize }" totalNum="${pageModel.totalNum }" submitUrl="index.action?pageIndex={0}" pageStyle="megas512"></z:pager>
							
						</ul>
				</div>
				<!-- 分页结束 -->
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
</body>
</html>