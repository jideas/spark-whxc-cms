package com.spark.base.common.system.dic;

/**
 * @author Jideas 添加枚举步骤： 01：在web/xml/dic/文件夹下，按照模板建造xml文件 02：再本类中添加于文件名相同的枚举项
 *         03：如果是系统枚举，则创建enum文件，（包：com.spark.cms.base.constant）
 */
public enum DictionaryType {
	/**
	 * 婚姻状况
	 */
	MaritalStatus,

	/**
	 * 面值卡金额
	 */
	CardAmount,

	/**
	 * 积分类型
	 */
	VantagesType,

	/**
	 * 行政区划
	 */
	Area, 
	
	/**
	 * 身份
	 */
	Identity, 
	
	/**
	 * 住宅状况
	 */
	LivingCondition,
	
	DeliveryHour,
	
	/**
	 * 网银银行对应端口
	 */
	BankGateMapping,

	;

	public String getFileName() {
		return this.name();
	}
}
