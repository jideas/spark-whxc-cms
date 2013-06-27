package com.spark.cms.services.channel.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.base.common.key.SortType;
import com.spark.base.common.utils.CheckIsNull;
import com.spark.base.common.utils.StringUtil;
import com.spark.base.hibernate.GenericDAO;
import com.spark.base.type.GUID;
import com.spark.cms.base.constant.ChannelContentsStatus;
import com.spark.cms.base.utils.BeanCopy;
import com.spark.cms.common.Constant;
import com.spark.cms.dao.po.ChannelAdvertisingPo;
import com.spark.cms.dao.po.ChannelContentPo;
import com.spark.cms.dao.po.ChannelGoodsPo;
import com.spark.cms.dao.po.ChannelPo;
import com.spark.cms.dao.po.FloorAdvertisingPo;
import com.spark.cms.dao.po.FloorPo;
import com.spark.cms.dao.po.GoodsPo;
import com.spark.cms.dao.po.PopularKeyWordsPo;
import com.spark.cms.services.channel.ChannelService;
import com.spark.cms.services.channel.ChannelThrowable;
import com.spark.cms.services.channel.GetChannelAdvertisingKey;
import com.spark.cms.services.channel.GetChannelContentListKey;
import com.spark.cms.services.channel.GetChannelGoodsListKey;
import com.spark.cms.services.channel.GetChannelListKey;
import com.spark.cms.services.channel.info.FloorInfo;
import com.spark.cms.services.goods.GoodsService;
import com.spark.cms.services.vo.ChannelAdvertisingVo;
import com.spark.cms.services.vo.ChannelContentVo;
import com.spark.cms.services.vo.ChannelGoodsVo;
import com.spark.cms.services.vo.ChannelVo;
import com.spark.cms.services.vo.FloorAdvertisingVo;
import com.spark.cms.services.vo.FloorVo;
import com.spark.cms.services.vo.GoodsVo;
import com.spark.cms.services.vo.PopularKeyWordsVo;

@Service
public class ChannelServiceImpl implements ChannelService {

	@Autowired
	private GenericDAO baseDAO;
	
	@Autowired
	private GoodsService goodsService;
	
	private enum QueryInfo {
		HQL, PARAMETERS
	}

	@Override
	public void createChannel(ChannelVo v) {
		ChannelPo p = BeanCopy.copy(ChannelPo.class, v);
		p.setRecid(GUID.randomID().toBytes());
		this.baseDAO.save(p);
		
		/*
		for (int index = 6; index <= 7; index++) {
			ChannelPo po = new ChannelPo();
			po.setRecid(GUID.randomID().toBytes());
			po.setCode("" + index);
			po.setName("栏目" + index);
			po.setChanneltype("01");
			po.setPageType("01");
			this.genericDAO.save(po);
		}
		*/
	}

	@Override
	public void createChannelContent(ChannelContentVo v) {
		ChannelContentPo p = BeanCopy.copy(ChannelContentPo.class, v);
		p.setRecid(GUID.randomID().toBytes());
		p.setStatus(ChannelContentsStatus.DISABLE.getCode());
		this.baseDAO.save(p);
	}

	@Override
	public void createChannelGoods(ChannelGoodsVo v) {
		ChannelGoodsPo p = BeanCopy.copy(ChannelGoodsPo.class, v);
		p.setRecid(GUID.randomID().toBytes());
		p.setStatus(ChannelContentsStatus.DISABLE.getCode());
		this.baseDAO.save(p);
	}

	@Override
	public void createChannelAdvertising(ChannelAdvertisingVo v) {
		ChannelAdvertisingPo p = BeanCopy.copy(ChannelAdvertisingPo.class, v);
		p.setRecid(GUID.randomID().toBytes());
		p.setStatus(ChannelContentsStatus.DISABLE.getCode());
		this.baseDAO.save(p);

	}
	
	@Override
	public boolean isChannelExist(String... channelIds) {
		if (null == channelIds || channelIds.length < 1) return false;
		String hql = "select count(*) from ChannelPo where recid in (";
		Object[] paramters = new Object[channelIds.length];
		for (int index = 0; index < channelIds.length; index++) {
			if (0 != index) {
				hql += ",";
			}
			hql += "?";
			paramters[index] = GUID.tryValueOf(channelIds[index]).toBytes();
		}
		hql += ")";
		long count = baseDAO.getGenericCountByHql(hql, paramters);
		return count > 0;
	}

	@Override
	public void createFloor(FloorInfo v) throws ChannelThrowable {
		FloorPo p = BeanCopy.copy(FloorPo.class, v);
		p.setOrdinal(v.getOrdinal());
		p.setGoodsCategoryId(StringUtil.isEmpty(v.getGoodsCategoryId()) ? null : GUID.tryValueOf(v.getGoodsCategoryId()).toBytes());
		p.setTheme(v.getTheme());
		p.setTitle(v.getTitle());
		p.setFloortype(v.getFloortype());
		GUID id = GUID.randomID();
		if (CheckIsNull.isNotEmpty(v.getOrdinal())) {
			StringBuffer sql = new StringBuffer();
			sql.append(" from FloorPo p ");
			sql.append(" where p.ordinal=? ");
			// sql.append(" and p.recid<>?");
			List<FloorPo> list = this.baseDAO.getGenericByHql(
					sql.toString(), v.getOrdinal());
			if (CheckIsNull.isNotEmpty(list)) {
				throw new ChannelThrowable("楼层序号重复，请重试！");
			}
		}

		p.setRecid(id.toBytes());
		this.baseDAO.save(p);
		if (null != v.getChannelVos() && v.getChannelVos().length > 0) {
			StringBuffer hsql = new StringBuffer();
			hsql.append(" from ChannelPo p1 ");
			hsql.append(" where p1.floorid=?");
			List<ChannelPo> list = this.baseDAO.getGenericByHql(hsql
					.toString(), GUID.valueOf(p.getRecid()).toBytes());
			for (ChannelPo p1 : list) {
				StringBuffer hsql1 = new StringBuffer();
				if (Constant.ChannelEnum.ChannelType.Advertise.getCode()
						.equals(p1.getChanneltype())) {
					hsql1.append(" delete from ChannelAdvertisingPo p ");
					hsql1.append(" where p.channelid=?");
					this.baseDAO.execteBulk(hsql1.toString(), p1.getRecid());
				} else if (Constant.ChannelEnum.ChannelType.Content.getCode()
						.equals(p1.getChanneltype())) {
					hsql1.append(" delete from ChannelContentPo p ");
					hsql1.append(" where p.channelid=?");
					this.baseDAO.execteBulk(hsql1.toString(), p1.getRecid());
				} else if (Constant.ChannelEnum.ChannelType.Goods.getCode()
						.equals(p1.getChanneltype())) {
					hsql1.append(" delete from ChannelGoodsPo p ");
					hsql1.append(" where p.channelid=?");
					this.baseDAO.execteBulk(hsql1.toString(), p1.getRecid());
				}
				this.baseDAO.delete(ChannelPo.class, p1.getRecid());
			}
			for (ChannelVo cv : v.getChannelVos()) {
				ChannelPo cp = BeanCopy.copy(ChannelPo.class, cv);
				cp.setFloorid(id.toBytes());
				this.baseDAO.save(cp);
			}
		}
		// 广告
		if (v.getAdvertisings() != null) {
			for (FloorAdvertisingVo fv: v.getAdvertisings()) {
				FloorAdvertisingPo fp = BeanCopy.copy(FloorAdvertisingPo.class, fv);
				fp.setFloorId(id.toBytes());
				fp.setRecid(GUID.randomID().toBytes());
				baseDAO.save(fp);
			}
		}
	}

	@Override
	public void modifyChannelContent(ChannelContentVo v) {
		ChannelContentPo p = this.baseDAO.get(ChannelContentPo.class, GUID
				.valueOf(v.getRecid()).toBytes());
		if (CheckIsNull.isNotEmpty(v.getContent()))
			p.setContent(v.getContent());
		if (CheckIsNull.isNotEmpty(v.getTitle()))
			p.setTitle(v.getTitle());
		// if (CheckIsNull.isNotEmpty(v.getStatus()))
		// p.setStatus(v.getStatus());
		this.baseDAO.save(p);

	}

	@Override
	public void modifyChannelGoods(ChannelGoodsVo v) {
		ChannelGoodsPo p = this.baseDAO.get(ChannelGoodsPo.class, GUID
				.valueOf(v.getRecid()).toBytes());
		if (CheckIsNull.isNotEmpty(v.getGoodsid()))
			p.setGoodsid(GUID.valueOf(v.getGoodsid()).toBytes());
		if (CheckIsNull.isNotEmpty(v.getOrdinal()))
			p.setOrdinal(v.getOrdinal());
		// if (CheckIsNull.isNotEmpty(v.getStatus()))
		// p.setStatus(v.getStatus());
		this.baseDAO.save(p);

	}

	@Override
	public void modifyChannelAdvertising(ChannelAdvertisingVo v) {
		ChannelAdvertisingPo p = this.baseDAO.get(
				ChannelAdvertisingPo.class, GUID.valueOf(v.getRecid())
						.toBytes());
		if (CheckIsNull.isNotEmpty(v.getImageurl()))
			p.setImageurl(v.getImageurl());
		if (CheckIsNull.isNotEmpty(v.getUrl()))
			p.setUrl(v.getUrl());
		// if (CheckIsNull.isNotEmpty(v.getStatus()))
		// p.setStatus(v.getStatus());
		this.baseDAO.save(p);

	}

	@Override
	public void modifyFloor(FloorInfo v) throws ChannelThrowable {
		FloorPo p = this.baseDAO.get(FloorPo.class, GUID.valueOf(
				v.getRecid()).toBytes());
		if (CheckIsNull.isEmpty(p)) {
			throw new ChannelThrowable("楼层数据错误！");
		}
		if (CheckIsNull.isNotEmpty(v.getOrdinal())) {
			StringBuffer sql = new StringBuffer();
			sql.append(" from FloorPo p ");
			sql.append(" where p.ordinal=? ");
			sql.append(" and p.recid<>?");
			List<FloorPo> list = this.baseDAO.getGenericByHql(
					sql.toString(), v.getOrdinal(), GUID.valueOf(v.getRecid())
							.toBytes());
			if (CheckIsNull.isNotEmpty(list)) {
				throw new ChannelThrowable("楼层序号重复，请重试！");
			}
			p.setOrdinal(v.getOrdinal());
		}

		if (CheckIsNull.isNotEmpty(v.getTheme()))
			p.setTheme(v.getTheme());
		if (CheckIsNull.isNotEmpty(v.getTitle()))
			p.setTitle(v.getTitle());
		if (CheckIsNull.isNotEmpty(v.getGoodsCategoryId())) {
			p.setGoodsCategoryId(GUID.valueOf(v.getGoodsCategoryId()).toBytes());
		}
		if (CheckIsNull.isNotEmpty(v.getUrl())) {
			p.setUrl(v.getUrl());
		}
		if (CheckIsNull.isNotEmpty(v.getImageUrl())) {
			p.setImageUrl(v.getImageUrl());
		}
		
		this.baseDAO.update(p);
		if (null != v.getChannelVos() && v.getChannelVos().length > 0) {
			StringBuffer hsql = new StringBuffer();
			hsql.append(" from ChannelPo p1 ");
			hsql.append(" where p1.floorid=?");
			List<ChannelPo> list = this.baseDAO.getGenericByHql(hsql
					.toString(), GUID.valueOf(v.getRecid()).toBytes());
			List<String> idList = new ArrayList<String>();
			for (int index = 0; index < v.getChannelVos().length; index++) {
				idList.add(v.getChannelVos()[index].getRecid());
			}
			for (ChannelPo p1 : list) {
				StringBuffer hsql1 = new StringBuffer();
				if (!idList.contains(GUID.valueOf(p1.getRecid()).toString())) {
					if (Constant.ChannelEnum.ChannelType.Advertise.getCode()
							.equals(p1.getChanneltype())) {
						hsql1.append(" delete from ChannelAdvertisingPo p ");
						hsql1.append(" where p.channelid=?");
						//hsql1.append(" and p.channelid not in ").append(channelIdCondition);
						this.baseDAO.execteBulk(hsql1.toString(), p1.getRecid(), idList.toArray());
					} else if (Constant.ChannelEnum.ChannelType.Content.getCode()
							.equals(p1.getChanneltype())) {
						hsql1.append(" delete from ChannelContentPo p ");
						hsql1.append(" where p.channelid=?");
						//hsql1.append(" and p.channelid not in ").append(channelIdCondition);
						this.baseDAO.execteBulk(hsql1.toString(), p1.getRecid(), idList.toArray());
					} else if (Constant.ChannelEnum.ChannelType.Goods.getCode()
							.equals(p1.getChanneltype())) {
						hsql1.append(" delete from ChannelGoodsPo p ");
						hsql1.append(" where p.channelid=?");
						//hsql1.append(" and p.channelid not in ").append(channelIdCondition);
						this.baseDAO.execteBulk(hsql1.toString(), p1.getRecid());
					}
				}
				this.baseDAO.delete(ChannelPo.class, p1.getRecid());
			}
			for (ChannelVo cv : v.getChannelVos()) {
				ChannelPo cp = BeanCopy.copy(ChannelPo.class, cv);
				cp.setFloorid(GUID.valueOf(v.getRecid()).toBytes());
				this.baseDAO.save(cp);
			}
		}
		
		// 广告
		// 先删除再保存
		baseDAO.execteBulk("delete from FloorAdvertisingPo where floorId=?", p.getRecid());
		if (v.getAdvertisings() != null) {
			for (FloorAdvertisingVo fv: v.getAdvertisings()) {
				FloorAdvertisingPo fp = BeanCopy.copy(FloorAdvertisingPo.class, fv);
				fp.setFloorId(p.getRecid());
				fp.setRecid(GUID.randomID().toBytes());
				baseDAO.save(fp);
			}
		}
	}

	@Override
	public ChannelVo findChannel(String id) {
		ChannelPo p = this.baseDAO.get(ChannelPo.class, GUID.valueOf(id)
				.toBytes());
		ChannelVo v = BeanCopy.copy(ChannelVo.class, p);
		// v.setChanneltype(ChannelType.getFlorType(p.getChanneltype()).getName());
		return v;
	}

	@Override
	public ChannelContentVo findChannelContent(String id) {
		ChannelContentPo p = this.baseDAO.get(ChannelContentPo.class, GUID
				.valueOf(id).toBytes());
		ChannelContentVo v = BeanCopy.copy(ChannelContentVo.class, p);
		return v;
	}

	@Override
	public ChannelGoodsVo findChannelGoods(String id) {
		ChannelGoodsPo p = this.baseDAO.get(ChannelGoodsPo.class, GUID
				.valueOf(id).toBytes());
		ChannelGoodsVo v = BeanCopy.copy(ChannelGoodsVo.class, p);
		return v;
	}

	@Override
	public ChannelAdvertisingVo findChannelAdvertising(String id) {
		ChannelAdvertisingPo p = this.baseDAO.get(
				ChannelAdvertisingPo.class, GUID.valueOf(id).toBytes());
		ChannelAdvertisingVo v = BeanCopy.copy(ChannelAdvertisingVo.class, p);
		return v;
	}

	@Override
	public FloorVo findFloor(String id) {
		FloorPo p = this.baseDAO.get(FloorPo.class, GUID.valueOf(id)
				.toBytes());
		FloorVo v = BeanCopy.copy(FloorVo.class, p);
		return v;
	}

	@Override
	public FloorInfo findFloorInfo(String id) {
		FloorVo v = findFloor(id);
		FloorInfo floorInfo = BeanCopy.copy(FloorInfo.class, v);
		floorInfo.setFloortype(v.getFloortype());
		floorInfo.setGoodsCategoryId(v.getGoodsCategoryId());
		floorInfo.setOrdinal(v.getOrdinal());
		floorInfo.setRecid(v.getRecid());
		floorInfo.setTheme(v.getTheme());
		floorInfo.setImageUrl(v.getImageUrl());
		floorInfo.setUrl(v.getUrl());
		floorInfo.setTitle(v.getTitle());
		ChannelVo[] channelVos = getList(id).toArray(new ChannelVo[0]);
		floorInfo.setChannelVos(channelVos);
		//List<FloorAdvertisingPo> adList = baseDAO.getGenericByHql("from FloorAdvertisingPo where floorId=?", GUID.tryValueOf(v.getRecid()).toBytes());
		List<FloorAdvertisingVo> adVoList = getFloorAdList(v.getRecid());
		floorInfo.setAdvertisings(adVoList.toArray(new FloorAdvertisingVo[0]));
		return floorInfo;
	}
	
	private List<FloorAdvertisingVo> getFloorAdList(String floorId) {
		List<FloorAdvertisingPo> adList = baseDAO.getGenericByHql("from FloorAdvertisingPo where floorId=?", GUID.tryValueOf(floorId).toBytes());
		List<FloorAdvertisingVo> adVoList = BeanCopy.copys(FloorAdvertisingVo.class, adList);
		return adVoList;
	}
	
	@Override
	public List<ChannelVo> getList(String id) {
		List<ChannelVo> r = new ArrayList<ChannelVo>();
		if (Constant.ChannelEnum.PageType.HomePage.getCode().equals(id)
				|| Constant.ChannelEnum.PageType.SecondPage.getCode()
						.equals(id)) {
			StringBuffer hsql = new StringBuffer();
			hsql.append(" from ChannelPo p ");
			hsql.append(" where p.pageType=?");
			hsql.append(" order by p.code desc ");
			List<ChannelPo> list = this.baseDAO.getGenericByHql(hsql
					.toString(), id);
			if (CheckIsNull.isNotEmpty(list)) {
				r = BeanCopy.copys(ChannelVo.class, list);
			}
		} else {

			StringBuffer hsql = new StringBuffer();
			hsql.append(" from ChannelPo p ");
			hsql.append(" where p.floorid=?");
			hsql.append(" order by p.code asc ");
			List<ChannelPo> list = this.baseDAO.getGenericByHql(hsql
					.toString(), GUID.valueOf(id).toBytes());
			if (CheckIsNull.isNotEmpty(list)) {
				r = BeanCopy.copys(ChannelVo.class, list);
			}
		}
		return r;
	}
	@Override
	public List<ChannelVo> getMainMenuList() {
		List<ChannelVo> resultList = new ArrayList<ChannelVo>();
		String hsql = "from ChannelPo where isMainMenu=true order by code";
		List<ChannelPo> list = this.baseDAO.getGenericByHql(hsql);
		if (CheckIsNull.isNotEmpty(list)) {
			resultList = BeanCopy.copys(ChannelVo.class, list);
		}
		return resultList;
	}
	
	public List<ChannelVo> getChannelList(GetChannelListKey key) {
		List<ChannelVo> resultList = new ArrayList<ChannelVo>();
		String hsql = "from ChannelPo where 1=1";
		List<Object> parameters = new ArrayList<Object>();
		if (StringUtil.isNotEmpty(key.getFloorId())) {
			hsql += " and floorid=?";
			parameters.add(GUID.tryValueOf(key.getFloorId()).toBytes());
		}
		if (null != key.getPageType()) {
			hsql += " and pageType=?";
			parameters.add(key.getPageType().getCode());
		}
		List<ChannelPo> list = this.baseDAO.getGenericByHql(hsql, parameters.toArray());
		if (CheckIsNull.isNotEmpty(list)) {
			resultList = BeanCopy.copys(ChannelVo.class, list);
		}
		return resultList;
	}
	@Override
	public void delete(String id) {
		this.baseDAO.delete(ChannelPo.class, GUID.valueOf(id).toBytes());

	}

	@Override
	public void modifyPopularKeyWords(PopularKeyWordsVo v) {
		
		baseDAO.execteBulk("delete from PopularKeyWordsPo ");
		PopularKeyWordsPo po = new PopularKeyWordsPo();
		po.setRecid(GUID.randomID().toBytes());
		po.setPopularKeyWords(v.getPopularKeyWords());
		baseDAO.save(po);
		
		/*
		StringBuffer hsql = new StringBuffer();
		hsql.append(" from PopularKeyWordsPo p ");
		List<PopularKeyWordsPo> list = this.baseDAO.getGenericByHql(hsql
				.toString());
		if (CheckIsNull.isEmpty(list)) {
			PopularKeyWordsPo p = new PopularKeyWordsPo();
			p.setRecid(GUID.randomID().toBytes());
			p.setPopularKeyWords(v.getPopularKeyWords());
			this.baseDAO.save(p);
		} else {
			PopularKeyWordsPo p = list.get(0);
			p.setPopularKeyWords(v.getPopularKeyWords());
			this.baseDAO.save(p);
		}
		*/
	}

	@Override
	public PopularKeyWordsVo getPopularKeyWordsVo() {
		StringBuffer hsql = new StringBuffer();
		hsql.append(" from PopularKeyWordsPo p ");
		List<PopularKeyWordsPo> list = this.baseDAO.getGenericByHql(hsql
				.toString());
		if (CheckIsNull.isEmpty(list)) {
			return null;
		} else {
			PopularKeyWordsPo p = list.get(0);
			PopularKeyWordsVo v = BeanCopy.copy(PopularKeyWordsVo.class, p);
			return v;
		}
	}

	@Override
	public void modifyChannel(ChannelVo v) {
		ChannelPo p = this.baseDAO.get(ChannelPo.class, GUID.valueOf(
				v.getRecid()).toBytes());
		if (null != p) {
			if (CheckIsNull.isNotEmpty(v.getCode()))
				p.setCode(v.getCode());
			if (CheckIsNull.isNotEmpty(v.getName()))
				p.setName(v.getName());
			if (CheckIsNull.isNotEmpty(v.getChanneltype()))
				p.setChanneltype(v.getChanneltype());
			this.baseDAO.save(p);
		}

	}

	@Override
	public void deleteFloor(String id) {
		FloorPo f = this.baseDAO.get(FloorPo.class, GUID.valueOf(id)
				.toBytes());
		if (null == f) {
			return;
		}
		StringBuffer hsql = new StringBuffer();
		hsql.append(" from ChannelPo p ");
		hsql.append(" where p.floorid=?");
		List<ChannelPo> list = this.baseDAO.getGenericByHql(hsql.toString(),
				GUID.valueOf(id).toBytes());
		for (ChannelPo p : list) {
			StringBuffer hsql1 = new StringBuffer();
			if (Constant.ChannelEnum.ChannelType.Advertise.getCode().equals(
					p.getChanneltype())) {
				hsql1.append(" delete from ChannelAdvertisingPo p ");
				hsql1.append(" where p.channelid=?");
				this.baseDAO.execteBulk(hsql1.toString(), p.getRecid());
			} else if (Constant.ChannelEnum.ChannelType.Content.getCode()
					.equals(p.getChanneltype())) {
				hsql1.append(" delete from ChannelContentPo p ");
				hsql1.append(" where p.channelid=?");
				this.baseDAO.execteBulk(hsql1.toString(), p.getRecid());
			} else if (Constant.ChannelEnum.ChannelType.Goods.getCode().equals(
					p.getChanneltype())) {
				hsql1.append(" delete from ChannelGoodsPo p ");
				hsql1.append(" where p.channelid=?");
				this.baseDAO.execteBulk(hsql1.toString(), p.getRecid());
			}
			this.baseDAO.delete(ChannelPo.class, p.getRecid());
		}
		this.baseDAO.delete(FloorPo.class, f.getRecid());
	}

	@Override
	public List<FloorVo> getFloorList(String id) {
		StringBuffer hsql = new StringBuffer();
		hsql.append(" from FloorPo p");
		hsql.append(" order by p.ordinal asc ");

		List<FloorPo> list = this.baseDAO.getGenericByHql(hsql.toString());
		if (CheckIsNull.isEmpty(list))
			return new ArrayList<FloorVo>();
		else
			return BeanCopy.copys(FloorVo.class, list);

	}
	@Override
	public List<FloorInfo> getFloorInfoList() {
		List<FloorVo> voList = getFloorList(null);
		if (CheckIsNull.isEmpty(voList)) return new ArrayList<FloorInfo>();
		List<FloorInfo> infoList = new ArrayList<FloorInfo>();
		for (FloorVo vo : voList) {
			//FloorInfo info = BeanCopy.copy(FloorInfo.class, vo);
			FloorInfo info = new FloorInfo();
			info.setFloortype(vo.getFloortype());
			info.setGoodsCategoryId(vo.getGoodsCategoryId());
			info.setOrdinal(vo.getOrdinal());
			info.setRecid(vo.getRecid());
			info.setTheme(vo.getTheme());
			info.setImageUrl(vo.getImageUrl());
			info.setUrl(vo.getUrl());
			info.setTitle(vo.getTitle());
			ChannelVo[] channelVos = getList(vo.getRecid()).toArray(new ChannelVo[0]);
			info.setChannelVos(channelVos);
			List<FloorAdvertisingVo> adVoList = getFloorAdList(vo.getRecid());
			info.setAdvertisings(adVoList.toArray(new FloorAdvertisingVo[0]));
			infoList.add(info);
		}
		return infoList;
	}

	@Override
	public List<ChannelAdvertisingVo> getChannelAdvertisingVos(GetChannelAdvertisingKey key) {
//		StringBuffer hql = new StringBuffer();
//		hql.append(" from ChannelAdvertisingPo p ");
//		hql.append(" where p.channelid=?");
//		List<ChannelAdvertisingVo> rl = new ArrayList<ChannelAdvertisingVo>();
//		List<ChannelAdvertisingPo> list = this.baseDAO.getGenericByHql(hql
//				.toString(), GUID.valueOf(key.getChannelId()).toBytes());
		Map<String, Object> queryInfo = getQueryChannelAdvertisingInfo(key);
		List<ChannelAdvertisingVo> rl = new ArrayList<ChannelAdvertisingVo>();
		String hql = (String) queryInfo.get(QueryInfo.HQL.name());
		Object[] parameters = (Object[]) queryInfo.get(QueryInfo.PARAMETERS.name());
		List<ChannelAdvertisingPo> list = this.baseDAO.getGenericByPosition(hql, key.getOffset(), key.getPageSize(), parameters);
		if (CheckIsNull.isNotEmpty(list)) {
			rl = BeanCopy.copys(ChannelAdvertisingVo.class, list);
		}
		return rl;
	}
	@Override
	public int getChannelAdvertisingCount(GetChannelAdvertisingKey key) {
		Map<String, Object> queryInfo = getQueryChannelAdvertisingInfo(key);
		String hql = (String) queryInfo.get(QueryInfo.HQL.name());
		Object[] parameters = (Object[]) queryInfo.get(QueryInfo.PARAMETERS.name());
		hql = "select count(*) " + hql;
		List<Object> list = baseDAO.getGenericByHql(hql, parameters);
		return Integer.parseInt(list.get(0).toString());
	}
	
	private Map<String, Object> getQueryChannelAdvertisingInfo(GetChannelAdvertisingKey key) {
		Map<String, Object> queryInfo = new HashMap<String, Object>();
		List<Object> parameters = new ArrayList<Object>();
		String hql = " from ChannelAdvertisingPo where 1=1";
		if (StringUtil.isNotEmpty(key.getChannelId())) {
			hql += " and channelid=?";
			parameters.add(GUID.valueOf(key.getChannelId()).toBytes());
		}
		if (null != key.getStatus()) {
			hql += " and status=?";
			parameters.add(key.getStatus().getCode());
		}
		queryInfo.put(QueryInfo.HQL.name(), hql);
		queryInfo.put(QueryInfo.PARAMETERS.name(), parameters.toArray());
		return queryInfo;
	}

	@Override
	public List<ChannelContentVo> getChannelContentVos(GetChannelContentListKey key) {
//		StringBuffer hql = new StringBuffer();
//		hql.append(" from ChannelContentPo p ");
//		hql.append(" where p.channelid=?");
//		List<ChannelContentVo> rl = new ArrayList<ChannelContentVo>();
//		List<ChannelContentPo> list = this.baseDAO.getGenericByHql(hql
//				.toString(), GUID.valueOf(channelId).toBytes());
		
		Map<String, Object> queryInfo = getQueryChannelContentInfo(key);
		String hql = (String) queryInfo.get(QueryInfo.HQL.name());
		Object[] parameters = (Object[]) queryInfo.get(QueryInfo.PARAMETERS.name());
		List<ChannelContentVo> rl = new ArrayList<ChannelContentVo>();
		hql += " order by createdate desc";
		List<ChannelContentPo> list = this.baseDAO.getGenericByHql(hql, parameters);
		
		if (CheckIsNull.isNotEmpty(list)) {
			rl = BeanCopy.copys(ChannelContentVo.class, list);
		}
		return rl;
	}
	private Map<String, Object> getQueryChannelContentInfo(GetChannelContentListKey key) {
		Map<String, Object> queryInfo = new HashMap<String, Object>();
		List<Object> parameters = new ArrayList<Object>();
		String hql = " from ChannelContentPo where 1=1";
		if (StringUtil.isNotEmpty(key.getChannelId())) {
			hql += " and channelid=? ";
			parameters.add(GUID.valueOf(key.getChannelId()).toBytes());
		}
		if (null != key.getStatus()) {
			hql += " and status=? ";
			parameters.add(key.getStatus().getCode());
		}
		//Object[] parameters = new Object[] {GUID.valueOf(key.getChannelId()).toBytes()};
		queryInfo.put(QueryInfo.HQL.name(), hql);
		queryInfo.put(QueryInfo.PARAMETERS.name(), parameters.toArray());
		return queryInfo;
	}
	
	@Override
	public int getChannelContentCount(GetChannelContentListKey key) {
		Map<String, Object> queryInfo = getQueryChannelContentInfo(key);
		String hql = (String) queryInfo.get(QueryInfo.HQL.name());
		Object[] parameters = (Object[]) queryInfo.get(QueryInfo.PARAMETERS.name());
		hql = "select count(*) " + hql;
		List<Object> list = baseDAO.getGenericByHql(hql, parameters);
		return Integer.parseInt(list.get(0).toString());
	}
	
	@Override
	public List<ChannelGoodsVo> getChannelGoodsVos(GetChannelGoodsListKey key) {
		List<ChannelGoodsVo> rl = new ArrayList<ChannelGoodsVo>();

		Map<String, Object> queryInfo = getQueryChannelGoodsInfo(key);
		String hql = (String) queryInfo.get(QueryInfo.HQL.name());
		Object[] parameters = (Object[]) queryInfo.get(QueryInfo.PARAMETERS.name());

		StringBuffer sb = new StringBuffer();
		sb.append("select channelGoods, goods.goodsname, goods.goodsspec, goods.goodsunit, goods.originalprice, goods.realprice, " +
				"goods.saledCount, goods.freedelivery, goods.picturepath1, ")
			.append("goods.picturepath2, goods.picturepath3, goods.mostSales, goods.popular, " +
					"goods.vantagesGoods, goods.vantagesCost, goods.publishDate, goods.published");
		sb.append(hql);
		if (key.getSortColumn() != null) {
			sb.append(" order by goods." + key.getSortColumn().getColumnName());
			if (key.getSortType() == null) {
				sb.append(" " + SortType.Desc.name());
			} else {
				sb.append(" " + key.getSortType().name());
			}
		}
		try {
			Query query = baseDAO.getSession().createQuery(sb.toString());
			query.setFirstResult(key.getOffset());
			query.setMaxResults(key.getPageSize());
			for (int pIndex = 0; pIndex < parameters.length; pIndex++) {
				query.setParameter(pIndex, parameters[pIndex]);
			}
			@SuppressWarnings("unchecked")
			List<Object> result = query.list(); 
			Iterator<Object> it = result.iterator();
			while (it.hasNext()) { 
				Object[] rs = (Object[]) it.next();
				ChannelGoodsPo po = (ChannelGoodsPo) rs[0];
				ChannelGoodsVo vo = BeanCopy.copy(ChannelGoodsVo.class, po);
//				GoodsPo goodsPo = (GoodsPo) rs[1];
//				GoodsVo goodsVo = BeanCopy.copy(GoodsVo.class, goodsPo);
//				vo.setGoods(goodsVo);
				vo.setGoodsName((String) rs[1]);
				vo.setGoodsspec((String) rs[2]);
				vo.setGoodsunit((String) rs[3]);
				vo.setOriginalprice((Double) rs[4]);
				vo.setRealprice((Double) rs[5]);
				vo.setSaledCount((Double) rs[6]);
				vo.setFreedelivery((Boolean) rs[7]);
				vo.setPicturepath1((String) rs[8]);
				vo.setPicturepath2((String) rs[9]);
				vo.setPicturepath3((String) rs[10]);
				vo.setMostSales((Boolean) rs[11]);
				vo.setPopular((Boolean) rs[12]);
				vo.setVantagesGoods((Boolean) rs[13]);
				vo.setVantagesCost((Double) rs[14]);
				vo.setPublishDate((Date)rs[15]);
				vo.setPublished((Boolean)rs[16]);
				rl.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		baseDAO.getSession().flush();
		return rl;
	}
	
	public List<GoodsVo> getGoodsListByChannel(GetChannelGoodsListKey key) {
		Map<String, Object> queryInfo = getQueryChannelGoodsInfo(key);
		String hql = (String) queryInfo.get(QueryInfo.HQL.name());
		Object[] parameters = (Object[]) queryInfo.get(QueryInfo.PARAMETERS.name());

		StringBuffer sb = new StringBuffer();
		sb.append(hql);
		if (key.getSortColumn() != null) {
			sb.append(" order by goods." + key.getSortColumn().getColumnName());
			if (key.getSortType() == null) {
				sb.append(" " + SortType.Desc.name());
			} else {
				sb.append(" " + key.getSortType().name());
			}
		}
		List<GoodsVo> goodsList = new ArrayList<GoodsVo>();
		try {
			Query query = baseDAO.getSession().createQuery(sb.toString());
			query.setFirstResult(key.getOffset());
			query.setMaxResults(key.getPageSize());
			for (int pIndex = 0; pIndex < parameters.length; pIndex++) {
				query.setParameter(pIndex, parameters[pIndex]);
			}
			@SuppressWarnings("unchecked")
			List<Object> result = query.list(); 
			Iterator<Object> it = result.iterator();
			while (it.hasNext()) { 
				Object[] rs = (Object[]) it.next();
				GoodsPo goodsPo = (GoodsPo) rs[1];
				GoodsVo goodsVo = BeanCopy.copy(GoodsVo.class, goodsPo);
				goodsList.add(goodsVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		baseDAO.getSession().flush();
		return goodsList;
	}
	
	public String[] getChannelGoodsIds(String channelId) {
		List<byte[]> list = this.baseDAO.getGenericByHql("select goodsid from ChannelGoodsPo where channelid=?", GUID.valueOf(channelId).toBytes());
		String[] result = new String[list.size()];
		int index = 0;
		for (byte[] recid : list) {
			result[index] = GUID.valueOf(recid).toString();
			index++;
		}
		return result;
	}
	private Map<String, Object> getQueryChannelGoodsInfo(GetChannelGoodsListKey key) {
		Map<String, Object> queryInfo = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		List<Object> parameters = new ArrayList<Object>();
		sb.append(" from ChannelGoodsPo channelGoods, GoodsPo goods ");
		sb.append(" where channelGoods.goodsid=goods.recid and channelGoods.channelid=? and goods.published=true");
		parameters.add(GUID.valueOf(key.getChannelId()).toBytes());
		
		if (null != key.getStatus()) {
			sb.append(" and status='" + key.getStatus().getCode() + "'");
		}
		if (null != key.getGoodsCategoryId()) {
			sb.append(" and (goods.categoryid1=? or goods.categoryid2=? or categoryid3=?)");
			parameters.add(GUID.valueOf(key.getGoodsCategoryId()).toBytes());
			parameters.add(GUID.valueOf(key.getGoodsCategoryId()).toBytes());
			parameters.add(GUID.valueOf(key.getGoodsCategoryId()).toBytes());
			
		}
		if (key.getPriceBegin() > -1.0) {
			sb.append(" and goods.realprice>?");
			parameters.add(key.getPriceBegin());
		}
		if (key.getPriceEnd() > -1.0) {
			sb.append(" and goods.realprice<?");
			parameters.add(key.getPriceEnd());
		}
		queryInfo.put(QueryInfo.HQL.name(), sb.toString());
		queryInfo.put(QueryInfo.PARAMETERS.name(), parameters.toArray());
		return queryInfo;
	}
	
	public int getChannelGoodsCount(GetChannelGoodsListKey key) {
		Map<String, Object> queryInfo = getQueryChannelGoodsInfo(key);
		String hql = (String) queryInfo.get(QueryInfo.HQL.name());
		Object[] parameters = (Object[]) queryInfo.get(QueryInfo.PARAMETERS.name());
		hql = "select count(*) " + hql;
		List<Object> list = baseDAO.getGenericByHql(hql, parameters);
		return Integer.parseInt(list.get(0).toString());
	}
	
	@Override
	public void deleteChannelAdvertising(String id) {
		this.baseDAO.delete(ChannelAdvertisingPo.class, GUID.valueOf(id)
				.toBytes());
	}

	@Override
	public void deleteChannelContent(String id) {
		this.baseDAO.delete(ChannelContentPo.class, GUID.valueOf(id)
				.toBytes());

	}

	@Override
	public void deleteChannelGoods(String channelId, String goodsId) throws ChannelThrowable {
		if(CheckIsNull.isEmpty(channelId)||CheckIsNull.isEmpty(goodsId))
		{
			throw new ChannelThrowable("channelId||goodsId不能为空！");
		}
		StringBuffer hql = new StringBuffer();
		hql.append("delete from ChannelGoodsPo p ");
		hql.append(" where p.channelid=? ");
		hql.append(" and p.goodsid=? ");
		this.baseDAO.execteBulk(hql.toString(), GUID.valueOf(channelId).toBytes(),GUID.valueOf(goodsId).toBytes());
	}

	@Override
	public boolean updateChannelGoodsStatus(String id, boolean isEnable) {
//		boolean result = false;
		int result = -1;
		String status = isEnable ? ChannelContentsStatus.ENABLE.getCode() : ChannelContentsStatus.DISABLE.getCode();
		String hql = "update ChannelGoodsPo set status='" + status + "' where recid=?";
		try {
			result = baseDAO.execteBulk(hql, GUID.tryValueOf(id).toBytes());
		} catch(Exception e) {
			e.printStackTrace();
		}
		return (result == 1);
//		try {
//			ChannelGoodsPo po = genericDAO.get(ChannelGoodsPo.class, GUID.tryValueOf(id).toBytes());
//			po.setStatus(status);
//			po.setOrdinal(0);
//			genericDAO.save(po);
//			result = true;
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		return result;
	}
	
	@Override
	public boolean updateChannelContentStatus(String id, boolean isEnable) {
		String status = isEnable ? ChannelContentsStatus.ENABLE.getCode() : ChannelContentsStatus.DISABLE.getCode();
		String hql = "update ChannelContentPo set status='" + status + "' where recid=?";
		int result = baseDAO.execteBulk(hql, GUID.tryValueOf(id).toBytes());
		return (result == 1);
	}
	
	@Override
	public boolean updateChannelAdvertisingStatus(String id, boolean isEnable) {
		String hql = "";
		int result = 0;
		if (isEnable) {
			ChannelAdvertisingPo po = baseDAO.get(ChannelAdvertisingPo.class, GUID.tryValueOf(id).toBytes());
			hql = "update ChannelAdvertisingPo set status='" + ChannelContentsStatus.DISABLE.getCode() + "' where channelid=? and recid <> ? ";
			baseDAO.execteBulk(hql, po.getChannelid(), GUID.tryValueOf(id).toBytes());
			hql = "update ChannelAdvertisingPo set status='" + ChannelContentsStatus.ENABLE.getCode() + "' where recid=?";
			result = baseDAO.execteBulk(hql, GUID.tryValueOf(id).toBytes());
		} else {
			hql = "update ChannelAdvertisingPo set status='" + ChannelContentsStatus.DISABLE.getCode() + "' where recid=?";
			result = baseDAO.execteBulk(hql, GUID.tryValueOf(id).toBytes());
		}
		return (result == 1);
	}
	
}
