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
			// 设置utf-8或GBK编码，否则邮件会有乱码，true表示为multipart邮件
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, "GBK");
			helper.setTo(mailTo); // 邮件接收地址
			helper.setFrom(userName); // 邮件发送地址,这里必须和xml里的邮件地址相同一致
			helper.setSubject(subject); // 主题
			helper.setText(htmlText, true); // 邮件内容，参数true，表示启用html格式
			mailSender.send(msg); // 发送邮件
		} catch (Exception e) {
			e.printStackTrace();
			return new ServiceMessage(false, e.getMessage());
		}
		return new ServiceMessage(true, "邮件发送成功！");
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
