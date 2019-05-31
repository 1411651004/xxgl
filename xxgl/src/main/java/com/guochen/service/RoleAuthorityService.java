package com.guochen.service;

import java.util.List;

import com.guochen.model.TRoleAuthority;

public interface RoleAuthorityService {
	
	public TRoleAuthority selectByPrimeryKey(int id);
	public TRoleAuthority selectByRoleAndAuthorityId(TRoleAuthority roleAuth);
	public int save(TRoleAuthority roleAuth);
	public int updateByPrimaryKey(TRoleAuthority roleAuth);
	public int deleteByPrimaryKey(int id);
	public int deleteByRoleId(int roleId);
}
