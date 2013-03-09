/**
 * 
 */
package com.spark.front.utils;

import chinapay.PrivateKey;
import chinapay.SecureLink;

import com.spark.cms.base.constant.card.CMS;
import com.spark.front.form.payment.UnionPayForm;

/**
 * @author Jideas
 * 
 */
public class UnionPayChkValue {

	public static String getPayQueryChkValue(String realPath, String str) {
		PrivateKey key = new PrivateKey();
		SecureLink t;
		boolean flag = key.buildKey(CMS.PayInfo.UnionPayUserNo, 0, realPath + "\\xml\\key\\MerPrK.key");
		if (flag == false) {
			System.out.println("build key error!");
			return null;
		}
		t = new chinapay.SecureLink(key);
		return t.Sign(str);
	}

	public static boolean checkVerifyTransResponse(UnionPayForm form, String realPath) {
		PrivateKey key = new PrivateKey();
		SecureLink t;
		boolean flag = key.buildKey("999999999999999", 0, realPath + "\\xml\\key\\PgPubk.key");
		if (flag == false) {
			System.out.println("build key error!");
			return false;
		}
		t = new chinapay.SecureLink(key);
		return t.verifyTransResponse(form.getMerid(), form.getOrderno(), form.getAmount(), form.getCurrencycode(), form
				.getTransdate(), form.getTranstype(), form.getStatus(), form.getCheckvalue());
	}
	public static boolean checkVerifyTransResponse2(UnionPayForm form, String realPath) {
		PrivateKey key = new PrivateKey();
		SecureLink t;
		boolean flag = key.buildKey(CMS.PayInfo.UnionPayUserNo, 0, realPath + "\\xml\\key\\MerPrK.key");
		if (flag == false) {
			System.out.println("build key error!");
			return false;
		}
		t = new chinapay.SecureLink(key);
		return t.verifyTransResponse(form.getMerid(), form.getOrderno(), form.getAmount(), form.getCurrencycode(), form
				.getTransdate(), form.getTranstype(), form.getStatus(), form.getCheckvalue());
	}
	public static String getPayMerPrKValue(String MerId, String OrdId, String TransAmt, String CuryId,
			String TransDate, String TransType, String realPath, String priv1) {
		PrivateKey key = new PrivateKey();
		SecureLink t;
		boolean flag = key.buildKey(MerId, 0, realPath + "\\xml\\key\\MerPrK.key");
		if (flag == false) {
			System.out.println("build key error!");
			return null;
		}
		t = new chinapay.SecureLink(key);
		String CheckValue = t.Sign(MerId + OrdId + TransAmt + CuryId + TransDate + TransType + priv1);
		return CheckValue;
	}
}
