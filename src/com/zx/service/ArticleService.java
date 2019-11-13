package com.zx.service;

import java.util.List;

import com.zx.common.PageModel;
import com.zx.vo.ArticleVo;

public interface ArticleService {
	//�������е���Ʒ��Ϣ
	public List<ArticleVo> findAllArticleByCode(String typeCode,String keyWord,PageModel pagemodel);
	//ͨ����Ʒid������Ʒ��Ϣ
	public  ArticleVo findAllArticleById(String id);
}
