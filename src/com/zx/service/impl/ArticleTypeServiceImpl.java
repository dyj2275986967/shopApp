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
	
	
	//����ȫ��������Ʒ��Ϣ
	public List<ArticleTypeVo> findAllFirstArticleType() {

		           List<ArticleTypeVo> articleType= articleTypeMapper.findArticleTypeByOneCode(4);
		           return articleType;
		
	}

	// ����ȫ����������Ʒ��Ϣ
	public List<ArticleTypeVo> findAllTwoArticleType(String typeCode) {
		//����һ��ҳ��������ʱ typeCode��ֵ ����Ϊnull  ���Ϊ null����Ϊ���ַ����ͷ���null
		   
		     //��ȡ�ֶ�ֵ ��ģ����ѯ
		            String code =typeCode.substring(0,4);     
		  
		
		 return findAllTwoArticleTypeUtil(    articleTypeMapper.findArticleTypeByTwoCode( code+"%"));


	}

	// ����ȫ��������Ʒ��Ϣ�Ĺ����� ���ָ��ַ� �Լ� ȥ��һ����Ʒ����
	public List<ArticleTypeVo> findAllTwoArticleTypeUtil(List<ArticleTypeVo> articleTypeTwo) {

		List<ArticleTypeVo> articleTypeUtil = new ArrayList();
		ArticleTypeVo vo = null;

		// �������
		for (ArticleTypeVo articleTypeVo : articleTypeTwo) {

			// bug��һ����Ʒ�ĳ���д�Ķ�ֵ
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
