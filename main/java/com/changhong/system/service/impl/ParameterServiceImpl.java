package com.changhong.system.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.changhong.system.mapper.ParameterMapper;
import com.changhong.system.model.Parameter;
import com.changhong.system.service.IParameterService;
import com.google.common.collect.Maps;

@Service("parameterService")
public class ParameterServiceImpl implements IParameterService{

	@Autowired
	private ParameterMapper parameterMapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		return parameterMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Parameter record) {
		return parameterMapper.insert(record);
	}

	@Override
	public int insertSelective(Parameter record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Parameter selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Parameter record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Parameter record) {
		return parameterMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Parameter> queryAll(String searchText) {
		List<Parameter> list = parameterMapper.queryAll(searchText);
		return list;
	}

	/**
	 * 初始化时读取字典数据封装MAP
	 * @return
	 * @author sw.j
	 */
	public HashMap<String, LinkedHashMap<String, String>> selectAllAsMap() {
        List<Parameter> list = parameterMapper.findAll();

        HashMap<String, LinkedHashMap<String, String>> all = Maps.newHashMap();
        LinkedHashMap<String, String> jsonMap;
        for (Parameter dictionary : list) {
            String key = dictionary.getViewtype();
            if (all.containsKey(key)) {
                // 如果包含这个field，就加入它的值
                jsonMap = all.get(key);
                jsonMap.put(dictionary.getParametervalue(), dictionary.getValuedesc());
            } else {
                jsonMap = Maps.newLinkedHashMap();
                jsonMap.put(dictionary.getParametervalue(), dictionary.getValuedesc());
                // 没有这个fiel，则新加入这个filed
                all.put(key, jsonMap);
            }
        }
        return all;
    }
	
	@Override
	public Map<String, String> selectAllPar() {
        HashMap<String, LinkedHashMap<String, String>> all = selectAllAsMap();
        LinkedHashMap<String, String> jsonMap = Maps.newLinkedHashMap();

        for (Entry<String, LinkedHashMap<String, String>> entry : all.entrySet()) {
            String key = entry.getKey();
            HashMap<String, String> value = entry.getValue();
            // 为了eval('(${applicationScope.parameters.sex})')这个单引号使用,替换所有的'，为\'
            String val = StringEscapeUtils.escapeEcmaScript(JSON.toJSONString(value));
            jsonMap.put(key, val);
        }
        return jsonMap;
    } 
	
}
