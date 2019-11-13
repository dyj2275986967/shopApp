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
	 * 直接进入登录页面
	 */
	@RequestMapping("/login")
	public String showLogin() {
   
		 
		return "login";

	}

	/*
	 * 处理登陆 请求，进行登陆
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
	 * 验证码
	 * 
	 * @return
	 */
@ResponseBody
@RequestMapping("/yzm")
	public void yzm(HttpServletRequest request,HttpServletResponse response) {
     	// 1、生成随机数
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 4; i++) {
			sb.append(random.nextInt(10));
		}

		request.getSession().setAttribute("yzm", sb.toString());

		// 2.创建画布
		BufferedImage image = new BufferedImage(70, 25, BufferedImage.TYPE_INT_RGB);

		// 3.创建画笔
		Graphics2D g2d = image.createGraphics();

		// 4.先填充一个白色矩形
		// 设置画笔颜色
		g2d.setColor(Color.WHITE);
		// 填充矩形
		g2d.fillRect(0, 0, 70, 25);

		// 5.设置线宽为两个像素
		g2d.setStroke(new BasicStroke(2f));
		// 6.在图片上面画一些干扰的元素
		for (int i = 0; i < 200; i++) {
			// 随机颜色
			int r = random.nextInt(255);
			int g = random.nextInt(255);
			int b = random.nextInt(255);
			Color c = new Color(r, g, b);
			g2d.setColor(c);

			// 随机位置
			int x = random.nextInt(70);
			int y = random.nextInt(25);
			g2d.drawLine(x, y, x, y);
		}

		// 7.设置画笔颜色及字体
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("宋体", Font.BOLD, 24));
		// 8.j将字写在画布上
		g2d.drawString(sb.toString(), 10, 22);

		// 8.设置响应头为image/png，得到一个OutputStream
		response.setContentType("image/png");
		OutputStream out;
		try {
			out = response.getOutputStream();
			ImageIO.write(image, "png", out);

			// 为了保证图片被下载到浏览器，必须flash一下
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
      	}
	}

/**
 * 退出
 */
@RequestMapping(value="/loginOut",produces= {"application/text;charset=utf-8"})
public String loginOut(HttpSession session,HttpServletRequest  request, HttpServletResponse response) {

		session.removeAttribute("userVo");

		Cookie cookie	=CookieUtils.getCookies(request, "rem");
        if(cookie!=null) {
        	cookie.setMaxAge(0);
        	//指定该 cookie的作用域
    		cookie.setPath(request.getContextPath());
    		
    		//通过response 将cookie响应至浏览器
    		 response.addCookie(cookie);
        	
        }
	  //退出重定向返回到首页
		return "redirect:/article/index";
	}
/**
 * 
 * 异步注册
 */

	@ResponseBody
	@RequestMapping(value="/register",produces= {"application/text;charset=utf-8"})
	public String register(	HttpServletRequest request ,UserVo user,	HttpServletResponse response ) {
           
		System.out.println(user+"==============user==============================");

		//生成uuid
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		String active = uuid;

		UserVo vo = new UserVo(user.getLoginName(), user.getPassword(), user.getName(), Integer.valueOf(user.getSex()),
				user.getEmail(), user.getPhone(), user.getAddress(), user.getCreateDate(), 1, "0", active);
         vo.setOkpassword(user.getOkpassword());
		vo.setYzm(user.getYzm());
		String remark=userService.userRegister(vo, request, response);
       System.out.println(remark+"===userRegister==================================");
		//如果返回值类型是success 就说明 注册无异常 可以发邮件
		if ("success".equals(remark)) {

			userService.sendEmain(uuid, user.getEmail());

			return remark;

		}

		return remark;

	}
	/*
	 * ajax校验用户账号是否相同
	 */
	@ResponseBody
	@RequestMapping(value="/ajaxRegister",produces= {"application/text;charset=utf-8"})
	public String ajaxRegister(HttpServletRequest request ,HttpServletResponse response,UserVo user) {
	
	
	
		
		return 	userService.marchUserLoginName(user, request, response);


	}
	/*
	 * ajax异步校验验证码是否相同
	 */
	@ResponseBody
	@RequestMapping(value="/ajaxYzm",produces= {"application/text;charset=utf-8"})
	public String ajaxYzm(	HttpServletRequest request,HttpServletResponse response,UserVo user) {
		
		return  	userService.marchUserYzm(user, request, response);

	
		
	}
	
	
	

	/*
	 * 展示登录页面
	 */
	@RequestMapping("/showRegister")
	public String showRigister() {

		return "register";
	}
	/*
	 * 激活账号
	 */
	@RequestMapping("/activeUser")
	public String activeUser(HttpServletRequest request,UserVo user) {
		userService.activeUser(user, request);

		return "redirect:/user/login";
		
		
	}
	


	



}
