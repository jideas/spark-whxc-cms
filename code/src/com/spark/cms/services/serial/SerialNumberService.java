/**
 * 
 */
package com.spark.cms.services.serial;

import com.spark.cms.base.constant.SheetNumberType;

/**
 * @author Jideas
 * 
 */

public interface SerialNumberService {

	String exeGetSheetNumber(SheetNumberType snType);

	/**
	 * @param snType
	 * @param withOutFour
	 * @return
	 */
	String exeGetSheetNumber(SheetNumberType snType, Boolean withOutFour);

	/**
	 * @param snType
	 * @return
	 */
	String exeGetSheetNumberWithOutDate(SheetNumberType snType);

}
