package com.changhong.appraise.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.changhong.appraise.model.AppraiseScheme;
import com.changhong.appraise.mapper.AppraiseSchemeMapper;
import com.changhong.appraise.service.AppraiseSchemeService;
import com.changhong.common.Tree;

@Service("appraiseschemeService")
public class AppraiseSchemeServiceImpl implements AppraiseSchemeService{
	
	@Autowired
	AppraiseSchemeMapper appraiseschemeMapper;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return appraiseschemeMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public int insert(AppraiseScheme record) {
		return appraiseschemeMapper.insert(record);
	}

	@Override
	public int insertSelective(AppraiseScheme record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AppraiseScheme selectByPrimaryKey(String id) {
		AppraiseScheme model = appraiseschemeMapper.selectByPrimaryKey(id);
		return model;
	}
	
	@Override
	public int updateByPrimaryKey(AppraiseScheme record) {
		return appraiseschemeMapper.updateByPrimaryKey(record);
	}


	@Override
	public List<AppraiseScheme> queryAll(String searchText) {
		List<AppraiseScheme> list = appraiseschemeMapper.queryAll(searchText);
		return list;
	}

	@Override
	public int updateByPrimaryKeySelective(AppraiseScheme record) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public Map<String, Object> queryAllModelFather() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveAppraiseSchemes(AppraiseScheme model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tree queryAppraiseSchemes() {
		// TODO Auto-generated method stub
		return null;
	}



}
