package com.changhong.appraise.service;

//import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import org.springframework.web.bind.annotation.RequestMethod;

import com.changhong.common.Tree;
import com.changhong.appraise.model.AppraiseScheme;

public interface AppraiseSchemeService {
	int deleteByPrimaryKey(String id);

    int insert(AppraiseScheme record);                  //对应controller的“/save"

    int insertSelective(AppraiseScheme record);

    AppraiseScheme selectByPrimaryKey(String id);       
    
    List<AppraiseScheme> queryAll(String searchText);   //对应controller的"/all"

    int updateByPrimaryKeySelective(AppraiseScheme record);  

    int updateByPrimaryKey(AppraiseScheme record);     //对应controller的“/save"
    
    Tree queryAppraiseSchemes();                 
    
    Map<String, Object> queryAllModelFather();
    
    
    String saveAppraiseSchemes(AppraiseScheme model);
    
    String delete(String id);                  //对应controller层的"/delete"
    
    /**
	 * 功能描述： 初始化时执行
	 * @return
	 * @author sw.j
	 */
    //Map<String, String> selectAllPar();     //对应controller层的"/synchro"
    

}
