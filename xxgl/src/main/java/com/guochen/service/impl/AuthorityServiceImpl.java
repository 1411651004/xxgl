package com.guochen.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guochen.dao.TAuthorityMapper;
import com.guochen.model.TAuthority;
import com.guochen.service.AuthorityService;

@Service
@Transactional
public class AuthorityServiceImpl implements AuthorityService {
	@Resource
	private TAuthorityMapper authoritymapper;

	@Override
	public TAuthority selectByPrimeryKey(int id) {
		return authoritymapper.selectByPrimaryKey(id);
	}

	@Override
	public int save(TAuthority authority) {
		return authoritymapper.insert(authority);
	}

	@Override
	public int updateByPrimaryKey(TAuthority authority) {
		return authoritymapper.updateByPrimaryKey(authority);
	}

	@Override
	public List<TAuthority> selectList(TAuthority authority) {
		return authoritymapper.selectAll(authority);
	}

	@Override
	public List<TAuthority> getTree() {
		TAuthority authority = new TAuthority();
		authority.setParentId(0);
		List<TAuthority> parentList = authoritymapper.selectAll(authority);
		List<TAuthority> allList = authoritymapper.selectAll(new TAuthority());
		if(parentList!=null && allList!=null){
			for(TAuthority au:parentList){
				List<TAuthority> childrenList = new ArrayList<TAuthority>();
				for(TAuthority al:allList){
					if(al.getParentId()==au.getId()){
						childrenList.add(al);
					}
				}
				au.setChildrenList(childrenList);
			}
		}
		return parentList;
	}

	@Override
	public List<TAuthority> selectAuthorityByRoleId(int roleId) {
		return authoritymapper.selectAuthorityByRoleId(roleId);
	}

}
