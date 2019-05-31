package com.guochen.service;

import java.util.List;

import com.guochen.model.Bfgl;
import com.guochen.model.Qyxx;
import com.guochen.page.Page;

public interface QyxxService {

    List<Qyxx> selectByQyxx(Qyxx record);

    int insertSelective(Qyxx record);
    
    int deleteByPrimaryKey(Integer id);
    
    int updateByPrimaryKeySelective(Qyxx record);
    
    public List<Qyxx> selectPageList(Page page, Qyxx record);

	List<Bfgl> selectBfgl(Bfgl bfgl);

	int updateBfglByPrimaryKey(Bfgl bfgl);

	int insertBfgl(Bfgl bfgl);

	List<Qyxx> selectByIds(String[] ids);
}