package com.base.utils.tag;

import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class SourceTag extends TagSupport {
	/**
	 * 资源标签
	 */
	private static final long serialVersionUID = 1L;
	private static Map<String, Object> tagSource = null;
	private String id;
	private String name;
	private String cssStyle;
	private String click;
	
	public void setId(String id){
		this.id = id;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setCssStyle(String cssStyle){
		this.cssStyle = cssStyle;
	}
	public void setClick(String click){
		this.click = click;
	}

	public static Map<String, Object> getTagSource() {
		return tagSource;
	}
	public static void setTagSource(Map<String, Object> tagSource) {
		SourceTag.tagSource = tagSource;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getCssStyle() {
		return cssStyle;
	}
	public String getClick() {
		return click;
	}
	public SourceTag() {
		System.out.println("MyTag构造方法：一个myTag类的对象被构建了....");
	}

	public int doStartTag() {
		JspWriter out = this.pageContext.getOut();

		try {
			out.print(name);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("对象正在处理开始标记.....");
		return EVAL_BODY_INCLUDE;
	}

	public int doAfterBody() throws JspException {
		System.out.println("处理标签体（after body）....");
		return SKIP_BODY;
	}

	public int doEndTag() throws JspException {
		System.out.println("对象正在处理结束标记.....");
		return EVAL_PAGE;
	}
}
