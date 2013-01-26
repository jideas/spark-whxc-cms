/**
 * 
 */
package com.spark.cms.services.gift;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.base.common.utils.CheckIsNull;
import com.spark.base.hibernate.GenericDAO;
import com.spark.base.type.GUID;
import com.spark.cms.base.constant.gift.GiftStatus;
import com.spark.cms.base.utils.BeanCopy;
import com.spark.cms.dao.po.GiftPo;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.vo.GiftVo;

/**
 * @author Jideas
 * 
 */
@Service
public class GiftServiceImpl implements GiftService {

	@Autowired
	private GenericDAO baseDAO;

	/*
	 * (non-Javadoc)新增赠品
	 * 
	 * @see com.spark.cms.services.gift.GiftService#createGift(com.spark.cms.services.vo.GiftVo)
	 */
	@Override
	public ServiceMessage createGift(GiftVo vo) {
		this.baseDAO.save(BeanCopy.copy(GiftPo.class, vo));
		return new ServiceMessage(true, "保存成功！");
	}

	@Override
	public List<GiftVo> getList(String memberId) {
		if(CheckIsNull.isEmpty(memberId))
		{
			return null;
		}
		StringBuffer hql = new StringBuffer();
		hql.append(" from GiftPo p ");
		hql.append(" where p.memberid=? ");
		hql.append(" and p.status='").append(GiftStatus.Created.getCode()).append("'");
		List<GiftPo> pl = this.baseDAO.getGenericByHql(hql.toString(), GUID.valueOf(memberId).toBytes());
		if(CheckIsNull.isNotEmpty(pl))
		{
			return BeanCopy.copys(GiftVo.class, pl);
		}
		return null;
	}

	@Override
	public void updateStatus(String memberId) {
		if(CheckIsNull.isEmpty(memberId))
		{
			return;
		}
		StringBuffer hql = new StringBuffer();
		hql.append(" update GiftPo p ");
		hql.append(" set status='").append(GiftStatus.Taken.getCode()).append("'");
		hql.append(" where p.memberid=? ");
		hql.append(" and p.status='").append(GiftStatus.Created.getCode()).append("'");
		this.baseDAO.execteBulk(hql.toString(), GUID.valueOf(memberId).toBytes());
	}

}
