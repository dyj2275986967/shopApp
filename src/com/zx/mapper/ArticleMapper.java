package com.zx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.zx.vo.*;
import com.zx.common.PageModel;

public interface ArticleMapper {
   @Select("select count(*) from ec_article where type_code like #{typeCode} and title like #{keyWord}")
	int findTotalNum(@Param("typeCode")String typeCode, @Param("keyWord")String keyWord);

    List<ArticleVo> findArticleMsgByCode(@Param("typeCode")String  typeCode,@Param("keyWord") String keyWord, @Param("pageModel")PageModel pageModel);
  

    ArticleVo findArticleMsgById(String id);

}