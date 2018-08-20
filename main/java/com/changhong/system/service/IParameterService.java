package com.changhong.system.service;

import java.util.List;
import java.util.Map;

import com.changhong.system.model.Parameter;

public interface IParameterService {
	int deleteByPrimaryKey(String id);

    int insert(Parameter record);

    int insertSelective(Parameter record);

    Parameter selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Parameter record);

    int updateByPrimaryKey(Parameter record);
    
    List<Parameter> queryAll(String searchText);
    
    /**
	 * 功能描述： 初始化时执行
	 * @return
	 * @author sw.j
	 */
    Map<String, String> selectAllPar();
}
