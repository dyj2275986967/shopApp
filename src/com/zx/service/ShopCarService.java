package com.zx.service;

import javax.servlet.http.HttpServletRequest;

public interface ShopCarService {
	//����Ʒ��Ϣ���ڹ��ﳵ

	 public void insertInToShopCarByDetial(int buynum, int userId, int articleId);
	 
	 public void insertInToShopCarByAddShopCar(int buynum, int userId, int articleId);
	 //չʾ��Ʒ
	 public void showShopCar(HttpServletRequest request);
	 //ɾ����Ʒ��Ϣ
	 public  boolean deleteShopCar(int userId,int articleId);

}
