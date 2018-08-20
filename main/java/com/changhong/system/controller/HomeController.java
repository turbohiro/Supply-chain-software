package com.changhong.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 主页面
 *
 * @author sw.j
 * @date 2018-5-2 下午2:03:20
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        // 转到登录页面
        return "login";
    	//测试extjs6
        //return "index";
    }

    /**
     * 主页面
     */
    @RequestMapping("/main")
    public String main() {
        return "main";
    }

    /**
     * 头部
     */
    @RequestMapping("/header")
    public String header() {
        return "header";
    }

    /**
     * 欢迎界面
     */
    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    /**
     * 打开本地exe1文件
     */
    @RequestMapping("/openExe1")
    public String openExe1() {
    	Runtime rt = Runtime.getRuntime();
		Process p = null;
		try{
		    //执行的文件的位置
			p =rt.exec(new String[]{"E:\\Green_SupplyChain\\绿色生产\\MS-MES-1.exe"});
			System.out.println("成功打开软件和文件！");
 
		}catch (Exception e) {
			System.out.println("打开软件失败");
			e.printStackTrace();			
		}
		return "main";
	}
    
    /**
     * 打开本地exe2文件
     */
    @RequestMapping("/openExe2")
    public String openExe2() {
    	Runtime rt = Runtime.getRuntime();
		Process p = null;
		try{
		    //执行的文件的位置
			p =rt.exec(new String[]{"E:\\Green_SupplyChain\\绿色设计\\程序新_20170620\\WinGUI_XJ.exe"});
			System.out.println("成功打开软件和文件！");
 
		}catch (Exception e) {
			System.out.println("打开软件失败");
			e.printStackTrace();			
		}
		return "main";
	}
    
}
