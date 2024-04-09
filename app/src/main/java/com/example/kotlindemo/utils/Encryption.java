package com.example.kotlindemo.utils;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Encoder;


/**
 *	加解密工具<br>
 *	DES加密双方约定秘钥,加密后使用base64转换<br> 
 */
public class Encryption {
	
	private static final String  TAG = "ENCRYPTION";

	private static final String DES_ALGORITHM = "DES";
	
	private static final String secretKey = "yzksecr0";

	/**
	 * DES加密
	 * @param plainData
	 * @param
	 * @return
	 * @throws Exception
	 */
	public static String encryption(String plainData){

		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(DES_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, Encryption.generateKey());
		} catch (NoSuchAlgorithmException e) {
			Log.e(TAG,e.getMessage().toString());
			return null;
		} catch (NoSuchPaddingException e) {
			Log.e(TAG,e.getMessage().toString());
			return null;
		} catch (InvalidKeyException e) {
			Log.e(TAG,e.getMessage().toString());
			return null;
		}catch (InvalidKeySpecException e) {
			Log.e(TAG,e.getMessage().toString());
			return null;
		}

		String result = null;
		try {
			// length must be multiple of 8 when decrypting with padded cipher异常，
			// 不能把加密后的字节数组直接转换成字符串
			byte[] buf = cipher.doFinal(plainData.getBytes("UTF-8"));
			result = new BASE64Encoder().encode(buf);
			return result;
		} catch (IllegalBlockSizeException e) {
			Log.e(TAG,e.getMessage().toString());
		} catch (BadPaddingException e) {
			Log.e(TAG,e.getMessage().toString());
		} catch (UnsupportedEncodingException e) {
			Log.e(TAG,e.getMessage().toString());
		} 
		return result;
	}

	/**
	 * DES解密
	 * @param secretData
	 * @param
	 * @return
	 * @throws Exception
	 */
//	public static String decryption(String secretData)throws Exception {
//
//		Cipher cipher = null;
//		try {
//			cipher = Cipher.getInstance(DES_ALGORITHM);
//			cipher.init(Cipher.DECRYPT_MODE, generateKey());
//		} catch (NoSuchAlgorithmException e) {
//			throw new Exception("NoSuchAlgorithmException", e);
//		} catch (NoSuchPaddingException e) {
//			throw new Exception("NoSuchPaddingException", e);
//		} catch (InvalidKeyException e) {
//			throw new Exception("InvalidKeyException", e);
//		}
//
//		BASE64Decoder decoder = new BASE64Decoder();
//		byte[] decryptFrom = decoder.decodeBuffer(secretData);
//		try {
//			byte[] buf = cipher.doFinal(decryptFrom);
//			String result = new String(buf,"UTF-8");
//			return result;
//		} catch (IllegalBlockSizeException e) {
//			throw new Exception("IllegalBlockSizeException", e);
//		} catch (BadPaddingException e) {
//			throw new Exception("BadPaddingException", e);
//		}
//	}

	/**
	 * 获得秘密密钥
	 * @param
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException 
	 * @throws InvalidKeyException 
	 */
	//	该实现在 windows 上每次生成的 key 都相同，但是在 solaris 或部分 linux 系统上则不同
	private static SecretKey generateKey()throws NoSuchAlgorithmException, 
		InvalidKeySpecException, InvalidKeyException {
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES_ALGORITHM);  
	    DESKeySpec keySpec = new DESKeySpec(secretKey.getBytes());  
	    keyFactory.generateSecret(keySpec);  
	    return keyFactory.generateSecret(keySpec);  
	}

	/**将16进制转换为二进制 
	 * @param hexStr 
	 * @return 
	 */  
	public static byte[] parseHexStr2Byte(String hexStr) {  
		if (hexStr.length() < 1)  
			return null;  
		byte[] result = new byte[hexStr.length()/2];  
		for (int i = 0;i< hexStr.length()/2; i++) {  
			int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);  
			int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);  
			result[i] = (byte) (high * 16 + low);  
		}  
		return result;  
	}  
	
	/**将二进制转换成16进制 
	 * @param buf 
	 * @return 
	 */  
	public static String parseByte2HexStr(byte buf[]) {  
		StringBuffer sb = new StringBuffer();  
		for (int i = 0; i < buf.length; i++) {  
			String hex = Integer.toHexString(buf[i] & 0xFF);  
			if (hex.length() == 1) {  
				hex = '0' + hex;  
			}  
			sb.append(hex.toUpperCase());  
		}  
	        return sb.toString();  
	}
	
}
