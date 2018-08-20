package com.changhong.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.beans.factory.annotation.Autowired;

import com.changhong.system.service.IParameterService;
import com.changhong.util.SpringContextHelper;

/**
 * 功能描述： 系统初始化监听
 * @author sw.j
 * @date 2018年5月23日 上午10:46:37
 * @version 1.0
 */
@WebListener
public class SystemInitListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
	@Autowired
	private SpringContextHelper springContextHelper;
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext servletContext = sce.getServletContext();
		IParameterService parameterService = SpringContextHelper.getBean("parameterService");
        servletContext.setAttribute("parameters", parameterService.selectAllPar());
	}

}
