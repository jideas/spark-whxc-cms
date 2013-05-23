/**
 * 
 */
package com.spark.cms.services.station.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.base.common.utils.CheckIsNull;
import com.spark.base.hibernate.GenericDAO;
import com.spark.base.type.GUID;
import com.spark.cms.base.utils.BeanCopy;
import com.spark.cms.dao.po.StationPo;
import com.spark.cms.services.form.station.StationListItem;
import com.spark.cms.services.station.GetStationListKey;
import com.spark.cms.services.station.StationService;
import com.spark.cms.services.vo.StationVo;

/**
 * @author Jideas
 * 
 */
@Service
public class StationServiceImpl implements StationService {

	@Autowired
	private GenericDAO baseDAO;

	/*
	 * (non-Javadoc) 根据id查询站点详情
	 * 
	 * @see com.spark.cms.services.station.StationService#find(java.lang.String)
	 */
	@Override
	public StationVo find(String recid) {
		List<StationPo> list = null;
		StringBuilder hql = new StringBuilder();
		hql.append("from StationPo as t");
		hql.append(" where ");
		hql.append(" t.recid=?");
		list = this.baseDAO.getGenericByHql(hql.toString(), GUID.valueOf(recid).toBytes());
		if (null == list || list.isEmpty()) {
			return null;
		}
		StationVo vo = BeanCopy.copy(StationVo.class, list.get(0));
		String stationNo = vo.getStationno();
		String str1 = stationNo.substring(0, 4);
		String str2 = stationNo.substring(4, 6);
		String str3 = stationNo.substring(6);
		if (str2.indexOf("4") >= 0) {
			str2 = str2.replace("4", "A");
			stationNo = str1 + str2 + str3;
		}
		vo.setStationno(stationNo);
		return vo;
	}

	/*
	 * (non-Javadoc)查询站点下拉列表数据
	 * 
	 * @see com.spark.cms.services.station.StationService#getComboListItem(com.spark.cms.services.station.GetStationListKey)
	 */
	@Override
	public List<StationListItem> getComboListItem(GetStationListKey key) {
		List<StationPo> list = null;
		StringBuilder hql = new StringBuilder();
		hql.append("from StationPo as t");
		if (CheckIsNull.isNotEmpty(key.getAddressCode())) {
			hql.append(" where ");
			hql.append(" t.town");
			hql.append(" ='" + key.getAddressCode() + "'");
			hql.append(" and t.isstop = ''");
		}
		list = this.baseDAO.getGenericByHql(hql.toString());
		List<StationListItem> itemlist = new ArrayList<StationListItem>();
		for (StationPo po : list) {

			String stationNo = po.getStationno();
			String str1 = stationNo.substring(0, 4);
			String str2 = stationNo.substring(4, 6);
			String str3 = stationNo.substring(6);
			if (str2.indexOf("4") >= 0) {
				str2 = str2.replace("4", "A");
				stationNo = str1 + str2 + str3;
			}
//			itemlist
//					.add(new StationListItem(GUID.valueOf(po.getRecid()).toString(), "[" + stationNo + "]" + po.getStationname()));
			itemlist
			.add(new StationListItem(GUID.valueOf(po.getRecid()).toString(), po.getStationname()));
		}
		return itemlist;
	}

	@Override
	public List<StationVo> getList(GetStationListKey key) {
		List<StationPo> list = null;
		StringBuilder hql = new StringBuilder();
		hql.append("from StationPo as t");
		if (CheckIsNull.isNotEmpty(key.getAddressCode())) {
			hql.append(" where ");
			hql.append(" t.town");
			hql.append(" ='" + key.getAddressCode() + "'");
			hql.append(" and t.isstop = ''");
		}
		list = this.baseDAO.getGenericByHql(hql.toString());
		List<StationVo> stationVos = BeanCopy.copys(StationVo.class, list);
		
		return stationVos;
	}
}
