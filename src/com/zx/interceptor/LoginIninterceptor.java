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
			//����
			return true;
		}
		
		//�ض��򵽵�¼ҳ��
		request.setAttribute("msg", "*���ȵ�¼");
	 request.getRequestDispatcher("/user/login").forward(request, response);

		return false;
	
	}

}
