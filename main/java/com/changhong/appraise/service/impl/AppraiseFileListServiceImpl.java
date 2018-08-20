package com.changhong.appraise.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.changhong.appraise.mapper.AppraiseFileListMapper;
import com.changhong.appraise.model.AppraiseFileList;
import com.changhong.appraise.service.AppraiseFileListService;

@Service("appraisefileListService")
public class AppraiseFileListServiceImpl implements AppraiseFileListService{
	
	@Autowired
	private AppraiseFileListMapper appraisefileListMapper;
	List<AppraiseFileList> filelist;
	@Override
	public int deleteByPrimaryKey(String id) {
		return appraisefileListMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public int insert(AppraiseFileList record) {
		return appraisefileListMapper.insert(record);
	}
	
	@Override
	public AppraiseFileList selectByPrimaryKey(String id) {
		AppraiseFileList norm = appraisefileListMapper.selectByPrimaryKey(id);
		return norm;
	}
	
	@Override
	public int updateByPrimaryKey(AppraiseFileList record) {
		return appraisefileListMapper.updateByPrimaryKey(record);
	}


	@Override
	public List<AppraiseFileList> queryAll(String searchText) {
		List<AppraiseFileList> list = appraisefileListMapper.queryAll(searchText);
		return list;
	}
	
	@Override
	public List<AppraiseFileList> queryAllByNormId(String normId) {
		 filelist = new ArrayList<AppraiseFileList>();
		filelist = appraisefileListMapper.selectBynormCode(normId);
		//filelist.add(norm);
		return filelist;
	}



	@Override
	public int insertSelective(AppraiseFileList record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(AppraiseFileList record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
