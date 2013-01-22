package com.spark.cms.services.goods;

import org.hibernate.annotations.common.util.StringHelper;

import com.spark.base.type.GUID;

public class GetEGoodsListKey {
	private String categoryId;
	private int offset = 0;
	private int count = Integer.MAX_VALUE;

	public byte[] getCategoryId() {
		if (StringHelper.isEmpty(categoryId) || categoryId.equals("root")) {
			return null;
		}
		return GUID.tryValueOf(categoryId).toBytes();
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		if (offset < 0) return;
		this.offset = offset;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		if (count < 1) return;
		this.count = count;
	}
	
	
}
