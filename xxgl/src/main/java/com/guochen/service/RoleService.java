package com.guochen.service;

import java.util.List;

import com.guochen.model.TRole;
import com.guochen.page.Page;

public interface RoleService {
	
	public TRole selectByPrimeryKey(int id);
	public int save(TRole role);
	public int updateByPrimaryKey(TRole role);
	public List<TRole> selectPageList(Page page, TRole role);
	public List<TRole> selectList();
	public int deleteByPrimaryKey(Integer id);
}
