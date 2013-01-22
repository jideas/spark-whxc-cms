/**
 * 
 */
package com.spark.cms.services.gift;

import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.vo.GiftVo;

/**
 * @author Jideas
 * 
 */
public interface GiftService {

	ServiceMessage createGift(GiftVo vo);
}
