package com.zx.web;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.common.CookieUtils;
import com.zx.service.UserService;
import com.zx.service.impl.UserServiceImpl;


import com.zx.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserAction {

	
	@Resource(name="UserService")
	private UserService userService;

	/*
	 * ֱ�ӽ����¼ҳ��
	 */
	@RequestMapping("/login")
	public String showLogin() {
   
		 
		return "login";

	}

	/*
	 * �����½ ���󣬽��е�½
	 */
	@ResponseBody
	@RequestMapping(value="/loginIn",produces= {"application/text;charset=utf-8"})
	public String login(String remember, UserVo user,HttpServletRequest request,HttpServletResponse response) {

		UserVo vo = new UserVo(user.getLoginName(), user.getPassword(), user.getActive());

		vo.setYzm(user.getYzm());
		

	

	   if("1".equals(remember)) {
		  
		     CookieUtils.addCookies(request, response, "rem",user.getLoginName()+"_"+user.getPassword(), 24*60*60); 
		   
	   }     	
	   return 	userService.marchUserMsg(vo, request, response);
		
	}

	/**
	 * ��֤��
	 * 
	 * @return
	 */
@ResponseBody
@RequestMapping("/yzm")
	public void yzm(HttpServletRequest request,HttpServletResponse response) {
     	// 1�����������
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 4; i++) {
			sb.append(random.nextInt(10));
		}

		request.getSession().setAttribute("yzm", sb.toString());

		// 2.��������
		BufferedImage image = new BufferedImage(70, 25, BufferedImage.TYPE_INT_RGB);

		// 3.��������
		Graphics2D g2d = image.createGraphics();

		// 4.�����һ����ɫ����
		// ���û�����ɫ
		g2d.setColor(Color.WHITE);
		// ������
		g2d.fillRect(0, 0, 70, 25);

		// 5.�����߿�Ϊ��������
		g2d.setStroke(new BasicStroke(2f));
		// 6.��ͼƬ���滭һЩ���ŵ�Ԫ��
		for (int i = 0; i < 200; i++) {
			// �����ɫ
			int r = random.nextInt(255);
			int g = random.nextInt(255);
			int b = random.nextInt(255);
			Color c = new Color(r, g, b);
			g2d.setColor(c);

			// ���λ��
			int x = random.nextInt(70);
			int y = random.nextInt(25);
			g2d.drawLine(x, y, x, y);
		}

		// 7.���û�����ɫ������
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("����", Font.BOLD, 24));
		// 8.j����д�ڻ�����
		g2d.drawString(sb.toString(), 10, 22);

		// 8.������ӦͷΪimage/png���õ�һ��OutputStream
		response.setContentType("image/png");
		OutputStream out;
		try {
			out = response.getOutputStream();
			ImageIO.write(image, "png", out);

			// Ϊ�˱�֤ͼƬ�����ص������������flashһ��
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
      	}
	}

/**
 * �˳�
 */
@RequestMapping(value="/loginOut",produces= {"application/text;charset=utf-8"})
public String loginOut(HttpSession session,HttpServletRequest  request, HttpServletResponse response) {

		session.removeAttribute("userVo");

		Cookie cookie	=CookieUtils.getCookies(request, "rem");
        if(cookie!=null) {
        	cookie.setMaxAge(0);
        	//ָ���� cookie��������
    		cookie.setPath(request.getContextPath());
    		
    		//ͨ��response ��cookie��Ӧ�������
    		 response.addCookie(cookie);
        	
        }
	  //�˳��ض��򷵻ص���ҳ
		return "redirect:/article/index";
	}
/**
 * 
 * �첽ע��
 */

	@ResponseBody
	@RequestMapping(value="/register",produces= {"application/text;charset=utf-8"})
	public String register(	HttpServletRequest request ,UserVo user,	HttpServletResponse response ) {
           
		System.out.println(user+"==============user==============================");

		//����uuid
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		String active = uuid;

		UserVo vo = new UserVo(user.getLoginName(), user.getPassword(), user.getName(), Integer.valueOf(user.getSex()),
				user.getEmail(), user.getPhone(), user.getAddress(), user.getCreateDate(), 1, "0", active);
         vo.setOkpassword(user.getOkpassword());
		vo.setYzm(user.getYzm());
		String remark=userService.userRegister(vo, request, response);
       System.out.println(remark+"===userRegister==================================");
		//�������ֵ������success ��˵�� ע�����쳣 ���Է��ʼ�
		if ("success".equals(remark)) {

			userService.sendEmain(uuid, user.getEmail());

			return remark;

		}

		return remark;

	}
	/*
	 * ajaxУ���û��˺��Ƿ���ͬ
	 */
	@ResponseBody
	@RequestMapping(value="/ajaxRegister",produces= {"application/text;charset=utf-8"})
	public String ajaxRegister(HttpServletRequest request ,HttpServletResponse response,UserVo user) {
	
	
	
		
		return 	userService.marchUserLoginName(user, request, response);


	}
	/*
	 * ajax�첽У����֤���Ƿ���ͬ
	 */
	@ResponseBody
	@RequestMapping(value="/ajaxYzm",produces= {"application/text;charset=utf-8"})
	public String ajaxYzm(	HttpServletRequest request,HttpServletResponse response,UserVo user) {
		
		return  	userService.marchUserYzm(user, request, response);

	
		
	}
	
	
	

	/*
	 * չʾ��¼ҳ��
	 */
	@RequestMapping("/showRegister")
	public String showRigister() {

		return "register";
	}
	/*
	 * �����˺�
	 */
	@RequestMapping("/activeUser")
	public String activeUser(HttpServletRequest request,UserVo user) {
		userService.activeUser(user, request);

		return "redirect:/user/login";
		
		
	}
	


	



}
