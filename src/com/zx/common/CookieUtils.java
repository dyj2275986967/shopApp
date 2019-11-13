package com.zx.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
	
	
	
	//添加cookie方法
	
	public static void  addCookies(HttpServletRequest request,HttpServletResponse response,String cookieName,String cookieValue,int time) {
		//将信息存到Cookie里面去
		Cookie cookie=new Cookie(cookieName,cookieValue);
	
		
		//指定cookie 的存活时间 单位 秒   
		cookie.setMaxAge(time);
		//指定该 cookie的作用域
		cookie.setPath(request.getContextPath());
		//通过response 将cookie响应至浏览器
		response.addCookie(cookie);		
	}
	
	//获得cookie的方法
	public static Cookie getCookies(HttpServletRequest request,String CookieKey) {
		
		Cookie []	cookie=request.getCookies();
		if (cookie!=null) {
			
			for (Cookie cookies : cookie) {
				if (cookies.getName().equals(CookieKey)) {
					return cookies;
				}
				
			}
			
		}
		
		
		
		return null;
		
		
	}
	
	
	
	

}
