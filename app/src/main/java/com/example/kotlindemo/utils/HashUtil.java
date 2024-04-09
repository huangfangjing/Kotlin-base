package com.example.kotlindemo.utils;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * hash算法工具类
 *
 * @author www.acc369.com
 * @creator yuyoo
 * @date 2014年7月2日 上午9:55:36
 */
@SuppressWarnings("rawtypes")
public final class HashUtil {

    private static final long FNV_64_INIT = 0xcbf29ce484222325L;
    private static final long FNV_64_PRIME = 0x100000001b3L;


    /**
     * 对key进行应用Ketama算法的hash运算
     * 备注: Ketama hash算法:key进行md5,然后取最高八个字节作为long类型的hash值
     * 特点: 优先保证hash的分布均匀性
     *
     * @param key - 被hash的key
     * @return - hash值(hashcode)
     */
    public static long kemata64(String key) {

        byte[] rtv = md5(key.getBytes());
        return ((long) rtv[rtv.length - 1] << 56)
                + ((long) (rtv[rtv.length - 2] & 255) << 48)
                + ((long) (rtv[rtv.length - 3] & 255) << 40)
                + ((long) (rtv[rtv.length - 4] & 255) << 32)
                + ((long) (rtv[rtv.length - 5] & 255) << 24)
                + ((rtv[rtv.length - 6] & 255) << 16)
                + ((rtv[rtv.length - 7] & 255) << 8)
                + ((rtv[rtv.length - 8] & 255) << 0);
    }


    /**
     * @param data
     * @return
     * @description md5 hash算法
     */
    public static byte[] md5(byte[] data) {
        return md("MD5", data);
    }

    public static String md5(String string) {
        String retString = "";
        if (TextUtils.isEmpty(string)) {
            return retString;
        }
        try {
            retString = new BigInteger(1, md("MD5", string.getBytes("UTF-8"))).toString(16);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return retString;
    }


    /**
     * sha-1 hash算法
     *
     * @param data
     * @return
     */
    public static byte[] sha1(byte[] data) {
        return md("SHA-1", data);
    }

    private static byte[] md(String alg, byte[] codes) {

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance(alg);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(alg + " is not supported", e);
        }
        byte[] rtv = null;
        synchronized (md) {
            md.reset();
            md.update(codes);
            rtv = md.digest();
        }
        return rtv;
    }

    /**
     * 防止非法实例化
     */
    private HashUtil() {
    }

    /**
     * sha-1 hash算法
     * @param msg
     * @return - sha-1串(base64编码)
     */
    public static String sha1Base64(String msg) {

        byte[] r = null;
        try {
            r = sha1(msg.getBytes("UTF-8"));
        } catch ( Exception ex) { }
        return CodecUtil.encodeBase64(r);
    }
}
