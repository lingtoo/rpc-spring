package com.ethome.iws.utils;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import sun.misc.BASE64Decoder;

public class StringUtils {
    /**
     * 一个方法 判断所传的参数是否是空值 全为空才返回true
     * @param test
     * @return
     */
    public static boolean isEmpty(String... test) {
        boolean result = true;
        for (String t : test) {
            result = (t == null ? true : t.equals(""));
            if (!result) return false;
        }
        return result;
    }

    /**
     * 判断所有参数是否都不是空的 全不为空才返回true
     * @param test
     * @return
     */
    public static boolean isNotEmpty(String... test) {
        boolean result = true;
        for (String t : test) {
            result = !(t == null ? true : t.equals(""));
            if (!result) return false;
        }
        return result;
    }

    /**
     * 判断两个字符串的前3个字节是否相等，如果相等返回true
     * @return
     */
    public static boolean equalsFor3(String str1, String str2) {
        boolean result = false;

        if (str1.substring(0,3).equals(str2.substring(0,3))){
            result = true;
        }

        return result;
    }

    /* base64解码 */
    public static String getFromBase64(String s) {
        byte[] b = null;
        String result = null;
        if (s != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(s);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    
	/**
	 * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * 
	 * @param params
	 *            需要排序并参与字符拼接的参数组
	 * @return 拼接后字符串
	 */
	public static String buildParams(Map<String, String> params) {
		return buildParams(params, "=", "&");
	}
	
	/**
	 * 把数组所有元素排序，并按照“参数keyValueSymbol参数值”的模式用“paramSymbol”字符拼接成字符串
	 * 
	 * @param params
	 *            需要排序并参与字符拼接的参数组
	 * @return 拼接后字符串
	 */
	public static String buildParams(Map<String, String> params, String keyValueSymbol, String paramSymbol) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		String prestr = "";
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);

			if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
				prestr = prestr + key + keyValueSymbol + value;
			} else {
				prestr = prestr + key + keyValueSymbol + value + paramSymbol;
			}
		}
		return prestr;
	}
	
	public static void main(String[] args){
		Map<String, String> params = new LinkedHashMap <String, String>();
		params.put("p1", "v1");
		params.put("p3", "v3");
		params.put("p2", "v2");
		System.out.println(StringUtils.buildParams(params));
	}
}
