/**
 * 
 */
package com.spark.cms.base.constant;

/**
 * @author Jideas
 * 
 */
public enum SheetNumberType {

	/**
	 * ���϶���
	 */
	OnlineOrder("WSDD", 6),

	/**
	 * �人7����ֵ�����κ�
	 */
	CardNumber("0", 4),

	/**
	 * ��ֵ��˳���
	 */
	CardOrdinal("MZK", 4),
	
	/**
	 * ��Ա���
	 */
	MemberCode("HY", 4),
	
	UnionPayOrdId("0",8),
	
	FolderCode("0",12),

	;
	private String defaultPrefix;
	private int length;

	private SheetNumberType(String defaultPrefix, int length) {
		this.defaultPrefix = defaultPrefix;
		this.length = length;
	}

	public String getDefaultPrefix() {
		return this.defaultPrefix;
	}

	public int getLength() {
		return length;
	}
}
