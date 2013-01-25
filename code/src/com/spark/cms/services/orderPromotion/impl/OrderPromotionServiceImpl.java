/**
 * 
 */
package com.spark.cms.services.orderPromotion.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.base.common.utils.CheckIsNull;
import com.spark.base.common.utils.DoubleUtil;
import com.spark.base.hibernate.GenericDAO;
import com.spark.base.type.GUID;
import com.spark.cms.base.constant.promotion.PromotionConstant;
import com.spark.cms.base.utils.BeanCopy;
import com.spark.cms.common.DataModel;
import com.spark.cms.dao.po.OrderPromotionPo;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.form.Login;
import com.spark.cms.services.form.promotion.OrderPromotionForm;
import com.spark.cms.services.goods.GoodsService;
import com.spark.cms.services.orderPromotion.GetOrderPmtListKey;
import com.spark.cms.services.orderPromotion.OrderPromotionService;
import com.spark.cms.services.orderPromotion.result.OrderPromotionResult;
import com.spark.cms.services.vo.GoodsVo;
import com.spark.cms.services.vo.OrderPromotionVo;

/**
 * @author Jideas
 * 
 */
@Service
public class OrderPromotionServiceImpl implements OrderPromotionService {

	@Autowired
	private GenericDAO baseDAO;

	@Autowired
	private GoodsService goodsService;

	/*
	 * (non-Javadoc) 停用
	 * 
	 * @see com.spark.cms.services.orderPromotion.OrderPromotionService#stopPromotion(java.lang.String,
	 *      com.spark.cms.services.form.Login)
	 */
	@Override
	public ServiceMessage exeStopPromotion(String recid, Login login) {
		OrderPromotionVo vo = this.findOrderPromotion(recid);
		if (null == vo) {
			return new ServiceMessage(false, "促销方案已不存在！");
		}
		vo.setIsactiving(false);
		return this.createOrModifyPmt(vo, login);
	}

	/*
	 * (non-Javadoc) 启用
	 * 
	 * @see com.spark.cms.services.orderPromotion.OrderPromotionService#activePromotion(java.lang.String,
	 *      com.spark.cms.services.form.Login)
	 */
	@Override
	public ServiceMessage exeActivePromotion(String recid, Login login) {
		OrderPromotionVo vo = this.findOrderPromotion(recid);
		if (null == vo) {
			return new ServiceMessage(false, "促销方案已不存在！");
		}
		vo.setIsactiving(true);
		if (!vo.isIsfreedelivery()) {
			boolean b = this.checkAmountOk(vo.getBeginamount(), vo.getEndamount(), vo.getRecid());
			if (!b) {
				return new ServiceMessage(false, "该方案的金额区间已经存在启用的促销！");
			}
		}
		return this.createOrModifyPmt(vo, login);
	}

	/*
	 * (non-Javadoc) 根据订单信息查询促销信息
	 * 
	 * @see com.spark.cms.services.orderPromotion.OrderPromotionService#findByOrderAmount(double)
	 */
	@Override
	public OrderPromotionResult findByOrderAmount(double amount) {
		StringBuilder hql = new StringBuilder();
		hql.append("from OrderPromotionPo as t ");
		hql.append(" where t.isactiving=? ");
		hql.append(" and t.beginamount<=" + amount + " ");
		hql.append(" and t.endamount>" + amount);
		hql.append(" and t.endamount is not null and t.endamount>0");
		List<OrderPromotionPo> polist = this.baseDAO.getGenericByHql(hql.toString(), true);
		OrderPromotionVo vo = null;
		if (null != polist && !polist.isEmpty()) {
			vo = BeanCopy.copy(OrderPromotionVo.class, polist.get(0));
		}
		OrderPromotionVo free = this.findOrderPromotion(PromotionConstant.DefaultFreeDeliveryId);
		OrderPromotionResult opr = new OrderPromotionResult(vo, null != free);
		if(null!=free)
		{
			opr.setBeginAmount(free.getBeginamount());
		}
		return opr;
	}

	/*
	 * (non-Javadoc) 查询指定的促销方案
	 * 
	 * @see com.spark.cms.services.orderPromotion.OrderPromotionService#findOrderPromotion(java.lang.String)
	 */
	@Override
	public OrderPromotionVo findOrderPromotion(String recid) {
		StringBuilder hql = new StringBuilder();
		hql.append("from OrderPromotionPo as t ");
		hql.append(" where recid=? ");
		List<OrderPromotionPo> polist = this.baseDAO.getGenericByHql(hql.toString(), GUID.valueOf(recid).toBytes());
		OrderPromotionPo po = null;
		if (polist == null || polist.isEmpty()) {
			return null;
		} else {
			po = polist.get(0);
		}
		return BeanCopy.copy(OrderPromotionVo.class, po);
	}

	/**
	 * 检查金额区间是否已重复
	 * 
	 * @param beginAmount
	 * @param endAmount
	 * @return true 为不重复
	 */
	private boolean checkAmountOk(double beginAmount, double endAmount, String recid) {
		StringBuilder hql = new StringBuilder();
		hql.append("from OrderPromotionPo as t where isactiving=?");
		List<OrderPromotionPo> polist = this.baseDAO.getGenericByHql(hql.toString(),true);
		for (OrderPromotionPo po : polist) {
			if (GUID.valueOf(po.getRecid()).equals(GUID.valueOf(recid)) || po.isIsfreedelivery()) {
				continue;
			}
			double amount1 = po.getBeginamount();
			double amount2 = po.getEndamount();
			if (amount1 <= beginAmount && amount2 > beginAmount) {
				return false;
			} else if (amount2 <= endAmount && amount1 >= beginAmount) {
				return false;
			} else if (amount2 >= endAmount && amount1 < endAmount) {
				return false;
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc) 新增促销
	 * 
	 * @see com.spark.cms.services.orderPromotion.OrderPromotionService#create(com.spark.cms.base.constant.promotion.PromotionType,
	 *      com.spark.cms.services.vo.OrderPromotionVo,
	 *      com.spark.cms.services.form.Login)
	 */
	@Override
	public ServiceMessage createOrModifyPmt(OrderPromotionVo vo, Login login) {
		boolean newCreate = false;
		if (CheckIsNull.isEmpty(vo.getRecid())) {
			newCreate = true;
			vo.setRecid(GUID.randomID().toString());
		} else if (vo.getRecid().equals(PromotionConstant.DefaultFreeDeliveryId)) {
			newCreate = this.findOrderPromotion(vo.getRecid()) == null;
		}
		if (!newCreate) {
			this.baseDAO.delete(OrderPromotionPo.class, GUID.valueOf(vo.getRecid()).toBytes());
		}
		this.baseDAO.save(BeanCopy.copy(OrderPromotionPo.class, vo));
		return new ServiceMessage(true, "促销方案操作成功！");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.orderPromotion.OrderPromotionService#getList()
	 */
	@Override
	public DataModel<OrderPromotionForm> getList(GetOrderPmtListKey key) {
		StringBuilder hql = new StringBuilder();
		hql.append("from OrderPromotionPo as t ");
		if (null != key.getActivingOnly()) {
			hql.append(" where t.isactiving=?");
		}
		hql.append(" order by t.isactiving ");
		int count = 0;
		List<OrderPromotionPo> polist = null;
		if (null != key.getActivingOnly()) {
			count = Integer.parseInt(this.baseDAO.getGenericByHql("select count(t.recid) " + hql.toString(),
					key.getActivingOnly()).get(0)
					+ "");
			if (key.getPageSize() == 0) {
				polist = this.baseDAO.getGenericByHql(hql.toString(), key.getActivingOnly());
			} else {
				polist = this.baseDAO.getGenericByPosition(hql.toString(), (key.getOffset() - 1) * key.getPageSize(),
						key.getPageSize(), key.getActivingOnly());
			}
		} else {
			count = Integer.parseInt(this.baseDAO.getGenericByHql("select count(t.recid) " + hql.toString()).get(0)
					+ "");
			if (key.getPageSize() == 0) {
				polist = this.baseDAO.getGenericByHql(hql.toString());
			} else {
				polist = this.baseDAO.getGenericByPosition(hql.toString(), (key.getOffset() - 1) * key.getPageSize(),
						key.getPageSize());
			}
		}
		List<OrderPromotionVo> volist = BeanCopy.copys(OrderPromotionVo.class, polist);
		List<OrderPromotionForm> list = new ArrayList<OrderPromotionForm>();
		for (OrderPromotionVo vo : volist) {
			OrderPromotionForm form = getShowForm(vo);
			if (null != form) {
				list.add(form);
			} else {
				count--;
			}
		}
		return new DataModel<OrderPromotionForm>(list, count);
	}

	/**
	 * @return
	 */
	private OrderPromotionForm getShowForm(OrderPromotionVo vo) {
		OrderPromotionForm f = new OrderPromotionForm();
		f.setRecid(vo.getRecid());
		if (CheckIsNull.isNotEmpty(vo.getBeginamount())) {
			f.setBeginAmount(DoubleUtil.getRoundStr(vo.getBeginamount()));
		}
		if (CheckIsNull.isNotEmpty(vo.getEndamount())) {
			f.setEndAmount(DoubleUtil.getRoundStr(vo.getEndamount()));
		}
		if (vo.getGoodsid() != null) {
			f.setDetailId(vo.getGoodsid());
			GoodsVo goodsVo = goodsService.getGoodsVo(vo.getGoodsid());
			if (null == goodsVo) {
				return null;
			}
			f.setDetail(goodsVo.getGoodsname() + "[" + goodsVo.getGoodsspec() + "]");
			f.setCount(DoubleUtil.getRoundStr(vo.getGoodscount()));
			f.setType(1 + "");
		} else if (null != vo.getVantages() && vo.getVantages() > 0) {
			f.setDetail("积分");
			f.setCount(DoubleUtil.getRoundStr(DoubleUtil.strToDouble(vo.getVantages() + ""), 0));
			f.setType(2 + "");
		} else if (vo.isIsfreedelivery()) {
			f.setDetail("免费送货上门");
			f.setCount("--");
			f.setType(3 + "");
		}
		f.setStatus(vo.isIsactiving() ? "启用中" : "已停用");
		return f;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.orderPromotion.OrderPromotionService#findOrderPmtForm(java.lang.String)
	 */
	@Override
	public OrderPromotionForm findOrderPmtForm(String recid) {
		OrderPromotionVo vo = this.findOrderPromotion(recid);
		return getShowForm(vo);
	}
}
