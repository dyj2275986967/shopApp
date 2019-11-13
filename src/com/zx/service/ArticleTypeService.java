package com.zx.service;

import java.util.List;

import com.zx.vo.ArticleTypeVo;
import com.zx.vo.ArticleVo;

public interface ArticleTypeService {

	//查找全部一级商品信息
	public List<ArticleTypeVo> findAllFirstArticleType();
	//查找全部二级级商品信息
	public List<ArticleTypeVo> findAllTwoArticleType(String typeCode);
	

}
