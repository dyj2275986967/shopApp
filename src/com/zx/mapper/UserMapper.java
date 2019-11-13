package com.zx.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zx.vo.ArticleVo;
import com.zx.vo.UserVo;


public interface UserMapper {
     @Select("select * from ec_user where login_name=#{loginName} ")
	UserVo userNotExist(String loginName);

	void userRegister(UserVo vo);

	@Update("update ec_user set disabled=1 ,active=null where active=#{active}")
	void updateUserAction(UserVo vo);




   UserVo marchUserMsg(UserVo vo);



}