package com.changhong.appraise.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.changhong.common.ExceptionReturn;
import com.changhong.common.ExtGridReturn;
import com.changhong.common.ExtPager;
import com.changhong.common.ExtReturn;
import com.changhong.util.ExportExcel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.changhong.appraise.model.ListAnalyze;
import com.changhong.appraise.service.ListAnalyzeService;
@Controller  
@RequestMapping("/listAnalyze")
public class listAnalyzeController {
	
	@Autowired
	private ListAnalyzeService  listanalyzeService;
	/**
     * index
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "appraise/listAnalyze";
    }
    
    /**
     * 保存系统菜单信息
     */
    @RequestMapping("/save")
    @ResponseBody
    public Object save(ListAnalyze norms) {
        try {
            if (norms == null) {
                return new ExtReturn(false, "模块不能为空！");
            }
            if (StringUtils.isBlank(norms.getNormname())) {
                return new ExtReturn(false, "模块名称不能为空！");
            }
            	 listanalyzeService.insert(norms);
            return new ExtReturn(true, "保存成功！");
        } catch (Exception e) {
            return new ExceptionReturn(e);
        }
    }
    
    /**
     * 查询所有
     */
    @RequestMapping("/all")
    @ResponseBody
    public Object all(ExtPager pager, @RequestParam(required = false) String listanalyzeName) {

        	PageHelper.startPage(pager.getStart(), pager.getLimit());
            List<ListAnalyze> list = listanalyzeService.queryAll(listanalyzeName==null?"":listanalyzeName);
            PageInfo<ListAnalyze> pageInfo = new PageInfo<>(list);
            return new ExtGridReturn(pageInfo.getTotal(), pageInfo.getList());
        }

    
    /**
     * 删除该模块
     */
    @RequestMapping("/del/{normId}")
    @ResponseBody
    public Object delete(@PathVariable int normId) {
        try {

            listanalyzeService.deleteByPrimaryKey(normId);
            return new ExtReturn(true, "删除成功！");
        } catch (Exception e) {
            return new ExceptionReturn(e);
        }
    }
 
    /**
     * 导出excel文件
     */
    @RequestMapping("/exportExcel")
    @ResponseBody
    public Object exportExcel(){
    	try {
    		ExportExcel<ListAnalyze> ex = new ExportExcel<ListAnalyze>();
    		String[] headers = { "指标ID", "指标名称", "得分", "编号"};
    		List<ListAnalyze> list = listanalyzeService.queryAll("");
			OutputStream out = new FileOutputStream("E://score.xls");
			ex.exportExcel("综合得分表", headers, list, out);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	
    	return new ExtReturn(true, "已成功导出到E盘根目录中score.xls！");
    	
    }

}
