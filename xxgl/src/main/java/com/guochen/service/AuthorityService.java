package com.guochen.service;

import java.util.List;

import com.guochen.model.TAuthority;

public interface AuthorityService {
	
	public TAuthority selectByPrimeryKey(int id);
	public int save(TAuthority authority);
	public int updateByPrimaryKey(TAuthority authority);
	public List<TAuthority> getTree();
	public List<TAuthority> selectList(TAuthority authority);
	public List<TAuthority> selectAuthorityByRoleId(int roleId);
}
