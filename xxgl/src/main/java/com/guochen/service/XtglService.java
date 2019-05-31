package com.guochen.service;

import java.util.List;

import com.guochen.model.Xtgl;
import com.guochen.page.Page;

public interface XtglService {

    List<Xtgl> selectByXtgl(Xtgl record);

    int insertSelective(Xtgl record);
    
    int deleteByPrimaryKey(Integer id);
    
    int updateByPrimaryKeySelective(Xtgl record);
    
    public List<Xtgl> selectPageList(Page page, Xtgl record);

    /**
     * 获取所有的季度信息
     * @return
     */
	List<Xtgl> getAllQuarters();

	/**
	 * 根据条件查询季度信息
	 * @param codeValue
	 * @return
	 */
	List<Xtgl> getQuarterByName(String codeValue);
}