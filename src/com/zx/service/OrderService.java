package com.zx.service;

import javax.servlet.http.HttpServletRequest;

import com.zx.vo.OrderVo;

public interface OrderService {
	
	//���붩����Ϣ
	public  OrderVo   insertOrderMsg(HttpServletRequest request);
	//չʾ������Ϣ
	public void selectOrderMsg(HttpServletRequest request);

}
