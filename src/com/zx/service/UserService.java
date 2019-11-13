package com.zx.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zx.vo.UserVo;

public interface UserService {
	
	
	//У���û�������Ϣ�Ƿ�ע��ɹ�(�������ݿ�ɹ�)
	
	public  String userRegister(UserVo vo,HttpServletRequest request,HttpServletResponse response); 
	//�첽У���Ƿ������ͬ�˺�
	public  String marchUserLoginName(UserVo vo,HttpServletRequest request,HttpServletResponse response); 
	//�첽У����֤��
	public  String marchUserYzm(UserVo vo,HttpServletRequest request,HttpServletResponse response); 
	//���ʼ�
	public  void  sendEmain(String uuid,String email);
	
	//�û���¼��ϢУ��
	public String marchUserMsg(UserVo  vo,HttpServletRequest request,HttpServletResponse response);
	//�û������˺�
	public void activeUser(UserVo vo,HttpServletRequest request);
	//������COOKIE ��SESSION  
	public void cookieRem(UserVo user,HttpServletRequest request);
	
	//����cookie�����Ϣ��ѯUserVo�����Ϣ
	
	public UserVo findUserMsgByCookie(UserVo user);
	
	
	
	

}
