package com.zx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import com.zx.vo.*;

public interface ArticleTypeMapper {
 @Select("select * from ec_article_type where length(code)=#{typeCode}")
	List<ArticleTypeVo> findArticleTypeByOneCode(int typeCode);
@Select("select * from ec_article_type where code like #{typeCode}")
    List<ArticleTypeVo> findArticleTypeByTwoCode(String typeCode);



}