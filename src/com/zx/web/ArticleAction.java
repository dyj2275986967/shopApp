package com.zx.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zx.common.PageModel;
import com.zx.service.ArticleService;
import com.zx.service.ArticleTypeService;
import com.zx.service.impl.ArticleServiceImpl;
import com.zx.service.impl.ArticleTypeServiceImpl;
import com.zx.vo.ArticleTypeVo;
import com.zx.vo.ArticleVo;



@Controller
//�����ռ�
@RequestMapping("/article")
public class ArticleAction  {
	//@Autowired�ǰ���byType����ʽ�����Զ�װ��
	//@Autowired
	//@Resource �ǰ���byName����ʽ����װ��
	@Resource(name="ArticleTypeService")
	private ArticleTypeService articleType;
	@Resource(name="ArticleService")
	private ArticleService article;
	/**
	 * չʾ��Ʒ��ҳ
	 */
@RequestMapping("/index")
	public String showIndex(String typeCode,String keyword,HttpServletRequest request,PageModel pageModel,String pageIndex) {
     List<ArticleTypeVo>		firstArticleType = articleType.findAllFirstArticleType();
	
     //��������Ϣ
     request.setAttribute("keyword", keyword);
     
     //��һ����Ʒ��Ϣ
		request.setAttribute("firstArticleType",firstArticleType );

		if (typeCode != null && !"".equals(typeCode)) {

			// ������ҳ��� ѡ�г�����ɫ�� ��
			 request.setAttribute("typeCode",typeCode.substring(0, 4));
			 List<ArticleTypeVo>		twoArticleType = articleType.findAllTwoArticleType(typeCode);
			 //�������Ʒ��Ϣ
			 request.setAttribute("twoArticleType", twoArticleType);
		}
       
		//�������Ʒcode
	String 	twoTypeCode=typeCode;
	 request.setAttribute("twoTypeCode", twoTypeCode);	
	// ��ҳ��ʾ��������

		
		//pageModel = new PageModel(); ?????
		if (pageIndex != null && !"".equals(pageIndex)) {
			
			pageModel.setPageIndex(Integer.valueOf(pageIndex));
		}
		 List<ArticleVo>articleAllMsgByCode = article.findAllArticleByCode(typeCode, keyword, pageModel);
         request.setAttribute("articleAllMsgByCode", articleAllMsgByCode);
         request.setAttribute("pageModel", pageModel);
		 //�浱ǰҳ��pageIndex
         request.setAttribute("pageIndex", pageIndex);
//ת����ҳ��
		return "main";
	}
 
@RequestMapping("detial")
	public String showDetial(String id,HttpServletRequest request) {

	ArticleVo	articleVo = article.findAllArticleById(id);
      	
      //����Ʒ����
      	request.setAttribute("articleVo", articleVo);
      	
      	
      //ת����ҳ��
		return "detial";
	}



}
