package com.guochen.service;

import java.util.List;

import com.guochen.model.TBuScope;


public interface ScopeService {
	
	public TBuScope selectByPrimeryKey(int id);
	public int save(TBuScope authority);
	public int updateByPrimaryKey(TBuScope scope);
	public List<TBuScope> getTree();
	public List<TBuScope> selectList(TBuScope scope);
}
