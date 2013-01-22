/**
 * 
 */
package com.spark.cms.services.freeDelivery.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.base.common.utils.CheckIsNull;
import com.spark.base.common.utils.DateUtil;
import com.spark.base.common.utils.DoubleUtil;
import com.spark.base.hibernate.GenericDAO;
import com.spark.base.type.GUID;
import com.spark.cms.base.constant.card.CMS;
import com.spark.cms.base.utils.BeanCopy;
import com.spark.cms.common.DataModel;
import com.spark.cms.dao.po.DeliveryPricePo;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.form.Login;
import com.spark.cms.services.form.delivery.DeliveryForm;
import com.spark.cms.services.freeDelivery.DeliveryPriceService;
import com.spark.cms.services.freeDelivery.GetDeliveryListKey;
import com.spark.cms.services.vo.DeliveryPriceVo;

/**
 * @author Jideas
 * 
 */
@Service
public class DeliveryPriceServiceImpl implements DeliveryPriceService {

	@Autowired
	private GenericDAO baseDAO;

	/*
	 * (non-Javadoc)保存单价
	 * 
	 * @see com.spark.cms.services.freeDelivery.DeliveryPriceService#createPurPrice(double)
	 */
	@Override
	public ServiceMessage createPurPrice(double price, Login login) {
		this.baseDAO.execteBulk("delete DeliveryPricePo as t where t.recid=?", CMS.DeliveryPriceRecid.toBytes());
		DeliveryPricePo po = new DeliveryPricePo();
		po.setRecid(CMS.DeliveryPriceRecid.toBytes());
		po.setDprice(price);
		po.setDcount(1);
		po.setLastModify(login.getName());
		po.setLastModifyDate(new Date());
		po.setSingle(true);
		this.baseDAO.save(po);
		return new ServiceMessage(true, "送货上门单价保存成功！");
	}

	/*
	 * (non-Javadoc)得到单价
	 * 
	 * @see com.spark.cms.services.freeDelivery.DeliveryPriceService#getPrice()
	 */
	@Override
	public Double getPrice() {
		StringBuilder hql = new StringBuilder();
		hql.append(" from DeliveryPricePo as t ");
		hql.append(" where t.single=? ");
		hql.append(" and t.recid=?");
		List<DeliveryPricePo> list = this.baseDAO.getGenericByHql(hql.toString(), true, CMS.DeliveryPriceRecid
				.toBytes());
		if (null == list || list.isEmpty()) {
			return null;
		} else {
			return list.get(0).getDprice();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.freeDelivery.DeliveryPriceService#findDeliveryPrice(java.lang.String)
	 */
	@Override
	public DeliveryForm findDeliveryPrice(String recid) {
		DeliveryPricePo po = this.baseDAO.get(DeliveryPricePo.class, GUID.valueOf(recid).toBytes());
		if (po == null) {
			return null;
		}
		return getForm(BeanCopy.copy(DeliveryPriceVo.class, po));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.freeDelivery.DeliveryPriceService#getDeliveryPriceList()
	 */
	@Override
	public DataModel<DeliveryForm> getDeliveryPriceList(GetDeliveryListKey key) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from DeliveryPricePo as t ");
		hql.append(" where t.single=?");
		if (null != key.getIsOnlyActiving()) {
			hql.append(" and t.activing=?");
		}
		hql.append(" order by t.activing ,t.dcount");
		int count = 0;
		List<DeliveryPricePo> polist = null;
		if (null != key.getIsOnlyActiving()) {
			count = Integer.parseInt(this.baseDAO.getGenericByHql("select count(t.recid) " + hql.toString(), false,
					key.getIsOnlyActiving()).get(0)
					+ "");
			if (key.getPageSize() == 0) {
				polist = this.baseDAO.getGenericByHql(hql.toString(), false, key.getIsOnlyActiving());
			} else {
				polist = this.baseDAO.getGenericByPosition(hql.toString(), (key.getOffset() - 1) * key.getPageSize(),
						key.getPageSize(), false, key.getIsOnlyActiving());
			}
		} else {
			count = Integer.parseInt(this.baseDAO.getGenericByHql("select count(t.recid) " + hql.toString(), false)
					.get(0)
					+ "");
			if (key.getPageSize() == 0) {
				polist = this.baseDAO.getGenericByHql(hql.toString(), false);
			} else {
				polist = this.baseDAO.getGenericByPosition(hql.toString(), (key.getOffset() - 1) * key.getPageSize(),
						key.getPageSize(), false);
			}
		}
		List<DeliveryPriceVo> volist = BeanCopy.copys(DeliveryPriceVo.class, polist);
		List<DeliveryForm> list = new ArrayList<DeliveryForm>();
		for (int i = 0; i < volist.size(); i++) {
			list.add(getForm(volist.get(i)));
		}
		return new DataModel<DeliveryForm>(list, count);
	}

	private DeliveryForm getForm(DeliveryPriceVo vo) {
		DeliveryForm form = new DeliveryForm();
		form.setRecid(vo.getRecid());
		form.setDcount(DoubleUtil.strToQfwStr(vo.getDcount() + "", 0));
		form.setDprice(DoubleUtil.getRoundStr(vo.getDprice()));
		form.setLastModify(vo.getLastModify());
		form.setLastModifyDate(DateUtil.dateFromat(vo.getLastModifyDate()));
		form.setRemark(vo.getRemark());
		form.setStatus(vo.isActiving() ? "启用中" : "已停用");
		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.freeDelivery.DeliveryPriceService#createOrModify(com.spark.cms.services.vo.DeliveryPriceVo,
	 *      com.spark.cms.services.form.Login)
	 */
	@Override
	public ServiceMessage createOrModify(DeliveryPriceVo vo, Login login) {
		if (!checkCouldSave(vo)) {
			return new ServiceMessage(true, "该送货次数已有现成方案，请检查");
		}
		boolean isCreate = CheckIsNull.isEmpty(vo.getRecid());
		vo.setSingle(false);
		vo.setLastModify(login.getName());
		vo.setLastModifyDate(new Date());
		if (isCreate) {
			vo.setRecid(GUID.randomID().toString());
		} else {
			this.baseDAO.delete(DeliveryPricePo.class, GUID.valueOf(vo.getRecid()).toBytes());
		}
		this.baseDAO.save(BeanCopy.copy(DeliveryPricePo.class, vo));
		return new ServiceMessage(true, "操作成功！");
	}

	/**
	 * @param vo
	 */
	private boolean checkCouldSave(DeliveryPriceVo vo) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from DeliveryPricePo as t ");
		hql.append(" where t.dcount=?");
		List<DeliveryPricePo> polist = this.baseDAO.getGenericByHql(hql.toString(), vo.getDcount());
		for (DeliveryPricePo po : polist) {
			if (GUID.valueOf(po.getRecid()).toString().equals(vo.getRecid())) {
				continue;
			}
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.freeDelivery.DeliveryPriceService#activeDeliveryPrice(java.lang.String,
	 *      com.spark.cms.services.form.Login)
	 */
	@Override
	public ServiceMessage exeActiveDeliveryPrice(String recid, Login login) {
		DeliveryPricePo po = this.baseDAO.get(DeliveryPricePo.class, GUID.valueOf(recid).toBytes());
		if (null == po) {
			return new ServiceMessage(false, "送货上门方案已不存在！");
		}
		DeliveryPriceVo vo = BeanCopy.copy(DeliveryPriceVo.class, po);
		vo.setActiving(true);
		return this.createOrModify(vo, login);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.freeDelivery.DeliveryPriceService#stopDeliveryPrice(java.lang.String,
	 *      com.spark.cms.services.form.Login)
	 */
	@Override
	public ServiceMessage exeStopDeliveryPrice(String recid, Login login) {
		DeliveryPricePo po = this.baseDAO.get(DeliveryPricePo.class, GUID.valueOf(recid).toBytes());
		if (null == po) {
			return new ServiceMessage(false, "送货上门方案已不存在！");
		}
		DeliveryPriceVo vo = BeanCopy.copy(DeliveryPriceVo.class, po);
		vo.setActiving(false);
		return this.createOrModify(vo, login);
	}

	/*
	 * (non-Javadoc)
	 * @see com.spark.cms.services.freeDelivery.DeliveryPriceService#findDelivery(java.lang.String)
	 */
	@Override
	public DeliveryPriceVo findDelivery(String recid) {
		DeliveryPricePo po = this.baseDAO.get(DeliveryPricePo.class, GUID.valueOf(recid).toBytes());
		if (po == null) {
			return null;
		}
		return BeanCopy.copy(DeliveryPriceVo.class, po);
	}
}
