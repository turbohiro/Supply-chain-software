package com.changhong.appraise.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.changhong.appraise.mapper.AppraiseNormMapper;
import com.changhong.appraise.model.AppraiseNorm;
import com.changhong.appraise.service.AppraiseNormService;
import com.changhong.common.Tree;
import com.changhong.common.TreemenuNorm;


@Service("appraisenormService")
public class AppraiseNormServiceImpl implements AppraiseNormService{
	
	@Autowired
	AppraiseNormMapper appraisenormMapper;
	List<AppraiseNorm> Normlist;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return appraisenormMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public int insert(AppraiseNorm record) {
		return appraisenormMapper.insert(record);
	}
	
	@Override
	public AppraiseNorm selectByPrimaryKey(String id) {
		AppraiseNorm norm = appraisenormMapper.selectByPrimaryKey(id);
		return norm;
	}
	
	@Override
	public int updateByPrimaryKey(AppraiseNorm record) {
		return appraisenormMapper.updateByPrimaryKey(record);
	}


	@Override
	public List<AppraiseNorm> queryAll(String searchText) {
		List<AppraiseNorm> list = appraisenormMapper.queryAll(searchText);
		return list;
	}
	
	@Override
	public Tree queryNorms() {
		List<AppraiseNorm> list = appraisenormMapper.selectNorms();
		TreemenuNorm menu = new TreemenuNorm(list);
		return menu.getTreeJson();
	}
	
	@Override
	public String saveNorm(AppraiseNorm record) {
		if(StringUtils.isBlank(record.getId())){
			String id = appraisenormMapper.selectMaxId();
			if(StringUtils.isBlank(id)){
				record.setId(String.valueOf("1000"));
			}else{
				int newId = Integer.parseInt(id)+1;
				record.setId(String.valueOf(newId));
			}
			appraisenormMapper.insert(record);
		}else{
			appraisenormMapper.updateByPrimaryKey(record);
		}
		return "01";
	}
	
	@Override
	public List<AppraiseNorm> queryAllByNormId(String orgId) {
		Normlist = new ArrayList<AppraiseNorm>();
		AppraiseNorm org = appraisenormMapper.selectBynormCode(orgId);
		Normlist.add(org);//添加自身
		orgChild(orgId);
		return Normlist;
	}

	/**
	 * 功能描述： 递归查询所有子机构
	 * @param orgId
	 * @author sw.j
	 */
	public void orgChild(String normId){
		List<AppraiseNorm> ls = appraisenormMapper.queryAllByNormId(normId);
		if(ls.size()>0){
			Normlist.addAll(ls);
			for(AppraiseNorm org:ls){
				orgChild(org.getNormcode());
			}
		}
	}
	@Override
	public int insertSelective(AppraiseNorm record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(AppraiseNorm record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Tree queryAppraiseNorms() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> queryAllModelFather() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
