package com.changhong.system.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.changhong.common.ExceptionReturn;
import com.changhong.common.ExtGridReturn;
import com.changhong.common.ExtPager;
import com.changhong.common.ExtReturn;
import com.changhong.common.Tree;
import com.changhong.system.model.Module;
import com.changhong.system.service.IModuleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;

@Controller  
@RequestMapping("/module")
public class ModuleController {
	
	@Autowired
	private IModuleService moduleService;
	
    /**
     * index
     */
    @RequestMapping(method = RequestMethod.GET)
	public String module(Model model){
    	Map<String, Object> map = moduleService.queryAllModelFather();
    	map.put("C0C50F39-DAF8-4696-B611-3084FAE1EFEC", "主菜单");
    	model.addAttribute("moduleMap", JSON.toJSONString(map));
		return "system/module";
	}
    
    /**
     * 查找系统的所有菜单
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST)
    public void all(HttpServletResponse response) throws IOException {
        Tree tree = moduleService.queryModules();
        String json = JSON.toJSONString(tree.getChildren());
        // 加入check
        response.setHeader("Content-Type", "application/octet-stream; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(json.replaceAll("\"leaf", "\"checked\":false,\"leaf"));
        response.getWriter().flush();
        response.getWriter().close();
    }
    
    /**
     * 查询所有
     */
    @RequestMapping("/all")
    @ResponseBody
    public Object all(ExtPager pager, @RequestParam(required = false) String moduleName) {
        try {
        	PageHelper.startPage(pager.getStart(), pager.getLimit());
            List<Module> list = moduleService.queryAll(moduleName==null?"":moduleName);
            PageInfo<Module> pageInfo = new PageInfo<>(list);
            return new ExtGridReturn(pageInfo.getTotal(), pageInfo.getList());
        } catch (Exception e) {
            return new ExceptionReturn(e);
        }
    }
    
    /**
     * 加载此角色的所有资源
     */
    @RequestMapping("/{roleId}")
    @ResponseBody
    public Object selectModulesByRoleId(@PathVariable String roleId) {
        try {
            if (null == roleId) {
                return new ExtReturn(false, "角色ID不能为空！");
            }
            return moduleService.selectRoleModuleByRoleId(roleId);
        } catch (Exception e) {
            return new ExceptionReturn(e);
        }
    }

    /**
     * 保存角色的系统菜单信息
     */
    @RequestMapping("/saverole")
    @ResponseBody
    public Object save(@RequestParam String roleId, @RequestParam String moduleIds) {
        try {
            ArrayList<String> modulesIdList = Lists.newArrayList();
            if (null == roleId) {
                return new ExtReturn(false, "角色不能为空！");
            }
            if (StringUtils.isBlank(moduleIds)) {
                return new ExtReturn(false, "选择的资源不能为空！");
            } else {
                String[] modules = StringUtils.split(moduleIds, ",");
                if (null == modules || modules.length == 0) {
                    return new ExtReturn(false, "选择的资源不能为空！");
                }
                for (int i = 0; i < modules.length; i++) {
                    modulesIdList.add(new String(modules[i]));
                }
            }
            String result = moduleService.saveModel(roleId, modulesIdList);
            if ("01".equals(result)) {
                return new ExtReturn(true, "保存成功！");
            } else if ("00".equals(result)) {
                return new ExtReturn(false, "保存失败！");
            } else {
                return new ExtReturn(false, result);
            }
        } catch (Exception e) {
            return new ExceptionReturn(e);
        }
    }

    /**
     * 保存系统菜单信息
     */
    @RequestMapping("/save")
    @ResponseBody
    public Object save(Module modules) {
        try {
            if (modules == null) {
                return new ExtReturn(false, "模块不能为空！");
            }
            if (StringUtils.isBlank(modules.getModelname())) {
                return new ExtReturn(false, "模块名称不能为空！");
            }
            
            modules.setGrade(1);
            modules.setModeltype("0");
            modules.setCompanycode("1");
            
            if (StringUtils.isBlank(modules.getId())) {
            	 String id =  java.util.UUID.randomUUID().toString();
            	 modules.setId(id);
            	moduleService.insert(modules);
            }else{
            	moduleService.updateByPrimaryKey(modules);
            }
            return new ExtReturn(true, "保存成功！");
        } catch (Exception e) {
            return new ExceptionReturn(e);
        }
    }

    /**
     * 删除该模块
     */
    @RequestMapping("/del/{moduleId}")
    @ResponseBody
    public Object delete(@PathVariable String moduleId) {
        try {
            if (null == moduleId) {
                return new ExtReturn(false, "模块主键不能为空！");
            }
            moduleService.delete(moduleId);
            return new ExtReturn(true, "删除成功！");
        } catch (Exception e) {
            return new ExceptionReturn(e);
        }
    }
}
