package com.zx.service;

import java.util.List;

import com.zx.vo.ArticleTypeVo;
import com.zx.vo.ArticleVo;

public interface ArticleTypeService {

	//����ȫ��һ����Ʒ��Ϣ
	public List<ArticleTypeVo> findAllFirstArticleType();
	//����ȫ����������Ʒ��Ϣ
	public List<ArticleTypeVo> findAllTwoArticleType(String typeCode);
	

}
