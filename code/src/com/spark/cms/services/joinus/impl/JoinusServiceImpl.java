/**
 * 
 */
package com.spark.cms.services.joinus.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.base.common.utils.DateUtil;
import com.spark.base.hibernate.impl.BaseDAOImpl;
import com.spark.base.type.GUID;
import com.spark.cms.base.utils.BeanCopy;
import com.spark.cms.common.DataModel;
import com.spark.cms.dao.po.JoinSevenBillPo;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.form.Login;
import com.spark.cms.services.joinus.GetJoinSevenBillsKey;
import com.spark.cms.services.joinus.JoinusService;
import com.spark.cms.services.vo.JoinSevenBillVo;

/**
 * @author Jideas
 * 
 */
@Service
public class JoinusServiceImpl implements JoinusService {

	@Autowired
	private BaseDAOImpl baseDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.joinus.JoinusService#CreateBill(com.spark.cms.services.vo.JoinSevenBillVo)
	 */
	@Override
	public ServiceMessage createBill(JoinSevenBillVo vo) {
		vo.setCreateDate(new Date().getTime());
		vo.setRecid(GUID.randomID().toString());
		this.baseDao.save(BeanCopy.copy(JoinSevenBillPo.class, vo));
		return new ServiceMessage(true, "提交加盟申请成功！");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.joinus.JoinusService#exeOperedBill(java.lang.String,
	 *      com.spark.cms.services.form.Login)
	 */
	@Override
	public ServiceMessage exeOperedBill(String recid, Login login) {
		StringBuilder ss = new StringBuilder();
		ss.append("update JoinSevenBillPo as t set opered=true where opered=false and recid=?");
		int count = this.baseDao.execteBulk(ss.toString(), GUID.valueOf(recid).toBytes());
		if (count == 1) {
			return new ServiceMessage(true, "变更加盟申请成功！");
		}
		return new ServiceMessage(false, "变更加盟申请失败！");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.joinus.JoinusService#getVolist(com.spark.cms.services.joinus.GetJoinSevenBillsKey)
	 */
	@Override
	public DataModel<JoinSevenBillVo> getVolist(GetJoinSevenBillsKey key) {
		StringBuilder hql = new StringBuilder();
		hql.append("from JoinSevenBillPo as t ");
		if (null != key.getOnlyOpered()) {
			hql.append(" where t.opered=?");
		}
		hql.append(" order by t.opered");
		int count = 0;
		List<JoinSevenBillPo> polist = null;
		if (null != key.getOnlyOpered()) {
			count = Integer.parseInt(this.baseDao.getGenericByHql("select count(t.recid) " + hql.toString(),
					key.getOnlyOpered()).get(0)
					+ "");
			if (key.getPageSize() == 0) {
				polist = this.baseDao.getGenericByHql(hql.toString(), key.getOnlyOpered());
			} else {
				polist = this.baseDao.getGenericByPosition(hql.toString(), (key.getOffset() - 1) * key.getPageSize(),
						key.getPageSize(), key.getOnlyOpered());
			}
		} else {
			count = Integer.parseInt(this.baseDao.getGenericByHql("select count(t.recid) " + hql.toString()).get(0)
					+ "");
			if (key.getPageSize() == 0) {
				polist = this.baseDao.getGenericByHql(hql.toString());
			} else {
				polist = this.baseDao.getGenericByPosition(hql.toString(), (key.getOffset() - 1) * key.getPageSize(),
						key.getPageSize());
			}
		}
		List<JoinSevenBillVo> volist = BeanCopy.copys(JoinSevenBillVo.class, polist);
		for (JoinSevenBillVo vo : volist) {
			vo.setCreateDateStr(DateUtil.dateFromat(vo.getCreateDate()));
		}
		return new DataModel<JoinSevenBillVo>(volist, count);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.joinus.JoinusService#find(java.lang.String)
	 */
	@Override
	public JoinSevenBillVo find(String recid) {
		JoinSevenBillPo po = this.baseDao.get(JoinSevenBillPo.class, GUID.valueOf(recid).toBytes());
		if (null == po) {
			return null;
		}
		JoinSevenBillVo vo = BeanCopy.copy(JoinSevenBillVo.class, po);
		vo.setCreateDateStr(DateUtil.dateFromat(vo.getCreateDate()));
		return vo;
	}

}
