/**
 * 
 */
package com.spark.cms.services.joinus;

import com.spark.cms.common.DataModel;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.form.Login;
import com.spark.cms.services.vo.JoinSevenBillVo;

/**
 * @author Jideas
 * 
 */
public interface JoinusService {

	public DataModel<JoinSevenBillVo> getVolist(GetJoinSevenBillsKey key);

	public ServiceMessage createBill(JoinSevenBillVo vo);

	public ServiceMessage exeOperedBill(String recid, Login login);

	public JoinSevenBillVo find(String recid);
}
