package com.example.kotlindemo.utils;

import org.apache.commons.codec.binary.Base64;


/**
 * 编码解码工具类
 * @creator yuyoo4j(zhandulin)
 * @author www.acc369.com
 * @date 2014年8月5日 上午9:15:47
 *
 */
public final class CodecUtil {
	
	/**
	 * 十六进制定义
	 */
	private final static byte[] hex = "0123456789ABCDEF".getBytes();

	public static String encodeBase64(byte[] data) {
		
		byte[] rv = Base64.encodeBase64(data);
		
		return new String(rv);
	}
	
	public static byte[] decodeBase64(String data) {
		
		return Base64.decodeBase64(data);
	} 

	/**
	 * 将一个IPv4的地址转换为1一个整数
	 * */
	public static int encodeIP(String ipv4) {
		String[] pieces = ipv4.split("\\.");
		int ipi = 0;
		int i = 3;
		for (String piece : pieces) {
			ipi |= Integer.parseInt(piece) << (8 * (i--));
		}
		return ipi;
	}

	/**
	 * 将一个整数转换为一个IPv4地址
	 * */
	public static String decodeIP(int ipv4) {
		StringBuilder sb = new StringBuilder(15);
		for (int i = 3; i >= 0; i--) {
			sb.append((ipv4 >> (8 * i)) & 0xff);
			if (i > 0) {
				sb.append(".");
			}
		}
		return sb.toString();
	}

	/**
	 * 从字节数组到十六进制字符串转换 
	 * @param  - 字节数组
	 * @return - 十六进制字符串
	 */
	public static String encodeHex(byte[] data) {

		try {
			byte[] buff = new byte[2 * data.length];
			for (int i = 0; i < data.length; i++) {
				buff[2 * i] = hex[(data[i] >> 4) & 0x0f];
				buff[2 * i + 1] = hex[data[i] & 0x0f];
			}
			return new String(buff);
		} catch (Exception ex) {
//			throw Errors.clone(Errors.E720, "十六进制编码异常：" + new String(data));
			return null;
		}
	}
	 
	/**
	 * 从十六进制字符串到字节数组转换 
	 * @param  - 十六进制字符串
	 * @return - 字节数组
	 */
	public static byte[] decodeHex(String hex) {

		try {
			byte[] b = new byte[hex.length() / 2];
			int j = 0;
			for (int i = 0; i < b.length; i++) {
				char c0 = hex.charAt(j++);
				char c1 = hex.charAt(j++);
				b[i] = (byte) ((parse(c0) << 4) | parse(c1));
			}
			return b;
		} catch (Exception ex) {
//			throw Errors.clone(Errors.E720, "十六进制解码异常：" + hex);
			return null;
		}
	}
	 
	/**
	 * 整数到字节数组转换 
	 * @param n - 整数
	 * @return - 字节数组
	 */
	public static byte[] encodeInt(int n) {

		byte[] ab = new byte[4];
		ab[0] = (byte) (0xff & n);
		ab[1] = (byte) ((0xff00 & n) >> 8);
		ab[2] = (byte) ((0xff0000 & n) >> 16);
		ab[3] = (byte) ((0xff000000 & n) >> 24);
		return ab;
	}

	/**
	 * 字节数组到整数的转换 
	 * @param b - 字节数组
	 * @return - 整数
	 */
	public static int decodeInt(byte b[]) {

		int s = 0;
		s = ((((b[0] & 0xff) << 8 | (b[1] & 0xff)) << 8) | (b[2] & 0xff)) << 8 | (b[3] & 0xff);
		return s;
	}


	/**
	 * 将字符转成整数 
	 * @param c - 字符
	 * @return - 整数
	 */
	private static int parse(char c) {
		if (c >= 'a')
			return (c - 'a' + 10) & 0x0f;
		if (c >= 'A')
			return (c - 'A' + 10) & 0x0f;
		return (c - '0') & 0x0f;
	}
	
	/**
	 * 防止非法实例化
	 */
	private CodecUtil() {}
}
