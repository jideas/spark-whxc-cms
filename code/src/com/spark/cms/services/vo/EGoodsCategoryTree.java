package com.spark.cms.services.vo;

import com.spark.base.type.GUID;

public class EGoodsCategoryTree {
	public final static String ERP_ROOT_ID_STR = "10000000000000000000000000000001";
	public static byte[] ERP_ROOT_ID = GUID.tryValueOf(ERP_ROOT_ID_STR).toBytes();
	
	private String state;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
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
		private String state = "open";

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}
		
	}
}
