package com.spark.cms.services.vo;


public class GoodsCategoryTree {
	public static final String Root_ID = "root";
	public static final String Root_Text = "全部";
	
	public static enum Status {
		open, closed
	}
	
	private String id;
	private String text;
	private String state;
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
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
	
	public CategoryNode getCategoryNodeById(String id) {
		for (CategoryNode node : children) {
			if (node.getId().equals(id)) {
				return node;
			}
			CategoryNode targetNode = getCategoryNodeById(node, id);
			if (null != targetNode) {
				return targetNode;
			}
		}
		return null;
	}
	
	private CategoryNode getCategoryNodeById(CategoryNode node, String id) {
		for (CategoryNode cNode : node.getChildren()) {
			if (cNode.getId().equals(id)) {
				return cNode;
			}
			CategoryNode targetNode = getCategoryNodeById(cNode, id);
			if (null != targetNode) {
				return targetNode;
			} 
		}
		return null;
	}
	
	public class CategoryNode {
		private String id;
		private String text;
//		private CategoryNode parent;
		private CategoryNode[] children;
		private String state = "open";
		private Attribute attributes;
		
		
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
		public Attribute getAttributes() {
			return attributes;
		}
		public void setAttributes(Attribute attributes) {
			this.attributes = attributes;
		}
		public String getState() {
//			if (null != attributes && attributes.levelNo == 1) {
//				return "open";
//			}
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		
	}
	
	public class Attribute {
		private int levelNo;
		private boolean isPropertied;
		private String categoryNo;
		
		public String getCategoryNo() {
			return categoryNo;
		}
		public void setCategoryNo(String categoryNo) {
			this.categoryNo = categoryNo;
		}
		public int getLevelNo() {
			return levelNo;
		}
		public void setLevelNo(int levelNo) {
			this.levelNo = levelNo;
		}
		public boolean isPropertied() {
			return isPropertied;
		}
		public void setPropertied(boolean isPropertied) {
			this.isPropertied = isPropertied;
		}
	}
}
