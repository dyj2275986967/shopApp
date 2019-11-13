package com.zx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.zx.vo.OrderItemVo;
import com.zx.vo.OrderVo;
import com.zx.vo.UserVo;


public interface OrderMapper {
        
	@Insert("insert into ec_order_item (order_id,article_id,order_num) values(#{orderId},#{articleId},#{buynum})")
	void insertOrderItemMsg(@Param("orderId")int orderId,@Param("articleId") int articleId,@Param("buynum")  int buynum);
	
	void insertOrderMsg(OrderVo orderVo);
   

    List<OrderVo>	 selectOrderMsg(int id);




}