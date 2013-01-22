/**
 * 
 */
package com.spark.cms.services.sms;

import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.sms.utils.SmsSendTask;

/**
 * @author Jideas
 * 
 */

public interface SmsService {
	
	ServiceMessage getBalance();

	ServiceMessage sendMsg(SmsSendTask task);
}
