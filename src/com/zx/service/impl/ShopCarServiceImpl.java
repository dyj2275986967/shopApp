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

   
	//处理从商品详情表带过来的请求
	public void insertInToShopCarByDetial(int buynum, int userId, int articleId) {
             
				ShopCarVo shopCar=shopcarMapper.findShopCarMsg(userId,articleId);
	     
		
					 
					 
		/*(判断用户是否是第一次购买该商品，如果不是)如果商品ID和用户id信息存在  
		   * 更新用户购买信息:将用户新加的商品数量 加上表中的商品数量一起更新到购物车表中
		   */
		 if( shopCar!=null) {

			         int num=buynum+shopCar.getBuynum();
			 
			         shopcarMapper.updateShopCar(num,userId,articleId);
			  	 //不存在则执行插入(判断用户是否是第一次购买该商品，是)
			}else {
				shopcarMapper.insertShopCar(buynum, userId, articleId);
				}
		 
	}

	
	//处理从添加商品(+)(-)带过来的请求
	public void insertInToShopCarByAddShopCar(int buynum, int userId, int articleId) {
		
		 ShopCarVo shopCar=shopcarMapper.findShopCarMsg(userId,articleId);
		  //如果商品ID和用户id信息存在  更新用户购买信息:将用户新加的商品数量 加上表中的商品数量一起更新到购物车表中
		 if( shopCar!=null) {

			
			   shopcarMapper.updateShopCar(buynum,userId,articleId);
				
		 //不存在则执行插入		
			}else {
			
				shopcarMapper.insertShopCar(buynum, userId, articleId);
			
		}
		
		
		
		
		
		
	}

	
	
	
	@Override
	public void showShopCar(HttpServletRequest request) {
		UserVo userVo=(UserVo)request.getSession().getAttribute("userVo");
		                                                            
		    
                List<ShopCarVo>  shoplist=  shopcarMapper.findArticleMsgByUserIdAndArticleId(userVo.getId());
                //订单总金额
                double allPrice=0.0;
                for (ShopCarVo shopCarVo : shoplist) {
                	allPrice+=	shopCarVo.getBuynum()*shopCarVo.getArticle().getPrice()*	shopCarVo.getArticle().getDiscount();
                	
              		}
                //将订单总金额 存放在request里面
                
                request.setAttribute("allPrice",allPrice);
                
                
                
                
                
                
                
                
                
      		
                 request.getSession().setAttribute("shopVo", shoplist);                 
		
	}

	@Override
	public boolean deleteShopCar(int userId, int articleId) {

 	   
 		shopcarMapper.deleteShopCarByuserIdAndArticleId(userId,articleId);		                           
	
   	  return true;
	}

}
