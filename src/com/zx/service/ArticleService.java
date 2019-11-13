package com.zx.service;

import java.util.List;

import com.zx.common.PageModel;
import com.zx.vo.ArticleVo;

public interface ArticleService {
	//查找所有的商品信息
	public List<ArticleVo> findAllArticleByCode(String typeCode,String keyWord,PageModel pagemodel);
	//通过商品id查找商品信息
	public  ArticleVo findAllArticleById(String id);
}
