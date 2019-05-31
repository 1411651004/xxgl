package com.guochen.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.guochen.model.Tkxx;

public interface TkxxDao {

    List<Tkxx> selectByTkxx(Tkxx record);

    int insertSelective(Tkxx record);
    
    int deleteByPrimaryKey(Integer id);
    
    int updateByPrimaryKeySelective(Tkxx record);
    
    public List<Tkxx> selectPageList(RowBounds rowBounds, Tkxx record);
    
    public Long selectPageCount(Tkxx record);

	List<Tkxx> selectAll();
}