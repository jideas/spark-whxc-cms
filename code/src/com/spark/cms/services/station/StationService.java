/**
 * 
 */
package com.spark.cms.services.station;

import java.util.List;

import com.spark.cms.services.form.station.StationListItem;
import com.spark.cms.services.vo.StationVo;

/**
 * @author Jideas
 * 
 */
public interface StationService {

	/**
	 * 根据id查询站点
	 */
	StationVo find(String stationId);

	/**
	 * 查询站点下拉列表数据
	 * 
	 * @param key
	 * @return
	 */
	List<StationListItem> getComboListItem(GetStationListKey key);
	/**
	 * 查询站点列表
	 * 
	 * @param key
	 * @return
	 */
	List<StationVo> getList(GetStationListKey key);
}
