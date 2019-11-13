package com.zx.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zx.vo.UserVo;

public interface UserService {
	
	
	//校验用户输入信息是否注册成功(插入数据库成功)
	
	public  String userRegister(UserVo vo,HttpServletRequest request,HttpServletResponse response); 
	//异步校验是否存在相同账号
	public  String marchUserLoginName(UserVo vo,HttpServletRequest request,HttpServletResponse response); 
	//异步校验验证码
	public  String marchUserYzm(UserVo vo,HttpServletRequest request,HttpServletResponse response); 
	//发邮件
	public  void  sendEmain(String uuid,String email);
	
	//用户登录信息校验
	public String marchUserMsg(UserVo  vo,HttpServletRequest request,HttpServletResponse response);
	//用户激活账号
	public void activeUser(UserVo vo,HttpServletRequest request);
	//拦截器COOKIE 存SESSION  
	public void cookieRem(UserVo user,HttpServletRequest request);
	
	//根据cookie里的信息查询UserVo里的信息
	
	public UserVo findUserMsgByCookie(UserVo user);
	
	
	
	

}
