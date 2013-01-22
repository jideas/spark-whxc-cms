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
	 * ����id��ѯվ��
	 */
	StationVo find(String stationId);

	/**
	 * ��ѯվ�������б�����
	 * 
	 * @param key
	 * @return
	 */
	List<StationListItem> getComboListItem(GetStationListKey key);
	/**
	 * ��ѯվ���б�
	 * 
	 * @param key
	 * @return
	 */
	List<StationVo> getList(GetStationListKey key);
}
