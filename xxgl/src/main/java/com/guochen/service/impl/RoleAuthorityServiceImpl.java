package com.guochen.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.guochen.dao.TAuthorityMapper;
import com.guochen.dao.TRoleAuthorityMapper;
import com.guochen.dao.TRoleMapper;
import com.guochen.model.TAuthority;
import com.guochen.model.TRole;
import com.guochen.model.TRoleAuthority;
import com.guochen.service.RoleAuthorityService;

@Service
@Transactional
public class RoleAuthorityServiceImpl implements RoleAuthorityService {
	@Resource
	private TRoleAuthorityMapper roleauthmapper;
	@Resource
	private TAuthorityMapper authmapper;
	@Resource
	private TRoleMapper rolemapper;

	@Override
	public TRoleAuthority selectByPrimeryKey(int id) {
		return roleauthmapper.selectByPrimaryKey(id);
	}

	@Override
	public int save(TRoleAuthority roleAuth) {
		return roleauthmapper.insert(roleAuth);
	}

	@Override
	public int updateByPrimaryKey(TRoleAuthority roleAuth) {
		return roleauthmapper.updateByPrimaryKey(roleAuth);
	}

	@Override
	public TRoleAuthority selectByRoleAndAuthorityId(TRoleAuthority roleAuth) {
		return roleauthmapper.selectByRoleAndAuthorityId(roleAuth);
	}

	@Override
	public int deleteByPrimaryKey(int id) {
		return roleauthmapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByRoleId(int roleId) {
		return roleauthmapper.deleteByRoleId(roleId);
	}


}
