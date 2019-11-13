package com.zx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.zx.vo.OrderItemVo;


public interface OrderItemMapper {


	

	List<OrderItemVo> selectOrderItemMsg(int id);

}