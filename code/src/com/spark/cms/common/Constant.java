package com.spark.cms.common;

public class Constant {
	public static String TRUE = "1";
	public static String FALSE = "0";
	public static final int MAX_PASSWORD = 30;// ����û����볤��

	public static final String Login_CheckWord = "kaptcha";
	public static final String LoginAdminUser = "Login_User_Info";
	public static final String Login = "Login_User";
	public static final String LoginMemberUser = "Login_Member_Info";
	
	/**
	 * ���ﳵ�Ƽ���Ŀ
	 */
	public static final String SHOPPINGCAR_CHANNELID = "D6BBD2F3C2088D76041170E7B7FB8CB7";
	/**
	 * ����ҳ������ĿһID
	 */
	public static final String SECOND_PAGE_CHANNELID_ONE = "9A240FB283AA41A0BA4F2A9382A022FB";
	/**
	 * ����ҳ������Ŀ��ID
	 */
	public static final String SECOND_PAGE_CHANNELID_TWO = "CCE90998A939D536C0FBCA0084FCDDB9";
	/**
	 * ������Ʒ��ĿID
	 */
	public static final String VANTAGE_MALL_CHANNELID = "1EC49AAAE022B1307E404BF201F5A64E";
	/**
	 * �ؼ�����ĿID
	 */
	public static final String SPECIAL_OFFER_CHANNELID = "0A2A5FA925D9C85B3D20396032AD1F37";
	/**
	 * һԪר����ĿID
	 */
	public static final String ONE_YUAN_CHANNELID = "CFDE496A34149599CA4AE7CC4DED1BD5";
	/**
	 * �����̳�·��
	 */
	public static final String VANTAGE_MALL_URL = "/front/vantageGoods";
	/**
	 * �ؼ���Ʒ·��
	 */
	public static final String SPECIAL_OFFER_URL = "/front/specialGoods";
	/**
	 * һԪר��·��
	 */
	public static final String ONE_YUAN_URL = "/front/oneyuanGoods";
	
	public static final int CATEGORYNO_EXIST = 1;
	
	public static final int CATEGORYNAME_EXIST = 2;
	
	public static final int NOT_EXIST = 0;
	
	public static enum MainMenu {
		home("sevenhome", ""),
		specialoffer("0A2A5FA925D9C85B3D20396032AD1F37", "/front/specialGoods"),
		vantagemall("1EC49AAAE022B1307E404BF201F5A64E", "/front/vantageGoods"),
		joinus("71B5B7B5DA0547ABBCB8DA9E33B49019", "/pub/sub/joinus.jsp"),
		oneyuan("CFDE496A34149599CA4AE7CC4DED1BD5", "/front/oneyuanGoods");
		
		private String channelId;
		private String url;
		
		private MainMenu(String channelId, String url) {
			this.channelId = channelId;
			this.url = url;
		}
		
		public String getChannelId() {
			return this.channelId;
		}
		
		public String getUrl() {
			return this.url;
		}
	}
	/**
	 * һԪר���۸�����
	 */
	public static final double ONE_YUAN_UPPER_LIMIT = 2.0;
	/**
	 * ����������Ʒ��ʾ����
	 */
	public static final int MAX_HOTSALE_SHOWCOUNT = 20;
	
	public final static class OrderEnum {
		public enum PayType {
			Balance("1", "���֧��"), Online("2", "����֧��");

			private String code, name;

			private PayType(String code, String name) {
				this.code = code;
				this.name = name;
			}

			public String getCode() {
				return code;
			}

			public String getName() {
				return name;
			}

			public static PayType getPayType(String code) {
				if (Balance.getCode().equals(code)) {
					return Balance;
				} else if (Online.getCode().equals(code)) {
					return Online;
				}
				return null;
			}

		}
	}

	public final static class MemberEnum {
		public enum MemberStatus {
			UnActive("01", "δ����"), Actived("02", "����"), Stoped("03", "ͣ��");

			private String code, name;

			private MemberStatus(String code, String name) {
				this.code = code;
				this.name = name;
			}

			public String getCode() {
				return code;
			}

			public String getName() {
				return name;
			}

			public static MemberStatus getStatus(String code) {
				if (UnActive.getCode().equals(code)) {
					return UnActive;
				} else if (Actived.getCode().equals(code)) {
					return Actived;
				} else if (Stoped.getCode().equals(code)) {
					return Stoped;
				}
				return null;
			}

		}

		public enum DealingsType {
			Card("01", "��ֵ��"), Charge("02", "��ֵ"), Consume("03", "����"), Refund(
					"04", "�˿�"), CardExtra("05", "��ֵ����");

			private String code, name;

			private DealingsType(String code, String name) {
				this.code = code;
				this.name = name;
			}

			public String getCode() {
				return code;
			}

			public String getName() {
				return name;
			}

			public static DealingsType getType(String code) {
				if (Card.getCode().equals(code)) {
					return Card;
				} else if (Charge.getCode().equals(code)) {
					return Charge;
				} else if (Consume.getCode().equals(code)) {
					return Consume;
				} else if (Refund.getCode().equals(code)) {
					return Refund;
				} else if (CardExtra.getCode().equals(code)) {
					return CardExtra;
				}
				return null;
			}

		}

		public enum VantagesType {
			Card("01", "��ֵ��"),
			Handsel("02","����"),
			Consume("03", "����"),Clear("05", "ϵͳ����"), Refund("04", "�˻�");

			private String code, name;

			private VantagesType(String code, String name) {
				this.code = code;
				this.name = name;
			}

			public String getCode() {
				return code;
			}

			public String getName() {
				return name;
			}

			public static VantagesType getType(String code) {
				if (Card.getCode().equals(code)) {
					return Card;
				} else if(Clear.getCode().equals(code)) {
					return Clear;
				} else if (Consume.getCode().equals(code)) {
					return Consume;
				} else if (Refund.getCode().equals(code)) {
					return Refund;
				} else if (Handsel.getCode().equals(code)) {
					return Handsel;
				}
				return null;
			}

		}

		public enum Sex {
			Male("01", "��"), Female("02", "Ů"), Others("03", "����");

			private String code, name;

			private Sex(String code, String name) {
				this.code = code;
				this.name = name;
			}

			public String getCode() {
				return code;
			}

			public String getName() {
				return name;
			}

			public static Sex getSex(String code) {
				if (Male.getCode().equals(code)) {
					return Male;
				} else if (Female.getCode().equals(code)) {
					return Female;
				} else if (Others.getCode().equals(code)) {
					return Others;
				}
				return null;
			}
		}

		public enum MaritalStatus {
			Unmarried("01", "δ��"), Married("02", "�ѻ�"), Others("03", "����");

			private String code, name;

			private MaritalStatus(String code, String name) {
				this.code = code;
				this.name = name;
			}

			public String getCode() {
				return code;
			}

			public String getName() {
				return name;
			}

			public static MaritalStatus getMaritalStatus(String code) {
				if (Unmarried.getCode().equals(code)) {
					return Unmarried;
				} else if (Married.getCode().equals(code)) {
					return Married;
				} else if (Others.getCode().equals(code)) {
					return Others;
				}
				return null;
			}
		}

		public enum LivingConditions {
			Renting("01", "�ⷿ"), PrivateHouses("02", "�з�"), Others("03", "����");

			private String code, name;

			private LivingConditions(String code, String name) {
				this.code = code;
				this.name = name;
			}

			public String getCode() {
				return code;
			}

			public String getName() {
				return name;
			}

			public static LivingConditions getLivingConditions(String code) {
				if (Renting.getCode().equals(code)) {
					return Renting;
				} else if (PrivateHouses.getCode().equals(code)) {
					return PrivateHouses;
				} else if (Others.getCode().equals(code)) {
					return Others;
				}
				return null;
			}
		}
	}

	public final static class ChannelEnum {
		public enum PageType {
			HomePage("01", "��ҳ����"), SecondPage("02", "����ҳ�����"), MainMenu(
					"0101", "���˵�");

			private String code, name;

			private PageType(String code, String name) {
				this.code = code;
				this.name = name;
			}

			public String getCode() {
				return code;
			}

			public String getName() {
				return name;
			}

			public static PageType getPageType(String code) {
				if (HomePage.getCode().equals(code)) {
					return HomePage;
				} else if (SecondPage.getCode().equals(code)) {
					return SecondPage;
				}
				return null;
			}
		}

		public enum FloorType {
			Goods("01", "��Ʒ¥"), Advertise("02", "���¥");
			// Others("03","��¥");

			private String code, name;

			private FloorType(String code, String name) {
				this.code = code;
				this.name = name;
			}

			public String getCode() {
				return code;
			}

			public String getName() {
				return name;
			}

			public static FloorType getFlorType(String code) {
				if (Goods.getCode().equals(code)) {
					return Goods;
				} else if (Advertise.getCode().equals(code)) {
					return Advertise;
				}
				// else if(Others.getCode().equals(code))
				// {
				// return Others;
				// }
				return null;
			}
		}

		public enum ChannelType {
			Goods("01", "��Ʒ"), Advertise("02", "���"), Content("03", "����");

			private String code, name;

			private ChannelType(String code, String name) {
				this.code = code;
				this.name = name;
			}

			public String getCode() {
				return code;
			}

			public String getName() {
				return name;
			}

			public static ChannelType getFlorType(String code) {
				if (Goods.getCode().equals(code)) {
					return Goods;
				} else if (Advertise.getCode().equals(code)) {
					return Advertise;
				} else if (Content.getCode().equals(code)) {
					return Content;
				}
				return null;
			}
		}

		public enum Theme {
			Forestgreen("01", "ɭ����", "sort-bg01.png", "#22a618"), Darkorange(
					"02", "�ٻ�ɫ", "sort-bg02.png", "#fba040"), Yellow("03",
					"�����", "sort-bg03.png", "#ffb847"), Mediumblue("04", "����ɫ",
					"sort-bg04.png", "#009807"), Mediumseagreen("05", "�к���",
					"sort-bg05.png", "#a2db4a"),

			Goldenrod("06", "������ɫ", "sort-bg06.png", "#e75c1f"), Freshgreen(
					"07", "������", "sort-bg07.png", "#02b3bf"), Orange("08",
					"��ɫ", "sort-bg08.png", "#f99200"), Peru("09", "��³ɫ",
					"sort-bg09.png", "#c75624"), Royalblue("10", "�ʼ���",
					"sort-bg010.png", "#5471cf"),

			Crazyred("11", "�����", "sort-bg011.png", "#e74747");

			private String code, name, fileName, borderColor;

			private Theme(String code, String name, String fileName,
					String borderColor) {
				this.code = code;
				this.name = name;
				this.fileName = fileName;
				this.borderColor = borderColor;
			}

			public String getFileName() {
				return fileName;
			}

			public String getCode() {
				return code;
			}

			public String getName() {
				return name;
			}

			public String getBorderColor() {
				return borderColor;
			}

			public static Theme getTheme(String code) {
				if (Forestgreen.getCode().equals(code)) {
					return Forestgreen;
				} else if (Darkorange.getCode().equals(code)) {
					return Darkorange;
				} else if (Yellow.getCode().equals(code)) {
					return Yellow;
				} else if (Mediumblue.getCode().equals(code)) {
					return Mediumblue;
				} else if (Mediumseagreen.getCode().equals(code)) {
					return Mediumseagreen;
				} else if (Goldenrod.getCode().equals(code)) {
					return Goldenrod;
				} else if (Freshgreen.getCode().equals(code)) {
					return Freshgreen;
				} else if (Orange.getCode().equals(code)) {
					return Orange;
				} else if (Peru.getCode().equals(code)) {
					return Peru;
				} else if (Royalblue.getCode().equals(code)) {
					return Royalblue;
				} else if (Crazyred.getCode().equals(code)) {
					return Crazyred;
				}
				return null;
			}
		}
	}

	public enum Status {
		Unreleased("01", "δ����"), Released("02", "�ѷ���"), Others("03", "����");

		private String code, name;

		private Status(String code, String name) {
			this.code = code;
			this.name = name;
		}

		public String getCode() {
			return code;
		}

		public String getName() {
			return name;
		}

		public static Status getStatus(String code) {
			if (Unreleased.getCode().equals(code)) {
				return Unreleased;
			} else if (Released.getCode().equals(code)) {
				return Released;
			} else if (Others.getCode().equals(code)) {
				return Others;
			}
			return null;
		}
	}

	public static final class Order {
		/**
		 * 
		 * <p>
		 * ���϶���״̬
		 * </p>
		 * 
		 */
		public enum OnlineOrderStatus {

			/**
			 * ������
			 */
			Paying("01", "������"),
			/**
			 * ����Ч
			 */
			Effected("02", "�Ѹ���"),
			/**
			 * �����
			 */
			Picking("03", "�����"),
			/**
			 * ������
			 */
			Delivering("04", "������"),
			/**
			 * �ѵ���
			 */
			Arrivaled("05", "�ѵ���"),
			/**
			 * ���
			 */
			Finished("06", "�����");

			final String code, name;

			public String getCode() {
				return code;
			}

			public String getName() {
				return name;
			}

			private OnlineOrderStatus(String code, String name) {
				this.code = code;
				this.name = name;
			}

			public static OnlineOrderStatus getStatus(String code) {
				if (code.equals(OnlineOrderStatus.Paying.getCode())) {
					return OnlineOrderStatus.Paying;
				} else if (code.equals(OnlineOrderStatus.Arrivaled.getCode())) {
					return OnlineOrderStatus.Arrivaled;
				} else if (code.equals(OnlineOrderStatus.Delivering.getCode())) {
					return OnlineOrderStatus.Delivering;
				} else if (code.equals(OnlineOrderStatus.Effected.getCode())) {
					return OnlineOrderStatus.Effected;
				} else if (code.equals(OnlineOrderStatus.Finished.getCode())) {
					return OnlineOrderStatus.Finished;
				} else if (code.equals(OnlineOrderStatus.Picking.getCode())) {
					return OnlineOrderStatus.Picking;
				} else {
					return null;
				}
			}

		}

		/**
		 * 
		 * <p>
		 * ���϶�������
		 * </p>
		 * 
		 */
		public enum OnlineOrderType {

			Common("��ͨ����", "0"), Booking("Ԥ������", "1");

			final String name;
			private String code;

			public String getCode() {
				return code;
			}

			public String getName() {
				return name;
			}

			private OnlineOrderType(String name, String code) {
				this.name = name;
				this.code = code;
			}

			public static OnlineOrderType getType(String code) {
				if (Common.code.equals(code)) {
					return Common;
				} else if (Booking.code.equals(code)) {
					return Booking;
				}
				return null;
			}
		}
	}

	public static final class Base {
		public enum GoodsType {

			Common("��ͨ", "0"), Booking("Ԥ��", "1");

			final String name;
			private String code;

			public String getCode() {
				return code;
			}

			public String getName() {
				return name;
			}
			
			public String toJsonString() {
				return "{'code':'" + code + "', 'name':'" + name + "'}";
			}

			private GoodsType(String name, String code) {
				this.name = name;
				this.code = code;
			}

			public static GoodsType getType(String code) {
				if (Common.code.equals(code)) {
					return Common;
				} else if (Booking.code.equals(code)) {
					return Booking;
				}
				return null;
			}
		}
	}
}
