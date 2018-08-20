package com.changhong.appraise.service.impl; 
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.changhong.appraise.model.AppraiseScore;
import com.changhong.appraise.mapper.AppraiseScoreMapper;
import com.changhong.appraise.service.AppraiseScoreService;

@Service("appraisescoreService")    //与后面定义的AppraiseScoreService名字对应
public class AppraiseScoreServiceImpl implements AppraiseScoreService{
	
	@Autowired
	private AppraiseScoreMapper appraisescoreMapper;
	//AppraiseScore score;

	@Override
	public int deleteByPrimaryKey(String id) {
		return appraisescoreMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public int insert(AppraiseScore record) {
		return appraisescoreMapper.insert(record);
	}
	
	@Override
	public AppraiseScore selectByPrimaryKey(String id) {
		AppraiseScore score = appraisescoreMapper.selectByPrimaryKey(id);
		return score;
	}
	
	@Override
	public int updateByPrimaryKey(AppraiseScore record) {
		return appraisescoreMapper.updateByPrimaryKey(record);
	}


	@Override
	public List<AppraiseScore> queryAll(String searchText) {
		List<AppraiseScore> list = appraisescoreMapper.queryAll(searchText);
		return list;
	}
	
	@Override
	public AppraiseScore queryAllByNormId(String normId) {
		AppraiseScore score = appraisescoreMapper.selectBynormCode(normId);
		return score;
	}

	@Override
	public int insertSelective(AppraiseScore record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(AppraiseScore record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
