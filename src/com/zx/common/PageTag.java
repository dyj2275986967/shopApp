package com.zx.common;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;


//标签处理类：用于处理业务逻辑
public class PageTag extends TagSupport {
    
	//当前页码
    private int pageIndex;
    //每页显示的记录数
    private int pageSize;
    //总记录数
    private int totalNum;
    //提交地址
    private String submitUrl;//index.action?pageIndex={0}
    
    private String pageStyle;

	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		System.out.println("------doStartTag-----");
		try {
			JspWriter jspWriter = this.pageContext.getOut();
			
			StringBuffer sb = new StringBuffer();
			
			//如果总记录数大于0则显示 分页标签信息 信息
			if(this.totalNum > 0) {
				
				//定义跳转地址
				String jumpurl = "";
				
				//计算总页码    999  %  10   
				int totalPageNum = this.totalNum % this.pageSize == 0 ? this.totalNum / this.pageSize : (this.totalNum / this.pageSize) + 1;
				
				StringBuffer pager = new StringBuffer();
				
				//当前页码在首页
				if(this.pageIndex == 1) {
					pager.append("<span class='disabled'>上一页</span>");
					
					//处理中间页码
					calcMiddlePage(pager,totalPageNum);
					
					//如果总共只有一页  那么下一页也不能点击
					if(totalPageNum == 1) {
						pager.append("<span class='disabled'>下一页</span>");
					}else {
						//this.submitUrl:index.action?pageIndex={0}
						jumpurl = this.submitUrl.replace("{0}", String.valueOf(this.pageIndex + 1));
						pager.append("<a href='"+jumpurl+"'>下一页</a>");
					}
				
					//当前页码在尾页
				}else if(this.pageIndex == totalPageNum) {
					jumpurl = this.submitUrl.replace("{0}", String.valueOf(this.pageIndex - 1));
					pager.append("<a href='"+jumpurl+"'>上一页</a>");
					
					//处理中间页码
					calcMiddlePage(pager,totalPageNum);
					
					
					pager.append("<span class='disabled'>下一页</span>");
				}else {
					//当前页码在中间  此时上一页  下一页都可以点击
					jumpurl = this.submitUrl.replace("{0}", String.valueOf(this.pageIndex - 1));
					pager.append("<a href='"+jumpurl+"'>上一页</a>");
					
					//处理中间页码
					calcMiddlePage(pager,totalPageNum);
					
					
					jumpurl = this.submitUrl.replace("{0}", String.valueOf(this.pageIndex + 1));
					pager.append("<a href='"+jumpurl+"'>下一页</a>");
					
				}
				

				
				
				
				//计算开始行号   1   11  21
				int startSize = (this.pageIndex - 1) * this.pageSize + 1;
				
				//计算结束行号 10   20 30 
				int endSize =  this.pageIndex == totalPageNum? this.totalNum :  this.pageIndex * this.pageSize;
				
				sb.append("<table style='text-align:center;width:100%' class='"+this.pageStyle+"'><tr><td>"+pager.toString()+"&nbsp;跳转到<input type='text' size='4' id='jumpNum'/><input type='button' value='跳转' id='jumpBut'/></td></tr>");
				sb.append("<tr><td>总共<font color='red'>"+this.totalNum+"</font>条记录,当前显示"+startSize+"-"+endSize+"条记录</td></tr></table>");
				
               sb.append("<script type='text/javascript'>");
				//给按钮绑定点击事件
				sb.append("document.getElementById('jumpBut').onclick = function(){");
				//获取用户输入的页码值
				sb.append("var value = document.getElementById('jumpNum').value;");
				sb.append("if(!/^[1-9]\\d*$/.test(value)||value > "+totalPageNum+"){");
				sb.append("alert('请输入[1-"+totalPageNum+"]之间的页码值！');");
				sb.append("}else{");
				// index.action?pageIndex = {0}
				sb.append("var submiturl = '"+this.submitUrl+"';");
				sb.append("submiturl = submiturl.replace('{0}',value);");
				sb.append("window.location = submiturl;");
				
				sb.append("}");
				
				sb.append("}");
				sb.append("</script>");
				
			}else {
				//如果总记录数为 0 则显示              总共0条记录,当前显示0-0条记录
				sb.append("<table class='"+this.pageStyle+"' style='text-align:center;width:100%'><tr><td>总共<font color='red'>0</font>条记录,当前显示0-0条记录</td></tr></table>");
				
			}
			
			
			
			 
			jspWriter.write(sb.toString());
			jspWriter.flush();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return super.doStartTag();
	}

	//计算中间页码
	private void calcMiddlePage(StringBuffer pager, int totalPageNum) {
		// TODO Auto-generated method stub
		//定义跳转地址
		String jumpUrl = "";
		//1、当总页码数量小于等于10 的时候 全部显示不需要 ...    1 2 3 4 5 6 7 8 9 10
		if(totalPageNum <= 10) {
			for(int i = 1;i<= totalPageNum;i++) {
				 //当前页码不能点击
				 if(i == this.pageIndex) {
					 pager.append("<span class='current'>"+i+"</span>");
				 }else {
					 jumpUrl = this.submitUrl.replace("{0}", String.valueOf(i));
					 pager.append("<a href='"+jumpUrl+"'>"+i+"</a>");
				 }
			} 
			//当前页码靠近首页  1 2 3 4 5 6 7 8 9 ...  100
		}else if(this.pageIndex <= 8) {
			for(int i = 1;i<=9;i++) {
				 //当前页码不能点击
				 if(i == this.pageIndex) {
					 pager.append("<span class='current'>"+i+"</span>");
				 }else {
					 jumpUrl = this.submitUrl.replace("{0}", String.valueOf(i));
					 pager.append("<a href='"+jumpUrl+"'>"+i+"</a>");
				 }
			}
			//拼装 ...
			pager.append("...");
			//拼装尾页
			 jumpUrl = this.submitUrl.replace("{0}", String.valueOf(totalPageNum));
			 pager.append("<a href='"+jumpUrl+"'>"+totalPageNum+"</a>");
			 
		 //当前页码靠近尾页 1 ...  91 92 93 94 95 96 97 98 99 100
		}else if(this.pageIndex + 8 >= totalPageNum) {
			//拼装首页
			 jumpUrl = this.submitUrl.replace("{0}", String.valueOf(1));
			 pager.append("<a href='"+jumpUrl+"'>"+1+"</a>");
			
			 //拼装 ...
			pager.append("...");
			
			for(int i = totalPageNum - 9;i<=totalPageNum;i++) {
				 //当前页码不能点击
				 if(i == this.pageIndex) {
					 pager.append("<span class='current'>"+i+"</span>");
				 }else {
					 jumpUrl = this.submitUrl.replace("{0}", String.valueOf(i));
					 pager.append("<a href='"+jumpUrl+"'>"+i+"</a>");
				 }
			}
			//当前页码在中间 1 ... 44 45 46 47 48 49 50 51 52 ... 100
		}else {
			//拼装首页
			 jumpUrl = this.submitUrl.replace("{0}", String.valueOf(1));
			 pager.append("<a href='"+jumpUrl+"'>"+1+"</a>");
			
			 //拼装 ...
			pager.append("...");
			
			for(int i = this.pageIndex - 4;i<=this.pageIndex+4;i++) {
				 //当前页码不能点击
				 if(i == this.pageIndex) {
					 pager.append("<span class='current'>"+i+"</span>");
				 }else {
					 jumpUrl = this.submitUrl.replace("{0}", String.valueOf(i));
					 pager.append("<a href='"+jumpUrl+"'>"+i+"</a>");
				 }
			}
			
			 //拼装 ...
			pager.append("...");
			//拼装尾页
			 jumpUrl = this.submitUrl.replace("{0}", String.valueOf(totalPageNum));
			 pager.append("<a href='"+jumpUrl+"'>"+totalPageNum+"</a>");
		}
		
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		System.out.println("pageIndex:"+pageIndex);
		if(pageIndex == 0) {
			pageIndex = 1;
		}
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public String getSubmitUrl() {
		return submitUrl;
	}

	public void setSubmitUrl(String submitUrl) {
		this.submitUrl = submitUrl;
	}

	public String getPageStyle() {
		return pageStyle;
	}

	public void setPageStyle(String pageStyle) {
		this.pageStyle = pageStyle;
	}
	
	   

	

}
