/**
 * 
 */
package com.spark.cms.base.constant.card;

import com.spark.base.type.GUID;

/**
 * @author Jideas 常量
 */
public interface CMS {

	public static class Reg {

		/**
		 * 最多几位小数的正则表达式
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
		 * 数字正则表达式
		 * 
		 * @param integer
		 *            整数部分位数
		 * @param decimal
		 *            小数部分位数（小数部分位数）
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
		 * 全局静态只能输入两位小数的正浮点数对象<br>
		 * 
		 */
		public static final String REGEXP_POSITIVE_DOUBLE = "^\\d*(\\.)?(\\d)?(\\d)?$";

		/**
		 * 汉字数字字母下划线
		 */
		public static final String TEXT = "^[a-zA-Z0-9_/u4e00-/u9fa5]+$";
		public static final String REGEXP_POSITIVE_DOUBLE_FUSHU_LIMIT = "^([+-]?\\d{0,15}(\\.\\d{0,2})?)$";

		/**
		 * 全局静态固话和传真验证 接受格式 XXXX-XXXX-XXX 其中XXXX代表数字，但不限长度
		 */
		public static final String REGEXP_PHONE = "(^\\d*)([+-]?)(\\d*)([+-]?)(\\d*$)";

		/**
		 * 全局静态只能输入两位小数的正浮点数对象,整数部分最长为8位，小数部分最长为2位<br>
		 */
		public static final String NUM_TEN_TWO = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";
		/**
		 * 折扣额等5（2）0%-100%
		 */
		public static final String NUM_FIVE_TWO = "(^(\\d)?(\\d)?(\\.)(\\d)?(\\d)?$)|(^(\\d)?(\\d)?$)|(^[1][0][0]$)";
		/**
		 * 全局静态邮编<br>
		 * 
		 */
		public static final String REGEXP_POSTCODE = "^[0-9](\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$";

		/**
		 * 全局静态手机<br>
		 * 
		 */
		public static final String REGEXP_mob = "^[1-9](\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$";

		/**
		 * 全局静态只能输入五位整数两位小数的正浮点数对象<br>
		 * 
		 */
		public static final String REGEXP_POSITIVE_FIVE_DOUBLE = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";

		/**
		 * 全局静态只能输入两位小数的正浮点数对象,整数部分最长为8位，小数部分最长为2位<br>
		 */
		public static final String NUM_EIGHT_TWO = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";

		/**
		 * 全局静态只能输入数字
		 */
		public static final String REGEXP_NUM = "^[0-9]+$";

		/**
		 * 全局静态只能输入数字和字母
		 */
		public static final String ENGLISH_AND_NUM = "^[A-Za-z0-9]+$";
		/**
		 * 全局静态只能输入时间格式
		 */
		public static final String TIEM_FORAMT = "^([0-9]?[0-9]?(\\:)?[0-5]?[0-9]$)?";
		/**
		 * 全局静态邮箱校验
		 */
		public static final String Mail = "(\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*;)*";
		// public static final String Mail="^[A-Za-z0-9_]@[A-Za-z].[A-Za-z]+$";

		/**
		 * 全局静态只能输入一位小数的正浮点数对象,整数部分最长为15位，没有小数部分<br>
		 */
		public static final String REGEXP_POSITIVE_ZERO_LIMIT = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";

		/**
		 * 全局静态只能输入一位小数的正浮点数对象,整数部分最长为15位，小数部分最长为1位<br>
		 */
		public static final String REGEXP_POSITIVE_ONE_LIMIT = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";
		/**
		 * 全局静态只能输入两位小数的正浮点数对象,整数部分最长为15位，小数部分最长为2位<br>
		 */
		public static final String REGEXP_POSITIVE_DOUBLE_LIMIT = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";
		/**
		 * 全局静态只能输入三位小数的正浮点数对象,整数部分最长为15位，小数部分最长为3位<br>
		 */
		public static final String REGEXP_POSITIVE_THREE_LIMIT = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?(\\d)?(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";
		/**
		 * 全局静态只能输入四位小数的正浮点数对象,整数部分最长为15位，小数部分最长为4位<br>
		 */
		public static final String REGEXP_POSITIVE_FOUR_LIMIT = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?(\\d)?(\\d)?(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";

		/**
		 * 小时正则表达式
		 */
		public static final String REGEXP_HOUR = "(^[0-9]$)|(^[0-1](\\d)?$)|(^[2][0-3]?$)";
		/**
		 * 分正则表达式
		 */
		public static final String REGEXP_MIN = "(^[0-9]$)|(^[0-5](\\d)?$)";

	}

	/**
	 * 公共加密，固定秘钥
	 */
	String CommonSecretKey = "spark4";

	/**
	 * 面值卡前缀
	 */
	String CardNoPrefix = "7888";

	/**
	 * 赠品生存周期毫秒数
	 */
	Long GiftLifeLength = 30 * 24 * 60 * 60000l;

	/**
	 * 送货上门单价id
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
		String AliPaySafekey = "hwancixvwh3cn8smqlhxgu6lz0prq06q";//安全检验码
		String AliPayPartner = "2088801170178021";//合作号 2088开头
		String AliPaySellerEmail ="whqhshg@126.com";//支付宝账号id,或者用户名，或者邮箱
		String AliPayBgRetUrl = "/front/payment/paidAliPay";
		String AliPayErrorBgRetUrl = "/front/payment/errorAliPay";
		
		
		
		String PayChargePageRetUrl = "/front/getBalance";
		String PayOrderPageRetUrl = "/front/order/getOrders";
	}
}
