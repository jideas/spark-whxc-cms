/**
 * 
 */
package com.spark.cms.services.email;

import com.spark.cms.services.ServiceMessage;

/**
 * @author Jideas
 * 
 */
public interface EmailService {

	/**
	 * �����ʼ�
	 */
	ServiceMessage sendEmail(String mailTo, String subject, String htmlText);

}
