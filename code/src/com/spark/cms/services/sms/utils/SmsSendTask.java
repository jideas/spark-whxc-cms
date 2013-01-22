package com.spark.cms.services.sms.utils;

/**
 * 手机短信发送
 */
public class SmsSendTask {

	public SmsSendTask() {

	}

	private String phoneNo;

	private String message; 

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	} 

	public enum ReturnFlag {

		Success("0", "无错误，提交成功"),

		NullContant("1", "用户名、密码、手机号、短信内容其中一项为空"),

		LoginError("2", "用户名或者密码错误"),

		NeedMoney("3", "账户余额不足"),

		WrongPhoneNo("4", "手机号码格式错误"),

		MessageTooLong("5", "短信内容字数超过限制"),

		IllegalCharacter("6", "提交内容包含非法字符"),

		PhoneTooMany("7", "手机号码个数超过限制"),

		NetError("8", "网络故障导致信息提交失败"),

		WrongCharset("9", "不支持的字符集"),

		Failure("-1", "提交失败"),

		NotActiving("-2", "系统已停用发送短信功能！"),

		NullPhoneNumber("-3", "未提供手机号码"),

		NullMessage("-4", "不允许发送空消息！"),

		TimeOut("-5", "当前短信服务器已停止服务！");

		private String code;
		private String title;

		private ReturnFlag(String code, String title) {
			this.code = code;
			this.title = title;
		}

		public String getCode() {
			return code;
		}

		public String getTitle() {
			return title;
		}

		public boolean isSuccess() {
			return this == Success;
		}

		public static ReturnFlag getFlag(String code) {
			if (null == code || "".equals(code)) {
				return null;
			}
			int i = Integer.parseInt(code);
			switch (i) {
			case -5:
				return TimeOut;
			case -4:
				return NullMessage;
			case -3:
				return NullPhoneNumber;
			case -2:
				return NotActiving;
			case -1:
				return Failure;
			case 0:
				return Success;
			case 1:
				return NullContant;
			case 2:
				return LoginError;
			case 3:
				return NeedMoney;
			case 4:
				return WrongPhoneNo;
			case 5:
				return MessageTooLong;
			case 6:
				return IllegalCharacter;
			case 7:
				return PhoneTooMany;
			case 8:
				return NetError;
			case 9:
				return WrongCharset;

			default:
				return null;
			}
		}
	}

}
