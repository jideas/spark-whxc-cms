package com.spark.cms.services.inventory.impl;

public enum InventoryLogType {

	/** 采购入库 */
	INSTORAGE("1","采购入库"),
	/** 采购退货 */
	BUYCANCEL("2","采购退货"),
	/** 销售出库*/
	OUTSTORAGE("3","销售出库"),
	/** 销售退货*/
	SALECANCEL("4","销售退货"),
	/** 库存调配*/
	STOREALLCOATE("5","库存调配"),
	/** 库存拆装*/
	STOREDISMOUNT("6","库存拆装"),
	/** 库存盘点*/
	STORECHECK("7","库存盘点"),
	/** 仓库启用 */
	INITVALUE("8","仓库启用"),
	/**商品盘盈**/
	CheckProfit("9","商品盘盈"),
	/**商品盘亏**/
	CheckDeficient("10","商品盘亏"),
	/**零星采购**/
	HandtomouthBuying("11","零星采购"),
	/**零售出库商品**/
	HandtomouthOUTSTORAGE("12","零售出库商品"),
	/**零售退货入库**/
	HandtomouthCANCELSTORAGE("13","零售退货入库"),
	/**其他出库**/
	OTHEROUTSTORAGE("14","其他出库"),
	/**调出商品**/
	GOODSOUTSTORAGE("15","调出商品"),
	/**调入商品**/
	GOODSINSTORAGE("16","调入商品"),
	/**调整成本**/
	AdjustInventoryCost("17","调整成本"),
	/**库存报损**/
	ReportLoss("18","库存报损"),
	/**领料出库**/
	MaterialsCheckout("19","领料出库"),
	/**退料出库**/
	MaterialsCheckin("20","退料出库"),
	/**成品出库**/
	GoodsCheckout("21","成品出库"),
	/**成品入库**/
	GoodsCheckin("22","成品入库"),
	GoodsSplitCheckout("23","商品拆分出库"),
	GoodsSplitCheckin("24","商品拆分入库"),
	
	;

	
	private String code,name; 
	
	InventoryLogType(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public String getCode(){
		return code;
	}
	public String getName() {
		return name;
	}
	public static InventoryLogType getEnum(String code){
		InventoryLogType typeEnum = null;
		int codeI = Integer.parseInt(code);
		switch(codeI){
		case 1:
			typeEnum = InventoryLogType.INSTORAGE;
			break;
		case 2:
			typeEnum = InventoryLogType.BUYCANCEL;
			break;
		case 3:
			typeEnum = InventoryLogType.OUTSTORAGE;
			break;
		case 4:
			typeEnum = InventoryLogType.SALECANCEL;
			break;
		case 5:
			typeEnum = InventoryLogType.STOREALLCOATE;
			break;
		case 6:
			typeEnum = InventoryLogType.STOREDISMOUNT;
			break;
		case 7:
			typeEnum = InventoryLogType.STORECHECK;
			break;
		case 8:
			typeEnum = InventoryLogType.INITVALUE;
			break;
		case 9:
			typeEnum = InventoryLogType.CheckProfit;
			break;
		case 10:
			typeEnum = InventoryLogType.CheckDeficient;
			break;
		case 11:
			typeEnum = InventoryLogType.HandtomouthBuying;
			break;
		case 12:
			typeEnum = InventoryLogType.HandtomouthOUTSTORAGE;
			break;
		case 13:
			typeEnum = InventoryLogType.HandtomouthCANCELSTORAGE;
			break;
		case 14:
			typeEnum = InventoryLogType.OTHEROUTSTORAGE;
			break;
		case 15:
			typeEnum = InventoryLogType.GOODSOUTSTORAGE;
			break;
		case 16:
			typeEnum = InventoryLogType.GOODSINSTORAGE;
			break;
		case 17:
			typeEnum = InventoryLogType.AdjustInventoryCost;
			break;
		case 18:
			typeEnum = InventoryLogType.ReportLoss;
			break;
		case 19:
			typeEnum = InventoryLogType.MaterialsCheckout;
			break;
		case 20:
			typeEnum = InventoryLogType.MaterialsCheckin;
			break;
		case 21:
			typeEnum = InventoryLogType.GoodsCheckout;
			break;
		case 22:
			typeEnum = InventoryLogType.GoodsCheckin;
			break;
		case 23:
			typeEnum = InventoryLogType.GoodsSplitCheckout;
			break;
		case 24:
			typeEnum = InventoryLogType.GoodsSplitCheckin;
			break;
		}
		
		return typeEnum;
	}
}
