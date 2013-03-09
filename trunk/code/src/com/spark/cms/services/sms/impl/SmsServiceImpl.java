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
			return new ServiceMessage(false, "ϵͳ���Ͷ��Ź�������ͣ��");
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
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// ���ó�ʱʱ��
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
			read.close();// �رն�ȡ��
			// ReturnMessage msg =
			// XmlParseUtil.getInstance().parseXml(sb.toString());
			return new ServiceMessage(true, "");
			// return new ServiceMessage(true, "���˺�ʣ�����������" +
			// sb.toString().substring(sb.toString().indexOf("#") + 1)
			// + "����");
		} catch (Exception e) {
			e.printStackTrace();
			return new ServiceMessage(false, "ϵͳ���ŷ�����ϣ�");
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
			return new ServiceMessage(false, "ϵͳ���Ͷ��Ź�������ͣ��");
		}
		ReturnFlag flag = doRealSend(task);
		if (flag != ReturnFlag.Success) {
			return sendMsgOnceMore(task);
		}
		return new ServiceMessage(true, "ϵͳ���Ͷ��ųɹ���");
	}

	private ServiceMessage sendMsgOnceMore(SmsSendTask task) {
		System.out.println("׼����ʼ���Ͷ��š�");
		if (!initConfig(true) || cannotGoOn(task)) {
			return new ServiceMessage(true, "ϵͳ���Ͷ��Ź�������ͣ��");
		}
		ReturnFlag flag = doRealSend(task);
		if (flag != ReturnFlag.Success) {
			return new ServiceMessage(false, flag.getTitle());
		}
		return new ServiceMessage(true, "ϵͳ���Ͷ��ųɹ���");
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
						+ URLEncoder(task.getMessage().replace(" ", "") + "��7������ݡ�", "UTF-8"));// ��7������ݡ��ܳ�
			} else {
				ss.append(config.getUserNameKey() + "=" + config.getUserName());
				ss.append("&" + config.getPasswordKey() + "=" + EncryptionUtil.encryptMD5(config.getPassword()));
				ss.append("&" + config.getPhoneNumberKey() + "=" + task.getPhoneNo());
				ss.append("&" + config.getMsgContentKey() + "=" + task.getMessage().replace(" ", "") + "��7������ݡ�");
			}
			System.out.println(ss.toString());
			// URL urls = new URL(config.getSubmitUrl());
			// HttpURLConnection uc = (HttpURLConnection) urls.openConnection();
			// uc.setRequestMethod("POST");
			// uc.setRequestProperty("content-type",
			// "application/x-www-form-urlencoded");
			// System.setProperty("sun.net.client.defaultConnectTimeout",
			// "30000");// ���ó�ʱʱ��
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
			// read.close();// �رն�ȡ��
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
	 *            ����
	 * @param type
	 *            �����ʽ utf-8 ISO8859_1��gbk,gb2312
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
