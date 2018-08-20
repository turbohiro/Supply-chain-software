package com.changhong.util;

/**
 * web中使用的常量
 *
 * @author sw.j
 * @date 2018-5-3 下午01:55:19
 */
public interface WebConstants {
    /**
     * 超时提醒
     */
    static final String TIME_OUT = "{\"error\":true,\"msg\":\"登录超时,请重新登录！\"}";
    /**
     * 保存session中的用户key
     */
    static final String CURRENT_USER = "CURRENT_USER";
    /**
     * exception
     */
    static final String JSON_MAPPING_EXCEPTION = "JsonMappingException: ";
    /**
     * exception
     */
    static final String IO_EXCEPTION = "IOException: ";
    /**
     * exception
     */
    static final String JSON_GENERATION_EXCEPTION = "JsonGenerationException: ";
    /**
     * exception
     */
    static final String EXCEPTION = "Exception: ";
    
    String IPLIST = "0.0.0.0|0.0.0.0";
}
