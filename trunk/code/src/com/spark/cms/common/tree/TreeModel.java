package com.spark.cms.common.tree;


public class TreeModel {
	public static String TreeRoot = "root";
	/**
	 * �ڵ�ID
	 */
	private String id;

	/**
	 * �ڵ��ı�
	 */
	private String text;

	/**
	 * �ڵ�ͼ��
	 */
	private String iconCls;

	/**
	 * ָʾ�ڵ��Ƿ���ѡ��״̬
	 */
	private boolean checked = false;

	/**
	 * �ڵ�ĳ�ʼ��״̬(�رջ�չ��)
	 */
	private String state="closed";

	/**
	 * �Զ�������
	 */
	private AttributesModel attributes;

	/**
	 * �ӽڵ�
	 */
	private TreeModel[] children;

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

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public boolean getChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public TreeModel[] getChildren() {
		return children;
	}

	public void setChildren(TreeModel[] children) {
		this.children = children;
	}

	public AttributesModel getAttributes() {
		return attributes;
	}

	public void setAttributes(AttributesModel attributes) {
		this.attributes = attributes;
	}
	
	
	public class AttributesModel {
		/**
		 * ��Դ·��
		 */
		private String href;
		
		/**
		 *��Դ���ڵ� 
		 */
		private String parentId;
		
		/**
		 * ��Դ����
		 */
		private String code;

		public String getHref() {
			return href;
		}

		public void setHref(String href) {
			this.href = href;
		}

		public String getParentId() {
			return parentId;
		}

		public void setParentId(String parentId) {
			this.parentId = parentId;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

	}

}
