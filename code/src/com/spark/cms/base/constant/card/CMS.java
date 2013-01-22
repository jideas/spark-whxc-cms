/**
 * 
 */
package com.spark.cms.base.constant.card;

import com.spark.base.type.GUID;

/**
 * @author Jideas ����
 */
public interface CMS {

	public static class Reg {

		/**
		 * ��༸λС����������ʽ
		 * 
		 * @param scale
		 * @return String
		 */
		public static String getReg(final int scale) {
			if (5 < scale) {
				return null;
			} else if (0 == scale) {
				return "^\\d{1,15}$";
			}
			int length = 16 - scale;
			return "^\\d{1," + length + "}(\\.\\d{0," + scale + "})?$";
		}

		/**
		 * ����������ʽ
		 * 
		 * @param integer
		 *            ��������λ��
		 * @param decimal
		 *            С������λ����С������λ����
		 * @return String
		 */
		public static String getReg(final int integer, final int decimal) {
			if (0 >= integer) {
				return null;
			}
			if (0 >= decimal) {
				return "^\\d{1," + integer + "}$";
			}
			return "^\\d{1," + integer + "}(\\.\\d{0," + decimal + "})?$";
		}

		/**
		 * ȫ�־�ֻ̬��������λС����������������<br>
		 * 
		 */
		public static final String REGEXP_POSITIVE_DOUBLE = "^\\d*(\\.)?(\\d)?(\\d)?$";

		/**
		 * ����������ĸ�»���
		 */
		public static final String TEXT = "^[a-zA-Z0-9_/u4e00-/u9fa5]+$";
		public static final String REGEXP_POSITIVE_DOUBLE_FUSHU_LIMIT = "^([+-]?\\d{0,15}(\\.\\d{0,2})?)$";

		/**
		 * ȫ�־�̬�̻��ʹ�����֤ ���ܸ�ʽ XXXX-XXXX-XXX ����XXXX�������֣������޳���
		 */
		public static final String REGEXP_PHONE = "(^\\d*)([+-]?)(\\d*)([+-]?)(\\d*$)";

		/**
		 * ȫ�־�ֻ̬��������λС����������������,���������Ϊ8λ��С�������Ϊ2λ<br>
		 */
		public static final String NUM_TEN_TWO = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";
		/**
		 * �ۿ۶��5��2��0%-100%
		 */
		public static final String NUM_FIVE_TWO = "(^(\\d)?(\\d)?(\\.)(\\d)?(\\d)?$)|(^(\\d)?(\\d)?$)|(^[1][0][0]$)";
		/**
		 * ȫ�־�̬�ʱ�<br>
		 * 
		 */
		public static final String REGEXP_POSTCODE = "^[0-9](\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$";

		/**
		 * ȫ�־�̬�ֻ�<br>
		 * 
		 */
		public static final String REGEXP_mob = "^[1-9](\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$";

		/**
		 * ȫ�־�ֻ̬��������λ������λС����������������<br>
		 * 
		 */
		public static final String REGEXP_POSITIVE_FIVE_DOUBLE = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";

		/**
		 * ȫ�־�ֻ̬��������λС����������������,���������Ϊ8λ��С�������Ϊ2λ<br>
		 */
		public static final String NUM_EIGHT_TWO = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";

		/**
		 * ȫ�־�ֻ̬����������
		 */
		public static final String REGEXP_NUM = "^[0-9]+$";

		/**
		 * ȫ�־�ֻ̬���������ֺ���ĸ
		 */
		public static final String ENGLISH_AND_NUM = "^[A-Za-z0-9]+$";
		/**
		 * ȫ�־�ֻ̬������ʱ���ʽ
		 */
		public static final String TIEM_FORAMT = "^([0-9]?[0-9]?(\\:)?[0-5]?[0-9]$)?";
		/**
		 * ȫ�־�̬����У��
		 */
		public static final String Mail = "(\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*;)*";
		// public static final String Mail="^[A-Za-z0-9_]@[A-Za-z].[A-Za-z]+$";

		/**
		 * ȫ�־�ֻ̬������һλС����������������,���������Ϊ15λ��û��С������<br>
		 */
		public static final String REGEXP_POSITIVE_ZERO_LIMIT = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";

		/**
		 * ȫ�־�ֻ̬������һλС����������������,���������Ϊ15λ��С�������Ϊ1λ<br>
		 */
		public static final String REGEXP_POSITIVE_ONE_LIMIT = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";
		/**
		 * ȫ�־�ֻ̬��������λС����������������,���������Ϊ15λ��С�������Ϊ2λ<br>
		 */
		public static final String REGEXP_POSITIVE_DOUBLE_LIMIT = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";
		/**
		 * ȫ�־�ֻ̬��������λС����������������,���������Ϊ15λ��С�������Ϊ3λ<br>
		 */
		public static final String REGEXP_POSITIVE_THREE_LIMIT = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?(\\d)?(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";
		/**
		 * ȫ�־�ֻ̬��������λС����������������,���������Ϊ15λ��С�������Ϊ4λ<br>
		 */
		public static final String REGEXP_POSITIVE_FOUR_LIMIT = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?(\\d)?(\\d)?(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";

		/**
		 * Сʱ������ʽ
		 */
		public static final String REGEXP_HOUR = "(^[0-9]$)|(^[0-1](\\d)?$)|(^[2][0-3]?$)";
		/**
		 * ��������ʽ
		 */
		public static final String REGEXP_MIN = "(^[0-9]$)|(^[0-5](\\d)?$)";

	}

	/**
	 * �������ܣ��̶���Կ
	 */
	String CommonSecretKey = "spark4";

	/**
	 * ��ֵ��ǰ׺
	 */
	String CardNoPrefix = "7888";

	/**
	 * ��Ʒ�������ں�����
	 */
	Long GiftLifeLength = 30 * 24 * 60 * 60000l;

	/**
	 * �ͻ����ŵ���id
	 */
	GUID DeliveryPriceRecid = GUID.emptyID;

	int TablePageSize = 10;

	interface PayInfo {
		String UnionPayUrl = "https://payment.chinapay.com/pay/TransGet"; 
		String UnionPayUserNo = "808080580108014";
		String UnionPayBgRetUrl = "/front/payment/paidUnionPay";
		String UnionPayQueryUrl = "http://control.chinapay.com/QueryWeb/processQuery.jsp";

		String AliPayUrl = "https://mapi.alipay.com/gateway.do"; 
		String AliPayServiceName = "create_direct_pay_by_user";
		String AliPaySafekey = "hwancixvwh3cn8smqlhxgu6lz0prq06q";//��ȫ������
		String AliPayPartner = "2088801170178021";//������ 2088��ͷ
		String AliPaySellerEmail ="whqhshg@126.com";//֧�����˺�id,�����û�������������
		String AliPayBgRetUrl = "/front/payment/paidAliPay";
		String AliPayErrorBgRetUrl = "/front/payment/errorAliPay";
		
		
		
		String PayChargePageRetUrl = "/front/getBalance";
		String PayOrderPageRetUrl = "/front/order/getOrders";
	}
}
