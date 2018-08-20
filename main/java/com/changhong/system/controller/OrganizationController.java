package com.changhong.system.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import com.changhong.system.model.Organization;
import com.changhong.system.service.IOrganizationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller  
@RequestMapping("/organization")
public class OrganizationController {

	@Autowired
	private IOrganizationService organizationService;
	/**
     * index
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "system/organization";
    }

    /**
     * 查找树形组织机构
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST)
    public void all(HttpServletResponse response) throws IOException {
        Tree tree = organizationService.queryOrgs();
        String json = JSON.toJSONString(tree.getChildren());
        // 加入check
        response.setHeader("Content-Type", "application/octet-stream; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(json);
        response.getWriter().flush();
        response.getWriter().close();
    }
    
    /**
     * 查找所有的机构
     */
    @RequestMapping("/all")
    @ResponseBody
    public Object all(ExtPager pager, @RequestParam(required = false) String roleName, @RequestParam(required = false) String orgId) {
    	if(StringUtils.isBlank(orgId)){
    		PageHelper.startPage(pager.getStart(), pager.getLimit()); 
    		List<Organization> list = organizationService.queryAll(roleName);
    		PageInfo<Organization> pageInfo = new PageInfo<>(list);
    		return new ExtGridReturn(pageInfo.getTotal(), pageInfo.getList());
    	}else{
    		PageHelper.startPage(pager.getStart(), pager.getLimit()); 
    		List<Organization> list = organizationService.queryAllByOrgId(orgId);
    		PageInfo<Organization> pageInfo = new PageInfo<>(list);
    		return new ExtGridReturn(pageInfo.getTotal(), pageInfo.getList());
    	}
    }
    
    /**
     * 保存
     * hiddenValue 取comboxtree的隱藏值
     */
    @RequestMapping("/save")
    @ResponseBody
    public Object save(Organization org,@RequestParam(required = false) String hiddenValue) {
        try {
        	org.setPath("/org");
            org.setCompanycode("1");
            
            if(hiddenValue.contains("_chmesorg_")){
            	//去除前缀
            	org.setFather(hiddenValue.replace("_chmesorg_", ""));
            }
            String result = organizationService.saveOrg(org);
            if(result.equals("01")){
            	return new ExtReturn(true, "保存成功！");
            }else{
            	return new ExtReturn(false, "保存失败！");
            }
        } catch (Exception e) {
            return new ExceptionReturn(e);
        }
    }
    
    /**
     * 删除
     */
    @RequestMapping("/del/{orgId}")
    @ResponseBody
    public Object delete(@PathVariable String orgId) {
        try {
            if (null == orgId) {
                return new ExtReturn(false, "主键不能为空！");
            }
            organizationService.deleteByPrimaryKey(orgId);
            return new ExtReturn(true, "删除成功！");
        } catch (Exception e) {
            return new ExceptionReturn(e);
        }
    }
    
}
