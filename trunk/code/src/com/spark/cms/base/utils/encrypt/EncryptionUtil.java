package com.spark.cms.base.utils.encrypt;

public abstract class EncryptionUtil {

	/**
	 * 用md5加密字符串
	 * 
	 * @param inbuf
	 * @return
	 */
	public static String encryptMD5(String inbuf) {
		MD5 md5 = new MD5();
		return md5.getMD5ofStr(inbuf);
	}

	/**
	 * 用AES加密字符串
	 * 
	 * @param inbuf
	 *            要解密的字符串
	 * @param secretkey
	 *            秘钥
	 * @throws Exception
	 */
	public static String encryptAES(String inbuf, String secretkey) throws Exception {
		String s = EncryptionUtil.encryptMD5(secretkey);
		s = s.substring(8, 24);
		return AES.Encrypt(inbuf, s);
	}

	/**
	 * 用AES解密字符串
	 * 
	 * @param inbuf
	 *            要解密的字符串
	 * @param secretkey
	 *            秘钥
	 * @return
	 * @throws Exception
	 */
	public static String decryptAES(String inbuf, String secretkey) throws Exception {
		String s = EncryptionUtil.encryptMD5(secretkey);
		s = s.substring(8, 24);
		return AES.Decrypt(inbuf, s);
	}
}
