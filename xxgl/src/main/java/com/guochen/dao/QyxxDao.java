package com.guochen.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.guochen.model.Qyxx;

public interface QyxxDao {

    List<Qyxx> selectByQyxx(Qyxx record);
    
    int insertSelective(Qyxx record);
    
    int deleteByPrimaryKey(Integer id);
    
    int updateByPrimaryKeySelective(Qyxx record);
    
    public List<Qyxx> selectPageList(RowBounds rowBounds, Qyxx record);
    
    public Long selectPageCount(Qyxx record);

	List<Qyxx> selectByIds(String[] ids);
    
    
}