package com.zx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.common.PageModel;


import com.zx.mapper.ArticleMapper;
import com.zx.service.ArticleService;
import com.zx.vo.ArticleVo;
@Transactional
@Service(value="ArticleService")
public class ArticleServiceImpl implements ArticleService {
	@Autowired
    private ArticleMapper  articleMapper;
	// 查找所有的商品信息
	public List<ArticleVo> findAllArticleByCode(String typeCode ,String keyWord,PageModel pageModel) {
	    //获取总记录数
	       int totalNum=articleMapper.findTotalNum(typeCode==null?"%%":typeCode+"%", keyWord==null?"%%":"%"+keyWord+"%");
		

		  pageModel.setTotalNum(totalNum);
		
		  return  articleMapper.findArticleMsgByCode(typeCode==null?"%%":typeCode+"%",keyWord==null?"%%":"%"+keyWord+"%",pageModel);
		
	}

	//通过商品id查找商品信息
	public ArticleVo findAllArticleById(String id) {
		

		
		
		
       return  articleMapper.findArticleMsgById(id);
		

		
		
	}
	
}
