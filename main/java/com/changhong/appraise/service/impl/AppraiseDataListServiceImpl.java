package com.changhong.appraise.service.impl;

import com.changhong.appraise.model.AppraiseDataList;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.changhong.appraise.mapper.AppraiseDataListMapper;
import com.changhong.appraise.service.AppraiseDataListService;

@Service("appraisedataListService")
public class AppraiseDataListServiceImpl implements AppraiseDataListService{
	
	@Autowired
	private AppraiseDataListMapper appraisedataListMapper;
	List<AppraiseDataList> datalist;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return appraisedataListMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public int insert(AppraiseDataList record) {
		return appraisedataListMapper.insert(record);
	}
	
	@Override
	public AppraiseDataList selectByPrimaryKey(String id) {
		AppraiseDataList norm = appraisedataListMapper.selectByPrimaryKey(id);
		return norm;
	}
	
	@Override
	public int updateByPrimaryKey(AppraiseDataList record) {
		return appraisedataListMapper.updateByPrimaryKey(record);
	}


	@Override
	public List<AppraiseDataList> queryAll(String searchText) {
		List<AppraiseDataList> list = appraisedataListMapper.queryAll(searchText);
		return list;
	}
	
	@Override
	public List<AppraiseDataList> queryAllByNormId(String normId) {
		 datalist = new ArrayList<AppraiseDataList>();
		 datalist = appraisedataListMapper.selectBynormCode(normId);
		//filelist.add(norm);
		return datalist;
	}
	
	@Override
	public int insertSelective(AppraiseDataList record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(AppraiseDataList record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
