package com.guochen.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guochen.dao.TConappMapper;
import com.guochen.model.TConapp;
import com.guochen.model.TProject;
import com.guochen.page.Page;
import com.guochen.page.PageUtils;
import com.guochen.service.ConAppService;

@Service
@Transactional
public class ConAppServiceImpl implements ConAppService {
	@Resource
	private TConappMapper tconappmapper;

	@Override
	public TConapp selectByPrimeryKey(int id) {
		return tconappmapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByContractId(int contractId) {
		return tconappmapper.deleteByContractId(contractId);
	}
	
	@Override
	public int save(TConapp con) {
		return tconappmapper.insert(con);
	}

	@Override
	public List<TConapp> listAll(int contractId) {
		return tconappmapper.selectAll(contractId);
	}

	@Override
	public int update(TConapp con) {
		return tconappmapper.updateByPrimaryKey(con);
	}

	@Override
	public TConapp selectCurrentByContractId(int contractId) {
		return tconappmapper.selectCurrentByContractId(contractId);
	}

	@Override
	public List<TConapp> mselectVerifyPageList(Page page, int userId) {
		page.setTotalCount(tconappmapper.mselectVerifyPageListCount(userId));
		return tconappmapper.mselectVerifyPageList(PageUtils.createRowBounds(page), userId);
	}

	@Override
	public List<TConapp> selectVerifyPageList(Page page, TProject project) {
		page.setTotalCount(tconappmapper.selectVerifyPageListCount(project));
		return tconappmapper.selectVerifyPageList(PageUtils.createRowBounds(page), project);
	}

	
}
