package com.changhong.util;

import com.google.common.base.Charsets;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

/**
 * 功能描述： 对密码进行加密
 * @author sw.j
 * @date 2018年5月15日 下午2:19:04
 * @version 1.0
 */
public class EncryptUtil {
	private static final HashFunction hashing = Hashing.md5();
    public static String encrypt(String rawPassword) {
       return  hashing.hashString(rawPassword, Charsets.UTF_8).toString();
    }
    public static boolean match(String rawPassword, String encodedPassword) {
        return rawPassword.equals(encodedPassword);
    }
}
