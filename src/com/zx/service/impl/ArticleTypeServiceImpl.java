package com.zx.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import com.zx.mapper.ArticleTypeMapper;



import com.zx.service.ArticleTypeService;
import com.zx.vo.ArticleTypeVo;

@Transactional
@Service(value="ArticleTypeService")
public class ArticleTypeServiceImpl implements ArticleTypeService {

	@Autowired
	private ArticleTypeMapper articleTypeMapper;
	
	
	//查找全部二级商品信息
	public List<ArticleTypeVo> findAllFirstArticleType() {

		           List<ArticleTypeVo> articleType= articleTypeMapper.findArticleTypeByOneCode(4);
		           return articleType;
		
	}

	// 查找全部二级级商品信息
	public List<ArticleTypeVo> findAllTwoArticleType(String typeCode) {
		//当第一进页面点击搜索时 typeCode的值 可能为null  如果为 null或者为空字符串就返回null
		   
		     //截取字段值 作模糊查询
		            String code =typeCode.substring(0,4);     
		  
		
		 return findAllTwoArticleTypeUtil(    articleTypeMapper.findArticleTypeByTwoCode( code+"%"));


	}

	// 查找全部二级商品信息的工具类 ：分割字符 以及 去掉一级商品数据
	public List<ArticleTypeVo> findAllTwoArticleTypeUtil(List<ArticleTypeVo> articleTypeTwo) {

		List<ArticleTypeVo> articleTypeUtil = new ArrayList();
		ArticleTypeVo vo = null;

		// 拆分数据
		for (ArticleTypeVo articleTypeVo : articleTypeTwo) {

			// bug：一级商品的长度写的定值
			if (articleTypeVo.getCode().length() != 4) {

				articleTypeVo.getName().replace("----", "");
				vo = new ArticleTypeVo(articleTypeVo.getCode(), articleTypeVo.getName().replace("----", ""),
						articleTypeVo.getRemark());

				articleTypeUtil.add(vo);

			}

		}

		return articleTypeUtil;
	}



}
