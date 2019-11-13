package com.zx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zx.vo.ShopCarVo;

import jdk.nashorn.internal.ir.annotations.Ignore;


public interface ShopcarMapper {
	

	ShopCarVo findShopCarMsg(@Param("userId")int userId, @Param("articleId")int articleId);
   
@Update("update ec_shopcar set buynum=#{num} where user_id=#{userId} and article_id=#{articleId}")
void updateShopCar(@Param("num")int num, @Param("userId")int userId, @Param("articleId")int articleId);

@Insert("insert into ec_shopcar (buynum,user_id,article_id)  values(#{buynum},#{userId},#{articleId}) ")
void insertShopCar(@Param("buynum")int buynum,@Param("userId") int userId, @Param("articleId")int articleId);


   List<ShopCarVo>  findArticleMsgByUserIdAndArticleId(int id);


@Delete("delete from ec_shopcar where user_id=#{userId} and article_id=#{articleId}")
void deleteShopCarByuserIdAndArticleId(@Param("userId")int userId, @Param("articleId")int articleId);

//插入后删除 购物车里下顶单的商品信息
@Delete("delete from ec_shopcar where user_id=#{id} and article_id=#{articleId}")
void deleteOrderShopCarByArticleId(@Param("id")int id,@Param("articleId") int articleId);

//加入购物车后要更新库存量
@Update("update ec_article set storage=#{storageNum}  where id=#{articleId} ")
void updateShopStorage(@Param("articleId")int articleId,@Param("storageNum")int storageNum);





}