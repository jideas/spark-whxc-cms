/**
 * 
 */
package com.spark.cms.services.serial.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.base.hibernate.GenericDAO;
import com.spark.base.type.GUID;
import com.spark.cms.base.constant.SheetNumberType;
import com.spark.cms.dao.po.SerialNumberPo;
import com.spark.cms.services.serial.SerialNumberService;

/**
 * @author Jideas 生成编号
 */
@Service
public class SerialNumberServiceImpl implements SerialNumberService {

	@Autowired
	private GenericDAO baseDAO;

	@Override
	public String exeGetSheetNumber(SheetNumberType snType) {
		return exeGetSheetNumber(snType, false);
	}

	/*
	 * (non-Javadoc)得到新的编号
	 * 
	 * @see com.spark.cms.services.serial.SerialNumberService#getSheetNumber(com.
	 *      spark.cms.base.constant.SheetNumberType)
	 */
	@Override
	public String exeGetSheetNumber(SheetNumberType snType, Boolean withOutFour) {
		StringBuffer buffer = new StringBuffer(snType.getDefaultPrefix());
		Calendar calendar = Calendar.getInstance();
		// year
		buffer.append(calendar.get(Calendar.YEAR));
		// month
		int month = calendar.get(Calendar.MONTH) + 1;
		if (month < 10) {
			buffer.append('0');
		}
		buffer.append(month);
		// day
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		if (day < 10) {
			buffer.append('0');
		}
		buffer.append(day);
		//
		int serialLength = snType.getLength();
		StringBuffer serialBuffer = new StringBuffer(serialLength);
		synchronized (this) {
			serialBuffer.append(doQueryIt(snType, buffer.toString(), withOutFour));
		}
		if (serialBuffer.length() > serialLength) {
			throw new IllegalStateException("单据编号超出设置的最大范围");
		}
		while (serialBuffer.length() < serialLength) {
			serialBuffer.insert(0, '0');
		}
		return buffer.append(serialBuffer).toString();
	}
	/*
	 * (non-Javadoc)得到新的编号
	 * 
	 * @see com.spark.cms.services.serial.SerialNumberService#getSheetNumber(com.
	 *      spark.cms.base.constant.SheetNumberType)
	 */
	@Override
	public String exeGetSheetNumberWithOutDate(SheetNumberType snType) {
		StringBuffer buffer = new StringBuffer(snType.getDefaultPrefix());
		//
		int serialLength = snType.getLength();
		StringBuffer serialBuffer = new StringBuffer(serialLength);
		synchronized (this) {
			serialBuffer.append(doQueryIt(snType, buffer.toString(), false));
		}
		if (serialBuffer.length() > serialLength) {
			throw new IllegalStateException("单据编号超出设置的最大范围");
		}
		while (serialBuffer.length() < serialLength) {
			serialBuffer.insert(0, '0');
		}
		return buffer.append(serialBuffer).toString();
	}
	private long doQueryIt(SheetNumberType snType, String prefix, Boolean withOutFour) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from SerialNumberPo as t");
		hql.append(" where t.type='" + snType.name() + "'");
		hql.append(" and ");
		hql.append(" t.prefix='" + prefix + "'");
		List<SerialNumberPo> list = null;
		try {
			list = this.baseDAO.getGenericByHql(hql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		long result = 0;
		if (list.isEmpty()) {
			result = 1;
		} else {
			result = list.get(0).getSerial() + 1;
			while (withOutFour && ("" + result).indexOf("4") > -1) {
				result++;
			}
			this.baseDAO.delete(SerialNumberPo.class, list.get(0).getRecid());
		}
		SerialNumberPo po = new SerialNumberPo();
		po.setRecid(GUID.randomID().toBytes());
		po.setPrefix(prefix);
		po.setCreateTime(System.currentTimeMillis());
		po.setSerial(result);
		po.setType(snType.name());
		this.baseDAO.save(po);
		return result;
	}

}
