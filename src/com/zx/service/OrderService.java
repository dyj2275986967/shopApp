package com.zx.service;

import javax.servlet.http.HttpServletRequest;

import com.zx.vo.OrderVo;

public interface OrderService {
	
	//插入订单信息
	public  OrderVo   insertOrderMsg(HttpServletRequest request);
	//展示订单信息
	public void selectOrderMsg(HttpServletRequest request);

}
