package com.zx.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.mail.smtp.SMTPMessage;



import com.zx.mapper.UserMapper;
import com.zx.service.UserService;
import com.zx.vo.UserVo;
@Transactional
@Service(value="UserService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;


	public String userRegister(UserVo vo, HttpServletRequest request, HttpServletResponse response) {


		String sessionYzm = (String) request.getSession().getAttribute("yzm");
	
	
			// 如果账号不存在就执行更新
    
   //正则表达式
   Pattern loginAndPwdP = Pattern.compile("^([a-zA-Z0-9]|[-]){5,12}$");  
   Pattern phoneP = Pattern.compile("^1[3456789]\\d{9}$");  
   Pattern emailP = Pattern.compile("^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");     
   //正则表达式
   
			// 如果账号为空 如果账号存在 如果账号格式不正确 
			if ("".equals(vo.getLoginName())||!loginAndPwdP.matcher(vo.getLoginName()).matches()||userMapper.userNotExist(vo.getLoginName()) != null) {
			         return "账号";

			 // 如果密码为空 如果密码格式不正确   如果2次密码不一致
			} else if("".equals(vo.getPassword())||!loginAndPwdP.matcher(vo.getPassword()).matches()||!vo.getPassword().equals(vo.getOkpassword())){
                  return "密码";
              
                  // 如果手机号为空 如果手机号格式不正确   
          }else if("".equals(vo.getPhone())||!phoneP.matcher(vo.getPhone()).matches()) {
        	  
        	 return "手机";
        
        	     // 如果邮箱为空 如果邮箱号格式不正确   
          }else if("".equals(vo.getEmail())||!emailP.matcher(vo.getEmail()).matches() ) {
        	  return "邮箱";
    
        	     // 如果名字为空	  
          }else if("".equals(vo.getYzm())||!sessionYzm.equals(vo.getYzm())){
        	  
        	return "yzm";
     
        	  
          }else if("".equals(vo.getName())) {
        	 return "名字";

           }else if("".equals(vo.getAddress())) {
        	   return "地址"; 	   

           }else {
        	   userMapper.userRegister(vo);
        	   return "success";
        	  
           }
	}

	public void sendEmain(String uuid, String email) {

		// TODO Auto-generated method stub

		try {
			// 1、创建Properties对象，封装邮件服务器的相关信息
			Properties props = new Properties();
			// 服务器地址
			props.setProperty("mail.smtp.host", "smtp.126.com");
			// 服务器需要鉴权
			props.setProperty("mail.smtp.auth", "true");

			// 2、创建Authenticator的实例，实现账户、密码的鉴权。
			Authenticator auth = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					// 使用账号 以及 授权码登录至邮件服务器，此处采用 126邮件服务器 授权码
					return new PasswordAuthentication("internetCompanyd@126.com", "dyj19971104");
				}
			};

			// 3、获得Session实例
			Session mailSession = Session.getInstance(props, auth);

			// 4、创建SMTPMessage，要提供session
			SMTPMessage msg = new SMTPMessage(mailSession);
			InetAddress host = InetAddress.getByName("alublabla");

			String ip = host.getHostAddress();

			// 5、设置邮件标题，没有标题的邮件几乎都会被认为是垃圾邮件被系统退回。
			msg.setSubject("用户注册激活邮件，请勿回复，按照指引激活");
			// 6、设置邮件内容
			msg.setContent("<a href='http://" + ip + ":8080/shopApp_customer_ssm/user/activeUser?active=" + uuid
					+ "'target='_blank'>恭喜您，注册成功，此邮件无需回复，点击即可激活</a>", "text/html;charset=UTF-8");

			// 设置接收者
			// 7、接收者类型由：TO(收件人)、CC(抄送)、BCC(密送)
			msg.setRecipient(RecipientType.TO, new InternetAddress(email));
			// 8、设置发送人
			msg.setFrom(new InternetAddress("internetCompanyd@126.com"));
			// 发送邮件
			Transport.send(msg);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	@Override
	public String marchUserMsg(UserVo vo, HttpServletRequest request, HttpServletResponse response) {
		// 如果有激活码就更新 用户值 将 disabled设置为1 即可登录状态
     
		

		UserVo daoU = userMapper.marchUserMsg(vo);

		response.setCharacterEncoding("utf-8");
		String sessionYzm = (String) request.getSession().getAttribute("yzm");
	

		try {
			// 如果没有用户信息则证明账号不存在
			if (daoU == null) {

				// 1表账号不存在
				return "1";

			} else if (!daoU.getPassword().equals(vo.getPassword())) {

				// 2表密码不存在
				return "2";
				// 不等于1 显示为激活
			} else if (!daoU.getDisable().equals("1")) {

				// 3表示未激活
				return "3";

			} else if (!sessionYzm.equals(vo.getYzm())) {

				// 4表示未激活
				return "4";

			} else {
				// 存用户信息
				request.getSession().setAttribute("userVo", daoU);
               return "成功";
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;      
	}

	/*
	 *校验账号是否存在
	 * @see com.zx.service.UserService#marchUserLoginName(com.zx.vo.UserVo, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String marchUserLoginName(UserVo vo, HttpServletRequest request, HttpServletResponse response) {
		
			// 如果账号存在则响应出1
			
			if (userMapper.userNotExist(vo.getLoginName()) != null) {

				// 表账号已存在
				return "1";
			

			}else {
			
				return "可以注册";
			}

	}
	
/*
 *校验用户输入验证码 
 */
	@Override
	public String marchUserYzm(UserVo vo,HttpServletRequest request, HttpServletResponse response) {
	
	String yzm=	(String)	request.getSession().getAttribute("yzm");
			// 如果账号存在则响应出1
			
			if (yzm.equals(vo.getYzm())) {

				// 表验证码正确
			return "1";
			
			}else {
		
			return "验证码不正确";
			}

		}
		
	


	@Override
	public void activeUser(UserVo vo, HttpServletRequest request) {

		
		 
		if (vo.getActive() != null && !"".equals(vo.getActive())) {

			userMapper.updateUserAction(vo);
			request.getSession().setAttribute("msg","恭喜您，激活成功");
		}else {
			request.getSession().setAttribute("msg","恭喜您，激活失败");
		}
		
	}

	@Override
	public void cookieRem(UserVo user,HttpServletRequest request) {
		
	UserVo userV=	userMapper.marchUserMsg(user);

	request.getSession().setAttribute("userVo", userV);
		 
		
	}

	//根据cookie里的信息查询UserVo里的信息
	public UserVo findUserMsgByCookie(UserVo  user) {
		
		return	userMapper.marchUserMsg(user);
		
		
		

	}
	
	
	

}
