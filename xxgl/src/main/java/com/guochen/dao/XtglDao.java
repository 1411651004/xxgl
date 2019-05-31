package com.guochen.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.guochen.model.Xtgl;

public interface XtglDao {

    List<Xtgl> selectByXtgl(Xtgl record);

    int insertSelective(Xtgl record);
    
    int deleteByPrimaryKey(Integer id);
    
    int updateByPrimaryKeySelective(Xtgl record);
    
    public List<Xtgl> selectPageList(RowBounds rowBounds, Xtgl record);
    
    public Long selectPageCount(Xtgl record);

	List<Xtgl> getAllQuarters();

	List<Xtgl> getQuarterByName(String codeValue);
}