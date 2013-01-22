package com.spark.cms.common.tree;

public class ChannelTree {
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
	private String state = "closed";

	/**
	 * �Զ�������
	 */
	private AttributesModel attributes;

	/**
	 * �ӽڵ�
	 */
	private ChannelTree[] children;

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

	public ChannelTree[] getChildren() {
		return children;
	}

	public void setChildren(ChannelTree[] children) {
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
		 * ¥�����
		 */
		private String ordinal;
		
		private boolean isMainMenu = false;

		/**
		 * ¥������
		 */
		private String floortype;
		
		/**
		 * ��¥��Ʒ����ID
		 */
		private String goodsCategoryId;
		
		/**
		 * ¥������
		 */
		private String theme;

		/**
		 * ��Ŀ���
		 */
		private String code;

		/**
		 * ��Ŀ��¥��ID
		 */
		private String floorid;

		/**
		 * ��Ŀ����
		 */
		private String channeltype;

		/**
		 * ��Ŀҳ����
		 */
		private String pagetype;

		/**
		 * ����Ƿ���¥��
		 */
		private boolean isfloor = false;
		
		private boolean autoContent = false;

		public String getOrdinal() {
			return ordinal;
		}

		public void setOrdinal(String ordinal) {
			this.ordinal = ordinal;
		}

		public String getFloortype() {
			return floortype;
		}

		public void setFloortype(String floortype) {
			this.floortype = floortype;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getFloorid() {
			return floorid;
		}

		public void setFloorid(String floorid) {
			this.floorid = floorid;
		}

		public String getChanneltype() {
			return channeltype;
		}

		public void setChanneltype(String channeltype) {
			this.channeltype = channeltype;
		}

		public String getPagetype() {
			return pagetype;
		}

		public void setPagetype(String pagetype) {
			this.pagetype = pagetype;
		}

		public boolean isIsfloor() {
			return isfloor;
		}

		public void setIsfloor(boolean isfloor) {
			this.isfloor = isfloor;
		}

		public String getTheme() {
			return theme;
		}

		public void setTheme(String theme) {
			this.theme = theme;
		}

		public String getGoodsCategoryId() {
			return goodsCategoryId;
		}

		public void setGoodsCategoryId(String goodsCategoryId) {
			this.goodsCategoryId = goodsCategoryId;
		}

		public boolean isMainMenu() {
			return isMainMenu;
		}

		public void setMainMenu(boolean isMainMenu) {
			this.isMainMenu = isMainMenu;
		}

		public boolean isAutoContent() {
			return autoContent;
		}

		public void setAutoContent(boolean autoContent) {
			this.autoContent = autoContent;
		}
	}

}
