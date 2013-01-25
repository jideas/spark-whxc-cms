/**
 * 
 */
package com.spark.front.utils;

/**
 * @author Jideas
 * 
 */
public interface RegEx {

	String Mobile = "^1[3-8]\\d{9}$";
	String Email = "^[0-9a-z][a-z0-9\\._-]{1,}@[a-z0-9-]{1,}[a-z0-9]\\.[a-z\\.]{1,}[a-z]$";
	String UserName = "^[a-zA-Z0-9_\u4e00-\u9fa5]+$";
	String PassWord = "^[A-Za-z0-9]+$";
	String PostCode = "[^0]\\d{5}";
	String OnlyNumber = "^[0-9]*$";
	String telephone = "(\\d{11})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$";

	
	
}
