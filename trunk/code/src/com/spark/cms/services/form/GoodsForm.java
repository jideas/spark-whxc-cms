package com.spark.cms.services.form;

import java.util.List;

import com.spark.cms.services.vo.GoodsVo;

public class GoodsForm extends GoodsVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<GoodsContentForm> goodsContentForms;

	

	public List<GoodsContentForm> getGoodsContentForms() {
		return goodsContentForms;
	}



	public void setGoodsContentForms(List<GoodsContentForm> goodsContentForms) {
		this.goodsContentForms = goodsContentForms;
	}



	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	
	

}
