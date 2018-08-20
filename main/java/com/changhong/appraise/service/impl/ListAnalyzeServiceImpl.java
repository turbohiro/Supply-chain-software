package com.changhong.appraise.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.changhong.appraise.model.AppraiseScore;
import com.changhong.appraise.model.ListAnalyze;
import com.changhong.appraise.service.ListAnalyzeService;
import com.changhong.appraise.mapper.AppraiseScoreMapper;
import com.changhong.appraise.mapper.ListAnalyzeMapper;

@Service("listanalyzeService")
public class ListAnalyzeServiceImpl implements ListAnalyzeService{
	@Autowired
	ListAnalyzeMapper listanalyzeMapper;
	@Autowired
	AppraiseScoreMapper appraiseScoreMapper;
	
	
	@Override
	public int deleteByPrimaryKey(int id) {
		return listanalyzeMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public int insert(ListAnalyze record) {
		return listanalyzeMapper.insert(record);
	}

	@Override
	public int insertSelective(ListAnalyze record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListAnalyze selectByPrimaryKey(int id) {
		ListAnalyze model = listanalyzeMapper.selectByPrimaryKey(id);
		return model;
	}
	
	@Override
	public int updateByPrimaryKey(ListAnalyze record) {
		return listanalyzeMapper.updateByPrimaryKey(record);
	}


	@Override
	public List<ListAnalyze> queryAll(String searchText) {
		List<ListAnalyze> list = listanalyzeMapper.queryAll(searchText);
		for(ListAnalyze a : list){
			AppraiseScore as = appraiseScoreMapper.selectBynormCode(a.getNormcode());
			if(as!=null){
				a.setScore(as.getScore()==null? "0":as.getScore());
			}
		}
		return list;
	}

	@Override
	public int updateByPrimaryKeySelective(ListAnalyze record) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
