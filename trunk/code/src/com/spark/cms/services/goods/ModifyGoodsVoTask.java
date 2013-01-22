package com.spark.cms.services.goods;

import com.spark.base.type.GUID;
import com.spark.cms.common.Constant.Base.GoodsType;

public class ModifyGoodsVoTask {
	private String property1;
	private String property2;
	private String property3;
	private String property4;
	private String property5;
	
	private double inventoryCount;
	private boolean isMostSales;
	private boolean isPopular;
	//private double realprice;
	private String vantagestype;
	private boolean freedelivery;

	private GoodsType goodsType;
	
	private byte[] goodsId;
	
	public ModifyGoodsVoTask(String goodsId) {
		this.goodsId = GUID.tryValueOf(goodsId).toBytes();
	}
	
	public byte[] getGoodsId() {
		return goodsId;
	}
	public String getProperty1() {
		return property1;
	}
	public void setProperty1(String property1) {
		this.property1 = property1;
	}
	public String getProperty2() {
		return property2;
	}
	public void setProperty2(String property2) {
		this.property2 = property2;
	}
	public String getProperty3() {
		return property3;
	}
	public void setProperty3(String property3) {
		this.property3 = property3;
	}
	public String getProperty4() {
		return property4;
	}
	public void setProperty4(String property4) {
		this.property4 = property4;
	}
	public String getProperty5() {
		return property5;
	}
	public void setProperty5(String property5) {
		this.property5 = property5;
	}

	public double getInventoryCount() {
		return inventoryCount;
	}

	public void setInventoryCount(double inventoryCount) {
		this.inventoryCount = inventoryCount;
	}

	public boolean isMostSales() {
		return isMostSales;
	}

	public void setMostSales(boolean isMostSales) {
		this.isMostSales = isMostSales;
	}

	public boolean isPopular() {
		return isPopular;
	}

	public void setPopular(boolean isPopular) {
		this.isPopular = isPopular;
	}


	public String getVantagestype() {
		return vantagestype;
	}

	public void setVantagestype(String vantagestype) {
		this.vantagestype = vantagestype;
	}

	public boolean isFreedelivery() {
		return freedelivery;
	}

	public void setFreedelivery(boolean freedelivery) {
		this.freedelivery = freedelivery;
	}

	public GoodsType getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(GoodsType goodsType) {
		this.goodsType = goodsType;
	}

	
	
}
