package com.changhong.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.changhong.system.service.IParameterService;

/**
 * 
 * 功能描述： 初始化后执行
 * @author sw.j
 * @date 2018年5月23日 上午10:48:14
 * @version 1.0
 */
@Component
@Order(value = 1)
public class SystemInit implements CommandLineRunner{
	private static final Logger logger = LoggerFactory.getLogger(SystemInit.class);
	@Autowired
	private IParameterService parameterService;
	@Override
	public void run(String... arg0) throws Exception {
		logger.info(">>>>>>>>>>>>>>>runner服务启动执行，执行加载数据操作<<<<<<<<<<<<<");
        logger.info("{}", parameterService.selectAllPar());
	}

}
