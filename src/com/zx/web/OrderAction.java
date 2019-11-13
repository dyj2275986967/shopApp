package com.zx.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import com.zx.service.OrderService;
import com.zx.service.impl.OrderServiceImpl;
import com.zx.vo.OrderVo;
import com.zx.vo.UserVo;
@Controller
//�����ռ�
@RequestMapping("/order")
public class OrderAction{

    
	@Resource(name="OrderService")
    private 	OrderService order;
    
    @RequestMapping("/aorder.do")
	public String order(HttpServletRequest request,String orderMsg) {

		// ��ȡ����������Ʒ��Ϣ

		// ����� ��Ϣ���������¶���
		if (orderMsg != null && !"".equals(orderMsg)) {
			// ������������Ϣ���������
			OrderVo vo=	order.insertOrderMsg(request);

			//��ȡ�û���Ϣ
			UserVo user = (UserVo) request.getSession().getAttribute("userVo");
			 
			//���ý��
         Double   WIDtotal_amount=vo.getAmount();
         //����
         request.setAttribute("WIDtotal_amount", WIDtotal_amount);
	          //���ö�����
        String  WIDout_trade_no= vo.getOrderCode();
        //�涩����
        request.setAttribute("WIDout_trade_no", WIDout_trade_no);
		 //���ö�������
		String	WIDsubject= vo.getOrderCode();
		   //�涩������
        request.setAttribute("WIDsubject", WIDsubject);
		 
			/**
			 * ����֧����֧������
			 */
        String msg=(String)request.getAttribute("alipay");
		 if ("alipay".equals(msg)) {
			 //����֧����ҳ��

			  return "indexalipay";
		}
     
		
			
		//return "forward://indexalipay.jsp";
			// û����ֱ��չʾ
		} else {

			// չʾ��Ʒ��Ϣ
			order.selectOrderMsg(request);

		}

	return "order";

		
	}
    
    
    @RequestMapping("/alipay")
    public String aliPay(HttpServletRequest request) {
    	
      request.setAttribute("alipay", "alipay");
    	
    	
    	
    	//����ת����ת���� ��˴�����
    	return "forward:/order/aorder.do";
    }
    
    
	
	
	
	
	
	
	
	
	
	

}
