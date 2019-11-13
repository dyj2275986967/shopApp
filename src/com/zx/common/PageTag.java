package com.zx.common;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;


//��ǩ�����ࣺ���ڴ���ҵ���߼�
public class PageTag extends TagSupport {
    
	//��ǰҳ��
    private int pageIndex;
    //ÿҳ��ʾ�ļ�¼��
    private int pageSize;
    //�ܼ�¼��
    private int totalNum;
    //�ύ��ַ
    private String submitUrl;//index.action?pageIndex={0}
    
    private String pageStyle;

	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		System.out.println("------doStartTag-----");
		try {
			JspWriter jspWriter = this.pageContext.getOut();
			
			StringBuffer sb = new StringBuffer();
			
			//����ܼ�¼������0����ʾ ��ҳ��ǩ��Ϣ ��Ϣ
			if(this.totalNum > 0) {
				
				//������ת��ַ
				String jumpurl = "";
				
				//������ҳ��    999  %  10   
				int totalPageNum = this.totalNum % this.pageSize == 0 ? this.totalNum / this.pageSize : (this.totalNum / this.pageSize) + 1;
				
				StringBuffer pager = new StringBuffer();
				
				//��ǰҳ������ҳ
				if(this.pageIndex == 1) {
					pager.append("<span class='disabled'>��һҳ</span>");
					
					//�����м�ҳ��
					calcMiddlePage(pager,totalPageNum);
					
					//����ܹ�ֻ��һҳ  ��ô��һҳҲ���ܵ��
					if(totalPageNum == 1) {
						pager.append("<span class='disabled'>��һҳ</span>");
					}else {
						//this.submitUrl:index.action?pageIndex={0}
						jumpurl = this.submitUrl.replace("{0}", String.valueOf(this.pageIndex + 1));
						pager.append("<a href='"+jumpurl+"'>��һҳ</a>");
					}
				
					//��ǰҳ����βҳ
				}else if(this.pageIndex == totalPageNum) {
					jumpurl = this.submitUrl.replace("{0}", String.valueOf(this.pageIndex - 1));
					pager.append("<a href='"+jumpurl+"'>��һҳ</a>");
					
					//�����м�ҳ��
					calcMiddlePage(pager,totalPageNum);
					
					
					pager.append("<span class='disabled'>��һҳ</span>");
				}else {
					//��ǰҳ�����м�  ��ʱ��һҳ  ��һҳ�����Ե��
					jumpurl = this.submitUrl.replace("{0}", String.valueOf(this.pageIndex - 1));
					pager.append("<a href='"+jumpurl+"'>��һҳ</a>");
					
					//�����м�ҳ��
					calcMiddlePage(pager,totalPageNum);
					
					
					jumpurl = this.submitUrl.replace("{0}", String.valueOf(this.pageIndex + 1));
					pager.append("<a href='"+jumpurl+"'>��һҳ</a>");
					
				}
				

				
				
				
				//���㿪ʼ�к�   1   11  21
				int startSize = (this.pageIndex - 1) * this.pageSize + 1;
				
				//��������к� 10   20 30 
				int endSize =  this.pageIndex == totalPageNum? this.totalNum :  this.pageIndex * this.pageSize;
				
				sb.append("<table style='text-align:center;width:100%' class='"+this.pageStyle+"'><tr><td>"+pager.toString()+"&nbsp;��ת��<input type='text' size='4' id='jumpNum'/><input type='button' value='��ת' id='jumpBut'/></td></tr>");
				sb.append("<tr><td>�ܹ�<font color='red'>"+this.totalNum+"</font>����¼,��ǰ��ʾ"+startSize+"-"+endSize+"����¼</td></tr></table>");
				
               sb.append("<script type='text/javascript'>");
				//����ť�󶨵���¼�
				sb.append("document.getElementById('jumpBut').onclick = function(){");
				//��ȡ�û������ҳ��ֵ
				sb.append("var value = document.getElementById('jumpNum').value;");
				sb.append("if(!/^[1-9]\\d*$/.test(value)||value > "+totalPageNum+"){");
				sb.append("alert('������[1-"+totalPageNum+"]֮���ҳ��ֵ��');");
				sb.append("}else{");
				// index.action?pageIndex = {0}
				sb.append("var submiturl = '"+this.submitUrl+"';");
				sb.append("submiturl = submiturl.replace('{0}',value);");
				sb.append("window.location = submiturl;");
				
				sb.append("}");
				
				sb.append("}");
				sb.append("</script>");
				
			}else {
				//����ܼ�¼��Ϊ 0 ����ʾ              �ܹ�0����¼,��ǰ��ʾ0-0����¼
				sb.append("<table class='"+this.pageStyle+"' style='text-align:center;width:100%'><tr><td>�ܹ�<font color='red'>0</font>����¼,��ǰ��ʾ0-0����¼</td></tr></table>");
				
			}
			
			
			
			 
			jspWriter.write(sb.toString());
			jspWriter.flush();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return super.doStartTag();
	}

	//�����м�ҳ��
	private void calcMiddlePage(StringBuffer pager, int totalPageNum) {
		// TODO Auto-generated method stub
		//������ת��ַ
		String jumpUrl = "";
		//1������ҳ������С�ڵ���10 ��ʱ�� ȫ����ʾ����Ҫ ...    1 2 3 4 5 6 7 8 9 10
		if(totalPageNum <= 10) {
			for(int i = 1;i<= totalPageNum;i++) {
				 //��ǰҳ�벻�ܵ��
				 if(i == this.pageIndex) {
					 pager.append("<span class='current'>"+i+"</span>");
				 }else {
					 jumpUrl = this.submitUrl.replace("{0}", String.valueOf(i));
					 pager.append("<a href='"+jumpUrl+"'>"+i+"</a>");
				 }
			} 
			//��ǰҳ�뿿����ҳ  1 2 3 4 5 6 7 8 9 ...  100
		}else if(this.pageIndex <= 8) {
			for(int i = 1;i<=9;i++) {
				 //��ǰҳ�벻�ܵ��
				 if(i == this.pageIndex) {
					 pager.append("<span class='current'>"+i+"</span>");
				 }else {
					 jumpUrl = this.submitUrl.replace("{0}", String.valueOf(i));
					 pager.append("<a href='"+jumpUrl+"'>"+i+"</a>");
				 }
			}
			//ƴװ ...
			pager.append("...");
			//ƴװβҳ
			 jumpUrl = this.submitUrl.replace("{0}", String.valueOf(totalPageNum));
			 pager.append("<a href='"+jumpUrl+"'>"+totalPageNum+"</a>");
			 
		 //��ǰҳ�뿿��βҳ 1 ...  91 92 93 94 95 96 97 98 99 100
		}else if(this.pageIndex + 8 >= totalPageNum) {
			//ƴװ��ҳ
			 jumpUrl = this.submitUrl.replace("{0}", String.valueOf(1));
			 pager.append("<a href='"+jumpUrl+"'>"+1+"</a>");
			
			 //ƴװ ...
			pager.append("...");
			
			for(int i = totalPageNum - 9;i<=totalPageNum;i++) {
				 //��ǰҳ�벻�ܵ��
				 if(i == this.pageIndex) {
					 pager.append("<span class='current'>"+i+"</span>");
				 }else {
					 jumpUrl = this.submitUrl.replace("{0}", String.valueOf(i));
					 pager.append("<a href='"+jumpUrl+"'>"+i+"</a>");
				 }
			}
			//��ǰҳ�����м� 1 ... 44 45 46 47 48 49 50 51 52 ... 100
		}else {
			//ƴװ��ҳ
			 jumpUrl = this.submitUrl.replace("{0}", String.valueOf(1));
			 pager.append("<a href='"+jumpUrl+"'>"+1+"</a>");
			
			 //ƴװ ...
			pager.append("...");
			
			for(int i = this.pageIndex - 4;i<=this.pageIndex+4;i++) {
				 //��ǰҳ�벻�ܵ��
				 if(i == this.pageIndex) {
					 pager.append("<span class='current'>"+i+"</span>");
				 }else {
					 jumpUrl = this.submitUrl.replace("{0}", String.valueOf(i));
					 pager.append("<a href='"+jumpUrl+"'>"+i+"</a>");
				 }
			}
			
			 //ƴװ ...
			pager.append("...");
			//ƴװβҳ
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
