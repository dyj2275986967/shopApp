<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%> 
<%@taglib  prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
 <c:set  value="${pageContext.request.contextPath}"   var="path"></c:set>   
<!DOCTYPE html>
<html lang="zh_CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>商品详情</title>
    <!-- Bootstrap core CSS -->
    <link href="${path }/resources/bootstrap/css/bootstrap.css" rel="stylesheet" />
     <link rel="stylesheet" href="${path }/resources/bootstrap/style.css" />
    <link href="${path }/resources/css/taobao.css" rel="stylesheet" />
   <script src="${path }/resources/jquery/jquery.js"></script>
     
  </head>
  
  <body>

	

  
 <!-- 横幅导航条开始 -->
    <nav class="navbar navbar-fixed-top navbar-inverse" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" 
          	data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">显示导航条</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="${path }/article/index">shopApp</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li ><a href="${path }/article/index">首页</a></li>
            <li><a href="${path }/shopCar/shopcar.do">购物车</a></li>
            <li class="active"><a href="${path}/order/aorder.do">我的订单</a></li>
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
        </div><!-- /.nav-collapse -->
        
         
      </div><!-- /.container -->
    </nav><!-- /.navbar -->
	
	<!--  横幅下方的主体开始 -->
    <div class="container">
				<div class="table-responsive">
				<c:forEach   items="${ requestScope.orderVoList}"  var="order">
				<table class="table table-hover table-bordered table-striped">
				
					<thead>
						<tr style="background-color:  #56cdc7">
							<th style="width: 15%">订单号</th>
							<th style="width: 40%">下单时间</th>
							<th style="width: 10%">发货时间</th>
							<th style="width: 8%">订单状态</th>
							<th style="width: 15%">订单总金额</th>
						</tr>
					</thead>
					
					<tbody>
					
						
					 
							<tr>
								<td>
	                              ${order.orderCode }
								</td>
								<td>
						
								<fmt:formatDate value="${order.createDate}"    pattern="yyyy年MM月dd日  HH:mm:ss"/>	
								</td>
								<td>
									
								</td>
								<td>
								<c:choose>
							<c:when test="${order.status eq 1}">
							       <font color="red"> 未发货</font>
							</c:when>
							<c:otherwise><font color="red">已发货</font></c:otherwise>
								</c:choose>
								
								
								</td>
								<td>
							       ${order.amount}
								</td>
							</tr>
						
					</tbody>
                             
					<thead>
						<tr>
							<th style="width: 15%">图片</th>
							<th style="width: 50%">名称</th>
							<th style="width: 10%">价格</th>
							<th style="width: 8%">数量</th>
							<th style="width: 7%">操作</th>
						</tr>
					</thead>
          		<c:forEach items="${order.orderItemList}"  var="ItemList"> 
					
					<tbody>
					
						
					 
							<tr>
								<td>
									<img alt="商品图片" src="${path }/image/article/${ItemList.article.image}">
								</td>
								<td>
							 	 ${ItemList.article.title}
								</td>
								<td>
									<span class="price"><font color="red">${ItemList.article.price}</font></span>
					          				</td>
								<td>
									<span > ${ItemList.orderNum}</span>
								</td>
								<td>
									<a href="${path }/article/detial?id=${ItemList.article.id }">查看商品</a>									
								</td>
							</tr>
						
					</tbody>
               </c:forEach> 

				</table>
				   </c:forEach>
			</div>
				 
			   
				
				 
			   
	     

      <footer>
        <p>&copy; 版权所有，欢迎借鉴</p>
      </footer>

    </div><!--/.container-->
    <!--  横幅下方的主体结束 -->

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
   
    <script src="resources/bootstrap/js/bootstrap.js"></script>

    <script src="resources/js/taobao.js"></script>
  </body>
</html>