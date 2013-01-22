package com.spark.cms.base.constant;

public enum PageConstant {

	HotSearchKey("00", "��������", "��������"),

	Channel01("01", "��Ŀ1", "��ֵ����"),

	Channel02("02", "��Ŀ2", "���λ2"),

	Channel31("31", "��Ŀ3ҳǩһ", "����챨"),

	Channel32("32", "��Ŀ3ҳǩ��", "�Զ���"),

	PowerPonit("99", "�õ�Ƭ", "�õ�Ƭ"),

	Channel04("04", "��Ŀ4", "��ʱ����"),

	Channel05("05", "��Ŀ5", "������Ʒ"),

	Channel06("06", "��Ŀ6", "������Ʒ"),

	Channel07("07", "��Ŀ7", "��Ʒ�ϼ�"),

	Channel08("08", "", "����ָ��"),

	Channel09("09", "", "���ͷ�ʽ"),

	Channel10("10", "", "֧����ʽ"),

	Channel11("11", "", "�ۺ����"),

	Channel12("12", "", "��ɫ����"),

	/**
	 * ����ҳ����Ŀ
	 */
	MostSales("13", "", "�����Ƽ�"),

	Popular("14", "", "������Ʒ"),

	Channel21("21","","���λ1"),
	
	Channel22("22","","���λ2"),
	
	Channel23("23","","���λ3"),
	;
	private String code;
	private String title;

	private PageConstant(String code, String name, String title) {
		this.code = code;
		this.title = title;
	}

	public String getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}
}
