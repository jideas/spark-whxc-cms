package com.spark.cms.services.vo;

public class LightGoodsCategoryTree {
	public static final String Root_ID = "root";
	public static final String Root_Text = "全部";
	
	private String id;
	private String text;
	private CategoryNode[] children;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * 获取根节点
	 * 
	 * @return
	 */
	public CategoryNode[] getChildren() {
		return children;
	}
	public void setChildren(CategoryNode[] children) {
		this.children = children;
	}
	
	public class CategoryNode {
		private String id;
		private String text;
		private CategoryNode[] children;
		
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public CategoryNode[] getChildren() {
			return children;
		}
		public void setChildren(CategoryNode[] children) {
			this.children = children;
		}
		
	}
}
