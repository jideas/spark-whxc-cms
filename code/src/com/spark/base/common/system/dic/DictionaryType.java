package com.spark.base.common.system.dic;

/**
 * @author Jideas ���ö�ٲ��裺 01����web/xml/dic/�ļ����£�����ģ�彨��xml�ļ� 02���ٱ�����������ļ�����ͬ��ö����
 *         03�������ϵͳö�٣��򴴽�enum�ļ���������com.spark.cms.base.constant��
 */
public enum DictionaryType {
	/**
	 * ����״��
	 */
	MaritalStatus,

	/**
	 * ��ֵ�����
	 */
	CardAmount,

	/**
	 * ��������
	 */
	VantagesType,

	/**
	 * ��������
	 */
	Area, 
	
	/**
	 * ���
	 */
	Identity, 
	
	/**
	 * סլ״��
	 */
	LivingCondition,
	
	DeliveryHour,
	
	/**
	 * �������ж�Ӧ�˿�
	 */
	BankGateMapping,

	;

	public String getFileName() {
		return this.name();
	}
}
