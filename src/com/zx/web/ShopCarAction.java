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
		
		  
				 //��session����ȡuser
		         UserVo user=      (UserVo)    session.getAttribute("userVo");
			
				//���articleld��Ϊ�գ����û�����˼��빺�ﳵ����
				if (articleId!=null&&!"".equals(articleId)) {
				shop.insertInToShopCarByDetial(Integer.valueOf(number),user.getId(), Integer.valueOf(articleId));
				shop.showShopCar(request);
				//�û�ֱ�ӽ��빺�ﳵ
				}else {
			shop.showShopCar(request);  
			}
				return "shopcar";
		
	}
@RequestMapping("/addShopCar")
	public String addShopCar(HttpServletRequest request,	HttpSession session,String articleId,String number) {

		// ��session����ȡuser

		UserVo user = (UserVo) session.getAttribute("userVo");

		// ���articleld��Ϊ�գ����û�����˼��빺�ﳵ����
		if (articleId != null && !"".equals(articleId)) {

			shop.insertInToShopCarByAddShopCar(Integer.valueOf(number), user.getId(), Integer.valueOf(articleId));

			shop.showShopCar(request);
			// �û�ֱ�ӽ��빺�ﳵ
		} else {

			shop.showShopCar(request);
		}

		return "shopcar";
	}

	/*
	 * ɾ�����ﳵ��Ķ���
	 */
	public String deleteShopCar(HttpSession session,String articleId) {


		// ��session����ȡuser

		UserVo user = (UserVo) session.getAttribute("userVo");

		shop.deleteShopCar(user.getId(), Integer.valueOf(articleId));

		return "forward:/shopCar/shopcar";//����ת����ת���� ��˴�����

	}



}
