package com.spark.cms.services.goods.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.annotations.common.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.base.common.system.dic.DicItem;
import com.spark.base.common.system.dic.DictionaryType;
import com.spark.base.common.system.dic.SparkDictionaryManager;
import com.spark.base.common.utils.BeanUtils;
import com.spark.base.common.utils.CheckIsNull;
import com.spark.base.common.utils.StringUtil;
import com.spark.base.hibernate.GenericDAO;
import com.spark.base.type.GUID;
import com.spark.cms.base.utils.BeanCopy;
import com.spark.cms.common.Constant;
import com.spark.cms.common.Constant.Base.GoodsType;
import com.spark.cms.dao.po.EGoodsPo;
import com.spark.cms.dao.po.GoodsCategoryPo;
import com.spark.cms.dao.po.GoodsContentPo;
import com.spark.cms.dao.po.GoodsPo;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.form.GoodsContentForm;
import com.spark.cms.services.form.GoodsForm;
import com.spark.cms.services.form.UserExtForm;
import com.spark.cms.services.goods.GetEGoodsListKey;
import com.spark.cms.services.goods.GetGoodsListKey;
import com.spark.cms.services.goods.GetVantageGoodsListKey;
import com.spark.cms.services.goods.GoodsService;
import com.spark.cms.services.goods.ModifyGoodsVoTask;
import com.spark.cms.services.vo.EGoodsVo;
import com.spark.cms.services.vo.GoodsCategoryTree;
import com.spark.cms.services.vo.GoodsContentVo;
import com.spark.cms.services.vo.GoodsVo;

@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GenericDAO baseDAO;

	private enum QueryGoodsInfo {
		HQL, PRAMETER
	}

	@Override
	public boolean createGoods(String categoryId, GoodsVo goodsVo) {
		// TODO 处理异常情况
		if (StringHelper.isEmpty(categoryId))
			return false;
		GoodsCategoryPo categoryPo = baseDAO.get(GoodsCategoryPo.class, GUID.tryValueOf(categoryId).toBytes());
		String[] parentIds = categoryPo.getPath().split(";");
		byte[] category1Recid = null; // 直属类ID
		byte[] category2Recid = null;
		byte[] category3Recid = null;
		if (parentIds.length == 1) {
			category1Recid = (GUID.tryValueOf(categoryId).toBytes());
		} else if (parentIds.length == 2) {
			category1Recid = (GUID.tryValueOf(categoryId).toBytes());
			category2Recid = (StringHelper.isEmpty(parentIds[1]) ? null : GUID.tryValueOf(parentIds[0]).toBytes());
		} else if (parentIds.length == 3) {
			category1Recid = (GUID.tryValueOf(categoryId).toBytes());
			category2Recid = (StringHelper.isEmpty(parentIds[1]) ? null : GUID.tryValueOf(parentIds[1]).toBytes());
			category3Recid = (StringHelper.isEmpty(parentIds[2]) ? null : GUID.tryValueOf(parentIds[0]).toBytes());
		}
		GoodsPo goodsPo = new GoodsPo();
		goodsPo.setRecid(GUID.tryValueOf(goodsVo.getRecid()).toBytes());
		goodsPo.setGoodscode(goodsVo.getGoodscode());
		goodsPo.setGoodsno(goodsVo.getGoodsno());
		goodsPo.setGoodsname(goodsVo.getGoodsname());
		goodsPo.setGoodsspec(goodsVo.getGoodsspec());
		goodsPo.setGoodsunit(goodsVo.getGoodsunit());
		goodsPo.setOriginalprice(goodsVo.getOriginalprice());
		goodsPo.setRealprice(goodsVo.getRealprice());
		goodsPo.setFreedelivery(goodsVo.isFreedelivery());
		goodsPo.setInventoryCount(goodsVo.getInventoryCount());
		goodsPo.setMostSales(goodsVo.isMostSales());
		goodsPo.setVantagestype(goodsVo.getVantagestype());
		goodsPo.setGoodsType(goodsVo.getGoodsType());
		goodsPo.setPopular(goodsVo.isPopular());
		goodsPo.setCategoryid1(category1Recid);
		goodsPo.setCategoryid2(category2Recid);
		goodsPo.setCategoryid3(category3Recid);

		goodsPo.setPropertiy1(goodsVo.getPropertiy1());
		goodsPo.setPropertiy2(goodsVo.getPropertiy2());
		goodsPo.setPropertiy3(goodsVo.getPropertiy3());
		goodsPo.setPropertiy4(goodsVo.getPropertiy4());
		goodsPo.setPropertiy5(goodsVo.getPropertiy5());
		baseDAO.save(goodsPo);
		return true;
	}

	public void createGoodsFromERP(String categoryId, String[] erpGoodsIds) {
		if (StringHelper.isEmpty(categoryId)) return;
		GoodsCategoryPo categoryPo = baseDAO.get(GoodsCategoryPo.class, GUID.tryValueOf(categoryId).toBytes());
		String[] parentIds = categoryPo.getPath().split(";");
		byte[] category1Recid = null; // 直属类ID
		byte[] category2Recid = null;
		byte[] category3Recid = null;
		if (parentIds.length == 1) {
			category1Recid = (GUID.tryValueOf(categoryId).toBytes());
		} else if (parentIds.length == 2) {
			category1Recid = (GUID.tryValueOf(categoryId).toBytes());
			category2Recid = (StringHelper.isEmpty(parentIds[1]) ? null : GUID.tryValueOf(parentIds[0]).toBytes());
		} else if (parentIds.length == 3) {
			category1Recid = (GUID.tryValueOf(categoryId).toBytes());
			category2Recid = (StringHelper.isEmpty(parentIds[1]) ? null : GUID.tryValueOf(parentIds[1]).toBytes());
			category3Recid = (StringHelper.isEmpty(parentIds[2]) ? null : GUID.tryValueOf(parentIds[0]).toBytes());
		}
		
		GoodsPo goodsPo = null;
		EGoodsPo eGoodsPo = null;
		for (String goodsId : erpGoodsIds) {
			eGoodsPo = baseDAO.get(EGoodsPo.class, GUID.tryValueOf(goodsId).toBytes());
			if (null == eGoodsPo) continue;
			goodsPo = new GoodsPo();
			// from erp
			goodsPo.setRecid(eGoodsPo.getRecid());
			goodsPo.setGoodscode(eGoodsPo.getGoodsCode());
			goodsPo.setGoodsno(eGoodsPo.getGoodsNo());
			goodsPo.setGoodsname(eGoodsPo.getGoodsName());
			goodsPo.setGoodsspec(eGoodsPo.getSpec());
			goodsPo.setGoodsunit(eGoodsPo.getGoodsUnit());
			goodsPo.setOriginalprice(eGoodsPo.getOriginalPrice());
			goodsPo.setRealprice(eGoodsPo.getSalePrice());
			// category
			goodsPo.setCategoryid1(category1Recid);
			goodsPo.setCategoryid2(category2Recid);
			goodsPo.setCategoryid3(category3Recid);
			// b2c info
			goodsPo.setVantagestype("1");
			goodsPo.setFreedelivery(false);
			goodsPo.setMostSales(false);
			goodsPo.setPopular(false);
			goodsPo.setGoodsType(GoodsType.Common.getCode());
			goodsPo.setPublished(false);
			goodsPo.setVantagesGoods(false);
			
			baseDAO.save(goodsPo);
		}
		
	}
	
	@Override
	public GoodsVo getGoodsVo(String goodsId) {
		// TODO Auto-generated method stub
		if (StringHelper.isEmpty(goodsId))
			return null;
		GoodsPo goodsPo = baseDAO.get(GoodsPo.class, GUID.tryValueOf(goodsId).toBytes());
		if (null == goodsPo) {
			return null;
		}
		GoodsVo goodsVo = new GoodsVo();
		try {
			// BeanUtils.copyProperties1(goodsPo, goodsVo);
			goodsVo = BeanCopy.copy(GoodsVo.class, goodsPo);
			goodsVo.setCategoryid1(GUID.valueOf(goodsPo.getCategoryid1()).toString());
			goodsVo.setCategoryid2(goodsPo.getCategoryid2() == null ? null : GUID.valueOf(goodsPo.getCategoryid2())
					.toString());
			goodsVo.setCategoryid3(goodsPo.getCategoryid3() == null ? null : GUID.valueOf(goodsPo.getCategoryid3())
					.toString());
			goodsVo.setRecid(GUID.valueOf(goodsPo.getRecid()).toString());
			goodsVo.setInventoryCount(goodsPo.getInventoryCount());
			goodsVo.setVantagestype(goodsPo.getVantagestype());
			goodsVo.setSaledCount(goodsPo.getSaledCount());
			goodsVo.setPopular(goodsPo.isPopular());
			goodsVo.setFreedelivery(goodsPo.isFreedelivery());
			goodsVo.setMostSales(goodsPo.isMostSales());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return goodsVo;
	}

	@Override
	public boolean modifyGoodsVo(ModifyGoodsVoTask task) {
		// TODO Auto-generated method stub
		String hql = "update GoodsPo set propertiy1=?";
		List<Object> paramlist = new ArrayList<Object>();
		paramlist.add(task.getProperty1());
		if (StringHelper.isNotEmpty(task.getProperty2())) {
			hql += ", propertiy2=?";
			paramlist.add(task.getProperty2());
		}
		if (StringHelper.isNotEmpty(task.getProperty3())) {
			hql += ", propertiy3=?";
			paramlist.add(task.getProperty3());
		}
		if (StringHelper.isNotEmpty(task.getProperty4())) {
			hql += ", propertiy4=?";
			paramlist.add(task.getProperty4());
		}
		if (StringHelper.isNotEmpty(task.getProperty5())) {
			hql += ", propertiy5=?";
			paramlist.add(task.getProperty5());
		}
		hql += ", inventoryCount=?";
		hql += ", isMostSales=?";
		hql += ", isPopular=?";
		hql += ", vantagestype=?";
		hql += ", freedelivery=?";
		//hql += ", realprice=?";
		hql += ", goodsType=?";
		paramlist.add(task.getInventoryCount());
		paramlist.add(task.isMostSales());
		paramlist.add(task.isPopular());
		paramlist.add(task.getVantagestype());
		paramlist.add(task.isFreedelivery());
		//paramlist.add(task.getRealprice());
		paramlist.add(task.getGoodsType().getCode());
		hql += " where recid=?";
		paramlist.add(task.getGoodsId());
		int result = baseDAO.execteBulk(hql, paramlist.toArray());
		return result == 1 ? true : false;
	}

	@Override
	public void modifyGoods(GoodsForm gf, UserExtForm loginUser) throws ServiceMessage {
		if (CheckIsNull.isEmpty(gf.getRecid())) {
			throw new ServiceMessage("recid不能为空！");
		}
		GoodsPo gp = this.baseDAO.get(GoodsPo.class, GUID.valueOf(gf.getRecid()).toBytes());
		if (null == gp) {
			throw new ServiceMessage("商品信息异常！");
		}
		if (CheckIsNull.isNotEmpty(gf.getPicturepath1())) {
			gp.setPicturepath1(gf.getPicturepath1());
		}
		if (CheckIsNull.isNotEmpty(gf.getPicturepath2())) {
			gp.setPicturepath2(gf.getPicturepath2());
		}
		if (CheckIsNull.isNotEmpty(gf.getPicturepath3())) {
			gp.setPicturepath3(gf.getPicturepath3());
			if (StringUtil.isEmpty(gf.getPicturepath2())) {
				gp.setPicturepath2(gf.getPicturepath3());
			}
			if (StringUtil.isEmpty(gf.getPicturepath1())) {
				gp.setPicturepath2(gf.getPicturepath3());
			}
		}
		if (gf.isPublished()) {
			gp.setPublished(true);
			gp.setPublishDate(new Date());
			gp.setPublishPersonId(GUID.tryValueOf(loginUser.getId()).toBytes());
			gp.setPublishPersonName(loginUser.getName());

		} else {
			gp.setPublished(false);
			gp.setPublishDate(null);
			gp.setPublishPersonId(null);
			gp.setPublishPersonName(null);
		}
		if (gf.isVantagesGoods()) {
			gp.setVantagesGoods(true);
			gp.setVantagesCost(gf.getVantagesCost());
		} else {
			gp.setVantagesGoods(false);
			gp.setVantagesCost(0);
		}
		this.baseDAO.save(gp);
		if (null != gf.getGoodsContentForms() && gf.getGoodsContentForms().size() > 0) {
			StringBuffer del = new StringBuffer();
			del.append(" delete from GoodsContentPo p ");
			del.append(" where p.goodsid=?");
			this.baseDAO.execteBulk(del.toString(), GUID.valueOf(gf.getRecid()).toBytes());
			for (GoodsContentForm cf : gf.getGoodsContentForms()) {
				GoodsContentPo p = BeanCopy.copy(GoodsContentPo.class, cf);
				try {
					BeanUtils.copyProperties1(cf, p);
				} catch (Exception e) {
					// TODO: handle exception
				}
				p.setGoodsid(GUID.valueOf(cf.getGoodsid()).toBytes());
				p.setRecid(GUID.randomID().toBytes());
				this.baseDAO.save(p);
			}
		}
	}

	public boolean modifyGoodsSaleCount(String goodsId, double changeCount) {
		if (StringUtil.isEmpty(goodsId))
			return false;
		int result = baseDAO.execteBulk("update GoodsPo set saledCount=(saledCount + " + changeCount
				+ ") where recid=?", GUID.tryValueOf(goodsId).toBytes());
		return (result == 1);
	}

	@Override
	public boolean deleteGoods(String goodsRecid) {
		int result = baseDAO.execteBulk("delete from GoodsPo where recid=?", GUID.tryValueOf(goodsRecid).toBytes());
		return (result == 1);
	}

	@Override
	public List<GoodsVo> getGoodsList(GetGoodsListKey key) {
		Map<String, Object> queryInfo = this.getQueryInfo(key);
		if (queryInfo.get(QueryGoodsInfo.HQL.name()) == null)
			return new ArrayList<GoodsVo>();
		// TODO 处理异常情况
		List<GoodsVo> goodsVoList = new ArrayList<GoodsVo>();
		List<GoodsPo> goodsPoList = new ArrayList<GoodsPo>();
		String hql = (String) queryInfo.get(QueryGoodsInfo.HQL.name());
		Object[] parameters = (Object[]) queryInfo.get(QueryGoodsInfo.PRAMETER.name());
		try {
			if (null == parameters) {
				goodsPoList = baseDAO.getGenericByPosition(hql, key.getOffset(), key.getPageSize());
			} else {
				goodsPoList = baseDAO.getGenericByPosition(hql, key.getOffset(), key.getPageSize(), parameters);
			}
			for (GoodsPo goodsPo : goodsPoList) {
				GoodsVo goodsVo = BeanCopy.copy(GoodsVo.class, goodsPo);
				goodsVo.setCategoryid1(GUID.valueOf(goodsPo.getCategoryid1()).toString());
				/*
				if (null != goodsPo.getCategoryid2()) {
					goodsVo.setCategoryid2(GUID.valueOf(goodsPo.getCategoryid2()).toString());
				}
				if (null != goodsPo.getCategoryid3()) {
					goodsVo.setCategoryid3(GUID.valueOf(goodsPo.getCategoryid3()).toString());
				}
				*/
				goodsVo.setRecid(GUID.valueOf(goodsPo.getRecid()).toString());
				goodsVoList.add(goodsVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return goodsVoList;
	}

	private Map<String, Object> getQueryInfo(GetGoodsListKey key) {
		String hql = null;
		List<Object> parameters = new ArrayList<Object>();
		if (null == key.getGoodsCategoryId()) {
			if (null == key.isPublished()) {
				hql = "from GoodsPo where 1=1 ";
			} else {
				hql = "from GoodsPo where isPublished=?";
				if (key.isPublished()) {
					parameters.add(true);
				} else {
					parameters.add(false);
				}
			}
		} else {
			hql = "from GoodsPo where (categoryid1=? or categoryid2= ? or categoryid3=?)";
			if (null == key.isPublished()) {

			} else {
				if (key.isPublished()) {
					hql += " and isPublished=true ";
				} else {
					hql += " and isPublished=false ";
				}
			}
			parameters.add(key.getGoodsCategoryId());
			parameters.add(key.getGoodsCategoryId());
			parameters.add(key.getGoodsCategoryId());
		}
		
		if (key.isVantageOnly()) {
			hql += " and isVantagesGoods=true";
		}
		if (key.getPriceBegin() > -1) {
			if (key.isVantageOnly()) {
				hql += " and vantagesCost>" + key.getPriceBegin();
			} else {
				hql += " and realprice>" + key.getPriceBegin();
			}
		} 
		if (key.getPriceEnd() > -1) {
			if (key.isVantageOnly()) {
				hql += " and vantagesCost<" + key.getPriceEnd();
			} else {
				hql += " and realprice<" + key.getPriceEnd();
			}
		}
		
		if (StringUtil.isNotEmpty(key.getSearchText())) {
			hql += " and ";
			hql += " (goodscode like '%" + key.getSearchText() + "%' or goodsno like '%" + key.getSearchText()
					+ "%' or goodsname like '%" + key.getSearchText() + "%')";
		}
		
		if (null != key.getAdvanceSearch()) {
			if (key.getAdvanceSearch().getIsMostSale() != null) {
				hql += " and isMostSales=?";
				parameters.add(key.getAdvanceSearch().getIsMostSale() ? true : false);
			}
			if (key.getAdvanceSearch().getIsPopular() != null) {
				hql += " and isPopular=?";
				parameters.add(key.getAdvanceSearch().getIsPopular() ? true : false);
			}
			if (key.getAdvanceSearch().getGoodsType() != null) {
				hql += " and goodsType='" + key.getAdvanceSearch().getGoodsType() + "'";
			}
			if (key.getAdvanceSearch().getGoodsVantageType() != null) {
				hql += " and vantagestype='" + key.getAdvanceSearch().getGoodsVantageType() + "'";
			}
		}

		if (CheckIsNull.isNotEmpty(key.getFilterIds())) {
			StringBuffer filterHql = new StringBuffer(" and recid not in (");
			for (int index = 0; index < key.getFilterIds().length; index++) {
				String filterId = key.getFilterIds()[index];
				if (index != 0) {
					filterHql.append(",");
				}
				filterHql.append("?");
				parameters.add(GUID.tryValueOf(filterId).toBytes());
			}
			filterHql.append(")");
			hql += filterHql;
		}

		if (key.getSortColumn() != null) {
			hql += " order by " + key.getSortColumn().getColumnName();
			if (key.getSortType() == null) {
				hql += " desc ";
			} else {
				hql += " " + key.getSortType().name();
			}
		}
		Map<String, Object> queryInfo = new HashMap<String, Object>();
		queryInfo.put(QueryGoodsInfo.HQL.name(), hql);
		queryInfo.put(QueryGoodsInfo.PRAMETER.name(), parameters.toArray());
		return queryInfo;
	}

	public int getGoodsTotalCount(GetGoodsListKey key) {
		Map<String, Object> queryInfo = this.getQueryInfo(key);
		if (queryInfo.get(QueryGoodsInfo.HQL.name()) == null)
			return 0;
		String hql = (String) queryInfo.get(QueryGoodsInfo.HQL.name());
		Object[] parameters = (Object[]) queryInfo.get(QueryGoodsInfo.PRAMETER.name());
		// baseDAO.getGenericCountByHql(hql, parameters);
		hql = "select count(*) " + hql;
		if (parameters == null) {
			List<Object> list = baseDAO.getGenericByHql(hql);
			return Integer.parseInt(list.get(0).toString());
		} else {
			List<Object> list = baseDAO.getGenericByHql(hql, parameters);
			return Integer.parseInt(list.get(0).toString());
		}
	}

	@Override
	public List<EGoodsVo> getEGoodsList(GetEGoodsListKey key) {
		List<EGoodsVo> goodsVoList = new ArrayList<EGoodsVo>();
		List<EGoodsPo> goodsPoList = new ArrayList<EGoodsPo>();
		// String sql = "from EGoodsPo where categoryId=? and recid not in
		// (select recid from GoodsPo)";
		// if (null == key.getCategoryId()) {
		// sql = "from EGoodsPo where recid not in (select recid from GoodsPo)";
		// // goodsPoList = baseDAO.getGenericByHql(sql);
		// goodsPoList = baseDAO.getGenericByPosition(sql, key.getOffset(),
		// key.getCount());
		// } else {
		// // goodsPoList = baseDAO.getGenericByHql(sql, key.getCategoryId());
		// String temp = "FROM EGoodsPo "
		// + " WHERE categoryId IN ("
		// + " SELECT c.recid FROM EGoodsCategoryPo c"
		// + " WHERE c.path LIKE '%" +
		// GUID.valueOf(key.getCategoryId()).toString() + "%'"
		// + " AND c.leafflag=TRUE) and recid not in (select recid from
		// GoodsPo)";
		// goodsPoList = baseDAO.getGenericByPosition(temp, key.getOffset(),
		// key.getCount());
		// //goodsPoList = baseDAO.getGenericByPosition(sql, key.getOffset(),
		// key.getCount(), key.getCategoryId());
		// }
		Map<String, Object> queryInfo = this.getEGoodsQueryInfo(key);
		String hql = (String) queryInfo.get(QueryGoodsInfo.HQL.name());
		goodsPoList = baseDAO.getGenericByPosition(hql, key.getOffset(), key.getCount());
		try {
			for (EGoodsPo goodsPo : goodsPoList) {
				EGoodsVo goodsVo = new EGoodsVo();
				BeanUtils.copyProperties1(goodsPo, goodsVo);
				goodsVo.setCategoryId(GUID.valueOf(goodsPo.getCategoryId()).toString());
				goodsVo.setRecid(GUID.valueOf(goodsPo.getRecid()).toString());
				goodsVo.setGoodsSpec(goodsPo.getSpec());
				goodsVoList.add(goodsVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<EGoodsVo>();
		}
		return goodsVoList;
	}

	private Map<String, Object> getEGoodsQueryInfo(GetEGoodsListKey key) {
		String hql = null;
		Object[] parameters = null;
		if (null == key.getCategoryId()) {
			hql = "from EGoodsPo where recid not in (select recid from GoodsPo)";
		} else {
			hql = "FROM EGoodsPo " + " WHERE categoryId IN (" + " SELECT c.recid FROM EGoodsCategoryPo c"
					+ " WHERE c.path LIKE '%" + GUID.valueOf(key.getCategoryId()).toString() + "%'"
					+ " AND c.leafflag=TRUE) and recid not in (select recid from GoodsPo)";
		}
		Map<String, Object> queryInfo = new HashMap<String, Object>();
		queryInfo.put(QueryGoodsInfo.HQL.name(), hql);
		queryInfo.put(QueryGoodsInfo.PRAMETER.name(), parameters);
		return queryInfo;
	}

	/**
	 * 获得ERP系统中的商品总数
	 * 
	 * @return
	 */
	public int getEGoodsTotalCount(GetEGoodsListKey key) {
		Map<String, Object> queryInfo = this.getEGoodsQueryInfo(key);
		String hql = (String) queryInfo.get(QueryGoodsInfo.HQL.name());
		hql = "select count(*) " + hql;
		List<Object> list = baseDAO.getGenericByHql(hql);
		return Integer.parseInt(list.get(0).toString());
	}

	@Override
	public List<GoodsContentVo> getGoodsContentList(String goodsId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" from GoodsContentPo p ");
		sql.append(" where p.goodsid=? order by ordinal asc");
		List<GoodsContentPo> list = this.baseDAO.getGenericByHql(sql.toString(), GUID.valueOf(goodsId).toBytes());
		if (CheckIsNull.isNotEmpty(list)) {
			return BeanCopy.copys(GoodsContentVo.class, list);
		}
		return null;
	}

	@Override
	public GoodsForm getGoodsForm(String goodsId) {
		GoodsVo gv = getGoodsVo(goodsId);
		if (gv == null) {
			return null;
		}
		GoodsForm gf = BeanCopy.copy(GoodsForm.class, gv);
		List<GoodsContentVo> list = getGoodsContentList(goodsId);
		if (list == null) {
			return gf;
		}
		List<GoodsContentForm> r = BeanCopy.copys(GoodsContentForm.class, list);
		gf.setGoodsContentForms(r);
		return gf;
	}

	@Override
	public List<DicItem> getVantageList() {
		List<DicItem> list = SparkDictionaryManager.getDicItemsList(DictionaryType.VantagesType);
		return list;
	}

	/*
	 * (non-Javadoc) 根据商品编号查询不同规格的同一产品
	 * 
	 * @see com.spark.cms.services.goods.GoodsService#getSameGoodsList(java.lang.String)
	 */
	@Override
	public List<GoodsForm> getSameGoodsList(String goodsCode) {
		List<GoodsForm> list = new ArrayList<GoodsForm>();
		StringBuilder hql = new StringBuilder();
		hql.append(" from GoodsPo as t where ");
		hql.append(" isPublished=true ");
		hql.append(" and goodscode=? ");
		hql.append(" order by goodsspec ");
		List<GoodsPo> polist = this.baseDAO.getGenericByHql(hql.toString(), goodsCode);
		if (polist == null || polist.isEmpty()) {
			return null;
		}
		list = BeanCopy.copys(GoodsForm.class, polist);
		for (GoodsForm goods : list) {
			List<GoodsContentVo> gcvlist = getGoodsContentList(goods.getRecid());
			List<GoodsContentForm> r = BeanCopy.copys(GoodsContentForm.class, gcvlist);
			goods.setGoodsContentForms(r);
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.goods.GoodsService#getPopularGoodsList(java.lang.String)
	 */
	@Override
	public List<GoodsForm> getPopularGoodsList(String categoryId) {
		List<GoodsForm> list = new ArrayList<GoodsForm>();
		StringBuilder hql = new StringBuilder();
		hql.append(" from GoodsPo as t where ");
		hql.append(" isPublished=true ");
		hql.append(" and (categoryid1=? or categoryid2=? or categoryid3=?) and isPopular=true");
		hql.append(" order by realprice desc ");
		List<GoodsPo> polist = this.baseDAO.getGenericByHql(hql.toString(), GUID.valueOf(categoryId).toBytes(),GUID.valueOf(categoryId).toBytes(),GUID.valueOf(categoryId).toBytes());
		if (polist == null || polist.isEmpty()) {
			return null;
		}
		list = BeanCopy.copys(GoodsForm.class, polist);
		for (GoodsForm goods : list) {
			List<GoodsContentVo> gcvlist = getGoodsContentList(goods.getRecid());
			List<GoodsContentForm> r = BeanCopy.copys(GoodsContentForm.class, gcvlist);
			goods.setGoodsContentForms(r);
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.goods.GoodsService#getPopularGoodsList(java.lang.String)
	 */
	@Override
	public List<GoodsForm> getHotGoodsList(String categoryId) {
//		List<GoodsForm> list = new ArrayList<GoodsForm>();
		/*StringBuilder hql = new StringBuilder();
		hql.append(" from GoodsPo as t where ");
		hql.append(" isPublished=true ");
		hql.append(" and( categoryid1=? or  categoryid2=? or  categoryid3=? )and isMostSales=true");
		hql.append(" order by realprice desc ");
		List<GoodsPo> polist = this.baseDAO.getGenericByHql(hql.toString(), GUID.valueOf(categoryId).toBytes(), GUID
				.valueOf(categoryId).toBytes(), GUID.valueOf(categoryId).toBytes());
		if (polist == null || polist.isEmpty()) {
			return null;
		}
		list = BeanCopy.copys(GoodsForm.class, polist);
		for (GoodsForm goods : list) {
			List<GoodsContentVo> gcvlist = getGoodsContentList(goods.getRecid());
			List<GoodsContentForm> r = BeanCopy.copys(GoodsContentForm.class, gcvlist);
			goods.setGoodsContentForms(r);
		}*/
		List<GoodsVo> voList = getHotGoodsVoList(categoryId);
		if (voList == null || voList.size() < 1) return new ArrayList<GoodsForm>(); 
		return BeanCopy.copys(GoodsForm.class, voList);
	}
	
	
	public List<GoodsVo> getHotGoodsVoList(String categoryId) {
		StringBuilder hql = new StringBuilder();
		List<GoodsPo> poList = new ArrayList<GoodsPo>();
		if (StringUtil.isEmpty(categoryId) || GoodsCategoryTree.Root_ID.equals(categoryId)) {
			hql.append(" from GoodsPo as t where ");
			hql.append(" isPublished=true and isMostSales=true ");
			hql.append(" order by realprice desc ");
			poList = this.baseDAO.getGenericByPosition(hql.toString(), 0, Constant.MAX_HOTSALE_SHOWCOUNT);
		} else {
			hql.append(" from GoodsPo as t where ");
			hql.append(" isPublished=true ");
			hql.append(" and( categoryid1=? or  categoryid2=? or  categoryid3=? )and isMostSales=true");
			hql.append(" order by realprice desc ");
			poList = this.baseDAO.getGenericByPosition(hql.toString(), 0, Constant.MAX_HOTSALE_SHOWCOUNT, GUID.valueOf(categoryId).toBytes(), GUID
					.valueOf(categoryId).toBytes(), GUID.valueOf(categoryId).toBytes());
		}
		if (poList == null || poList.size() < 1) return new ArrayList<GoodsVo>(); 
		return BeanCopy.copys(GoodsVo.class, poList);
	} 
	
	public List<GoodsVo> getVantageGoodsList(GetVantageGoodsListKey key) {
		
		return null;
	}
}
