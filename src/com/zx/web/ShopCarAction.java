package com.zx.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zx.service.ShopCarService;
import com.zx.service.impl.ShopCarServiceImpl;

import com.zx.vo.UserVo;

@Controller
@RequestMapping("/shopCar")
public class ShopCarAction {

	@Resource(name = "ShopCarService")
	private ShopCarService shop;

	
	@RequestMapping("/shopcar.do")
	public String showShopCar(HttpServletRequest request,String articleId,HttpSession session,String number) {
		
		  
				 //从session里面取user
		         UserVo user=      (UserVo)    session.getAttribute("userVo");
			
				//如果articleld不为空，则用户点击了加入购物车操作
				if (articleId!=null&&!"".equals(articleId)) {
				shop.insertInToShopCarByDetial(Integer.valueOf(number),user.getId(), Integer.valueOf(articleId));
				shop.showShopCar(request);
				//用户直接进入购物车
				}else {
			shop.showShopCar(request);  
			}
				return "shopcar";
		
	}
@RequestMapping("/addShopCar")
	public String addShopCar(HttpServletRequest request,	HttpSession session,String articleId,String number) {

		// 从session里面取user

		UserVo user = (UserVo) session.getAttribute("userVo");

		// 如果articleld不为空，则用户点击了加入购物车操作
		if (articleId != null && !"".equals(articleId)) {

			shop.insertInToShopCarByAddShopCar(Integer.valueOf(number), user.getId(), Integer.valueOf(articleId));

			shop.showShopCar(request);
			// 用户直接进入购物车
		} else {

			shop.showShopCar(request);
		}

		return "shopcar";
	}

	/*
	 * 删除购物车里的东西
	 */
	public String deleteShopCar(HttpSession session,String articleId) {


		// 从session里面取user

		UserVo user = (UserVo) session.getAttribute("userVo");

		shop.deleteShopCar(user.getId(), Integer.valueOf(articleId));

		return "forward:/shopCar/shopcar";//请求转发，转发至 后端处理器

	}



}
