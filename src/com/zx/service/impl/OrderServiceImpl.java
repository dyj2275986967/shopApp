package com.zx.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.zx.mapper.ArticleMapper;
import com.zx.mapper.OrderItemMapper;
import com.zx.mapper.OrderMapper;
import com.zx.mapper.ShopcarMapper;
import com.zx.service.OrderService;
import com.zx.vo.ArticleVo;
import com.zx.vo.OrderItemVo;
import com.zx.vo.OrderVo;
import com.zx.vo.ShopCarVo;
import com.zx.vo.UserVo;
@Transactional(rollbackFor=java.lang.Exception.class)
@Service(value="OrderService")
public class OrderServiceImpl implements OrderService {

@Autowired
private OrderMapper orderMapper;
@Autowired
private ShopcarMapper shopcarMapper;
@Autowired
private ArticleMapper articleMapper;
@Autowired
private OrderItemMapper orderItemMapper;
	@Override
	public OrderVo insertOrderMsg(HttpServletRequest request) {
		//������
		OrderVo orderVo = new OrderVo();
		//������ϸ��
		OrderItemVo itemVo=new OrderItemVo();
		// ��ȡ�µ���id
		UserVo user = (UserVo) request.getSession().getAttribute("userVo");
       //���ɶ���״̬  ����µ�Ĭ��Ϊ1  1Ϊ δ����״̬
		String status="1";
		orderVo.setStatus(status);
		// �����µ�ʱ��
		Date date = new Date();
		orderVo.setCreateDate(date);
		// ���ɶ������
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String str = sdf.format(date);
		String orderCode = "PO-" + str + "-" + user.getId();
		orderVo.setOrderCode(orderCode);
		orderVo.setUserId(user.getId());

		// ��ȡ����������Ʒ��Ϣ
		String msg = request.getParameter("orderMsg");
    	String[] article = msg.split("_");
	
		
	//׼��list  ������Ʒ��Ϣ
		List<ShopCarVo>  shopCarList=new ArrayList();

	  	double amount = 0;
		String a[][]=new String[article.length/3][3];
		 int j = 0;
	         for (int i = 0; i < a.length; i++) {
				for (int k = 0; k < a[i].length; k++) {
                  
	                     a[i][k]=article[j];
			       j++;
				}
			}
		ArticleVo ar=null;
	      for (int i = 0; i < a.length; i++) {
	    	  ShopCarVo  shopCar =new ShopCarVo();
					for (int k = 0; k < a[i].length; k++) {
						
						
				       //���k=0 ��˵��������������ƷID
						if (k==0) {
							shopCar.setArticleId(Integer.valueOf(a[i][k]));
							// ������ƷID��ѯ��Ʒ��Ϣ			
							shopCar.setArticle( articleMapper.findArticleMsgById(a[i][k]));
	
					  //���k=1 ��˵�������������ܽ��
						}else if(k==1) {
							amount+=Double.valueOf(a[i][k]);
							
					 //���k=2	��˵��������������Ʒ����	
						}else if(k==2) {
				shopCar.setBuynum(Integer.valueOf(a[i][k]));
						}
						}
					shopCarList.add(shopCar);
				}
	//���ܽ��	
		orderVo.setAmount(amount);
		try {
			/*********************
			 * �����ݵ�������ϸ��
			 *******************/
			itemVo.setShopCarList(shopCarList);
		
		   //���붩����  ���ض��� orderId
	       orderMapper.insertOrderMsg(orderVo);
	       
	      //    int i=5/0;   
	       
	    	itemVo.setOrderId(orderVo.getId());	
		
			/*********************
			 * �����ݵ�������ϸ��
			 *******************/
		
			//���붩����ϸ��
			 for (ShopCarVo shopCarVo : itemVo.getShopCarList()) {
				 	 //��ѯ�����Ϣ
			ArticleVo articleVo=	articleMapper.findArticleMsgById(String.valueOf(shopCarVo.getArticleId()));
				 //���¿����Ϣ
				 shopcarMapper.updateShopStorage(shopCarVo.getArticleId(), articleVo.getStorage()-shopCarVo.getBuynum());
				 
				 orderMapper.insertOrderItemMsg(itemVo.getOrderId(),shopCarVo.getArticleId(),shopCarVo.getBuynum());
				}
			//�����ɾ�� ���ﳵ���¶�������Ʒ��Ϣ
			for (ShopCarVo shopCarVo : itemVo.getShopCarList()) {
				shopcarMapper.deleteOrderShopCarByArticleId(user.getId(), shopCarVo.getArticleId());
			}
		} catch (Exception e) {
			throw new RuntimeException("����ʧ��");
		}
	
		//���ض�������
		return orderVo;
	}

	public void selectOrderMsg(HttpServletRequest request) {
        // ��ȡ�û���Ϣ    
		UserVo user = (UserVo) request.getSession().getAttribute("userVo");
		//�鶩����Ϣ               
		
		                                            
	    List<OrderVo>	 orderVoList =  orderMapper.selectOrderMsg(user.getId());
	  
	 
	  
	    //����������Ϣ �����û��м�������   ͨ������id��ѯ   ������ϸ���е���Ϣ  
	    for (OrderVo order : orderVoList) {
        
	    	order.setOrderItemList(	 orderItemMapper.selectOrderItemMsg(order.getId())); 
	   
	    		}

	    //�涩����Ϣ
	    request.setAttribute("orderVoList", orderVoList);
		
	}

           
}
