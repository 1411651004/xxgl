package com.guochen.service;

import java.util.List;

import com.guochen.model.Tkxx;
import com.guochen.page.Page;

public interface TkxxService {

    List<Tkxx> selectByTkxx(Tkxx record);

    int insertSelective(Tkxx record);
    
    int deleteByPrimaryKey(Integer id);
    
    int updateByPrimaryKeySelective(Tkxx record);
    
    public List<Tkxx> selectPageList(Page page, Tkxx record);

	List<Tkxx> selectAll();
}