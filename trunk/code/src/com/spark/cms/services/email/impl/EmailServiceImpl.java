/**
 * 
 */
package com.spark.cms.services.email.impl;

import java.lang.reflect.Field;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.spark.base.common.utils.CheckIsNull;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.email.EmailService;

/**
 * @author Jideas
 * 
 */
@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender mailSender;

	private static String userName;

	public ServiceMessage sendEmail(String mailTo, String subject, String htmlText) {
		try {
			if (CheckIsNull.isEmpty(userName)) {
				initUserName();
			}
			MimeMessage msg = mailSender.createMimeMessage();
			// ����utf-8��GBK���룬�����ʼ��������룬true��ʾΪmultipart�ʼ�
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, "GBK");
			helper.setTo(mailTo); // �ʼ����յ�ַ
			helper.setFrom(userName); // �ʼ����͵�ַ,��������xml����ʼ���ַ��ͬһ��
			helper.setSubject(subject); // ����
			helper.setText(htmlText, true); // �ʼ����ݣ�����true����ʾ����html��ʽ
			mailSender.send(msg); // �����ʼ�
		} catch (Exception e) {
			e.printStackTrace();
			return new ServiceMessage(false, e.getMessage());
		}
		return new ServiceMessage(true, "�ʼ����ͳɹ���");
	}

	/**
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * 
	 */
	private void initUserName() throws SecurityException, NoSuchFieldException, IllegalArgumentException,
			IllegalAccessException {
		Field f = this.mailSender.getClass().getDeclaredField("username");
		f.setAccessible(true);
		userName = (String) f.get(this.mailSender);
		f.setAccessible(false);
	}
}
