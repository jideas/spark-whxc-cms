/**
 * 
 */
package com.spark.cms.services.goodsPromotion.impl;

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
import com.spark.cms.base.constant.promotion.PromotionConstant;
import com.spark.cms.base.utils.BeanCopy;
import com.spark.cms.common.DataModel;
import com.spark.cms.dao.po.GoodsPromotionPo;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.form.Login;
import com.spark.cms.services.form.promotion.GoodsPromotionForm;
import com.spark.cms.services.goods.GoodsService;
import com.spark.cms.services.goodsPromotion.GetGoodsPromotionListKey;
import com.spark.cms.services.goodsPromotion.GoodsPromotionService;
import com.spark.cms.services.vo.GoodsPromotionVo;
import com.spark.cms.services.vo.GoodsVo;

/**
 * @author Jideas
 * 
 */
@Service
public class GoodsPromotionServiceImpl implements GoodsPromotionService {

	@Autowired
	private GenericDAO baseDAO;
	@Autowired
	private GoodsService goodsService;

	/*
	 * (non-Javadoc)根据促销id更改已售出商品数量
	 * 
	 * @see com.spark.cms.services.goodsPromotion.GoodsPromotionService#changeSaledCount(java.lang.String,
	 *      double)
	 */
	@Override
	public ServiceMessage changeSaledCount(String goodsId, double count) {
		StringBuilder hql = new StringBuilder();
		hql.append(" update GoodsPromotionPo as t ");
		hql.append(" set ");
		hql.append(" saledcount=t.saledcount+? ");
		hql.append(" where t.goodsid=? ");
		hql.append(" and (t.pcount-t.saledcount)>=? ");
		hql.append(" and t.isactiving=? ");
		int n = this.baseDAO.execteBulk(hql.toString(), count, GUID.valueOf(goodsId).toBytes(), count, true);
		if (1 == n) {
			return new ServiceMessage(true, "");
		}
		return new ServiceMessage(false, "该促销已停用或促销商品不足。");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.goodsPromotion.GoodsPromotionService#createOrModify(com.spark.cms.services.vo.GoodsPromotionVo,
	 *      com.spark.cms.services.form.Login)
	 */
	@Override
	public ServiceMessage createOrModify(GoodsPromotionVo vo, Login login) {
		boolean newCreate = false;
		if (CheckIsNull.isEmpty(vo.getRecid())) {
			newCreate = true;
			vo.setRecid(GUID.randomID().toString());
		} else if (vo.getRecid().equals(PromotionConstant.DefaultFreeDeliveryId)) {
			newCreate = this.findGoodsPromotion(vo.getRecid()) == null;
		}
		boolean b = this.checkPromotionDouble(vo.getGoodsid(), vo.getRecid()).isOperSuccess();
		if (!b) {
			return new ServiceMessage(false, "该金额区间已经存在启用的促销方案！");
		}

		if (!newCreate) {
			this.baseDAO.delete(GoodsPromotionPo.class, GUID.valueOf(vo.getRecid()).toBytes());
		}
		vo.setDisrate(DoubleUtil.div(vo.getDisrate(), 100));
		this.baseDAO.save(BeanCopy.copy(GoodsPromotionPo.class, vo));
		return new ServiceMessage(true, "商品促销方案操作成功！");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.goodsPromotion.GoodsPromotionService#exeActivePromotion(java.lang.String,
	 *      com.spark.cms.services.form.Login)
	 */
	@Override
	public ServiceMessage exeActivePromotion(String recid, Login login) {
		GoodsPromotionVo vo = this.findGoodsPromotion(recid);
		if (null == vo) {
			return new ServiceMessage(false, "促销方案已不存在！");
		}
		vo.setIsactiving(true);
		return this.createOrModify(vo, login);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.goodsPromotion.GoodsPromotionService#exeStopPromotion(java.lang.String,
	 *      com.spark.cms.services.form.Login)
	 */
	@Override
	public ServiceMessage exeStopPromotion(String recid, Login login) {
		GoodsPromotionVo vo = this.findGoodsPromotion(recid);
		if (null == vo) {
			return new ServiceMessage(false, "促销方案已不存在！");
		}
		vo.setIsactiving(false);
		return this.createOrModify(vo, login);
	}

	/*
	 * (non-Javadoc)商品id查询对应的促销
	 * 
	 * @see com.spark.cms.services.goodsPromotion.GoodsPromotionService#findByGoodsId(java.lang.String)
	 */
	@Override
	public GoodsPromotionVo findByGoodsId(String goodsId) {
		StringBuilder hql = new StringBuilder();
		hql.append("from GoodsPromotionPo as t ");
		hql.append(" where t.isactiving=?");
		hql.append(" and t.goodsid = ?");
		hql.append(" and t.begintime<?");
		hql.append(" and t.endtime >?");
		Date date = new Date();
		List<GoodsPromotionPo> list = this.baseDAO.getGenericByHql(hql.toString(), true, GUID.valueOf(goodsId)
				.toBytes(), date.getTime(), date.getTime());
		if (null == list || list.isEmpty()) {
			return null;
		}
		return BeanCopy.copy(GoodsPromotionVo.class, list.get(0));
	}

	/*
	 * (non-Javadoc)根据id查询促销方案
	 * 
	 * @see com.spark.cms.services.goodsPromotion.GoodsPromotionService#findGoodsPromotion(java.lang.String)
	 */
	@Override
	public GoodsPromotionVo findGoodsPromotion(String recid) {
		StringBuilder hql = new StringBuilder();
		hql.append("from GoodsPromotionPo as t ");
		hql.append(" where recid=? ");
		List<GoodsPromotionPo> polist = this.baseDAO.getGenericByHql(hql.toString(), GUID.valueOf(recid).toBytes());
		GoodsPromotionPo po = null;
		if (polist == null || polist.isEmpty()) {
			return null;
		} else {
			po = polist.get(0);
		}
		return BeanCopy.copy(GoodsPromotionVo.class, po);
	}

	/*
	 * (non-Javadoc) 查询商品促销方案列表
	 * 
	 * @see com.spark.cms.services.goodsPromotion.GoodsPromotionService#getFormList(com.spark.cms.services.goodsPromotion.GetGoodsPromotionListKey)
	 */
	@Override
	public DataModel<GoodsPromotionForm> getFormList(GetGoodsPromotionListKey key) {
		StringBuilder hql = new StringBuilder();
		hql.append("from GoodsPromotionPo as t ");
		if (null != key.isOnlyActiving()) {
			hql.append(" where t.isactiving=?");
		}
		hql.append(" order by t.isactiving ");
		int count = 0;
		List<GoodsPromotionPo> polist = null;
		if (null != key.isOnlyActiving()) {
			count = Integer.parseInt(this.baseDAO.getGenericByHql("select count(t.recid) " + hql.toString(),
					key.isOnlyActiving()).get(0)
					+ "");
			if (key.getPageSize() == 0) {
				polist = this.baseDAO.getGenericByHql(hql.toString(), key.isOnlyActiving());
			} else {
				polist = this.baseDAO.getGenericByPosition(hql.toString(), (key.getOffset() - 1) * key.getPageSize(),
						key.getPageSize(), key.isOnlyActiving());
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
		List<GoodsPromotionVo> volist = BeanCopy.copys(GoodsPromotionVo.class, polist);
		List<GoodsPromotionForm> list = new ArrayList<GoodsPromotionForm>();
		for (GoodsPromotionVo vo : volist) {
			GoodsPromotionForm form = getShowForm(vo);
			if (null != form) {
				list.add(form);
			} else {
				count--;
			}
		}
		return new DataModel<GoodsPromotionForm>(list, count);
	}

	/**
	 * 组装form
	 * 
	 * @param vo
	 * @return
	 */
	private GoodsPromotionForm getShowForm(GoodsPromotionVo vo) {
		GoodsPromotionForm form = new GoodsPromotionForm();
		form.setRecid(vo.getRecid());
		if (null != vo.getBegintime() && vo.getBegintime() > 0) {
			form.setBeginTime(DateUtil.dateFromat(vo.getBegintime(), "yyyy-MM-dd HH:mm:ss"));
		} else {
			form.setBeginTime("--");
		}
		form.setCount(DoubleUtil.getRoundStr(vo.getPcount()));
		form.setDisRate(DoubleUtil.getRoundStr(DoubleUtil.mul(vo.getDisrate(), 100)) + "%");
		if (null != vo.getEndtime() && vo.getEndtime() > 0) {
			form.setEndTime(DateUtil.dateFromat(vo.getEndtime(), "yyyy-MM-dd HH:mm:ss"));
		} else {
			form.setEndTime("--");
		}
		GoodsVo goodsVo = goodsService.getGoodsVo(vo.getGoodsid());
		if (goodsVo == null) {
			return null;
		}
		form.setGoodsId(vo.getGoodsid());
		form.setGoodsName(goodsVo.getGoodsname() + "[" + goodsVo.getGoodsspec() + "]");
		form.setReserveCount(DoubleUtil.getRoundStr(DoubleUtil.sub(vo.getPcount(), vo.getSaledcount())));
		form.setSalesCount(DoubleUtil.getRoundStr(vo.getSaledcount()));
		form.setStatus(vo.isIsactiving() ? "启用中" : "已停用");
		return form;
	}

	/*
	 * (non-Javadoc) 检查该商品是否已有启用中的促销方案
	 * 
	 * @see com.spark.cms.services.goodsPromotion.GoodsPromotionService#checkPromotionDouble(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public ServiceMessage checkPromotionDouble(String goodsId, String recid) {
		StringBuilder hql = new StringBuilder();
		hql.append("from GoodsPromotionPo as t ");
		hql.append(" where  t.recid<>?");
		hql.append(" and t.goodsid = ?");
		hql.append(" and t.begintime<?");
		hql.append(" and t.endtime >?");
		Date date = new Date();
		List<GoodsPromotionPo> list = this.baseDAO.getGenericByHql(hql.toString(), GUID.valueOf(recid).toBytes(), GUID
				.valueOf(goodsId).toBytes(), date.getTime(), date.getTime());
		return new ServiceMessage(null == list || list.size() == 0, "");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.goodsPromotion.GoodsPromotionService#findGoodsPmtForm(java.lang.String)
	 */
	@Override
	public GoodsPromotionForm findGoodsPmtForm(String recid) {
		GoodsPromotionVo vo = this.findGoodsPromotion(recid);
		return getShowForm(vo);
	}
}
