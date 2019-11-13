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
	
	
			// ����˺Ų����ھ�ִ�и���
    
   //������ʽ
   Pattern loginAndPwdP = Pattern.compile("^([a-zA-Z0-9]|[-]){5,12}$");  
   Pattern phoneP = Pattern.compile("^1[3456789]\\d{9}$");  
   Pattern emailP = Pattern.compile("^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");     
   //������ʽ
   
			// ����˺�Ϊ�� ����˺Ŵ��� ����˺Ÿ�ʽ����ȷ 
			if ("".equals(vo.getLoginName())||!loginAndPwdP.matcher(vo.getLoginName()).matches()||userMapper.userNotExist(vo.getLoginName()) != null) {
			         return "�˺�";

			 // �������Ϊ�� ��������ʽ����ȷ   ���2�����벻һ��
			} else if("".equals(vo.getPassword())||!loginAndPwdP.matcher(vo.getPassword()).matches()||!vo.getPassword().equals(vo.getOkpassword())){
                  return "����";
              
                  // ����ֻ���Ϊ�� ����ֻ��Ÿ�ʽ����ȷ   
          }else if("".equals(vo.getPhone())||!phoneP.matcher(vo.getPhone()).matches()) {
        	  
        	 return "�ֻ�";
        
        	     // �������Ϊ�� �������Ÿ�ʽ����ȷ   
          }else if("".equals(vo.getEmail())||!emailP.matcher(vo.getEmail()).matches() ) {
        	  return "����";
    
        	     // �������Ϊ��	  
          }else if("".equals(vo.getYzm())||!sessionYzm.equals(vo.getYzm())){
        	  
        	return "yzm";
     
        	  
          }else if("".equals(vo.getName())) {
        	 return "����";

           }else if("".equals(vo.getAddress())) {
        	   return "��ַ"; 	   

           }else {
        	   userMapper.userRegister(vo);
        	   return "success";
        	  
           }
	}

	public void sendEmain(String uuid, String email) {

		// TODO Auto-generated method stub

		try {
			// 1������Properties���󣬷�װ�ʼ��������������Ϣ
			Properties props = new Properties();
			// ��������ַ
			props.setProperty("mail.smtp.host", "smtp.126.com");
			// ��������Ҫ��Ȩ
			props.setProperty("mail.smtp.auth", "true");

			// 2������Authenticator��ʵ����ʵ���˻�������ļ�Ȩ��
			Authenticator auth = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					// ʹ���˺� �Լ� ��Ȩ���¼���ʼ����������˴����� 126�ʼ������� ��Ȩ��
					return new PasswordAuthentication("internetCompanyd@126.com", "dyj19971104");
				}
			};

			// 3�����Sessionʵ��
			Session mailSession = Session.getInstance(props, auth);

			// 4������SMTPMessage��Ҫ�ṩsession
			SMTPMessage msg = new SMTPMessage(mailSession);
			InetAddress host = InetAddress.getByName("alublabla");

			String ip = host.getHostAddress();

			// 5�������ʼ����⣬û�б�����ʼ��������ᱻ��Ϊ�������ʼ���ϵͳ�˻ء�
			msg.setSubject("�û�ע�ἤ���ʼ�������ظ�������ָ������");
			// 6�������ʼ�����
			msg.setContent("<a href='http://" + ip + ":8080/shopApp_customer_ssm/user/activeUser?active=" + uuid
					+ "'target='_blank'>��ϲ����ע��ɹ������ʼ�����ظ���������ɼ���</a>", "text/html;charset=UTF-8");

			// ���ý�����
			// 7�������������ɣ�TO(�ռ���)��CC(����)��BCC(����)
			msg.setRecipient(RecipientType.TO, new InternetAddress(email));
			// 8�����÷�����
			msg.setFrom(new InternetAddress("internetCompanyd@126.com"));
			// �����ʼ�
			Transport.send(msg);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	@Override
	public String marchUserMsg(UserVo vo, HttpServletRequest request, HttpServletResponse response) {
		// ����м�����͸��� �û�ֵ �� disabled����Ϊ1 ���ɵ�¼״̬
     
		

		UserVo daoU = userMapper.marchUserMsg(vo);

		response.setCharacterEncoding("utf-8");
		String sessionYzm = (String) request.getSession().getAttribute("yzm");
	

		try {
			// ���û���û���Ϣ��֤���˺Ų�����
			if (daoU == null) {

				// 1���˺Ų�����
				return "1";

			} else if (!daoU.getPassword().equals(vo.getPassword())) {

				// 2�����벻����
				return "2";
				// ������1 ��ʾΪ����
			} else if (!daoU.getDisable().equals("1")) {

				// 3��ʾδ����
				return "3";

			} else if (!sessionYzm.equals(vo.getYzm())) {

				// 4��ʾδ����
				return "4";

			} else {
				// ���û���Ϣ
				request.getSession().setAttribute("userVo", daoU);
               return "�ɹ�";
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;      
	}

	/*
	 *У���˺��Ƿ����
	 * @see com.zx.service.UserService#marchUserLoginName(com.zx.vo.UserVo, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String marchUserLoginName(UserVo vo, HttpServletRequest request, HttpServletResponse response) {
		
			// ����˺Ŵ�������Ӧ��1
			
			if (userMapper.userNotExist(vo.getLoginName()) != null) {

				// ���˺��Ѵ���
				return "1";
			

			}else {
			
				return "����ע��";
			}

	}
	
/*
 *У���û�������֤�� 
 */
	@Override
	public String marchUserYzm(UserVo vo,HttpServletRequest request, HttpServletResponse response) {
	
	String yzm=	(String)	request.getSession().getAttribute("yzm");
			// ����˺Ŵ�������Ӧ��1
			
			if (yzm.equals(vo.getYzm())) {

				// ����֤����ȷ
			return "1";
			
			}else {
		
			return "��֤�벻��ȷ";
			}

		}
		
	


	@Override
	public void activeUser(UserVo vo, HttpServletRequest request) {

		
		 
		if (vo.getActive() != null && !"".equals(vo.getActive())) {

			userMapper.updateUserAction(vo);
			request.getSession().setAttribute("msg","��ϲ��������ɹ�");
		}else {
			request.getSession().setAttribute("msg","��ϲ��������ʧ��");
		}
		
	}

	@Override
	public void cookieRem(UserVo user,HttpServletRequest request) {
		
	UserVo userV=	userMapper.marchUserMsg(user);

	request.getSession().setAttribute("userVo", userV);
		 
		
	}

	//����cookie�����Ϣ��ѯUserVo�����Ϣ
	public UserVo findUserMsgByCookie(UserVo  user) {
		
		return	userMapper.marchUserMsg(user);
		
		
		

	}
	
	
	

}
