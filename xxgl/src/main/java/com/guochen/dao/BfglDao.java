package com.guochen.dao;

import java.util.List;

import com.guochen.model.Bfgl;

public interface BfglDao {
	List<Bfgl> selectByBfgl(Bfgl bfgl);

	int updateByPrimaryKey(Bfgl bfgl);
	
	int insertSelective(Bfgl bfgl);
}
