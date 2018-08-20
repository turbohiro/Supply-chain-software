package com.changhong;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 
 * 功能描述：使用tomcat启动类
 * @author sw.j
 * @date 2018年5月22日 下午5:16:27
 * @version 1.0
 */
public class ServletInitializer extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(BasalApplication.class);
	}
}
