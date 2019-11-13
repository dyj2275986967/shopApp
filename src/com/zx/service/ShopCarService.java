package com.zx.service;

import javax.servlet.http.HttpServletRequest;

public interface ShopCarService {
	//将商品信息存在购物车

	 public void insertInToShopCarByDetial(int buynum, int userId, int articleId);
	 
	 public void insertInToShopCarByAddShopCar(int buynum, int userId, int articleId);
	 //展示商品
	 public void showShopCar(HttpServletRequest request);
	 //删除商品信息
	 public  boolean deleteShopCar(int userId,int articleId);

}
