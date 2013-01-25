/**
 * 
 */
package com.spark.cms.services.CardPromotion.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.base.common.utils.CheckIsNull;
import com.spark.base.common.utils.DoubleUtil;
import com.spark.base.hibernate.GenericDAO;
import com.spark.base.type.GUID;
import com.spark.cms.base.utils.BeanCopy;
import com.spark.cms.common.DataModel;
import com.spark.cms.dao.po.CardPromotionPo;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.CardPromotion.CardPmtService;
import com.spark.cms.services.CardPromotion.GetCardPmtListKey;
import com.spark.cms.services.form.Login;
import com.spark.cms.services.form.promotion.CardPromotionForm;
import com.spark.cms.services.goods.GoodsService;
import com.spark.cms.services.vo.CardPromotionVo;
import com.spark.cms.services.vo.GoodsVo;

/**
 * @author Jideas
 * 
 */
@Service
public class CardPmtServiceImpl implements CardPmtService {

	@Autowired
	private GenericDAO baseDAO;

	@Autowired
	private GoodsService goodsService;

	/*
	 * (non-Javadoc) 停用
	 * 
	 * @see com.spark.cms.services.orderPromotion.CardPromotionService#stopPromotion(java.lang.String,
	 *      com.spark.cms.services.form.Login)
	 */
	@Override
	public ServiceMessage exeStopPromotion(String recid, Login login) {
		CardPromotionVo vo = this.findCardPromotion(recid);
		if (null == vo) {
			return new ServiceMessage(false, "促销方案已不存在！");
		}
		vo.setActiving(false);
		return this.createOrModifyPmt(vo, login);
	}

	/*
	 * (non-Javadoc) 启用
	 * 
	 * @see com.spark.cms.services.orderPromotion.CardPromotionService#activePromotion(java.lang.String,
	 *      com.spark.cms.services.form.Login)
	 */
	@Override
	public ServiceMessage exeActivePromotion(String recid, Login login) {
		CardPromotionVo vo = this.findCardPromotion(recid);
		if (null == vo) {
			return new ServiceMessage(false, "促销方案已不存在！");
		}
		vo.setActiving(true);
		boolean b = this.checkAmountOk(vo.getAmount(), vo.getRecid());
		if (!b) {
			return new ServiceMessage(false, "该金额区间已经存在促销方案！");
		}
		return this.createOrModifyPmt(vo, login);
	}

	/*
	 * (non-Javadoc) 根据充值信息查询促销信息
	 * 
	 * @see com.spark.cms.services.orderPromotion.CardPromotionService#findByCardAmount(double)
	 */
	@Override
	public CardPromotionVo findByCardAmount(double amount) {
		StringBuilder hql = new StringBuilder();
		hql.append("from CardPromotionPo as t ");
		hql.append(" where t.activing=? ");
		hql.append(" and t.amount=" + amount + " ");
		List<CardPromotionPo> polist = this.baseDAO.getGenericByHql(hql.toString(), true);
		CardPromotionVo vo = null;
		if (null != polist && !polist.isEmpty()) {
			vo = BeanCopy.copy(CardPromotionVo.class, polist.get(0));
		}
		return vo;
	}

	/*
	 * (non-Javadoc) 查询指定的促销方案
	 * 
	 * @see com.spark.cms.services.orderPromotion.CardPromotionService#findCardPromotion(java.lang.String)
	 */
	@Override
	public CardPromotionVo findCardPromotion(String recid) {
		StringBuilder hql = new StringBuilder();
		hql.append("from CardPromotionPo as t ");
		hql.append(" where recid=? ");
		List<CardPromotionPo> polist = this.baseDAO.getGenericByHql(hql.toString(), GUID.valueOf(recid).toBytes());
		CardPromotionPo po = null;
		if (polist == null || polist.isEmpty()) {
			return null;
		} else {
			po = polist.get(0);
		}
		return BeanCopy.copy(CardPromotionVo.class, po);
	}

	/**
	 * 检查金额是否已重复
	 * 
	 * @param beginAmount
	 * @param endAmount
	 * @return true 为不重复
	 */
	private boolean checkAmountOk(double amount, String recid) {
		StringBuilder hql = new StringBuilder();
		hql.append("from CardPromotionPo as t ");
		hql.append(" where t.amount=" + amount +" and t.activing=?");
		List<CardPromotionPo> polist = this.baseDAO.getGenericByHql(hql.toString(),true);
		for (CardPromotionPo po : polist) {
			if (GUID.valueOf(po.getRecid()).equals(GUID.valueOf(recid))) {
				continue;
			}
			if (po.getAmount() == amount) {
				return false;
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc) 新增促销
	 * 
	 * @see com.spark.cms.services.orderPromotion.CardPromotionService#create(com.spark.cms.base.constant.promotion.PromotionType,
	 *      com.spark.cms.services.vo.CardPromotionVo,
	 *      com.spark.cms.services.form.Login)
	 */
	@Override
	public ServiceMessage createOrModifyPmt(CardPromotionVo vo, Login login) {
		boolean newCreate = false;
		if (CheckIsNull.isEmpty(vo.getRecid())) {
			newCreate = true;
			vo.setRecid(GUID.randomID().toString());
		}
		if (!newCreate) {
			this.baseDAO.delete(CardPromotionPo.class, GUID.valueOf(vo.getRecid()).toBytes());
		}
		this.baseDAO.save(BeanCopy.copy(CardPromotionPo.class, vo));
		return new ServiceMessage(true, "促销方案操作成功！");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.orderPromotion.CardPromotionService#getList()
	 */
	@Override
	public DataModel<CardPromotionForm> getList(GetCardPmtListKey key) {
		StringBuilder hql = new StringBuilder();
		hql.append("from CardPromotionPo as t ");
		if (null != key.getOnlyActiving()) {
			hql.append(" where t.activing=?");
		}
		hql.append(" order by t.activing ");
		int count = 0;
		List<CardPromotionPo> polist = null;
		if (null != key.getOnlyActiving()) {
			count = Integer.parseInt(this.baseDAO.getGenericByHql("select count(t.recid) " + hql.toString(),
					key.getOnlyActiving()).get(0)
					+ "");
			if (key.getPageSize() == 0) {
				polist = this.baseDAO.getGenericByHql(hql.toString(), key.getOnlyActiving());
			} else {
				polist = this.baseDAO.getGenericByPosition(hql.toString(), (key.getOffset() - 1) * key.getPageSize(),
						key.getPageSize(), key.getOnlyActiving());
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
		List<CardPromotionVo> volist = BeanCopy.copys(CardPromotionVo.class, polist);
		List<CardPromotionForm> list = new ArrayList<CardPromotionForm>();
		for (CardPromotionVo vo : volist) {
			list.add(getShowForm(vo));
		}
		return new DataModel<CardPromotionForm>(list, count);
	}

	/**
	 * @return
	 */
	private CardPromotionForm getShowForm(CardPromotionVo vo) {
		CardPromotionForm f = new CardPromotionForm();
		f.setRecid(vo.getRecid());
		if (CheckIsNull.isNotEmpty(vo.getAmount())) {
			f.setAmount(DoubleUtil.getRoundStr(vo.getAmount()));
		}
		if (vo.getGoodsId() != null) {
			GoodsVo goodsVo = goodsService.getGoodsVo(vo.getGoodsId());
			f.setDetailId(vo.getGoodsId());
			f.setDetail(goodsVo.getGoodsname() + "[" + goodsVo.getGoodsspec() + "]");
			f.setCount(DoubleUtil.getRoundStr(vo.getGoodsCount()));
			f.setType(1 + "");
		} else if (vo.getVantages() > 0) {
			f.setDetail("积分");
			f.setCount(DoubleUtil.getRoundStr(DoubleUtil.strToDouble(vo.getVantages() + ""), 0));
			f.setType(2 + "");
		} else if (vo.getExtraAmount() > 0) {
			f.setDetail("金额");
			f.setCount(DoubleUtil.getRoundStr(vo.getExtraAmount(), 2));
			f.setType(3 + "");
		}
		f.setStatus(vo.isActiving() ? "启用中" : "已停用");
		return f;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.orderPromotion.CardPromotionService#findCardPmtForm(java.lang.String)
	 */
	@Override
	public CardPromotionForm findCardPmtForm(String recid) {
		CardPromotionVo vo = this.findCardPromotion(recid);
		return getShowForm(vo);
	}
}
