package com.zx.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zx.vo.UserVo;

public class LoginIninterceptor extends HandlerInterceptorAdapter {
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		
    
		UserVo user=(UserVo)request.getSession().getAttribute("userVo");
		if (user!=null) {
			//放行
			return true;
		}
		
		//重定向到登录页面
		request.setAttribute("msg", "*请先登录");
	 request.getRequestDispatcher("/user/login").forward(request, response);

		return false;
	
	}

}
