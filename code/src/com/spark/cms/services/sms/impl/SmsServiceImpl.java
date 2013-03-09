/**
 * 
 */
package com.spark.cms.services.sms.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.base.common.utils.CheckIsNull;
import com.spark.cms.base.constant.card.CMS;
import com.spark.cms.base.utils.encrypt.EncryptionUtil;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.sms.SmsConfigService;
import com.spark.cms.services.sms.SmsService;
import com.spark.cms.services.sms.utils.SmsSendTask;
import com.spark.cms.services.sms.utils.SmsSendTask.ReturnFlag;
import com.spark.cms.services.vo.SmsConfigVo;

/**
 * @author Jideas
 * 
 */
@Service
public class SmsServiceImpl implements SmsService {

	private static SmsConfigVo config = null;

	@Autowired
	private SmsConfigService configService;

	private boolean initConfig(boolean compel) {
		if (compel || config == null) {
			config = configService.findVo();
			try {
				config.setSecretKey(EncryptionUtil.decryptAES(config.getSecretKey(), CMS.CommonSecretKey));
				config.setUserName(EncryptionUtil.decryptAES(config.getUserName(), config.getSecretKey()));
				String password = EncryptionUtil.decryptAES(config.getPassword(), CMS.CommonSecretKey);
				config.setPassword(EncryptionUtil.decryptAES(password, config.getSecretKey()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (null == config || config.isActiving()) {
				return false;
			}
		}
		return true;
	}

	public ServiceMessage getBalance() {
		if (!initConfig(false)) {
			return new ServiceMessage(false, "系统发送短信功能已暂停！");
		}
		try {
			String parameter = "";
			if (CheckIsNull.isNotEmpty(config.getCompAccountKey())) {
				parameter = config.getCompAccountKey() + "=" + config.getCompAccount() + "&" + config.getUserNameKey()
						+ "=" + config.getUserName() + "&" + config.getPasswordKey() + "="
						+ EncryptionUtil.encryptMD5(config.getPassword()).toLowerCase();
			} else {
				parameter = config.getUserNameKey() + "=" + config.getUserName() + "&" + config.getPasswordKey() + "="
						+ EncryptionUtil.encryptMD5(config.getPassword());
			}
			StringBuilder sb = new StringBuilder();
			URL urls = new URL(config.getBalanceUrl());
			HttpURLConnection uc = (HttpURLConnection) urls.openConnection();
			uc.setRequestMethod("POST");
			uc.setRequestProperty("content-type", "application/x-www-form-urlencoded");
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 设置超时时间
			System.setProperty("sun.net.client.defaultReadTimeout", "30000");
			uc.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(uc.getOutputStream());
			writer.write(parameter);
			writer.flush();
			writer.close();
			BufferedReader read;
			read = new BufferedReader(new InputStreamReader(uc.getInputStream()));
			String str;
			while ((str = read.readLine()) != null) {
				sb.append(str.trim());
			}
			read.close();// 关闭读取流
			// ReturnMessage msg =
			// XmlParseUtil.getInstance().parseXml(sb.toString());
			return new ServiceMessage(true, "");
			// return new ServiceMessage(true, "该账号剩余短信条数：" +
			// sb.toString().substring(sb.toString().indexOf("#") + 1)
			// + "条。");
		} catch (Exception e) {
			e.printStackTrace();
			return new ServiceMessage(false, "系统短信服务故障！");
		}
	}

	@SuppressWarnings("deprecation")
	public boolean cannotSendMsg() {
		Date date = new Date();
		if (date.getHours() >= 0 && date.getHours() < 24) {
			return false;
		}
		return true;
	}

	private boolean cannotGoOn(SmsSendTask task) {
		if (cannotSendMsg()) {
			return true;
		}
		if (CheckIsNull.isEmpty(task.getPhoneNo())) {
			return true;
		}
		if (CheckIsNull.isEmpty(task.getMessage())) {
			return true;
		}
		if (task.getMessage().length() > 60) {
			return true;
		}
		return false;
	}

	public ServiceMessage sendMsg(SmsSendTask task) {
		if (!initConfig(false) || cannotGoOn(task)) {
			return new ServiceMessage(false, "系统发送短信功能已暂停！");
		}
		ReturnFlag flag = doRealSend(task);
		if (flag != ReturnFlag.Success) {
			return sendMsgOnceMore(task);
		}
		return new ServiceMessage(true, "系统发送短信成功！");
	}

	private ServiceMessage sendMsgOnceMore(SmsSendTask task) {
		System.out.println("准备开始发送短信。");
		if (!initConfig(true) || cannotGoOn(task)) {
			return new ServiceMessage(true, "系统发送短信功能已暂停！");
		}
		ReturnFlag flag = doRealSend(task);
		if (flag != ReturnFlag.Success) {
			return new ServiceMessage(false, flag.getTitle());
		}
		return new ServiceMessage(true, "系统发送短信成功！");
	}

	private ReturnFlag doRealSend(SmsSendTask task) {
		try {
			StringBuilder ss = new StringBuilder();
			if (CheckIsNull.isNotEmpty(config.getCompAccountKey())) {
				ss.append(config.getCompAccountKey() + "=" + config.getCompAccount());
				ss.append("&" + config.getUserNameKey() + "=" + config.getUserName());
				ss.append("&" + config.getPasswordKey() + "="
						+ EncryptionUtil.encryptMD5(config.getPassword()).toLowerCase());
				ss.append("&" + config.getPhoneNumberKey() + "=" + task.getPhoneNo());
				ss.append("&" + config.getMsgContentKey() + "="
						+ URLEncoder(task.getMessage().replace(" ", "") + "【7号生活馆】", "UTF-8"));// 【7号生活馆】杰驰
			} else {
				ss.append(config.getUserNameKey() + "=" + config.getUserName());
				ss.append("&" + config.getPasswordKey() + "=" + EncryptionUtil.encryptMD5(config.getPassword()));
				ss.append("&" + config.getPhoneNumberKey() + "=" + task.getPhoneNo());
				ss.append("&" + config.getMsgContentKey() + "=" + task.getMessage().replace(" ", "") + "【7号生活馆】");
			}
			System.out.println(ss.toString());
			// URL urls = new URL(config.getSubmitUrl());
			// HttpURLConnection uc = (HttpURLConnection) urls.openConnection();
			// uc.setRequestMethod("POST");
			// uc.setRequestProperty("content-type",
			// "application/x-www-form-urlencoded");
			// System.setProperty("sun.net.client.defaultConnectTimeout",
			// "30000");// 设置超时时间
			// System.setProperty("sun.net.client.defaultReadTimeout", "30000");
			// uc.setDoOutput(true);
			// OutputStreamWriter writer = new
			// OutputStreamWriter(uc.getOutputStream());
			// writer.write(ss.toString());
			// writer.flush();
			// writer.close();
			// BufferedReader read;
			// read = new BufferedReader(new
			// InputStreamReader(uc.getInputStream()));
			// String str;
			// StringBuilder sb = new StringBuilder();
			// while ((str = read.readLine()) != null) {
			// sb.append(str.trim());
			// }
			// read.close();// 关闭读取流
			// System.out.println(sb.toString());
			// ReturnMessage msg =
			// XmlParseUtil.getInstance().parseXml(sb.toString());
			// ReturnFlag flag = (ReturnFlag.getFlag(msg.getResult()));
			return ReturnFlag.Success;
		} catch (Exception e) {
			e.printStackTrace();
			return ReturnFlag.Failure;
		}
	}

	/**
	 * 
	 * @param str
	 *            内容
	 * @param type
	 *            编码格式 utf-8 ISO8859_1，gbk,gb2312
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws UnsupportedEncodingException
	 */
	public static String URLEncoder(String str, String type) throws UnsupportedEncodingException {
		if (str == null) {
			return str;
		}
		str = java.net.URLEncoder.encode(str, type);

		return str;
	}
}
