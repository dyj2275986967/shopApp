package com.zx.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
	
	
	
	//���cookie����
	
	public static void  addCookies(HttpServletRequest request,HttpServletResponse response,String cookieName,String cookieValue,int time) {
		//����Ϣ�浽Cookie����ȥ
		Cookie cookie=new Cookie(cookieName,cookieValue);
	
		
		//ָ��cookie �Ĵ��ʱ�� ��λ ��   
		cookie.setMaxAge(time);
		//ָ���� cookie��������
		cookie.setPath(request.getContextPath());
		//ͨ��response ��cookie��Ӧ�������
		response.addCookie(cookie);		
	}
	
	//���cookie�ķ���
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
