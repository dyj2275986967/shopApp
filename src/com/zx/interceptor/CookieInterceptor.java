package com.zx.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zx.common.CookieUtils;
import com.zx.service.UserService;
import com.zx.service.impl.UserServiceImpl;

import com.zx.vo.UserVo;

/*
 * cookieÀ¹½ØÆ÷
 */
public class CookieInterceptor extends HandlerInterceptorAdapter {

	@Resource(name = "UserService")
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();
		UserVo user = (UserVo) session.getAttribute("userVo");

		UserVo u = new UserVo();
		if (user == null) {

			Cookie cookie = CookieUtils.getCookies(request, "rem");
			String cookieMsg = "";
			String infos[] = new String[2];
			if (cookie != null) {
				cookieMsg = cookie.getValue();
				infos = cookieMsg.split("_");
				u.setLoginName(infos[0]);
				u.setPassword(infos[1]);
				userService.cookieRem(u, request);

			}
		}



		return true;
	}

}