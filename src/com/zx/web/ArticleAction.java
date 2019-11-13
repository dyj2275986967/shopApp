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
//命名空间
@RequestMapping("/article")
public class ArticleAction  {
	//@Autowired是按照byType的形式进行自动装配
	//@Autowired
	//@Resource 是按照byName的形式进行装配
	@Resource(name="ArticleTypeService")
	private ArticleTypeService articleType;
	@Resource(name="ArticleService")
	private ArticleService article;
	/**
	 * 展示商品首页
	 */
@RequestMapping("/index")
	public String showIndex(String typeCode,String keyword,HttpServletRequest request,PageModel pageModel,String pageIndex) {
     List<ArticleTypeVo>		firstArticleType = articleType.findAllFirstArticleType();
	
     //存搜索信息
     request.setAttribute("keyword", keyword);
     
     //存一级商品信息
		request.setAttribute("firstArticleType",firstArticleType );

		if (typeCode != null && !"".equals(typeCode)) {

			// 控制首页左边 选中出现蓝色的 条
			 request.setAttribute("typeCode",typeCode.substring(0, 4));
			 List<ArticleTypeVo>		twoArticleType = articleType.findAllTwoArticleType(typeCode);
			 //存二级商品信息
			 request.setAttribute("twoArticleType", twoArticleType);
		}
       
		//存二级商品code
	String 	twoTypeCode=typeCode;
	 request.setAttribute("twoTypeCode", twoTypeCode);	
	// 分页显示界面数据

		
		//pageModel = new PageModel(); ?????
		if (pageIndex != null && !"".equals(pageIndex)) {
			
			pageModel.setPageIndex(Integer.valueOf(pageIndex));
		}
		 List<ArticleVo>articleAllMsgByCode = article.findAllArticleByCode(typeCode, keyword, pageModel);
         request.setAttribute("articleAllMsgByCode", articleAllMsgByCode);
         request.setAttribute("pageModel", pageModel);
		 //存当前页面pageIndex
         request.setAttribute("pageIndex", pageIndex);
//转发至页面
		return "main";
	}
 
@RequestMapping("detial")
	public String showDetial(String id,HttpServletRequest request) {

	ArticleVo	articleVo = article.findAllArticleById(id);
      	
      //存商品详情
      	request.setAttribute("articleVo", articleVo);
      	
      	
      //转发至页面
		return "detial";
	}



}
