package com.guochen.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guochen.dao.TRoleMapper;
import com.guochen.model.TRole;
import com.guochen.page.Page;
import com.guochen.page.PageUtils;
import com.guochen.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	@Resource
	private TRoleMapper rolemapper;

	@Override
	public TRole selectByPrimeryKey(int id) {
		return rolemapper.selectByPrimaryKey(id);
	}

	@Override
	public int save(TRole role) {
		return rolemapper.insert(role);
	}

	@Override
	public int updateByPrimaryKey(TRole role) {
		return rolemapper.updateByPrimaryKey(role);
	}

	@Override
	public List<TRole> selectPageList(Page page, TRole role) {
		page.setTotalCount(rolemapper.selectPageCount(role));
		return rolemapper.selectPageList(PageUtils.createRowBounds(page), role);
	}

	@Override
	public List<TRole> selectList() {
		return rolemapper.selectAll();
	}
	
	public int deleteByPrimaryKey(Integer id){
		return rolemapper.deleteByPrimaryKey(id);
	}
}
