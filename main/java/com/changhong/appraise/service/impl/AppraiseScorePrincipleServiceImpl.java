package com.changhong.appraise.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.changhong.appraise.model.AppraiseScorePrinciple;
import com.changhong.appraise.mapper.AppraiseScorePrincipleMapper;
import com.changhong.appraise.service.AppraiseScorePrincipleService;

@Service("appraiseScorePrincipleService")
public class AppraiseScorePrincipleServiceImpl implements AppraiseScorePrincipleService{
	
	@Autowired
	private AppraiseScorePrincipleMapper appraisescorePrincipleMapper;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return appraisescorePrincipleMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public int insert(AppraiseScorePrinciple record) {
		return appraisescorePrincipleMapper.insert(record);
	}
	
	@Override
	public AppraiseScorePrinciple selectByPrimaryKey(String id) {
		AppraiseScorePrinciple norm = appraisescorePrincipleMapper.selectByPrimaryKey(id);
		return norm;
	}
	
	@Override
	public int updateByPrimaryKey(AppraiseScorePrinciple record) {
		return appraisescorePrincipleMapper.updateByPrimaryKey(record);
	}


	@Override
	public List<AppraiseScorePrinciple> queryAll(String searchText) {
		List<AppraiseScorePrinciple> list = appraisescorePrincipleMapper.queryAll(searchText);
		return list;
	}
	
	@Override
	public AppraiseScorePrinciple queryAllByNormId(String normId) {
		AppraiseScorePrinciple principle = appraisescorePrincipleMapper.selectBynormCode(normId);
		return principle;
	}

	@Override
	public int insertSelective(AppraiseScorePrinciple record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(AppraiseScorePrinciple record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<AppraiseScorePrinciple> selectAppraiseScorePrinciple() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraiseScorePrinciple> selectBynormCode(String normCode) {
		// TODO Auto-generated method stub
		return null;
	}


}
