package com.zx.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import com.zx.service.OrderService;
import com.zx.service.impl.OrderServiceImpl;
import com.zx.vo.OrderVo;
import com.zx.vo.UserVo;
@Controller
//命名空间
@RequestMapping("/order")
public class OrderAction{

    
	@Resource(name="OrderService")
    private 	OrderService order;
    
    @RequestMapping("/aorder.do")
	public String order(HttpServletRequest request,String orderMsg) {

		// 获取带过来的商品信息

		// 如果有 信息带过来就下订单
		if (orderMsg != null && !"".equals(orderMsg)) {
			// 将带过来的信息传到服务层
			OrderVo vo=	order.insertOrderMsg(request);

			//获取用户信息
			UserVo user = (UserVo) request.getSession().getAttribute("userVo");
			 
			//设置金额
         Double   WIDtotal_amount=vo.getAmount();
         //存金额
         request.setAttribute("WIDtotal_amount", WIDtotal_amount);
	          //设置订单号
        String  WIDout_trade_no= vo.getOrderCode();
        //存订单号
        request.setAttribute("WIDout_trade_no", WIDout_trade_no);
		 //设置订单名称
		String	WIDsubject= vo.getOrderCode();
		   //存订单名称
        request.setAttribute("WIDsubject", WIDsubject);
		 
			/**
			 * 调用支付宝支付请求
			 */
        String msg=(String)request.getAttribute("alipay");
		 if ("alipay".equals(msg)) {
			 //调用支付宝页面

			  return "indexalipay";
		}
     
		
			
		//return "forward://indexalipay.jsp";
			// 没有则直接展示
		} else {

			// 展示商品信息
			order.selectOrderMsg(request);

		}

	return "order";

		
	}
    
    
    @RequestMapping("/alipay")
    public String aliPay(HttpServletRequest request) {
    	
      request.setAttribute("alipay", "alipay");
    	
    	
    	
    	//请求转发，转发至 后端处理器
    	return "forward:/order/aorder.do";
    }
    
    
	
	
	
	
	
	
	
	
	
	

}
