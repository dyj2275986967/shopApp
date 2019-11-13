package com.zx.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.zx.mapper.ArticleMapper;
import com.zx.mapper.OrderItemMapper;
import com.zx.mapper.OrderMapper;
import com.zx.mapper.ShopcarMapper;
import com.zx.service.OrderService;
import com.zx.vo.ArticleVo;
import com.zx.vo.OrderItemVo;
import com.zx.vo.OrderVo;
import com.zx.vo.ShopCarVo;
import com.zx.vo.UserVo;
@Transactional(rollbackFor=java.lang.Exception.class)
@Service(value="OrderService")
public class OrderServiceImpl implements OrderService {

@Autowired
private OrderMapper orderMapper;
@Autowired
private ShopcarMapper shopcarMapper;
@Autowired
private ArticleMapper articleMapper;
@Autowired
private OrderItemMapper orderItemMapper;
	@Override
	public OrderVo insertOrderMsg(HttpServletRequest request) {
		//订单表
		OrderVo orderVo = new OrderVo();
		//订单详细表
		OrderItemVo itemVo=new OrderItemVo();
		// 获取下单人id
		UserVo user = (UserVo) request.getSession().getAttribute("userVo");
       //生成订单状态  买家下单默认为1  1为 未发货状态
		String status="1";
		orderVo.setStatus(status);
		// 生成下单时间
		Date date = new Date();
		orderVo.setCreateDate(date);
		// 生成订单编号
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String str = sdf.format(date);
		String orderCode = "PO-" + str + "-" + user.getId();
		orderVo.setOrderCode(orderCode);
		orderVo.setUserId(user.getId());

		// 获取带过来的商品信息
		String msg = request.getParameter("orderMsg");
    	String[] article = msg.split("_");
	
		
	//准备list  储存商品信息
		List<ShopCarVo>  shopCarList=new ArrayList();

	  	double amount = 0;
		String a[][]=new String[article.length/3][3];
		 int j = 0;
	         for (int i = 0; i < a.length; i++) {
				for (int k = 0; k < a[i].length; k++) {
                  
	                     a[i][k]=article[j];
			       j++;
				}
			}
		ArticleVo ar=null;
	      for (int i = 0; i < a.length; i++) {
	    	  ShopCarVo  shopCar =new ShopCarVo();
					for (int k = 0; k < a[i].length; k++) {
						
						
				       //如果k=0 就说明带过来的是商品ID
						if (k==0) {
							shopCar.setArticleId(Integer.valueOf(a[i][k]));
							// 根据商品ID查询商品信息			
							shopCar.setArticle( articleMapper.findArticleMsgById(a[i][k]));
	
					  //如果k=1 就说明带过来的是总金额
						}else if(k==1) {
							amount+=Double.valueOf(a[i][k]);
							
					 //如果k=2	就说明带过来的是商品数量	
						}else if(k==2) {
				shopCar.setBuynum(Integer.valueOf(a[i][k]));
						}
						}
					shopCarList.add(shopCar);
				}
	//存总金额	
		orderVo.setAmount(amount);
		try {
			/*********************
			 * 加数据到订单详细表
			 *******************/
			itemVo.setShopCarList(shopCarList);
		
		   //插入订单表  返回定单 orderId
	       orderMapper.insertOrderMsg(orderVo);
	       
	      //    int i=5/0;   
	       
	    	itemVo.setOrderId(orderVo.getId());	
		
			/*********************
			 * 加数据到订单详细表
			 *******************/
		
			//插入订单详细表
			 for (ShopCarVo shopCarVo : itemVo.getShopCarList()) {
				 	 //查询库存信息
			ArticleVo articleVo=	articleMapper.findArticleMsgById(String.valueOf(shopCarVo.getArticleId()));
				 //更新库存信息
				 shopcarMapper.updateShopStorage(shopCarVo.getArticleId(), articleVo.getStorage()-shopCarVo.getBuynum());
				 
				 orderMapper.insertOrderItemMsg(itemVo.getOrderId(),shopCarVo.getArticleId(),shopCarVo.getBuynum());
				}
			//插入后删除 购物车里下顶单的商品信息
			for (ShopCarVo shopCarVo : itemVo.getShopCarList()) {
				shopcarMapper.deleteOrderShopCarByArticleId(user.getId(), shopCarVo.getArticleId());
			}
		} catch (Exception e) {
			throw new RuntimeException("保存失败");
		}
	
		//返回订单详情
		return orderVo;
	}

	public void selectOrderMsg(HttpServletRequest request) {
        // 获取用户信息    
		UserVo user = (UserVo) request.getSession().getAttribute("userVo");
		//查订单信息               
		
		                                            
	    List<OrderVo>	 orderVoList =  orderMapper.selectOrderMsg(user.getId());
	  
	 
	  
	    //遍历订单信息 看该用户有几个订单   通过订单id查询   订单详细表中的信息  
	    for (OrderVo order : orderVoList) {
        
	    	order.setOrderItemList(	 orderItemMapper.selectOrderItemMsg(order.getId())); 
	   
	    		}

	    //存订单信息
	    request.setAttribute("orderVoList", orderVoList);
		
	}

           
}
