package com.zx.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.mapper.ArticleMapper;
import com.zx.mapper.ShopcarMapper;
import com.zx.service.ShopCarService;
import com.zx.vo.ArticleVo;
import com.zx.vo.ShopCarVo;
import com.zx.vo.UserVo;
@Transactional
@Service(value="ShopCarService")
public class ShopCarServiceImpl implements ShopCarService {

	@Autowired
   private ShopcarMapper  shopcarMapper;
   @Autowired
   private ArticleMapper articleMapper;

   
	//�������Ʒ����������������
	public void insertInToShopCarByDetial(int buynum, int userId, int articleId) {
             
				ShopCarVo shopCar=shopcarMapper.findShopCarMsg(userId,articleId);
	     
		
					 
					 
		/*(�ж��û��Ƿ��ǵ�һ�ι������Ʒ���������)�����ƷID���û�id��Ϣ����  
		   * �����û�������Ϣ:���û��¼ӵ���Ʒ���� ���ϱ��е���Ʒ����һ����µ����ﳵ����
		   */
		 if( shopCar!=null) {

			         int num=buynum+shopCar.getBuynum();
			 
			         shopcarMapper.updateShopCar(num,userId,articleId);
			  	 //��������ִ�в���(�ж��û��Ƿ��ǵ�һ�ι������Ʒ����)
			}else {
				shopcarMapper.insertShopCar(buynum, userId, articleId);
				}
		 
	}

	
	//����������Ʒ(+)(-)������������
	public void insertInToShopCarByAddShopCar(int buynum, int userId, int articleId) {
		
		 ShopCarVo shopCar=shopcarMapper.findShopCarMsg(userId,articleId);
		  //�����ƷID���û�id��Ϣ����  �����û�������Ϣ:���û��¼ӵ���Ʒ���� ���ϱ��е���Ʒ����һ����µ����ﳵ����
		 if( shopCar!=null) {

			
			   shopcarMapper.updateShopCar(buynum,userId,articleId);
				
		 //��������ִ�в���		
			}else {
			
				shopcarMapper.insertShopCar(buynum, userId, articleId);
			
		}
		
		
		
		
		
		
	}

	
	
	
	@Override
	public void showShopCar(HttpServletRequest request) {
		UserVo userVo=(UserVo)request.getSession().getAttribute("userVo");
		                                                            
		    
                List<ShopCarVo>  shoplist=  shopcarMapper.findArticleMsgByUserIdAndArticleId(userVo.getId());
                //�����ܽ��
                double allPrice=0.0;
                for (ShopCarVo shopCarVo : shoplist) {
                	allPrice+=	shopCarVo.getBuynum()*shopCarVo.getArticle().getPrice()*	shopCarVo.getArticle().getDiscount();
                	
              		}
                //�������ܽ�� �����request����
                
                request.setAttribute("allPrice",allPrice);
                
                
                
                
                
                
                
                
                
      		
                 request.getSession().setAttribute("shopVo", shoplist);                 
		
	}

	@Override
	public boolean deleteShopCar(int userId, int articleId) {

 	   
 		shopcarMapper.deleteShopCarByuserIdAndArticleId(userId,articleId);		                           
	
   	  return true;
	}

}
