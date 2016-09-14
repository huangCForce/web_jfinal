package com.huang.utils;

import com.huang.common.GlobalConstant;
import com.jfinal.kit.StrKit;

/**
 * 系统模块工具类
 * 
 * @author huangchentao
 * @date Mar 21, 2013
 */
public class SystemUtils {

	/**
	 * 数据加密
	 * 
	 * @param data要加密的数据
	 * @param key加密参数
	 * @return String加密后的数据
	 * 
	 * @author huangchentao
	 * @date Apr 11, 2013
	 */
	public static String encrypt(String data, String key) {
		return (StrKit.notBlank(data)) ? CryptoUtils.encrypt(data, fixKey(key))
				: null;
	}

	/**
	 * 数据加密
	 * 
	 * @param data要加密的数据
	 * @return String加密后的数据
	 */
	public static String encrypt(String data) {
		return encrypt(data, "");
	}

	/**
	 * 数据解密
	 * 
	 * @param data要解密的数据
	 * @param key解密参数
	 * @return String解密后的数据
	 * 
	 * @author huangchentao
	 * @date Mar 21, 2013
	 */
	public static String decrypt(String data, String key) {
		return (StrKit.notBlank(data)) ? CryptoUtils.decrypt(data, fixKey(key))
				: null;
	}

	/**
	 * 数据解密
	 * 
	 * @param data要解密的数据
	 * @return String解密后的数据
	 */
	public static String decrypt(String data) {
		return decrypt(data, "");
	}

	/**
	 * 对盐值进行转换
	 * 
	 * @param key
	 * @return
	 * 
	 * @author huangchentao
	 * @date Mar 29, 2013
	 */
	private static String fixKey(String key) {
		if (StrKit.isBlank(key)) {
			return GlobalConstant.CRYPT_KEY;
		}
		while (key.length() % 8 != 0) {
			key += "A";
		}
		return key;
	}

	public static void main(String[] args) {
		System.err.println(encrypt("admin", "admin"));
	}
}
